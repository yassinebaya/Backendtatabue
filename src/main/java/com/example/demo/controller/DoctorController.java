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
import com.example.demo.dtos.SubjectDTO;
import com.example.demo.entites.Assistant;
import com.example.demo.entites.Projets;
import com.example.demo.entites.Subject;
import com.example.demo.mappers.MaperSubject;
import com.example.demo.repo.SubjectRepo;
import com.example.demo.service.DoctorService;


@RestController
@CrossOrigin("*")
public class DoctorController {
    @Autowired
    MaperSubject maperSubject;
    @Autowired
    SubjectRepo subjectRepo;
    @Autowired
    private DoctorService doctorService;
    @PostMapping("/subjects")
    @PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant')")
    public Subject createSubject(Subject subject) {
        subject.setAssistantId(subject.getAssistantId());
        subject.setCategorieId(subject.getCategorieId());
        subject.setDocumentType(subject.getDocumentType());
        subject.setProjets(subject.getProjets());
        subject.setPhaseId(subject.getPhaseId());
       return  doctorService.createSubject(subject);
    }
 @PutMapping("/subjects/{id}")
 @PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant')")
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
       subject.setProjets(subjectdetail.getProjets());
       subject.setPhaseId(subjectdetail.getPhaseId());
       subject.setDocumentType(subjectdetail.getDocumentType());
     Subject updatedsubject = subjectRepo.save(subject);
		return ResponseEntity.ok(updatedsubject);
	}
@GetMapping("/subjects")
@PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant','SCOP_stagiaire')")
public Subject getStagiaireSubjects(@RequestParam Assistant assistantId){
Subject subject=subjectRepo.findByAssistantSubjects(assistantId);
  return subject;
 }
 @GetMapping("/subjectsbyid")
 @PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant','SCOP_stagiaire')")
 public SubjectDTO getSubjects(@RequestParam Subject subject){
   SubjectDTO subjectdto=maperSubject.fromsubject(subject);
   return subjectdto;
  }

  @GetMapping("/subjectsbyProjet")
  @PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant','SCOP_stagiaire')")
  public List<Subject> getSubjectsbyProjet(@RequestParam Projets projets){
   List<Subject> subject=subjectRepo.findByProjets(projets);
     return subject;
   }

  @GetMapping("/allsubjects")
  @PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant','SCOP_stagiaire')")
  public List<Subject> allSubjects(){
    List<Subject> subject=subjectRepo.findAll();
      return subject;
     }
     	
 @DeleteMapping("/subjects/{id}")
 @PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant')")
	public ResponseEntity<Map<String, Boolean>> deletesubject(@PathVariable long id){
        Subject subject = subjectRepo.findBySubject(id);
        if (subject==null) throw new RuntimeException("subject not exist with id :" + id);
		  subjectRepo.delete(subject);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}


}
     

