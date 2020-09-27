package com.papple.blog.controllers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;

import com.papple.blog.models.BlogConfig;
import com.papple.blog.models.Follow;
import com.papple.blog.models.FollowPK;
import com.papple.blog.models.Good;
import com.papple.blog.models.Hashtag;
import com.papple.blog.models.HashtagPK;
import com.papple.blog.models.History;
import com.papple.blog.models.PKSet;
import com.papple.blog.models.Notification;
import com.papple.blog.models.Post;
import com.papple.blog.models.Storage;
import com.papple.blog.models.TagScore;
import com.papple.blog.models.User;
import com.papple.blog.models.unNotification;
import com.papple.blog.payload.response.HashtagList;
import com.papple.blog.payload.response.PopularScore;
import com.papple.blog.payload.response.PostDetail;
import com.papple.blog.payload.response.PostList;
import com.papple.blog.repository.ConfigRepository;
import com.papple.blog.repository.FollowRepository;
import com.papple.blog.repository.GoodRepository;
import com.papple.blog.repository.HistoryRepository;
import com.papple.blog.repository.PostAlgorithmRepository;
import com.papple.blog.repository.PostListRepository;
import com.papple.blog.repository.StorageRepository;
import com.papple.blog.repository.TagScoreRepository;
import com.papple.blog.repository.UserRepository;
import com.papple.blog.repository.unNotificationRepository;
import com.papple.blog.security.services.CommentService;
import com.papple.blog.security.services.FollowService;
import com.papple.blog.security.services.HashtagService;
import com.papple.blog.security.services.NotificationService;
import com.papple.blog.security.services.PostService;

