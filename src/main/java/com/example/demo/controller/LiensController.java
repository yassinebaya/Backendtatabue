package com.example.demo.controller;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.AppUser;
import com.example.demo.entites.Assistant;
import com.example.demo.entites.Liens;
import com.example.demo.entites.Subject;
import com.example.demo.exeception.UserAlreadyExistsException;
import com.example.demo.repo.AppUserRepository;
import com.example.demo.repo.LienRepo;
import com.example.demo.services.DriveService;

@RestController
@CrossOrigin("*")
public class LiensController {
@Autowired
LienRepo lienRepo;
@Autowired
AppUserRepository appUserRepository;
@Autowired
   DriveService service;
    
    @PutMapping("/lien/{id}")
//	@PreAuthorize("hasAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT')")
	public ResponseEntity<Liens> updateLiens(@PathVariable long id, Liens detaillien){
		Liens liens =lienRepo.findBylien(id);
		if (liens==null) throw new RuntimeException("lien not exist with id :" + id);
      liens.setLien(detaillien.getLien());
      liens.setAppUser(detaillien.getAppUser());
      liens.setTitre(detaillien.getTitre());
     Liens updatedlLiens = lienRepo.save(liens);
		return ResponseEntity.ok(updatedlLiens);
	}

@GetMapping("/lien")
 public List<Liens> LesLien(@RequestParam AppUser iduser,@RequestParam Subject idsubject){
//AppUser appuser=appUserRepository.findByIduser(id);

List<Liens> liens=lienRepo.findByUser(iduser,idsubject);
     return liens;

    }

    @DeleteMapping("/lien/{id}")
 // @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
	public ResponseEntity<Map<String, Boolean>> deletelien(@PathVariable Long id) throws GeneralSecurityException, IOException{
		Liens liens =lienRepo.findBylien(id);
		if (liens==null) throw new UserAlreadyExistsException("Assistant not exist with id :" + id);
		lienRepo.delete(liens);
        service.deleteFileFromDrive(liens.getLien());
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}



}
