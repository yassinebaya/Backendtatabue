package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.AppUser;
import com.example.demo.entites.Assistant;
import com.example.demo.repo.AppUserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@CrossOrigin("*")
public class AssistantController {
@Autowired
AppUserRepository appUserRepository;
@GetMapping("/assistants")
 public Page<Assistant> assistants(@RequestParam String keyword,@RequestParam int page,@RequestParam int size){
    Pageable pageable = PageRequest.of(page,size);
    Page<Assistant> lAssistants=appUserRepository.findByUsernameLike("%"+keyword+"%",pageable);
    

    System.out.println(lAssistants);
     return lAssistants;
    }
    
}
