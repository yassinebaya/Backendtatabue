package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Assistant;
import com.example.demo.entites.Subject;
import com.example.demo.repo.AppUserRepository;
import com.example.demo.service.DoctorService;

@RestController
@CrossOrigin("*")
public class DoctorController {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private DoctorService doctorService;
    @PostMapping("/doctors")
	public Subject createSubject(Subject subject,int assistantId1,Long categorieId1,Long projetId1,Long phaseId1,Long documentType1) {
        subject.setAssistantId(appUserRepository.findByAssistant(assistantId1));
       return  doctorService.createSubject(subject);
	}
     
}
