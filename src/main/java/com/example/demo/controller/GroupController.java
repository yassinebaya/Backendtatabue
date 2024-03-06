package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Groupe;
import com.example.demo.repo.GroupeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin("*")
public class GroupController {
    @Autowired
    GroupeRepository groupeRepository;

    @GetMapping("/AllGroupe")
    public List<Groupe> getMethodName() {
    List<Groupe> groupe=groupeRepository.findAll();
        return groupe;
    }
     
    @GetMapping("/searchGroupes")
    public Page<Groupe> searchGroupes(@RequestParam String nom,@RequestParam int page,@RequestParam int size) {
        Pageable pageable=PageRequest.of(page,size);
        Page<Groupe> pageGroupe=groupeRepository.findByNomLike("%"+nom+"%",pageable);
        return pageGroupe;
    }
    
    
}
