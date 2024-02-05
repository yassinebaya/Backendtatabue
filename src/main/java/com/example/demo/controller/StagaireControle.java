package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Stagaire;
import com.example.demo.repo.AppUserRepository;
@RestController
@CrossOrigin("*")
public class StagaireControle {
@Autowired
 AppUserRepository appUserRepository;
@GetMapping("getStudent/{id}")
@PreAuthorize("hasAuthority('SCOPE_STAGIAIRE')")
 public Stagaire getStudent(@PathVariable int id){
    System.out.println(id);
Stagaire stagaire=appUserRepository.findByStagaire(id);
  return stagaire;

 }















}
