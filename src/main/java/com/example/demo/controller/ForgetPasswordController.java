package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repo.AppUserRepository;
import com.example.demo.service.AccoubtService;

@RestController
@CrossOrigin("*")
public class ForgetPasswordController {

 @Autowired
 AccoubtService accoubtService;

     @PostMapping("forgotPassword")
    public ResponseEntity<Map<String, String>> forgotPassword(String email){
        try {
            this.accoubtService.envoyerEmailToken(email);

            return ResponseEntity.ok(Map.of("message","The activation code has been sent to "+email));
       
        }catch (Exception e) {

            return new ResponseEntity<>(Map.of("errorMessage",e.getMessage()),HttpStatus.NOT_FOUND);
        
        }
    }



    
}
