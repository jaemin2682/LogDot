package com.papple.blog.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "config")
public class BlogConfig {
	@Id
	private String email;
	
	private String name;
	
	@Size(max = 10000)
	private String description;
	
	private String picture;
}
