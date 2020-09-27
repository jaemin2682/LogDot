package com.papple.blog.repository;

import com.papple.blog.models.unNotification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface unNotificationRepository extends JpaRepository<unNotification, Long>{
    unNotification findByTargetuserAndActionuser(String targetuser, String actionuser);
}