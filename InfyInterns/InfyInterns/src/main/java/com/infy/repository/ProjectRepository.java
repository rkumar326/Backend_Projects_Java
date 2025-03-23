package com.infy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

	// add methods if required

}
