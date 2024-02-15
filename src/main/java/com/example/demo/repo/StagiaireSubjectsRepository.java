package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.Stagaire;
import com.example.demo.entites.StagiaireSujects;
import com.example.demo.entites.Subject;

public interface StagiaireSubjectsRepository extends JpaRepository<StagiaireSujects,Long> {

  @Query("SELECT a FROM StagiaireSujects a WHERE a.stagaire= ?1 ")
   StagiaireSujects findByStagiaireSujects(Stagaire stagaire);

   @Query("SELECT a FROM StagiaireSujects a WHERE a.stagaire= ?1 and a.subject= ?2")
   StagiaireSujects findByoneStagiaireSujects(Stagaire stagaire,Subject subject);
    
}
