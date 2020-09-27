package com.papple.blog.models;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Table(	name = "history")
public class History {
   
    @EmbeddedId
    private PKSet historyPK;

    @CreationTimestamp
    private LocalDateTime createdate;

    public History(PKSet historyPK){
        this.historyPK = historyPK;
    }
    
}