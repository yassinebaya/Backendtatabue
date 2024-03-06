package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Assistant;
import com.example.demo.entites.CommentairesAssistantStagiaire;

import com.example.demo.entites.CommentairesAssistantStagiaireSubject;

import com.example.demo.entites.Stagaire;

import com.example.demo.repo.CommentaireASRepository;

@RestController
@CrossOrigin("*")
public class CommentaireAssistantStagiaire {
    @Autowired
CommentaireASRepository commentaireASRepository;
 @GetMapping("/CommentaireAssistantStagiaire")
public CommentairesAssistantStagiaire searchCommentairesAssistantStagiaire(@RequestParam Assistant assistant,@RequestParam Stagaire stagaire){
CommentairesAssistantStagiaire commentaireAssistantStagiaire=commentaireASRepository.findByCommentairesAssistantStagiaire(assistant,stagaire);
  return commentaireAssistantStagiaire;

 }
 @DeleteMapping("/CommentaireAssistantStagiaire/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCommentairesAssistantStagiaire(@PathVariable Long id){
	CommentairesAssistantStagiaire commentairesAssistantStagiaire = commentaireASRepository.findByCommentaires(id);
  System.out.println(commentairesAssistantStagiaire);
        if (commentairesAssistantStagiaire==null) throw new RuntimeException("Assistant not exist with id :" + id);
        commentaireASRepository.delete(commentairesAssistantStagiaire);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}


	 @PostMapping("/CommentaireAssistantStagiaire")
    public CommentairesAssistantStagiaire ajouterCommentaireStagiaireSubject(CommentairesAssistantStagiaire commentaireAS) {
        return commentaireASRepository.save(commentaireAS);
    }





}
