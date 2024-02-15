package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.SubjectCategorie;
import java.util.List;
import java.util.Optional;


public interface SubjectCategorieReposirory extends JpaRepository<SubjectCategorie,Long>  {
    @Query("SELECT a FROM SubjectCategorie a WHERE a.id= ?1 ")
     SubjectCategorie findBySubjectCategorie(Long id);

    
    
}
