package com.papple.blog.controllers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
//import java.lang.StackWalker.Option;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import io.swagger.annotations.ApiOperation;

import com.papple.blog.email.MailHandler;
import com.papple.blog.email.TempKey;
import com.papple.blog.models.BlogConfig;
import com.papple.blog.models.ERole;
import com.papple.blog.models.Post;
import com.papple.blog.models.Role;
import com.papple.blog.models.User;
import com.papple.blog.models.UserAuth;
import com.papple.blog.payload.request.LoginRequest;
import com.papple.blog.payload.request.SignupRequest;
import com.papple.blog.payload.request.UpdateRequest;
import com.papple.blog.payload.response.JwtResponse;
import com.papple.blog.payload.response.MessageResponse;
import com.papple.blog.repository.AuthRepository;
import com.papple.blog.repository.ConfigRepository;
import com.papple.blog.repository.GoodRepository;
import com.papple.blog.repository.HistoryRepository;
import com.papple.blog.repository.ProfileRepository;
import com.papple.blog.repository.HashtagRepository;
import com.papple.blog.repository.RoleRepository;
import com.papple.blog.repository.StorageRepository;
import com.papple.blog.repository.UserRepository;
import com.papple.blog.security.jwt.JwtUtils;
import com.papple.blog.security.services.FollowService;
import com.papple.blog.security.services.PostService;
import com.papple.blog.security.services.UserDetailsImpl;
import com.papple.blog.social.SessionUser;

