package com.papple.blog.controllers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.StringTokenizer;

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

import com.papple.blog.models.BlogConfig;
import com.papple.blog.models.User;
import com.papple.blog.repository.ConfigRepository;
import com.papple.blog.repository.UserRepository;

import io.swagger.annotations.ApiOperation;

// http://localhost:8081/swagger-ui.html
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/blog")
public class ConfigController {
	@Autowired
    private ConfigRepository configRepository;
	@Autowired
	private UserRepository userRepository;

    @PostMapping
	@ApiOperation(value = "블로그 설정 저장, 수정")
	public ResponseEntity<BlogConfig> saveConfig(@RequestBody BlogConfig config) {
    	
    	Optional<BlogConfig> origin = configRepository.findById(config.getEmail());
    	String picture = "";
    	
    	if(origin.isPresent()) {
    		picture = origin.get().getPicture();
    	}
    	BlogConfig bc = configRepository.save(config);			//id가 있으면 수정, 없으면 저장
    	bc.setPicture(picture);
    	configRepository.updatePicture(picture, origin.get().getEmail());
    	
        return new ResponseEntity<BlogConfig>(bc, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "블로그 설정 보기")
	public ResponseEntity<BlogConfig> getConfig(@RequestParam String email) {
    	BlogConfig bc = configRepository.findById(email).get();
        return new ResponseEntity<BlogConfig>(bc, HttpStatus.OK);
    }
   
    @PutMapping("/upload")
   	@ApiOperation(value = "블로그 대표 사진 업로드")
   	public ResponseEntity<String> fileUpload(@RequestParam("filename") MultipartFile mFile, HttpServletRequest request, @RequestParam String email){
   		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
   		Date nowdate = new Date();
   		String dateString = formatter.format(nowdate);	//현재시간 문자열
   		
   		//기존에 있던 사진 경로 추출
   		BlogConfig config = configRepository.findById(email).get();
   		if(config != null && config.getPicture() != null && !config.getPicture().equals("") && 	//기존 경로가 있을 때에만 기존 경로의 파일을 지운다.
   				!config.getPicture().equals("http://i3a604.p.ssafy.io/images/blogRep/blog_basic.jpg")) {	// 기본사진도 지우지 않는다.
   			String old_access_path = config.getPicture();
   							
   			String tem = old_access_path.replace("/blogRep", "+");
   			StringTokenizer st = new StringTokenizer(tem, "+");
   			
   			String prev = st.nextToken();
   			String next = st.nextToken();
   			
   			String old_real_path = "/home/ubuntu/s03p13a604/back/src/main/webapp/resources/blogRep" + next;
   			
   			File delFile = new File(old_real_path);
   			if(delFile.exists()) delFile.delete();
   		}
   		
   		// 새로운 파일 서버에 저장, blog config의 경로 수정
   		String real_path = "/home/ubuntu/s03p13a604/back/src/main/webapp/resources/blogRep/" + 
   				dateString + "_" + mFile.getOriginalFilename();	//경로 + 날짜시간 + _ +파일이름으로 저장

   		String access_path = "http://i3a604.p.ssafy.io/images/blogRep/" + dateString + "_" + mFile.getOriginalFilename();
   		
   		try {
   	   		configRepository.updatePicture(access_path, email);
   			mFile.transferTo(new File(real_path));	//실제경로로 파일을 저장
   			return new ResponseEntity<String>(access_path, HttpStatus.OK);	//접근경로 return
   		} catch (IOException e) {
   			System.out.println("파일 업로드 실패");
   			return new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
   		}
   	}
   
	
}

