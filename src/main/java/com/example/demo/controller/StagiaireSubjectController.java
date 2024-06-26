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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.StagiaireSubjectStatutDTO;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.StagiaireSujects;
import com.example.demo.entites.Subject;
import com.example.demo.repo.AppUserRepository;
import com.example.demo.repo.StagiaireSubjectsRepository;
import com.example.demo.service.StagiareQuestionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin("*")
public class StagiaireSubjectController {
@Autowired
StagiaireSubjectsRepository stagiaireSubjectsRepository;
@Autowired
AppUserRepository appUserRepository;
@Autowired
StagiareQuestionService stagiareQuestionService;
 @GetMapping("stagiaireSujects/{stagaire}")
   @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN','SCOPE_STAGIAIRE')")
public List<StagiaireSujects> getStagiaireSubjects(@PathVariable Stagaire stagaire){
List<StagiaireSujects> stagiaireSujects=stagiaireSubjectsRepository.findByListStagiaireSujects(stagaire);
  return stagiaireSujects;

 }
 @GetMapping("/stagiaireSujects")
 @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN','SCOPE_STAGIAIRE')")
 public StagiaireSujects getStagiaireSubjects(@RequestParam Stagaire stagaire,@RequestParam Subject subject){
  StagiaireSujects stagiaireSujects=null;
   stagiaireSujects=stagiaireSubjectsRepository.findByoneStagiaireSujects(stagaire,subject);
    return stagiaireSujects;
  }
 @PutMapping("/stagiaireSujects/{id}")
 @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN','SCOPE_STAGIAIRE')")
  public ResponseEntity<StagiaireSujects> updateStagiaireSubject(@PathVariable long id,StagiaireSujects detail){
		StagiaireSujects stagairesuSujects = stagiaireSubjectsRepository.findByidStagiaireSujects(id);
		if (stagairesuSujects==null) throw new RuntimeException("question not exist with id :" + id);
       stagairesuSujects.setStagaire(detail.getStagaire());
       stagairesuSujects.setSubject(detail.getSubject());
        stagairesuSujects.setSubjectEtape(detail.getSubjectEtape());
       stagairesuSujects.setRemarquesProfesseur(detail.getRemarquesProfesseur());
      stagairesuSujects.setDateEnvoiVerification(detail.getDateEnvoiVerification());   
      StagiaireSujects updatedsubject = stagiaireSubjectsRepository.save(stagairesuSujects);
		return ResponseEntity.ok(updatedsubject);
	}
   @PostMapping("/stagiaireSujects")
   @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN','SCOPE_STAGIAIRE')")
  public StagiaireSujects saveStagiaireSuject(StagiaireSujects detailStagiaireSujects) {
      StagiaireSujects stagairesSujects=stagiaireSubjectsRepository.save(detailStagiaireSujects);
      return stagairesSujects;
  }
   @DeleteMapping("/stagiaireSujects")
   @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN','SCOP_STAGIAIRE')")
	public ResponseEntity<Map<String, Boolean>> deletesubjectStagaire(@RequestParam Stagaire stagaire){
    List<StagiaireSujects> stagairesuSujects = stagiaireSubjectsRepository.findByListStagiaireSujects(stagaire);
		if (stagairesuSujects==null) throw new RuntimeException("question not exist with id :" + stagaire);
    stagiaireSubjectsRepository.deleteAll(stagairesuSujects);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
