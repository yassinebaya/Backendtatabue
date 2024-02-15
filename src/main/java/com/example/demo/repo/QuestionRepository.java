package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.Question;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query("SELECT a FROM Question a WHERE a.id= ?1 ")
     Question findByQuestion(Long id);
}
