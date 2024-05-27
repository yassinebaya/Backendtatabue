package com.example.demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entites.CommentairesAssistantStagiaire;
public interface CommentaireASRepository extends JpaRepository<CommentairesAssistantStagiaire,Long> {

 //CommentairesAssistantStagiaire findByCommentaires(Long id);

    
}
