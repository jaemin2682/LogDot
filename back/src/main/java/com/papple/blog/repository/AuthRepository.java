package com.papple.blog.repository;

import com.papple.blog.models.UserAuth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<UserAuth, String>{
    
}