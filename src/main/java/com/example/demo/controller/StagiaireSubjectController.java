package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.StagiaireSujects;
import com.example.demo.entites.Subject;
import com.example.demo.repo.AppUserRepository;
import com.example.demo.repo.StagiaireSubjectsRepository;

@RestController
@CrossOrigin("*")
public class StagiaireSubjectController {
@Autowired
StagiaireSubjectsRepository stagiaireSubjectsRepository;
@Autowired
AppUserRepository appUserRepository;
 @GetMapping("stagiaireSujects/{stagaire}")
public StagiaireSujects getStagiaireSubjects(@PathVariable Stagaire stagaire){
StagiaireSujects stagiaireSujects=stagiaireSubjectsRepository.findByStagiaireSujects(stagaire);
  return stagiaireSujects;

 }
 @GetMapping("/stagiaireSujects")
 public StagiaireSujects getStagiaireSubjects(@RequestParam Stagaire stagaire,@RequestParam Subject subject){
  StagiaireSujects stagiaireSujects=null;
   stagiaireSujects=stagiaireSubjectsRepository.findByoneStagiaireSujects(stagaire,subject);
    return stagiaireSujects;
  }
 @PutMapping("/stagiaireSujects/{id}")
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

}
