package com.papple.blog.controllers;


import java.util.ArrayList;
import java.util.List;
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

import io.swagger.annotations.ApiOperation;
import com.papple.blog.models.TemporaryPost;
import com.papple.blog.payload.response.HashtagList;
import com.papple.blog.payload.response.TemPostResponse;
import com.papple.blog.repository.TempPostRepository;


// http://localhost:8081/swagger-ui.html
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/temppost")
public class TemporaryPostController {
	@Autowired
	TempPostRepository temRepository;
	
	@PostMapping
	@ApiOperation("임시 게시물로 저장")
	public ResponseEntity<TemporaryPost> saveTemPost(@RequestBody TemporaryPost tempost, HashtagList tag) {
		StringBuilder hashtag = new StringBuilder();
		
		if(tag.getTag().size() == 1 && tag.getTag().get(0).equals("none")) {
			System.out.println("태그 없음");
		}
		else {
			for(int i=0;i<tag.getTag().size();i++) {
				if(i == tag.getTag().size() - 1) hashtag.append(tag.getTag().get(i));
				else hashtag.append(tag.getTag().get(i) + "++");
			}
		}
		System.out.println(hashtag.toString());
		
		tempost.setTag(hashtag.toString());
		
		TemporaryPost post = temRepository.save(tempost);
		return new ResponseEntity<TemporaryPost>(post, HttpStatus.OK);
	}
	
	@GetMapping("/is")
	@ApiOperation("임시 게시물이 있는지 여부 반환(있음 : 1, 없음 : 0)")
	public ResponseEntity<Integer> isTemPost(String email) {
		return new ResponseEntity<Integer>(temRepository.isTemPost(email), HttpStatus.OK);
	}
	
	@DeleteMapping
	@ApiOperation("임시게시물 삭제")
	public ResponseEntity<String> delTemPost(String email) {
		temRepository.deleteById(email);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@GetMapping
	@ApiOperation("임시 게시물 가져오기")
	public ResponseEntity<TemPostResponse> getTemPost(String email) {
		
		TemporaryPost tempost = temRepository.findById(email).get();	// 해당 이메일의 임시포스트
		//해당 데이터가 없을 때, 예외처리
		System.out.println(tempost);
		
		TemPostResponse response = new TemPostResponse(tempost.getWriter(), tempost.getTitle(), tempost.getContent(), tempost.getPicture(), tempost.getSummary());
		
		StringTokenizer st = new StringTokenizer(tempost.getTag(), "++");	// 태그 자르기
		List<String> tag = new ArrayList<>();
		while(st.hasMoreTokens()) tag.add(st.nextToken());
		System.out.println(tag);
		
		response.setTag(tag);
		
		return new ResponseEntity<TemPostResponse>(response, HttpStatus.OK);
	}

}
