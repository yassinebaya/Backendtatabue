package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.Question;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.Stagairequsetion;



public interface StagaireQuestionRepo extends JpaRepository<Stagairequsetion,Long> {
    @Query("SELECT a FROM Stagairequsetion a WHERE a.id= ?1 ")
    Stagairequsetion findByIdStagairequsetion(Long id);

    @Query("SELECT a FROM Stagairequsetion a WHERE a.question= ?1 and a.stagaire= ?2 ")
    Stagairequsetion findByStagairequsetion(Question question,Stagaire stagaire);
    @Query("SELECT a FROM Stagairequsetion a WHERE a.stagaire= ?1 ")
    List<Stagairequsetion> findByStagaire(Stagaire stagaire);
    @Query("SELECT a FROM Stagairequsetion a ")
    List<Stagairequsetion> getAllStagiairesQuestions();
    
}
