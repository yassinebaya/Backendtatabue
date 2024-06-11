package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dtos.ChangePasswordRequestDTO;
import com.example.demo.entites.AppUser;
import com.example.demo.repo.AppUserRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin("*")
public class UserController {
@Autowired
AppUserRepository appUserRepository;

@Autowired
PasswordEncoder passwordEncoder;
    
@PutMapping("/updatepassword/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT','SCOPE_STAGIAIRE')")
	public ResponseEntity<AppUser> updatepassword(@PathVariable Long id,@RequestBody ChangePasswordRequestDTO request){
        AppUser appuser=appUserRepository.findByIduser(id);
       
        if (!passwordEncoder.matches(request.currentPassword(),appuser.getPassword()))
              throw new RuntimeException("The current password is incorrect");
          if(!request.newPassword().equals(request.confirmPassword())){
              throw new RuntimeException("Confirmed password not match");
          }
          appuser.setPassword(passwordEncoder.encode(request.newPassword()));
          appUserRepository.save(appuser);
		return ResponseEntity.ok(appuser);
	}

}
