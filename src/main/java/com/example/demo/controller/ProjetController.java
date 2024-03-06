package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entites.Projets;
import com.example.demo.repo.ProjetsRepository;

@RestController
@CrossOrigin("*")
public class ProjetController {

    @Autowired
    ProjetsRepository projetsRepository;
    @GetMapping("/projets/{id}")
 public Projets getprojets(@PathVariable long id){
    Projets projets=projetsRepository.findByProjets(id);
   return projets;
  }
  @GetMapping("/projets")
    public List<Projets> projetsAll(){
      List<Projets> projets=projetsRepository.findAll();
      return projets;
    }
}