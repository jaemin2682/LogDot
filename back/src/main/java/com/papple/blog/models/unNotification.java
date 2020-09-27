package com.papple.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "unnotification")
public class unNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        

    private String targetuser;

    private String actionuser;

    public unNotification(String targetuser, String actionuser){
        this.targetuser = targetuser;
        this.actionuser = actionuser;
    }
}