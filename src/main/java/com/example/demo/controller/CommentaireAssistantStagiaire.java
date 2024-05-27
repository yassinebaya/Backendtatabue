package com.example.demo.controller;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entites.CommentairesAssistantStagiaire;
import com.example.demo.repo.CommentaireASRepository;

@RestController
@CrossOrigin("*")
public class CommentaireAssistantStagiaire {
 /*  @Autowired
CommentaireASRepository commentaireASRepository;

 @DeleteMapping("/CommentaireAssistantStagiaire/{id}")
 @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN')")
	public ResponseEntity<Map<String, Boolean>> deleteCommentairesAssistantStagiaire(@PathVariable Long id){
	CommentairesAssistantStagiaire commentairesAssistantStagiaire = commentaireASRepository.findByCommentaires(id);
        if (commentairesAssistantStagiaire==null) throw new RuntimeException("Assistant not exist with id :" + id);
        commentaireASRepository.delete(commentairesAssistantStagiaire);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}


	 @PostMapping("/CommentaireAssistantStagiaire")
     @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN')")
    public CommentairesAssistantStagiaire ajouterCommentaireStagiaireSubject(CommentairesAssistantStagiaire commentaireAS) {
        return commentaireASRepository.save(commentaireAS);
    }

*/



}
