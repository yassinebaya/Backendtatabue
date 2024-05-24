package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.AppUser;
import com.example.demo.exeception.UserAlreadyExistsException;
import com.example.demo.repo.AppUserRepository;

@RestController
@CrossOrigin("*")
public class UserController {
@Autowired
AppUserRepository appUserRepository;

@Autowired
PasswordEncoder passwordEncoder;
    
@PutMapping("/updatepassword/{id}")
//@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT')")
	public ResponseEntity<AppUser> updatepassword(@PathVariable Long id,@RequestParam String ancienpassword,@RequestParam String password){
        System.out.println(passwordEncoder.encode(ancienpassword));
        AppUser appuser=appUserRepository.findByIduser(id);
        System.out.println(appuser.getPassword());
       
         if (appuser==null) throw new UserAlreadyExistsException("password ancien incorrect");
            appuser.setPassword(passwordEncoder.encode(password));
		AppUser appUser2 =appUserRepository.save(appuser);
		return ResponseEntity.ok(appUser2);
	}

}
