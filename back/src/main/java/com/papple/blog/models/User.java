package com.papple.blog.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(	name = "user", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "email"), 
	@UniqueConstraint(columnNames = "nickname")
})
@Entity
@Data
public class User {
	@Id
	private String email;

	private String password;
	
	private String nickname;

	private String profile;
	
	private Long score;
	
	private Integer usercertification;

	private String notification;
	
	private LocalDateTime recentscoredate;
	
	private Long todayscore;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
		this.todayscore = 0l;
	}

	public User(String email, String nickname, String password, Integer usercertification, Long score) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.todayscore = 0l;
		this.score = score;
		this.usercertification = usercertification;
		this.notification = "111111";
		this.profile = "http://i3a604.p.ssafy.io/images/profile/basic.svg";
	}

	@Builder
    public User(String nickname, String email, String profile, Set<Role> roles) {
        this.nickname = nickname;
        this.email = email;
        this.profile = profile;
        this.roles = roles;
    }
    public User update(String nickname, String profile) {
        this.nickname = nickname;
        this.profile = profile;
        return this;
    }
    public String getRoleKey() {
        return "ROLE_USER";
    }
}
