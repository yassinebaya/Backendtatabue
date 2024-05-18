package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entites.Liens;
import com.example.demo.repo.LienRepo;

@RestController
@CrossOrigin("*")
public class LiensController {
@Autowired
LienRepo lienRepo;
    
    @PutMapping("/lien/{id}")
//	@PreAuthorize("hasAuthority('SCOPE_ADMIN','SCOP_ASSISTANT')")
	public ResponseEntity<Liens> updateLiens(@PathVariable long id, Liens detaillien){
		Liens liens =lienRepo.findBylien(id);
		if (liens==null) throw new RuntimeException("lien not exist with id :" + id);
      liens.setLien(detaillien.getLien());
      liens.setAssistant(detaillien.getAssistant());
      liens.setTitre(detaillien.getTitre());

  Liens updatedlLiens = lienRepo.save(liens);
		return ResponseEntity.ok(updatedlLiens);
	}

}
