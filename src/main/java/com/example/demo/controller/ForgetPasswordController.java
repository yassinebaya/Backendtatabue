package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.ChangePasswordRequestDTO;
import com.example.demo.service.AccoubtService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin("*")
public class ForgetPasswordController {
 @org.springframework.context.annotation.Lazy
 @Autowired
 AccoubtService accoubtService;

     @PostMapping("/forgotPassword")
    public ResponseEntity<Map<String, String>> forgotPassword(@RequestParam String email){
        try {
            this.accoubtService.envoyerEmailToken(email);

            return ResponseEntity.ok(Map.of("message","The activation code has been sent to "+email));
       
        }catch (Exception e) {

            return new ResponseEntity<>(Map.of("errorMessage",e.getMessage()),HttpStatus.NOT_FOUND);
        
        }
    }

    @PostMapping("/ChangerMotDePasse")
    public ResponseEntity<Map<String, String>> ChangerModepass(@RequestBody ChangePasswordRequestDTO request){

        try {
          
            System.out.println(request);
            this.accoubtService.changePassword(request,request.Token());

            return ResponseEntity.ok(Map.of("message","The Password has been changed to "));
       
        }catch (Exception e) {

            return new ResponseEntity<>(Map.of("errorMessage",e.getMessage()),HttpStatus.NOT_FOUND);
        
        }
    }


    
}
