package com.app.reactapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.reactapp.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
}
