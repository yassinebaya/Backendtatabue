package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.DocumentType;
import com.example.demo.repo.DocumentypeRepository;

@RestController
@CrossOrigin("*")
public class DocumentTyprController {
    @Autowired
DocumentypeRepository documentypeRepository;
  @GetMapping("/documentType")
  @PreAuthorize("hasAnyAuthority('SCOPE_admin','SCOPE_assistant')")
    public List<DocumentType> projetsAll(){
      List<DocumentType> documentType=documentypeRepository.findAll();
      return documentType;
    }
      
}
