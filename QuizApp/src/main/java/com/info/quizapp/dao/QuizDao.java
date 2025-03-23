package com.info.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.info.quizapp.entity.Quiz;



public interface QuizDao extends JpaRepository<Quiz, Integer> {

}