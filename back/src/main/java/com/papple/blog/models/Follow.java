package com.papple.blog.models;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "follow")
public class Follow implements Serializable {
	
	@EmbeddedId
	private FollowPK followPK;
}
