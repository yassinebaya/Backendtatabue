package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entites.SubjectCategorie;
import com.example.demo.repo.SubjectCategorieRepo;


@RestController
@CrossOrigin("*")
public class SubjectCategorieController {
    @Autowired
    SubjectCategorieRepo subjectCategorieRepo;
@GetMapping("/subjectCategorie")
 @PreAuthorize("hasAnyAuthority('SCOPE_assistant','SCOPE_admin')")
public List<SubjectCategorie> subjectCategorieAll(){
       List<SubjectCategorie> stagiaireSujects=subjectCategorieRepo.subjectCategorieAll();
  return stagiaireSujects;

 }


}
