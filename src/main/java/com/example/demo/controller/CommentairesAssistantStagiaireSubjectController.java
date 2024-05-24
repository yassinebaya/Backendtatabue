package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Assistant;

import com.example.demo.entites.CommentairesAssistantStagiaireSubject;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.Subject;
import com.example.demo.repo.CommentaireASSRepository;

@RestController
@CrossOrigin("*")
public class CommentairesAssistantStagiaireSubjectController {
 @Autowired
 CommentaireASSRepository commentaireASSRepository;
@GetMapping("/commentairesAssistantStagiaireSubject")
 @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT','SCOPE_STAGIAIRE')")
public List<CommentairesAssistantStagiaireSubject> searchCommentairesAssistantStagiaireSubject(@RequestParam Assistant assistant,@RequestParam Subject subject,@RequestParam Stagaire stagaire){
   List<CommentairesAssistantStagiaireSubject> commentaireAssistantStagiairesSubject=commentaireASSRepository.commentairesAssistantStagiaireSubject(assistant,subject,stagaire);
  return commentaireAssistantStagiairesSubject;

 }

  @PostMapping("/commentairesAssistantStagiaireSubject")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT','SCOPE_STAGIAIRE')")
    public CommentairesAssistantStagiaireSubject ajouterCommentaireStagiaireSubject(CommentairesAssistantStagiaireSubject commentaireSS) {
        return commentaireASSRepository.save(commentaireSS);
    }

    @DeleteMapping("/commentairesAssistantStagiaireSubject/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT','SCOPE_STAGIAIRE')")
	public ResponseEntity<Map<String, Boolean>> deleteCommentairesAssistantStagiaireSubject(@PathVariable Long id){
		CommentairesAssistantStagiaireSubject commentairesAssistantStagiaireSubject = commentaireASSRepository.findByIdCommentaire(id);
        if (commentairesAssistantStagiaireSubject==null) throw new RuntimeException("Assistant not exist with id :" + id);
        commentaireASSRepository.delete(commentairesAssistantStagiaireSubject);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}


    
}