@CrossOrigin(origins = {"http://i3a604.p.ssafy.io", "http://localhost:8080" },allowedHeaders = "*",allowCredentials = "true", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	AuthRepository authRepository;
	@Autowired
	HistoryRepository historyRepository;
	@Autowired
	StorageRepository storageRepository;
	@Autowired
	PostService postService;
	@Autowired
	FollowService followService;
	@Autowired
	HashtagRepository hashtagRepository;
	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	private GoodRepository goodRepository;
	@Autowired
	ConfigRepository configRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	JavaMailSender mailSender;
 
	@PostMapping("/signin")
	@ApiOperation(value = "로그인")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		// 이메일 인증 받은 회원인지
		User userauth = userRepository.getUserByEmail(loginRequest.getEmail());
		if(userauth.getUsercertification() == 0){
			return ResponseEntity.badRequest().body("User authentication failed.");
		}

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												userDetails.getEmail(), 
												 userDetails.getUsername(), 
												 roles));
	}

	
	@PostMapping("/signup")
	@ApiOperation(value = "회원 가입")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		
		// Create new user's account
		User user = new User(signUpRequest.getEmail(), null,
							encoder.encode(signUpRequest.getPassword()),0, 0l);

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		sendMail(signUpRequest.getEmail());

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@GetMapping("/checkEmailDuplication")
	@ApiOperation(value = "이메일 중복 체크")
	public ResponseEntity<?> checkEmailDuplication(@RequestParam(required = true) final String email){
		 if (userRepository.existsById(email)) {
		 	return ResponseEntity
		 			.badRequest()
		 			.body(new MessageResponse("Error: Email is already in use!"));
		 }
		 return ResponseEntity.ok().body(new MessageResponse("Success"));
	}

	@GetMapping("/sendEmail")
	@ApiOperation(value = "인증 이메일 보내기")
	public ResponseEntity<?> sendEmail(@RequestParam final String email){
		sendMail(email);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	// 회원가입시 인증 이메일 보내기
	@Async
	public void sendMail(String email){
		try{
			final String key = new TempKey().getKey(50, false); // 인증키 생성
			UserAuth userauth = new UserAuth(email, key);
			authRepository.save(userauth);	// 인증키 DB저장
			MailHandler sendMail = new MailHandler(mailSender);
			sendMail.setSubject("[LOG DOT] 이메일 인증입니다."); // 메일제목
			sendMail.setText( // 메일내용
			"<div style='width:100%'>"+
				"<div style='max-width:600px;margin:0 auto;padding:60px 0 30px 0;font-family:'Roboto',Arial,Helvetica,sans-serif;font-size:16px;line-height:1.5;border:1px solid #e2e2e2'>"+
					"<div align='center' style='padding-right:0px;padding-left:0px' class='logo-area'>"+
						"<a href='http://i3a604.p.ssafy.io' style='outline:none' target='_blank'>"+
						"<img align='center' border='0' src='http://i3a604.p.ssafy.io/images/postRep/20200811065341_Main_Logo_temp.svg' alt='Logo' title='Logo' style='text-decoration-line: none; height: auto; border: none; width: 100%; max-width: 143px; display: block;' width='143'>"+
						"</a>"+
			  		"</div>"+
			 		"<hr style='border:0;border-top:solid 1px #e2e2e2;width:90%;margin:30px auto' class='horizontal-line'>"+
			  		"<div align='center' style='max-width:90%;margin-left:auto;margin-right:auto;margin-top:40px' class='nomal-paragraph'>"+
						"<div style='font-size:20px; font-weight:bold; color:#777777; margin:40px 0 30px 0'>LOGDOT에 가입해주셔서 감사합니다.</div>"+
			  		"</div>"+
			  		"<div align='center' style='max-width:90%;margin-left:auto;margin-right:auto;margin-top:30px' class='nomal-paragraph'>"+
						"<div style='font-size: 14px;font-weight:600; color:#838383; margin:30px 0 30px 0;'>아래 버튼을 클릭하여 인증을 마치면 계정이 활성화 됩니다.</div>"+
			  		"</div>"+
			  		"<div align='center' style='padding-top:10px;padding-right:10px;padding-bottom:20px;padding-left:10px'>"+
						"<a href='http://i3a604.p.ssafy.io:8081/api/auth/emailConfirm?email=" + email + "&key=" + key + "' "+
									"style='width: 300px; text-decoration-line: none; display: inline-block; color: rgb(255, 255, 255); background-color: #6da7ff; border-radius: 10px; border-width: 1px; border-style: solid; border-color: #6da7ff; padding: 10px 25px;' target='_blank'>"+
							"메일 인증 완료"+
						"</a>"+
			  		"</div>"+
			  		"<hr style='border:0;border-top:solid 1px #e2e2e2;width:90%;margin:30px auto' class='horizontal-line'>"+
			  		"<div align='center' style='max-width:90%;margin-left:auto;margin-right:auto;margin-top:40px' class='nomal-paragraph'>"+
						"<div style='font-size: 12px; color:#838383;'>Copyright @ 2020 LOGDOT</div>"+
					"</div>"+
				"</div>"+
		  	"</div>");
			sendMail.setFrom("LOGDOT@logdot.com", "LOGDOT"); // 보낸이
			sendMail.setTo(email); // 받는이
			sendMail.send();
			// 글씨 크기 24,20,20,12
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	@GetMapping("/emailConfirm")
	@ApiOperation(value = "이메일 인증")
	public RedirectView emailConfirm(@RequestParam(required = true) final String email, final String key){
		Optional<UserAuth> userauth = authRepository.findById(email);
		if(userauth != null){
			userRepository.updateAuth(1, email);
			return new RedirectView("http://i3a604.p.ssafy.io/account/setNickname/"+email+"/"+key);
		}
		// 에러 페이지로 
		return null;
	}

	@GetMapping("/unregister")
	@ApiOperation(value = "회원탈퇴")
	public ResponseEntity<?> unregister(@RequestParam(required = true) final String email) {     
		historyRepository.deleteByEmail(email);			// 히스토리 삭제
		goodRepository.deleteByEmail(email);			// 좋아요 삭제
		storageRepository.deleteByEmail(email);			// 보관함 삭제
		followService.deleteByEmail(email);				// 팔로우 삭제
		hashtagRepository.deleteHashtagByEmail(email);	// Hashtag 삭제
		
		List<Post> postList= postService.findByWriter(email);
		for(Post post : postList) {	//해당 사용자가 작성했던 글 관련 데이터 삭제
			historyRepository.deleteByPostid(post.getId());
			storageRepository.deleteByPostid(post.getId());
			hashtagRepository.deleteHashtagByPostid(post.getId());
		}
		postService.deleteByWriter(email);				// 쓴 글 삭제
		userRepository.deleteById(email);				// 회원 삭제
		
        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
	} 

	@GetMapping("/userInfo")
	@ApiOperation(value = "회원정보 조회")
    public ResponseEntity<?> userInfo(@RequestParam(required = true) final String email) {
		User user = userRepository.getUserByEmail(email);
		// User user = userRepository.getOne(email); 
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/nicknameUpdate")
	@ApiOperation(value = "닉네임 업데이트")	//이때가 닉네임 처음 설정할 때.
	public ResponseEntity<?> nicknameUpdate(@RequestParam(required = true) final String nickname,
									@RequestParam(required = true) final String email) {

		if (userRepository.existsByNickname(nickname)) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Nickname is already taken!"));
		}
		userRepository.updateNickname(nickname, email);
		
		BlogConfig config = new BlogConfig(email, nickname + "의 블로그", nickname + "의 블로그 입니다.", "http://i3a604.p.ssafy.io/images/blogRep/blog_basic.jpg");
		configRepository.save(config);
		
        return ResponseEntity.ok(new MessageResponse("Nickname registered successfully!"));
	}
	
	@PostMapping("/nicknameUpdate")
	@ApiOperation(value="계정 설정 [닉네임]")
	public ResponseEntity<?> nicknameUpdate(@RequestBody User user){

		if (userRepository.existsByNickname(user.getNickname())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Nickname is already taken!"));
		}

		userRepository.updateNickname(user.getNickname(), user.getEmail());					// 닉네임 재설정
		return ResponseEntity.ok(new MessageResponse("Nickname updated successfully!"));
	}

	@PostMapping("/passwordUpdate")
	@ApiOperation(value="계정 설정 [비밀번호]")
	public ResponseEntity<?> passwordUpdate(@Valid @RequestBody UpdateRequest updateRequest){

		User user = userRepository.getUserByEmail(updateRequest.getEmail());

		// 사용자 인증
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(updateRequest.getEmail(), updateRequest.getPassword()));

		// 인증된 사용자인 경우 업데이트
		userRepository.resetPassword(encoder.encode(updateRequest.getNewpassword()), updateRequest.getEmail());	
		return ResponseEntity.ok(new MessageResponse("Password updated successfully!"));

	}

	@GetMapping("/passwordEmail")
	@ApiOperation(value = "비밀번호 재설정 이메일")
	public ResponseEntity<?> passwordEmail(@RequestParam(required = true) final String email) {
		
		User user = userRepository.getUserByEmail(email);

		if(user != null){
			try{
				MailHandler sendMail = new MailHandler(mailSender);
				sendMail.setSubject("[LOG DOT] 비밀번호를 재설정 해주세요."); // 메일제목

				sendMail.setText( 
					"<div style='width:100%'>"+
						"<div style='max-width:600px;margin:0 auto;padding:60px 0 30px 0;font-family:'Roboto',Arial,Helvetica,sans-serif;font-size:16px;line-height:1.5;border:1px solid #e2e2e2'>"+
							"<div align='center' style='padding-right:0px;padding-left:0px' class='logo-area'>"+
								"<a href='http://i3a604.p.ssafy.io' style='outline:none' target='_blank'>"+
								"<img align='center' border='0' src='http://i3a604.p.ssafy.io/images/postRep/20200811065341_Main_Logo_temp.svg' alt='Logo' title='Logo' style='text-decoration-line: none; height: auto; border: none; width: 100%; max-width: 143px; display: block;' width='143'>"+
								"</a>"+
							"</div>"+
							"<hr style='border:0;border-top:solid 1px #e2e2e2;width:90%;margin:30px auto' class='horizontal-line'>"+
							"<div align='center' style='max-width:90%;margin-left:auto;margin-right:auto;margin-top:40px' class='nomal-paragraph'>"+
								"<div style='font-size:20px; font-weight:bold; color:#777777; margin:40px 0 30px 0'>LOGDOT 비밀번호 재설정 이메일입니다.</div>"+
							"</div>"+
							"<div align='center' style='max-width:90%;margin-left:auto;margin-right:auto;margin-top:30px' class='nomal-paragraph'>"+
								"<div style='font-size: 14px;font-weight:600; color:#838383; margin:30px 0 30px 0;'>아래 버튼을 클릭하면 비밀번호를 재설정 할 수 있습니다.</div>"+
							"</div>"+
							"<div align='center' style='padding-top:10px;padding-right:10px;padding-bottom:20px;padding-left:10px'>"+
								"<a href='http://i3a604.p.ssafy.io/account/resetpassword/" + email + "' "+
											"style='width: 300px; text-decoration-line: none; display: inline-block; color: rgb(255, 255, 255); background-color: #6da7ff; border-radius: 10px; border-width: 1px; border-style: solid; border-color: #6da7ff; padding: 10px 25px;' target='_blank'>"+
									"비밀번호 재설정"+
								"</a>"+
							"</div>"+
							"<hr style='border:0;border-top:solid 1px #e2e2e2;width:90%;margin:30px auto' class='horizontal-line'>"+
							"<div align='center' style='max-width:90%;margin-left:auto;margin-right:auto;margin-top:40px' class='nomal-paragraph'>"+
								"<div style='font-size: 12px; color:#838383;'>Copyright @ 2020 LOGDOT</div>"+
							"</div>"+
						"</div>"+
					"</div>");
				sendMail.setFrom("LOGDOT@logdot.com", "LOGDOT"); // 보낸이
				sendMail.setTo(email); // 받는이
				sendMail.send();
			} catch(Exception e){
				e.printStackTrace();
			}
			return ResponseEntity.ok(new MessageResponse("Email sended successfully!"));
		} else{
			return ResponseEntity.badRequest().body("User eamil does not exist.");
		}
	}

	@PostMapping("/resetPassword")
	@ApiOperation(value = "비밀번호 재설정")
	public ResponseEntity<?> resetPassword(@Valid @RequestBody LoginRequest loginRequest) {

		userRepository.resetPassword(encoder.encode(loginRequest.getPassword()), loginRequest.getEmail());

		return ResponseEntity.ok(new MessageResponse("Password registered successfully!"));
	}
	
	// Profile 사진 업로드 관련
	@GetMapping("/pflist")
	@ApiOperation(value = "유저의 사진 히스토리 목록을 가져옴")
	public ResponseEntity<List<String>> getProfileHistory(String email) {
		return new ResponseEntity<List<String>>(profileRepository.searchByEmail(email), HttpStatus.OK);
	}
	
	
	@PostMapping("/profile")
	@ApiOperation(value = "path 변수가 비었을 때는 서버에 파일 저장 + 유저 대표사진 update + profile history 등록,  path가 있을 때는 대표사진만 update")
	public ResponseEntity<String> fileUpload(@RequestParam("filename") MultipartFile mFile, @RequestParam String email, 
			@RequestParam(required = false) String path, HttpServletRequest request) {
		if(path == null || path.equals("")) {	// path 변수가 안들어오면 (새 첨부 파일로 대표이미지를 등록하면(
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			Date nowdate = new Date();
			String dateString = formatter.format(nowdate);	//현재시간 문자열
			
			String real_path = "/home/ubuntu/s03p13a604/back/src/main/webapp/resources/profile/" + 
					dateString + "_" + mFile.getOriginalFilename();	//경로 + 날짜시간 + _ +파일이름으로 저장
			String access_path = "http://i3a604.p.ssafy.io/images/profile/" + dateString + "_" + mFile.getOriginalFilename();
			try {
				mFile.transferTo(new File(real_path));					// 서버에 파일 저장
				userRepository.updateProfile(access_path, email);		// 유저 대표사진 update
				profileRepository.insertProfile(email, access_path);	// profile history 등록
				return new ResponseEntity<String>(access_path, HttpStatus.OK);
			} catch(Exception e) {
				System.out.println("파일 업로드 실패");
				return new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
			}
		}
		else {		//path 변수가 들어오면
			userRepository.updateProfile(path, email);		// 유저 대표사진 updatedd
			profileRepository.updateProfile(email, path);	// 시간 재등록
			return new ResponseEntity<String>(path, HttpStatus.OK);
		}
		
		
	}
	
	@DeleteMapping("/delprofile")
	@ApiOperation(value = "서버에 있는 프로필 사진 파일을 삭제 + 프로필 히스토리에서 삭제")
	public ResponseEntity<String> fileDelete(String filePath, String email) {
		
		User user = userRepository.getUserByEmail(email);
		
		if(!user.getProfile().equals(filePath)) {
			String tem = filePath.replace("/profile", "+");
			StringTokenizer st = new StringTokenizer(tem, "+");
			
			String prev = st.nextToken();	//http://i3a604.p.ssafy.io/images
			String next = st.nextToken();	///"/" + dateString + "_" + mFile.getOriginalFilename();
			
			String path = "/home/ubuntu/s03p13a604/back/src/main/webapp/resources/profile" + next;
			
			File delFile = new File(path);
			if(delFile.exists()) delFile.delete();		//해당 path의 서버의 파일 삭제
		}
		
		System.out.println(profileRepository.deleteProfile(email, filePath));	// 프로필 히스토리에서 삭제
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@PutMapping("/unprofile")
	@ApiOperation(value = "프로필 사진을 기본 사진으로 setting")
	public ResponseEntity<String> fileUnUpload(String email) {
		userRepository.deleteProfile(email);
//		profileRepository.unProfile(email);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@GetMapping("/notificationSetting")
	@ApiOperation(value = "알림설정")
	public ResponseEntity<String> getMethodName(@RequestParam final String email, 
															@RequestParam String notification) {

		User user = userRepository.getUserByEmail(email);
		user.setNotification(notification);	// 1111111  (1은 ON, 0은 OFF)
		userRepository.save(user);

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@GetMapping("/loginSuccess")
	public RedirectView loginSucess(HttpSession httpSession){
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		User checkUser =  userRepository.getUserByEmail(user.getEmail());

		// 처음 로그인하는 계정인 경우 
		if(checkUser.getUsercertification()==null){
			checkUser.setUsercertification(1);
			userRepository.save(checkUser);
			BlogConfig config = new BlogConfig(user.getEmail(), "개발자의 블로그", "개발자의 블로그 입니다.", 
			"http://i3a604.p.ssafy.io/images/blogRep/blog_basic.jpg");
			configRepository.save(config);

			return new RedirectView("http://i3a604.p.ssafy.io/account/setNickname/"+user.getEmail());

		} else{

			return new RedirectView("http://i3a604.p.ssafy.io/main/home/"+user.getEmail());
			
		}	
		
	}
}

