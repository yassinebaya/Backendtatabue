package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Assistant;
import com.example.demo.entites.Question;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.StagiaireSujects;
import com.example.demo.entites.Subject;
import com.example.demo.repo.AppUserRepository;
import com.example.demo.repo.DocumentypeRepository;
import com.example.demo.repo.ProjetphaseRepository;
import com.example.demo.repo.ProjetsRepository;
import com.example.demo.repo.SubjectCategorieReposirory;
import com.example.demo.repo.SubjectRepo;
import com.example.demo.service.DoctorService;
import com.lowagie.text.Document;

@RestController
@CrossOrigin("*")
public class DoctorController {
    @Autowired
    SubjectRepo subjectRepo;
    @Autowired
    private DoctorService doctorService;
    @PostMapping("/subjects")
    public Subject createSubject(Subject subject) {
        subject.setAssistantId(subject.getAssistantId());
        subject.setCategorieId(subject.getCategorieId());
        subject.setDocumentType(subject.getDocumentType());
        subject.setProjetId(subject.getProjetId());
        subject.setPhaseId(subject.getPhaseId());
       return  doctorService.createSubject(subject);
    }
 @PutMapping("/subjects/{id}")
	public ResponseEntity<Subject> updateSubjects(@PathVariable long id, Subject subjectdetail){
		Subject subject = subjectRepo.findBySubject(id);
		if (subject==null) throw new RuntimeException("question not exist with id :" + id);
       subject.setId(subjectdetail.getId());
       subject.setName(subjectdetail.getName());
       subject.setDescription(subjectdetail.getDescription());
       subject.setDateDebut(subjectdetail.getDateDebut());
       subject.setDateDernierDelais(subjectdetail.getDateDernierDelais());
       subject.setCommentaire(subjectdetail.getCommentaire());
       subject.setModeleVide(subjectdetail.getModeleVide());
       subject.setAssistantId(subjectdetail.getAssistantId());
       subject.setEtatPublication(subjectdetail.isEtatPublication());
       subject.setLabelLien(subjectdetail.getLabelLien());
       subject.setTitreLien(subjectdetail.getTitreLien());
       subject.setLangue(subjectdetail.getLangue());
       subject.setUsed(subjectdetail.isUsed());
       subject.setCategorieId(subjectdetail.getCategorieId());
       subject.setProjetId(subjectdetail.getProjetId());
       subject.setPhaseId(subjectdetail.getPhaseId());
       subject.setDocumentType(subjectdetail.getDocumentType());
     Subject updatedsubject = subjectRepo.save(subject);
		return ResponseEntity.ok(updatedsubject);
	}
@GetMapping("/subjects")
public Subject getStagiaireSubjects(@RequestParam Assistant assistantId){
Subject subject=subjectRepo.findByAssistantSubjects(assistantId);
  return subject;
 }
 @GetMapping("/subjects/{id}")
 public Subject getSubjects(@PathVariable long id){
 Subject subject=subjectRepo.findBySubject(id);
   return subject;
  }
}
     

