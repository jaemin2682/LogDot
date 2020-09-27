package com.papple.blog.models;

import java.time.LocalDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "good")
public class Good {
   
    @EmbeddedId
    private PKSet goodPK;
    
    @CreationTimestamp
    private LocalDateTime createdate;
    
    public Good(PKSet goodPK){
        this.goodPK = goodPK;
    }
}