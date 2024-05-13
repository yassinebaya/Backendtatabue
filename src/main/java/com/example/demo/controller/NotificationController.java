package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.entites.Notifications;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.StagiaireSujects;
import com.example.demo.repo.NotificationRepo;

@RestController
@CrossOrigin("*")
public class NotificationController {
    @Autowired
    NotificationRepo notificationRepo;

     @GetMapping("/notifications")
     @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN','SCOPE_STAGIAIRE')")
    public List<Notifications> notificationAll(){
      List<Notifications> projets=notificationRepo.findAll();
      return projets;
    }

    @GetMapping("/notificationById/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN','SCOPE_STAGIAIRE')")
   public Notifications notificationById(@PathVariable long id){
   Notifications notification=notificationRepo.findByNotifications(id);
     return notification;
   }


   @PostMapping("/notifications")
   @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN')")
	public Notifications SaveNotification(Notifications notifications) {
       return notificationRepo.save(notifications);
	}
	
  
    @PutMapping("/notifications/{id}")
	  @PreAuthorize("hasAuthority('SCOPE_ADMIN','SCOP_ASSISTANT')")
	public ResponseEntity<Notifications> updateNotification(@PathVariable long id, Notifications detaillNotifications){
		Notifications notification = notificationRepo.findByNotifications(id);
		if (notification==null) throw new RuntimeException("question not exist with id :" + id);
    notification.setAssistant(detaillNotifications.getAssistant());
    notification.setMessage(detaillNotifications.getMessage());
    notification.setStatut(detaillNotifications.isStatut());
    notification.setType(detaillNotifications.getType());
     Notifications updatedNotification = notificationRepo.save(notification);
		return ResponseEntity.ok(updatedNotification);
	}

   @DeleteMapping("/notifications/{id}")
   @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN')")
	public ResponseEntity<Map<String, Boolean>> deleteNotifications(@PathVariable long id){
    Notifications notification = notificationRepo.findByNotifications(id);
		if (notification==null) throw new RuntimeException("question not exist with id :" + id);
         notificationRepo.delete(notification);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}


    
}
