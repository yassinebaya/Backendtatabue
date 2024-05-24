package com.example.demo.controller;
import java.util.List;

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

import com.example.demo.dtos.Responce;
import com.example.demo.dtos.StagaireQuestionDTO;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.Stagairequsetion;
import com.example.demo.entites.Subject;
import com.example.demo.repo.StagaireQuestionRepo;
import com.example.demo.service.StagiareQuestionService;


@RestController
@CrossOrigin("*")
public class StagaireQuestionController {
    @Autowired
    StagaireQuestionRepo stagaireQuestionRepo;

   @Autowired
    StagiareQuestionService questionService;
    @PutMapping("/stagiaireQuestions/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT','SCOPE_STAGIAIRE')")
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
@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT','SCOPE_STAGIAIRE')")
 public List<Stagairequsetion> getQuestionStagaire(@RequestParam Stagaire stagaire){
 List<Stagairequsetion> stagairequsetion=stagaireQuestionRepo.findByStagaire(stagaire);
  return stagairequsetion;

 }
@PostMapping("/stagiaireQuestions")
@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT','SCOPE_STAGIAIRE')")
    public Responce ajouterCommentaireStagiaireSubject(@RequestParam Subject subject,@RequestParam Stagaire stagaire) {

         questionService.savestagairequestion(subject,stagaire);
         Responce responce=new Responce("stagaire a ajoute");

        return responce;
    }

@GetMapping("/getallstagairequestions")
@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT','SCOPE_STAGIAIRE')")
 public List<Stagairequsetion> getAllStagiairesQuestionsTable(){
	List<Stagairequsetion> stagairequestion=stagaireQuestionRepo.getAllStagiairesQuestions();
     return  stagairequestion;
    }


    @PostMapping("/savestagairequestion")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT','SCOPE_STAGIAIRE')")
        public Responce ajouterCommentaireStagiaireSubject(@org.springframework.web.bind.annotation.RequestBody List<StagaireQuestionDTO> stagairequsetions) {
             questionService.saveresponce(stagairequsetions);
             Responce responce=new Responce("stagaire a ajoute");
    
            return responce;
        }
    


}
