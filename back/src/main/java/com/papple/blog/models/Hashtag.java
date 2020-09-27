package com.papple.blog.models;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Table(name = "hashtag")
public class Hashtag {
	@EmbeddedId
	private HashtagPK hashtagPK;
}
