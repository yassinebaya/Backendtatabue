package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.Assistant;

import com.example.demo.entites.CommentairesAssistantStagiaireSubject;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.Subject;
import java.util.List;


public interface CommentaireASSRepository extends JpaRepository<CommentairesAssistantStagiaireSubject,Long> {
    @Query("SELECT a FROM CommentairesAssistantStagiaireSubject a WHERE a.assistant= ?1 and a.subject= ?2 and a.stagaire= ?3 ")
     CommentairesAssistantStagiaireSubject commentairesAssistantStagiaireSubject(Assistant assistant,Subject subject,Stagaire stagaire);

     @Query("SELECT a FROM CommentairesAssistantStagiaireSubject a WHERE a.id= ?1 ")
     CommentairesAssistantStagiaireSubject findByIdCommentaire(Long id);
    
}
