package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Groupe;
import com.example.demo.entites.Indicateurs;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.Subject;
import com.example.demo.repo.AppUserRepository;
import com.example.demo.repo.SubjectRepo;
@RestController
@CrossOrigin("*")
public class StagaireControle {
@Autowired
 AppUserRepository appUserRepository;
 @Autowired
 SubjectRepo subjectRepo;
 
@GetMapping("getStudent/{id}")
@PreAuthorize("hasAuthority('SCOPE_STAGIAIRE')")
 public Stagaire getStudent(@PathVariable Long id){
Stagaire stagaire=appUserRepository.findByStagaire(id);
  return stagaire;


 }

 @GetMapping("stagiares")
 public List<Stagaire> allStagaire(){
  List<Stagaire> stagaire=appUserRepository.allStagaires();
    return stagaire;
  
   }


  @PutMapping("/updateEtat/{id}")
	public ResponseEntity<Subject> updateIndecateures(@PathVariable long id,@RequestBody Subject subjectsdetaille){
	    Subject subject =subjectRepo.findBySubject(id);
		if (subject==null) throw new RuntimeException("question not exist with id :" + id);
    subject.setEtatPublication(subjectsdetaille.isEtatPublication());
    Subject updatedsubject = subjectRepo.save(subject);
		return ResponseEntity.ok(updatedsubject);
	}

  @PutMapping("/stagiares/{idsujet}")
	public ResponseEntity<Stagaire> updatenotifications(@PathVariable long idsujet,@RequestBody Stagaire stagairedetaille){
	  Stagaire stagaire =appUserRepository.findByStagaire(idsujet);
		if (stagaire==null) throw new RuntimeException("question not exist with id :" + idsujet);
    stagaire.setNotification(stagairedetaille.isNotification());
    Stagaire updatedsStagaire = appUserRepository.save(stagaire);
		return ResponseEntity.ok(updatedsStagaire);
	}

  @GetMapping("/stagiareskywordbygroube")
  public Page<Stagaire> getStagiareKyword(@RequestParam Groupe groupe,@RequestParam String numerodossier,@RequestParam int page,@RequestParam int size){
  
     Pageable pageable = PageRequest.of(page,size);
     Page<Stagaire> stagaire=appUserRepository.findByStagairesbyGroupe(groupe,numerodossier,pageable);
     return stagaire;
   
    }
    @GetMapping("/stagiareskyword")
    public Page<Stagaire> getStagiareKyword(@RequestParam String numerodossier,@RequestParam int page,@RequestParam int size){
      
       Pageable pageable = PageRequest.of(page,size);
       Page<Stagaire> stagaire=appUserRepository.findByStagairesbykeyword(numerodossier,pageable);
       return stagaire;
     
      }

}
