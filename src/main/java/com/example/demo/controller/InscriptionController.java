package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Groupe;
import com.example.demo.entites.Inscriptions;
import com.example.demo.entites.Stagaire;

import com.example.demo.entites.Stagairequsetion;

import com.example.demo.repo.InscriptionRepository;

@RestController
@CrossOrigin("*")
public class InscriptionController  {
        @Autowired
InscriptionRepository inscriptionRepository;

@GetMapping("/inscriptionskywordbygroube")
  public Page<Inscriptions> getStagiareKyword(@RequestParam Groupe groupe,@RequestParam String numerodossier,@RequestParam int page,@RequestParam int size){
  
     Pageable pageable = PageRequest.of(page,size);
     Page<Inscriptions> inscription=inscriptionRepository.findByInscriptionsbyGroupe(groupe,numerodossier,pageable);
     return inscription;
   
    }
    @GetMapping("/inscriptionkyword")
    public Page<Inscriptions> getStagiareKyword(@RequestParam String numerodossier,@RequestParam int page,@RequestParam int size){
      
       Pageable pageable = PageRequest.of(page,size);
       Page<Inscriptions> inscription=inscriptionRepository.findByInscriptionsbykeyword(numerodossier,pageable);
                     return inscription;

      }
        @PostMapping("/inscriptions")
    public Inscriptions saveInscriptions(Inscriptions inscriptions) {
        return inscriptionRepository.save(inscriptions);
    }
    
@PutMapping("/inscriptionGroupe/{idInscription}")
	public ResponseEntity<Inscriptions> updateinscriptiongroupe(@PathVariable long idInscription,Inscriptions inscriptionsdetaille){
	  Inscriptions inscriptions =inscriptionRepository.findByInscription(idInscription);
		if (inscriptions==null) throw new RuntimeException("question not exist with id :" +  idInscription);
    inscriptions.setGroupe(inscriptionsdetaille.getGroupe());
    Inscriptions updatedsInscriptions = inscriptionRepository.save(inscriptions);
		return ResponseEntity.ok(updatedsInscriptions);
	}
     
      }

    

