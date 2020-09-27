package com.papple.blog.models;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Table(	name = "lookup")
public class LookUp {
	@EmbeddedId
	private PKSet lookupPK;
}
