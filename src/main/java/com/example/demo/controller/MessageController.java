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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Groupe;
import com.example.demo.entites.Message;
import com.example.demo.repo.MessageRepository;

@RestController
@CrossOrigin("*")
public class MessageController {
    @Autowired
    MessageRepository messageRepository;

@PostMapping("/message")
@PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN')")
	public Message AddMessage(Message message) {
       return messageRepository.save(message);
	}

  /*   @GetMapping("/message/{id}")
     @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN','SCOPE_STAGIAIRE')")
    public List<Message> messageByGroupe(@RequestParam Groupe groupe){
      List<Message> messages=messageRepository.findByMessageByGroupe(groupe);
      return messages;
    }*/ 


 @DeleteMapping("/message/{id}")
   @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN')")
	public ResponseEntity<Map<String, Boolean>> deleteMessage(@PathVariable long id){
          Message message = messageRepository.findByMessage(id);
		if (message==null) throw new RuntimeException("question not exist with id :" + id);
        messageRepository.delete(message);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
    
}
