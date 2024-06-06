package com.example.demo.repo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.StagiaireSubjectStatut;
import com.example.demo.entites.Subject;

public interface StagiaireSubjectStatutRepo extends JpaRepository<StagiaireSubjectStatut,Long> {

     @Query("SELECT a FROM StagiaireSubjectStatut a WHERE a.id= ?1 ")
     StagiaireSubjectStatut findByidStagiaireSujectsStatut(Long id);
     
     @Query("SELECT a FROM StagiaireSubjectStatut a WHERE a.idStagiaire= ?1 ")
     List<StagiaireSubjectStatut> findByStagiaireSujectsStatut(Stagaire stagaire);
     @Query("SELECT a FROM StagiaireSubjectStatut a WHERE a.idStagiaire= ?1 and a.idSubject= ?2")
     StagiaireSubjectStatut findByStagiaireSujectsStatutS(Stagaire stagaire,Subject subject);








}
