package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Projetphase;


import com.example.demo.repo.ProjetphaseRepository;

@RestController
@CrossOrigin("*")
public class PhaseProjets {
@Autowired
ProjetphaseRepository projetphaseRepository;

     @GetMapping("/phaseProjet")

    public List<Projetphase> projetsAll(){
      List<Projetphase> phasesprojets=projetphaseRepository.findAll();
      return phasesprojets;
    }
    
}
