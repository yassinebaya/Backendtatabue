package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Assistant;
import com.example.demo.entites.CommentairesAssistantStagiaire;
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
    
}
