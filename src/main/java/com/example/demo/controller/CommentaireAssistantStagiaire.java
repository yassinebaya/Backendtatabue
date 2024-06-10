package com.example.demo.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


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
