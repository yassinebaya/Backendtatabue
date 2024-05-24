package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entites.SubjectCategorie;
import com.example.demo.repo.SubjectCategorieRepo;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@CrossOrigin("*")
public class SubjectCategorieController {
    @Autowired
    SubjectCategorieRepo subjectCategorieRepo;
@GetMapping("/subjectCategorie")
 @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN','SCOPE_STAGIAIRE')")
public List<SubjectCategorie> subjectCategorieAll(){
       List<SubjectCategorie> stagiaireSujects=subjectCategorieRepo.subjectCategorieAll();
  return stagiaireSujects;

 }

 @GetMapping("/subjectCategorie/{id}")
 @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN','SCOPE_STAGIAIRE')")
public SubjectCategorie getCategoriesByCategorie(@PathVariable Long id){
    SubjectCategorie subjectCategorie =subjectCategorieRepo.findBySubjectCategorie(id);
  return subjectCategorie;

 }

@PutMapping("/subjectCategorie/{id}")
@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT','SCOPE_STAGIAIRE')")
	public ResponseEntity<SubjectCategorie> updatecategorie(@PathVariable Long id,SubjectCategorie categoriedetail){
		SubjectCategorie subjectCategorie =subjectCategorieRepo.findBySubjectCategorie(id);

		if (subjectCategorie==null) throw new RuntimeException("Categorie not exist with id :" + id);
      
		subjectCategorie.setSubjects(categoriedetail.getSubjects());
        subjectCategorie.setTitle(categoriedetail.getTitle());
        subjectCategorie.setUsed(categoriedetail.getUsed());
		 SubjectCategorie updatesSubjectCategorie =subjectCategorieRepo.save(subjectCategorie);
		return ResponseEntity.ok(updatesSubjectCategorie);
	}


    @PostMapping("/subjectCategorie/{id}")
   @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT','SCOPE_STAGIAIRE')")
        public ResponseEntity<SubjectCategorie> saveCategorie(SubjectCategorie categoriedetail){
            SubjectCategorie subjectCategorie =new SubjectCategorie();
            subjectCategorie.setSubjects(categoriedetail.getSubjects());
            subjectCategorie.setTitle(categoriedetail.getTitle());
            subjectCategorie.setUsed(categoriedetail.getUsed());
             SubjectCategorie updatesSubjectCategorie =subjectCategorieRepo.save(subjectCategorie);
            return ResponseEntity.ok(updatesSubjectCategorie);
        }
    

}
