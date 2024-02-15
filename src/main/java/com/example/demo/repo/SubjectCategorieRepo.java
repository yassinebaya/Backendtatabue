package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.SubjectCategorie;


public interface SubjectCategorieRepo extends JpaRepository<SubjectCategorie,Long> {
    @Query("SELECT a FROM SubjectCategorie a ")
    List<SubjectCategorie> subjectCategorieAll();

    
}
