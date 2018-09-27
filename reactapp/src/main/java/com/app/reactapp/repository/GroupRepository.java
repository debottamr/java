package com.app.reactapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.reactapp.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
	
    Group findByName(String name);
    
    List<Group> findAllByUserId(String id);

}