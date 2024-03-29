package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.Stagairequsetion;
import com.example.demo.repo.StagaireQuestionRepo;

@RestController
@CrossOrigin("*")
public class StagaireQuestionController {
    @Autowired
    StagaireQuestionRepo stagaireQuestionRepo;
    @PutMapping("/stagiaireQuestions/{id}")
     @PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant')")
	public ResponseEntity<Stagairequsetion> updateStagiaireQuestion(@PathVariable long id, Stagairequsetion stagairequsetiondetail){
		Stagairequsetion stagairequsetion = stagaireQuestionRepo.findByIdStagairequsetion(id);
		if (stagairequsetion==null) throw new RuntimeException("question not exist with id :" + id);

        stagairequsetion.setStagaire(stagairequsetiondetail.getStagaire());
        stagairequsetion.setAnswer(stagairequsetiondetail.getAnswer());
        stagairequsetion.setQuestion(stagairequsetion.getQuestion());
        Stagairequsetion updatedsubject = stagaireQuestionRepo.save(stagairequsetion);
		return ResponseEntity.ok(updatedsubject);
	}

@GetMapping("/stagiaireQuestions")
@PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant','SCOP_stagiaire')")
 public Stagairequsetion getQuestionStagaire(@RequestParam Stagaire stagaire){
    Stagairequsetion stagairequsetion=stagaireQuestionRepo.findByStagaire(stagaire);
  return stagairequsetion;

 }
@PostMapping("/stagiaireQuestions")
@PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant','SCOP_stagiaire')")
    public Stagairequsetion ajouterCommentaireStagiaireSubject(Stagairequsetion stagairequsetion) {
        return stagaireQuestionRepo.save(stagairequsetion);
    }

}
