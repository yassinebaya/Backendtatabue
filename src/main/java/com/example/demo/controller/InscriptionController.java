package com.example.demo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.example.demo.repo.InscriptionRepository;

@RestController
@CrossOrigin("*")
public class InscriptionController  {
        @Autowired
InscriptionRepository inscriptionRepository;

@GetMapping("/inscriptions")
@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT')")
  public List<Inscriptions> getInscrits(){
   List<Inscriptions> inscription=inscriptionRepository.getinscrit();
     return inscription;
   
    }

    @GetMapping("/inscriptionskywordbygroube1")
    @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN')")
      public Page<Inscriptions> getInscriptionKywordByGroupe(@RequestParam Groupe groupe,@RequestParam String numerodossier,@RequestParam int page,@RequestParam int size){
         Pageable pageable = PageRequest.of(page,size);
         Page<Inscriptions> inscription=inscriptionRepository.findByInscriptionsbyGroupe(groupe,numerodossier,pageable);
         return inscription;
       
        }

    @GetMapping("/inscriptionkyword")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT')")
    public Page<Inscriptions> getInscriptionKyword(@RequestParam String numerodossier,@RequestParam int page,@RequestParam int size){
      
       Pageable pageable = PageRequest.of(page,size);
       Page<Inscriptions> inscription=inscriptionRepository.findByInscriptionsbykeyword(numerodossier,pageable);
                     return inscription;

      }
        @PostMapping("/inscriptions")
        @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT')")
    public Inscriptions saveInscriptions(Inscriptions inscriptions) {
 
        return inscriptionRepository.save(inscriptions);
    }
    
/*@PutMapping("/inscriptionGroupe/{idInscription}")
@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOP_ASSISTANT')")
	public ResponseEntity<Inscriptions> updateinscriptiongroupe(@PathVariable long idInscription,Inscriptions inscriptionsdetaille){
	  Inscriptions inscriptions =inscriptionRepository.findByInscription(idInscription);
		if (inscriptions==null) throw new RuntimeException("question not exist with id :" +  idInscription);
    inscriptions.setGroupe(inscriptionsdetaille.getGroupe());
    Inscriptions updatedsInscriptions = inscriptionRepository.save(inscriptions);
		return ResponseEntity.ok(updatedsInscriptions);
	}*/

  @PutMapping("/dateenvoi/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT')")
	public ResponseEntity<Inscriptions> updateEnvoi(@PathVariable long id,@RequestBody Inscriptions inscriptionsDetaille){
	   Inscriptions inscriptions =inscriptionRepository.findByInscription(id);
		if (inscriptions==null) throw new RuntimeException("inscriptions not exist with id :" + id);
             inscriptions.setEmailEnvoie(inscriptionsDetaille.getEmailEnvoie());
             Inscriptions updatedinscriptions = inscriptionRepository.save(inscriptions);
		return ResponseEntity.ok(updatedinscriptions);
     
      }

      @PutMapping("/inscriptionGroupe/{numeroDossier}")
@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT')")
	public ResponseEntity<Inscriptions> updateinscriptionBygroub(@PathVariable String numeroDossier,Groupe groupe ,String statut){
	  Inscriptions inscriptions =inscriptionRepository.findByInscriptionbysossier(numeroDossier);
		if (inscriptions==null) throw new RuntimeException("inscription not exist with id :" +  numeroDossier);
    inscriptions.setGroupe(groupe);
    inscriptions.setStatut(statut);
    Inscriptions updatedsInscriptions = inscriptionRepository.save(inscriptions);
		return ResponseEntity.ok(updatedsInscriptions);
	}

    
    }
