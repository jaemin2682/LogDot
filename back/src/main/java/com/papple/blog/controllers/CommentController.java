package com.papple.blog.controllers;

import java.time.LocalDateTime;
import java.util.List;

import com.papple.blog.models.Comment;
import com.papple.blog.models.LikeComment;
import com.papple.blog.models.Notification;
import com.papple.blog.models.Post;
import com.papple.blog.models.User;
import com.papple.blog.models.unNotification;
import com.papple.blog.payload.request.CommentRequest;
import com.papple.blog.payload.response.CommentResponse;
import com.papple.blog.payload.response.MessageResponse;
import com.papple.blog.repository.PostAlgorithmRepository;
import com.papple.blog.repository.UserRepository;
import com.papple.blog.repository.unNotificationRepository;
import com.papple.blog.security.services.CommentService;
import com.papple.blog.security.services.NotificationService;
import com.papple.blog.security.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

// http://localhost:8081/swagger-ui.html
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
    private CommentService commentService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PostAlgorithmRepository algoRepository;
    @Autowired
    private unNotificationRepository unnotificationRepository;

    @GetMapping("/allComment")
	@ApiOperation(value = "해당 포스트의 모든 댓글 보기")
	public ResponseEntity<List<CommentResponse>> allComment(@RequestParam(required = true) Long postid, @RequestParam(required = true) String email) throws Exception {
        List<CommentResponse> list = commentService.findByPostidAndReplytoIsNull(postid);

        for(CommentResponse com : list){
            com.setIslike(commentService.findByEmailAndCommentid(email, com.getId()));
        }
        return new ResponseEntity<List<CommentResponse>>(list, HttpStatus.OK);
    }
    
    @GetMapping("/allReply")
	@ApiOperation(value = "해당 댓글의 모든 답댓글 보기")
	public ResponseEntity<List<CommentResponse>> allReply(@RequestParam(required = true) Long postid, @RequestParam(required = true) Long id, @RequestParam(required = true) String email) throws Exception {
		List<CommentResponse> list = commentService.findByPostidAndReplyto(postid, id);
        for(CommentResponse com : list){
            com.setIslike(commentService.findByEmailAndCommentid(email, com.getId()));
        }
        return new ResponseEntity<List<CommentResponse>>(list, HttpStatus.OK);
	}   
    
    @PostMapping
    @ApiOperation(value = "새 댓글 쓰기")
    public ResponseEntity<String> writeComment(@RequestBody Comment comment) {
    
        Comment com = commentService.save(comment);     // 댓글또는답댓글 저장

        Post post = postService.findById(comment.getPostid()).get();
        String actionName = userRepository.getUserByEmail(comment.getEmail()).getNickname();
        
        algoRepository.setCurDateComment(com.getId());
        
        // 점수 추가 - 댓글 : 4점
     	Long max_score = 150l;
     	Long act_score = 4l;
     	Long cur_score = algoRepository.getScore(comment.getEmail());
     	Long acq_score = max_score - cur_score < act_score ? max_score - cur_score : act_score;
     	
     	String ori_day = algoRepository.getDate(comment.getEmail());
     	String pre_day = algoRepository.getDateFormatted(comment.getEmail());
     	algoRepository.updateDate(comment.getEmail());
     	String post_day = algoRepository.getDateFormatted(comment.getEmail());
     	if(ori_day != null && pre_day.equals(post_day)) algoRepository.updateScore(acq_score, comment.getEmail());
     	else {
     		algoRepository.setScore(comment.getEmail());
     		algoRepository.updateScore(act_score, comment.getEmail());
     	}
        
        
        if(comment.getReplyto()==null){ // 댓글
            
            if(!comment.getEmail().equals(post.getWriter())){   // 자신의 게시물일 경우 X
                // 알림 발생(000010)
                User user = userRepository.getUserByEmail(post.getWriter());
                int setting = Integer.parseInt(user.getNotification(),2);
                // 알림 ON 했는지 && targetuser가 내 알림 껐는지
                if( (setting& (1<<1)) != 0 
                        && unnotificationRepository.findByTargetuserAndActionuser(post.getWriter(), comment.getEmail()) == null){
                    Notification notification = Notification.builder()
                        .message(actionName +"님이 회원님의 게시물에 댓글을 남겼습니다. "+comment.getContent())
                        .actionuser(comment.getEmail())
                        .targetuser(post.getWriter())
                        .build();
                        
                    notification.setPostid(comment.getPostid());
                    notification.setType(1<<1);
                    notificationService.save(notification);
                }
            }

        } else{ // 답댓글
            Comment comm = commentService.findById(comment.getReplyto()).get();
            comm.setReplycount(comm.getReplycount()+1);
            commentService.save(comm);   // 부모댓글 수정

            if(!comment.getEmail().equals(comm.getEmail())){ // 자신의 댓글에 답글 다는 경우는 X
                // 알림 발생(001000)
                User user = userRepository.getUserByEmail(comm.getEmail());
                int setting = Integer.parseInt(user.getNotification(),2);
                // 알림 ON 했는지 && targetuser가 내 알림 껐는지
                System.out.println(setting);
                if( (setting & (1<<3)) != 0
                        && unnotificationRepository.findByTargetuserAndActionuser(comm.getEmail(), comment.getEmail()) == null){
                    Notification notification = Notification.builder()
                        .message(actionName +"님이 답글에서 회원님을 언급했습니다. "+comment.getContent())
                        .actionuser(comment.getEmail())
                        .targetuser(comm.getEmail())
                        .build();
                        
                    notification.setPostid(comment.getPostid());
                    notification.setType(1<<3);
                    notificationService.save(notification);
                }
            }
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    
    @PutMapping
	@ApiOperation(value = "댓글 수정 ")
	public ResponseEntity<String> modifyComment(@RequestBody CommentRequest commentRequest) {

        Comment comment = commentService.findById(commentRequest.getId()).get();
        comment.setContent(commentRequest.getContent());
        commentService.save(comment);

        return new ResponseEntity<>("success", HttpStatus.OK);
	}	

    @DeleteMapping
	@ApiOperation(value = "댓글 삭제 - 답글 삭제")
	public ResponseEntity<?> deleteComment(Long id) {

        if(!commentService.findById(id).isPresent()) return ResponseEntity.badRequest()
                                                                    .body(new MessageResponse("comment does not exist"));
        
        Comment comment = commentService.findById(id).get();
        if(comment.getReplycount()>0){ // 답글있으면 삭제
            commentService.deleteByReplyto(id);
        }

         // 댓글,답글 삭제
        commentService.deleteById(id);   
            
        if(comment.getReplyto()!=null){ // 부모댓글이 있는 경우
            Comment parentComment = commentService.findById(comment.getReplyto()).get();
            parentComment.setReplycount(parentComment.getReplycount()-1);    // 답글수--
            commentService.save(parentComment);
        }

		return ResponseEntity.ok().body(new MessageResponse("Success"));
	
    }
    
    @PutMapping("/like")
	@ApiOperation(value = "댓글 좋아요++")
	public ResponseEntity<String> incGood(@RequestParam(required = true) Long id, @RequestParam(required = true) String email) {
        Comment comment = commentService.findById(id).get();
        comment.setLikes(comment.getLikes()+1);
        commentService.save(comment);

        commentService.likeComment(email, id);
		// 알람 발생(000100)
		// 이전에 좋아요 눌렀었었는지 확인
		if(notificationService.findByActionuserAndCommentidAndType(email, id, 4) == null){
            String actionName = userRepository.getUserByEmail(email).getNickname();

            User user = userRepository.getUserByEmail(comment.getEmail());
            int setting = Integer.parseInt(user.getNotification(),2);
            // 알림 ON 했는지 && targetuser가 내 알림 껐는지
            if( (setting& (1<<2)) != 0
                    && unnotificationRepository.findByTargetuserAndActionuser(comment.getEmail(), email) == null){
                Notification notification = Notification.builder()
                    .message(actionName +"님이 회원님의 댓글을 좋아합니다.")
                    .actionuser(email)
                    .targetuser(comment.getEmail())
                    .build();
                notification.setCommentid(id);
                notification.setPostid(comment.getPostid());
                notification.setType(1<<2);
                notificationService.save(notification);
            }
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
    }
    
    @PutMapping("/unlike")
	@ApiOperation(value = "댓글 좋아요--")
	public ResponseEntity<String> decGood(@RequestParam(required = true) Long id, @RequestParam(required = true) String email) {
        Comment comment = commentService.findById(id).get();
        comment.setLikes(comment.getLikes()-1);
        commentService.save(comment);
        
        commentService.unlikeComment(email, id);
        return new ResponseEntity<String>("success", HttpStatus.OK);
		
	}
	
}