// http://localhost:8081/swagger-ui.html
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	private PostService postService;
	@Autowired
	private HashtagService hashtagService;
	@Autowired
	private HistoryRepository historyRepository;
	@Autowired
	private StorageRepository storageRepository;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private PostListRepository postListRepository;
	@Autowired
	private FollowService followService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private PostAlgorithmRepository algoRepository;
	@Autowired
    private ConfigRepository configRepository;
	@Autowired
	private GoodRepository goodRepository;
	@Autowired
	private TagScoreRepository tagscoreRepository;
	@Autowired
	private unNotificationRepository unnotificationRepository;

	@GetMapping("/all")
	@ApiOperation(value = "모든 포스트 보기")
	public ResponseEntity<List<PostList>> searchAll(String email) throws Exception {
		System.out.println("모든 포스트 출력");
		List<PostList> list = postListRepository.searchAllPost();
		for(PostList post : list) if(goodRepository.isGood(email, post.getId()) > 0) post.setIsgood(true);
		return new ResponseEntity<List<PostList>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/page")
	@ApiOperation(value = "페이징 테스트")
	public ResponseEntity<List<PostList>> Paging(int page)  {
		int pgStart = 10 * (page - 1);
		return new ResponseEntity<List<PostList>>(postListRepository.paging(pgStart), HttpStatus.OK);
	}
	
	
	@GetMapping("writer/{email}")
	@ApiOperation(value = "해당 이메일의 포스트 리스트 보기(나 혹은 다른 사람 블로그에서)")
	public ResponseEntity<List<PostList>> searchByEmail(@PathVariable String email) throws Exception {
		System.out.println("해당 이메일의 포스트 출력");
		List<PostList> list = postListRepository.searchByEmail(email);
		for(int i=0;i<list.size();i++) if(goodRepository.isGood(email, list.get(i).getId()) > 0) list.get(i).setIsgood(true);
		return new ResponseEntity<List<PostList>>(list, HttpStatus.OK);
	}
	 
	@GetMapping("/postDetail")
	@ApiOperation(value = "해당 POST ID의 포스트 보기 (email 값이 없으면 isgood은 false, 조회수 ++, email이 있으면 isgood 활성화, 조회수++, history 추가")
	public ResponseEntity<PostDetail> searchByIdAndEmail(@RequestParam(required = true) Long id, 
			@RequestParam(required = false) String email) throws Exception {
		// detail + hashtag
		PostDetail detail = postListRepository.searchPostDetail(id);
		List<String> tag = postListRepository.searchHashtag(id);
		detail.setTag(tag);
		
		for(int i=0;i<tag.size();i++) {		//각 태그에 태그점수 증가
			tagscoreRepository.plusScore(tag.get(i));
		}
		
		if(email != null && !email.equals("")) {	//email이 있을 때만
			if(goodRepository.isGood(email, id) > 0) detail.setIsgood(true);
			// 조회 테이블에 추가(추천 게시물 관련)
			if(algoRepository.isRead(email, id) < 1) algoRepository.insertRead(email, id);	//이미 조회한 게시물이 아니라면, 조회 게시물에 insert
		}
		
		Post temp = postService.findById(id).get();		//조회수, history
		temp.setViews(temp.getViews()+1);
		postService.save(temp);
		
		// history에 담기
		if(email != null && !email.equals("") && !email.equals(detail.getWriter())) {	//email이 있을 때만 && 자신의 게시물 아닐
			if(historyRepository.isHistory(email, id) > 0) historyRepository.deleteByEmailAndPostid(email, id);
			History history = new History(new PKSet(email, id));
			historyRepository.save(history);
		}
		// 저장여부 표시
		if(storageRepository.isStore(email, id) > 0) detail.setIsstore(true);
		
		//작성자의 blog 설정
		Optional<BlogConfig> bco = configRepository.findById(detail.getWriter());

		if(bco.isPresent()) {
			BlogConfig bc = bco.get();
			detail.setBlogName(bc.getName());
			detail.setBlogDescription(bc.getDescription());
			detail.setBlogPicture(bc.getPicture());
		}
		detail.setFollowerNum(followService.MyFollowerCnt(detail.getWriter()));
		detail.setFollow(followService.isFollow(email, detail.getWriter()) > 0 ? true : false);
		
		return new ResponseEntity<PostDetail>(detail, HttpStatus.OK);
	}
	
	@GetMapping("mycategory/{postid}")
	@ApiOperation(value = "해당 게시물의 해시태그 목록 출력")
	public ResponseEntity<List<String>> searchHashTag(@PathVariable Long postid) throws Exception {
		List<Hashtag> hashList = hashtagService.findByPostid(postid);
		List<String> list = new ArrayList<>();
		for(int i=0;i<hashList.size();i++) list.add(hashList.get(i).getHashtagPK().getHashtag());
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}
	
	@GetMapping("mycategory/postlist")
	@ApiOperation(value = "내가 쓴 특정 해시태그의 글들을 출력(HashTag Category 안 게시물들) ")
	public ResponseEntity<List<PostList>> searchHashTag(String hashtag, String email) throws Exception {
		List<PostList> list = postListRepository.searchPostByMyTag(email, hashtag);
		for(PostList post : list) {
			User user = userRepository.getUserByEmail(post.getWriter());	//작성자의 user 정보
			post.setNickname(user.getNickname());
			post.setProfile(user.getProfile());
			post.setScore(user.getScore());
			if(goodRepository.isGood(email, post.getId()) > 0) post.setIsgood(true);
		}
		return new ResponseEntity<List<PostList>>(list, HttpStatus.OK);
	}	
	
	@GetMapping("mycategory/taglist")
	@ApiOperation(value = "내가 쓴 글들의 해시태그 리스트와 글 개수 리턴 - 정렬됨")
	public ResponseEntity<List<Object[]>> searchMyHashCategory(String email) throws Exception {
		List<Hashtag> hashlist = hashtagService.myHashCategory(email);
		Set<String> s = new TreeSet();
		for(int i=0;i<hashlist.size();i++) s.add(hashlist.get(i).getHashtagPK().getHashtag());
		List<Object[]> res = new ArrayList<Object[]>();
		for(String hash : s) res.add(new Object[] {hash, 0});	// 해시태그 이름, 글 개수 0
		for(Object[] cur : res) cur[1] = postService.cntCategory(email, (String)cur[0]);	//태그 이름마다 글 개수 넣어줌
		return new ResponseEntity<List<Object[]>>(res, HttpStatus.OK);
	}	
	
	@GetMapping("mycategory/totcnt")
	@ApiOperation(value = "내가 쓴 전체 게시물 개수")
	public ResponseEntity<Integer> getMyPostCnt(String email) {
		return new ResponseEntity<Integer>(postService.cntMyPost(email), HttpStatus.OK);
	}
	
	@GetMapping("search/{word}")
	@ApiOperation(value = "해당 word를 제목 또는 내용에 포함하고 있는 포스트 리스트 출력.")
	public ResponseEntity<List<PostList>> searchByWord(@PathVariable String word, String email) throws Exception {
		List<PostList> list = postListRepository.searchByWord(word);
		for(PostList post : list) {
			User user = userRepository.getUserByEmail(post.getWriter());	//작성자의 user 정보
			post.setNickname(user.getNickname());
			post.setProfile(user.getProfile());
			post.setScore(user.getScore());
			if(goodRepository.isGood(email, post.getId()) > 0) post.setIsgood(true);
		}
		return new ResponseEntity<List<PostList>>(list, HttpStatus.OK);
	}
	
	@GetMapping("searchHash/{hashtag}")
	@ApiOperation(value = "해시태그로 게시물 검색")
	public ResponseEntity<List<PostList>> searchByHashtag(@PathVariable String hashtag, String email) throws Exception {
		//해당 태그가 없었다면, 인기태그에 추가. 있었다면, 점수 증가
		if(tagscoreRepository.isExist(hashtag) == 0) tagscoreRepository.save(new TagScore(hashtag, 0l));
		else tagscoreRepository.plusScore(hashtag);
		
		List<PostList> list = postListRepository.searchByTag(hashtag);
		for(PostList post : list) {
			User user = userRepository.getUserByEmail(post.getWriter());	//작성자의 user 정보
			post.setNickname(user.getNickname());
			post.setProfile(user.getProfile());
			post.setScore(user.getScore());
			if(goodRepository.isGood(email, post.getId()) > 0) post.setIsgood(true);
		}
		return new ResponseEntity<List<PostList>>(list, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value = "새 글 게시 - 글 정보 + 파일의 접근경로 DB에 저장")
	public ResponseEntity<String> insert(@RequestBody Post post, HashtagList tag) {
		Post p = postService.save(post);	// 글 저장		
		algoRepository.setCurDatePost(p.getId());
		if(tag.getTag().size() == 1 && tag.getTag().get(0).equals("none")) {
			System.out.println("태그 없음");
		}
		else {
			for(int i=0;i<tag.getTag().size();i++) {
				Hashtag ht = new Hashtag(new HashtagPK(p.getId(), tag.getTag().get(i)));
				hashtagService.save(ht);	//해시태그 등록
				// 해시태그 점수 등록
				if(tagscoreRepository.isExist(tag.getTag().get(i)) == 0) tagscoreRepository.save(new TagScore(tag.getTag().get(i), 0l));
			}
		}
		
		// 점수 추가 - 포스팅 : 100점
		Long max_score = 150l;
		Long act_score = 100l;
		Long cur_score = algoRepository.getScore(post.getWriter());
		Long acq_score = max_score - cur_score < act_score ? max_score - cur_score : act_score;
		
		String ori_day = algoRepository.getDate(post.getWriter());
		String pre_day = algoRepository.getDateFormatted(post.getWriter());
		algoRepository.updateDate(post.getWriter());
		String post_day = algoRepository.getDateFormatted(post.getWriter());
		if(ori_day != null && pre_day.equals(post_day)) algoRepository.updateScore(acq_score, post.getWriter());
		else {
			algoRepository.setScore(post.getWriter());
			algoRepository.updateScore(act_score, post.getWriter());
		}
		
		System.out.println("cur_score : " + cur_score);
		System.out.println("acq_score : " + acq_score);
		System.out.println("ori_day : " + ori_day);
		System.out.println("pre_day : " + pre_day);
		System.out.println("post_day : " + post_day);
		

		// 알림 발생(100000)
		List<Follow> followerList = followService.findByFollowed(post.getWriter());	
		String actionName = userRepository.getUserByEmail(post.getWriter()).getNickname();
		for(Follow f : followerList){
			if(f == null) continue;	// NullPointerException Remove
			User user = userRepository.getUserByEmail(f.getFollowPK().getFollower());
			if(user == null) continue;
            int setting = Integer.parseInt(user.getNotification(),2);
            // 알림 ON 했는지 && targetuser가 내 알림 껐는지
			if( (setting& (1<<5)) != 0
					&& unnotificationRepository.findByTargetuserAndActionuser(f.getFollowPK().getFollower(), post.getWriter()) == null){
				Notification notification = Notification.builder()
					.message(actionName +"님의 블로그에 새로운 게시물이 등록되었습니다. 가장 먼저 방문해 게시물을 확인해보세요.")
					.actionuser(post.getWriter())
					.targetuser(f.getFollowPK().getFollower())
					.build();
				
				notification.setType(1<<5);
				notification.setPostid(p.getId());
				notificationService.save(notification);
			}
		}
		
		if(p != null) return new ResponseEntity<>("success", HttpStatus.OK);
		return new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
	}

	@PutMapping("/upload")
	@ApiOperation(value = "post 대표 사진 업로드")
	public ResponseEntity<String> fileUpload(@RequestParam("filename") MultipartFile mFile, HttpServletRequest request){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date nowdate = new Date();
		String dateString = formatter.format(nowdate);	//현재시간 문자열
		
		String real_path = "/home/ubuntu/s03p13a604/back/src/main/webapp/resources/postRep/" + 
				dateString + "_" + mFile.getOriginalFilename();	//경로 + 날짜시간 + _ +파일이름으로 저장

		String access_path = "http://i3a604.p.ssafy.io/images/postRep/" + dateString + "_" + mFile.getOriginalFilename();
		
		try {
			mFile.transferTo(new File(real_path));	//실제경로로 파일을 저장
			return new ResponseEntity<String>(access_path, HttpStatus.OK);	//접근경로 return
		} catch (IOException e) {
			System.out.println("파일 업로드 실패");
			return new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
		}
	}
	
	@PutMapping("/unupload")
	@ApiOperation(value = "post 대표 사진 지우기(picture column null로)")
	public ResponseEntity<String> fileUnUpload(Long id) {
		postService.deletePicture(id);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@DeleteMapping("/delfile")
	@ApiOperation(value = "서버에 저장된 사진 지우기")
	public ResponseEntity<String> fileDelete(String filePath) {
		String tem = filePath.replace("/postRep", "+");
		StringTokenizer st = new StringTokenizer(tem, "+");
		
		String prev = st.nextToken();	//http://i3a604.p.ssafy.io/images
		String next = st.nextToken();	///"/" + dateString + "_" + mFile.getOriginalFilename();
		
		String path = "/home/ubuntu/s03p13a604/back/src/main/webapp/resources/postRep" + next;
		
		File delFile = new File(path);
		if(delFile.exists()) delFile.delete();
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@PutMapping
	@ApiOperation(value = "포스트 수정 (+해시태그 수정 - 해당 글의 해시태그를 모두 지우고, 다시 생성하는 로직)")
	public ResponseEntity<String> modify(@RequestBody Post post, HashtagList tag) {
		System.out.println("글 수정");
		
		Optional<Post> tem = postService.findById(post.getId());
		if(tem != null) {
			tem.ifPresent(selectPost -> {
				selectPost.setTitle(post.getTitle());
				selectPost.setContent(post.getContent());
				selectPost.setPicture(post.getPicture());
				selectPost.setSummary(post.getSummary());
				Post newPost = postService.save(selectPost);
				System.out.println(newPost);
			});
			
			hashtagService.deleteHashtagByPostid(post.getId());	//해당 글의 해시태그 모두 삭제
			
			if(tag.getTag().size() == 1 && tag.getTag().get(0).equals("none")) {
				System.out.println("태그 없음");
			}
			else {
				for(int i=0;i<tag.getTag().size();i++) {	//다시 생성
					Hashtag ht = new Hashtag(new HashtagPK(post.getId(), tag.getTag().get(i)));
					hashtagService.save(ht);
					// 해시태그 점수 등록
					if(tagscoreRepository.isExist(tag.getTag().get(i)) == 0) tagscoreRepository.save(new TagScore(tag.getTag().get(i), 0l));
				}
			}
			
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
	}	
	
	@PutMapping("good")
	@ApiOperation(value = "포스트 좋아요++ : 좋아요 정보 생성, 보관함에 추가")
	public ResponseEntity<String> incGood(@RequestParam(required = true) Long id, @RequestParam(required = true) String email) {
		System.out.println("좋아요 수 count++");
		Optional<Post> tem = postService.findById(id);
		if(tem != null) {
			tem.ifPresent(selectPost -> {
				selectPost.setGood(tem.get().getGood()+1);
				Post newPost = postService.save(selectPost);

				// 좋아요 리스트에 담기
				Good good = new Good(new PKSet(email, id));
				goodRepository.save(good);
				
				if(!newPost.getWriter().equals(email)){	//자신의 글은 보관함, 알림 반영 X
					// 알람 발생(0000001)
					// 1)이전에 좋아요 눌렀었었는지 확인 2)알림 ON 해놨는지 확인 3)targetuser가 내 알림 껐는지
					User user = userRepository.getUserByEmail(newPost.getWriter());
					int setting = Integer.parseInt(user.getNotification(),2);

					if(notificationService.findByActionuserAndPostidAndType(email, id, 1) == null
						&& ( (setting& (1<<0)) != 0) 
						&& unnotificationRepository.findByTargetuserAndActionuser(newPost.getWriter(), email) == null){
						String actionName = userRepository.getUserByEmail(email).getNickname();
						Notification notification = Notification.builder()
									.message(actionName +"님이 회원님의 게시물을 좋아합니다.")
									.actionuser(email)
									.targetuser(newPost.getWriter())
									.build();
						notification.setPostid(id);
						notification.setType(1<<0);
						notificationService.save(notification);
					}
				}
			});

			// 점수 추가 - 좋아요 : 2점
			Long max_score = 150l;
			Long act_score = 2l;
			Long cur_score = algoRepository.getScore(email);
			Long acq_score = max_score - cur_score < act_score ? max_score - cur_score : act_score;
			
			String ori_day = algoRepository.getDate(email);
			String pre_day = algoRepository.getDateFormatted(email);
			algoRepository.updateDate(email);
			String post_day = algoRepository.getDateFormatted(email);
			if(ori_day != null && pre_day.equals(post_day)) algoRepository.updateScore(acq_score, email);
			else {
				algoRepository.setScore(email);
				algoRepository.updateScore(act_score, email);
			}
			
			
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
	}
	
	@PutMapping("ungood")
	@ApiOperation(value = "포스트 좋아요-- : 좋아요 정보 삭제, 보관함에서 지우기")
	public ResponseEntity<String> decGood(@RequestParam(required = true) Long id, @RequestParam(required = true) String email) {
		System.out.println("좋아요 수 count--");
		Optional<Post> tem = postService.findById(id);
		if(tem != null) {
			tem.ifPresent(selectPost -> {
				selectPost.setGood(tem.get().getGood()-1);
				Post newPost = postService.save(selectPost);

				// 좋아요 리스트에서 지우기
				goodRepository.deleteByEmailAndPostid(email, id);

			});
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
	}
	
	@PostMapping("storage")
	@ApiOperation(value = "보관함에 저장")
	public ResponseEntity<String> insertStorage(String email, Long postid) {
		Storage storage = new Storage(new PKSet(email, postid));
		storageRepository.save(storage);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@DeleteMapping("storage")
	@ApiOperation(value = "보관함에서 삭제")
	public ResponseEntity<String> deleteStorage(String email, Long postid) {
		PKSet pk = new PKSet(email, postid);
		storageRepository.deleteById(pk);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@GetMapping("storage")
	@ApiOperation(value = "보관함 조회")
	public ResponseEntity<List<Storage>> searchStorage(String email) {
		System.out.println(storageRepository.searchStorageByEmail(email));
		return new ResponseEntity<List<Storage>>(storageRepository.searchStorageByEmail(email), HttpStatus.OK);
	}
	
	@PostMapping("share")
	@ApiOperation(value = "보관함에서 삭제")
	public ResponseEntity<String> share(String email) {
		// 점수 추가 - 공유 : 4점
		Long max_score = 150l;
		Long act_score = 4l;
		Long cur_score = algoRepository.getScore(email);
		Long acq_score = max_score - cur_score < act_score ? max_score - cur_score : act_score;
		
		String ori_day = algoRepository.getDate(email);
		String pre_day = algoRepository.getDateFormatted(email);
		algoRepository.updateDate(email);
		String post_day = algoRepository.getDateFormatted(email);
		if(ori_day != null && pre_day.equals(post_day)) algoRepository.updateScore(acq_score, email);
		else {
			algoRepository.setScore(email);
			algoRepository.updateScore(act_score, email);
		}		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@DeleteMapping
	@ApiOperation(value = "포스트 삭제 - 보관함, 기록, 해시태그, 좋아요, 파일도 함께 삭제")
	public ResponseEntity<String> delete(Long id) {
		System.out.println("글 삭제");
		Optional<Post> post = postService.findById(id);
		if(post != null) {
			storageRepository.deleteByPostid(id);
			goodRepository.deleteByPostid(id);
			historyRepository.deleteByPostid(id);
			hashtagService.deleteHashtagByPostid(id);
			commentService.deleteByPostid(id);
			
			post.ifPresent(selectPost -> {
				String path = selectPost.getPicture();
				if(path != null && !path.equals("")) {
					String tem = path.replace("/postRep", "+");
					StringTokenizer st = new StringTokenizer(tem, "+");
					
					String prev = st.nextToken();	//http://i3a604.p.ssafy.io/images
					String next = st.nextToken();	///"/" + dateString + "_" + mFile.getOriginalFilename();
					
					String realpath = "/home/ubuntu/s03p13a604/back/src/main/webapp/resources/postRep" + next;
					
					File delFile = new File(realpath);
					if(delFile.exists()) delFile.delete();
				}
			});
			
			postService.deleteById(id);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
	}
}
