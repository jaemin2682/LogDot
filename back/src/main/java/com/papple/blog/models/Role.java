package com.papple.blog.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	public Role() {

	}

	public Role(ERole name) {
		this.name = name;
	}

}