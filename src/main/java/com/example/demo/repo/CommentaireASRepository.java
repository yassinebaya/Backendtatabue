package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.Assistant;
import com.example.demo.entites.CommentairesAssistantStagiaire;
import com.example.demo.entites.Stagaire;
import java.util.List;


public interface CommentaireASRepository extends JpaRepository<CommentairesAssistantStagiaire,Long> {
 @Query("SELECT a FROM CommentairesAssistantStagiaire a WHERE a.assistant= ?1 and a.stagaire= ?2")
 CommentairesAssistantStagiaire findByCommentairesAssistantStagiaire(Assistant assistant,Stagaire stagaire);
 @Query("SELECT a FROM CommentairesAssistantStagiaire a WHERE a.id= ?1 ")
 CommentairesAssistantStagiaire findByCommentaires(Long id);

    
}
