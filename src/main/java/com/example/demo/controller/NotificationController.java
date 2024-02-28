package com.example.demo.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Notifications;
import com.example.demo.repo.NotificationRepo;

@RestController
@CrossOrigin("*")
public class NotificationController {
    @Autowired
    NotificationRepo notificationRepo;

     @GetMapping("/notifications")
    public List<Notifications> notificationAll(){
      List<Notifications> projets=notificationRepo.findAll();
      return projets;
    }
    
}
