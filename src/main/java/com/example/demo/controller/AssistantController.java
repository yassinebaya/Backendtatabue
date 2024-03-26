package com.example.demo.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entites.Assistant;
import com.example.demo.repo.AppUserRepository;
import com.example.demo.service.AccoubtService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin("*")

public class AssistantController {
@Autowired
private AppUserRepository appUserRepository;
@Lazy
@Autowired
private AccoubtService accoubtService;
@Lazy
 @Autowired
private PasswordEncoder passwordEncoder;

@GetMapping("/assistants")
@PreAuthorize("hasAnyAuthority('SCOPE_assistant','SCOPE_admin')")
 public Page<Assistant> assistants(@RequestParam String keyword,@RequestParam int page,@RequestParam int size){
    Pageable pageable = PageRequest.of(page,size);
    Page<Assistant> lAssistants=appUserRepository.findByUsernameLike("%"+keyword+"%",pageable);
     return lAssistants;
    }

@PostMapping("/assistants")
@PreAuthorize("hasAnyAuthority('SCOPE_assistant','SCOPE_admin')")
	public Assistant createAssistant(Assistant assistant) {
       return accoubtService.createAssistant(assistant);
	}
	
 @DeleteMapping("/assistants/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_admin')")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Assistant assistant = appUserRepository.findByAssistant(id);
        if (assistant==null) throw new RuntimeException("Assistant not exist with id :" + id);
		appUserRepository.delete(assistant);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

 @GetMapping("assistants/{id}")
 @PreAuthorize("hasAnyAuthority('SCOPE_assistant','SCOPE_admin','SCOPE_stagiaire')")
public Assistant getaAssistant(@PathVariable Long id){
Assistant Assistant=appUserRepository.findByAssistant(id);
  return Assistant;

 }
@PutMapping("/assistants/{id}")
@PreAuthorize("hasAnyAuthority('SCOPE_admin','SCOPE_assistant')")
	public ResponseEntity<Assistant> updateEmployee(@PathVariable Long id, Assistant assistantdetail){
		Assistant assistant = appUserRepository.findByAssistant(id);
		if (assistant==null) throw new RuntimeException("Assistant not exist with id :" + id);
		assistant.setUsername(assistantdetail.getUsername());
		assistant.setPassword(passwordEncoder.encode(assistantdetail.getPassword()));
		assistant.setEmail(assistantdetail.getEmail());
		assistant.setNom(assistantdetail.getNom());
		assistant.setTel(assistantdetail.getTel());
		 Assistant updatedaAssistant = appUserRepository.save(assistant);
		return ResponseEntity.ok(updatedaAssistant);
	}

}
