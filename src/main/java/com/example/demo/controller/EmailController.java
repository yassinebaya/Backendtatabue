package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.services.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@RestController
public class EmailController {
     @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
     @Secured("SCOPE_ASSISTANT")
    public String sendEmail(String to,String subject,String text) {
        emailService.sendSimpleMessage(to,subject,text);
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString("bravo");
            return json;
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "erreur";
        }
       
    
    }
    
}
