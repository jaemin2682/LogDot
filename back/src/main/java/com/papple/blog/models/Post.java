package com.papple.blog.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import lombok.*;
@Entity
@Data
@Table( name = "post")
@AllArgsConstructor
//@NamedQuery(name = "Post.findOneThing", query = "SELECT id, content, good, title, writer FROM post where title= ?1")
//Pageable 이란것이 있음.
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	private String title;
	
	@NotBlank
	@Size(max = 100000)
	private String content;
	
	@Size(max = 10000)
	private String summary;
	
	private String picture;
//	@ManyToOne(targetEntity = User.class)
//	@JoinColumn(name="email")
	@Size(max = 50)
	private String writer;
	
	private int good;		// 좋아요

	private int views;		// 조회수

	@CreationTimestamp
    private LocalDateTime createdate;

	public Post() {
		this.good = 0;
		this.views = 0;
	}

}
