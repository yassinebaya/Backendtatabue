package com.example.demo.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entites.Groupe;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.Subject;
import com.example.demo.repo.AppUserRepository;
import com.example.demo.repo.SubjectRepo;
@RestController
@CrossOrigin("*")
public class StagaireControle {
@Autowired
 AppUserRepository appUserRepository;
 @Autowired
 SubjectRepo subjectRepo;
 
@GetMapping("getStudent/{stagiaireId}")
@PreAuthorize("hasAuthority('SCOPE_STAGIAIRE')")
 public Stagaire getStudent(@PathVariable Long stagiaireId){

 Stagaire stagaire=appUserRepository.findByStagaire(stagiaireId);


  return stagaire;


 }

 @GetMapping("/stagiaires")
 @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN')")
 public List<Stagaire> allStagaire(){
  List<Stagaire> stagaire=appUserRepository.allStagaires();
    return stagaire;
  
   }


  @PutMapping("/updateEtat/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN')")
	public ResponseEntity<Subject> updateIndecateures(@PathVariable long id,@RequestBody Subject subjectsdetaille){
	    Subject subject =subjectRepo.findBySubject(id);
		if (subject==null) throw new RuntimeException("question not exist with id :" + id);
    subject.setEtatPublication(subjectsdetaille.isEtatPublication());
    Subject updatedsubject = subjectRepo.save(subject);
		return ResponseEntity.ok(updatedsubject);
	}

  @PutMapping("/stagiares/{idsujet}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN')")
	public ResponseEntity<Stagaire> updatenotifications(@PathVariable long idsujet,@RequestBody Stagaire stagairedetaille){
	  Stagaire stagaire =appUserRepository.findByStagaire(idsujet);
		if (stagaire==null) throw new RuntimeException("question not exist with id :" + idsujet);
    stagaire.setNotification(stagairedetaille.isNotification());
    Stagaire updatedsStagaire = appUserRepository.save(stagaire);
		return ResponseEntity.ok(updatedsStagaire);
	}

  @GetMapping("/stagiareskywordbygroube")
  @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN')")
  public Page<Stagaire> getStagiareKyword(@RequestParam Groupe groupe,@RequestParam String numerodossier,@RequestParam int page,@RequestParam int size){
  
     Pageable pageable = PageRequest.of(page,size);
     Page<Stagaire> stagaire=appUserRepository.findByStagairesbyGroupe(groupe,numerodossier,pageable);
     return stagaire;
   
    }
    @GetMapping("/stagiareskyword")
    @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN')")
    public Page<Stagaire> getStagiareKyword(@RequestParam String numerodossier,@RequestParam int page,@RequestParam int size){
      
       Pageable pageable = PageRequest.of(page,size);
       Page<Stagaire> stagaire=appUserRepository.findByStagairesbykeyword(numerodossier,pageable);
       return stagaire;
     
      }
      @GetMapping("/stagiareskywordbynotegroube")
      @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN')")
      public Page<Stagaire> getStagiareByNotgroup(@RequestParam Groupe groupe, @RequestParam String numerodossier,@RequestParam int page,@RequestParam int size){
        
         Pageable pageable = PageRequest.of(page,size);
         Page<Stagaire> stagaire=appUserRepository.findByStagairesbynoteGroupe(groupe,numerodossier,pageable);
         return stagaire;
       
        }

  @DeleteMapping("/stagiaireDelete/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN')")
	public ResponseEntity<Map<String, Boolean>> deleteStagiaireyid(@PathVariable long id){
    Stagaire stagaire =appUserRepository.findByStagaire(id);
		if (stagaire==null) throw new RuntimeException("question not exist with id :" + id);
		appUserRepository.delete(stagaire);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);

}

@PutMapping("/stagiaresCheked/{idStagiaire}")
@PreAuthorize("hasAnyAuthority('SCOPE_admin')")
	public ResponseEntity<Stagaire> updateCheked(@PathVariable long idStagiaire,@RequestBody Stagaire stagairedetaille){
	  Stagaire stagaire =appUserRepository.findByStagaire(idStagiaire);
		if (stagaire==null) throw new RuntimeException("question not exist with id :" + idStagiaire);
    stagaire.setChecked(stagairedetaille.isChecked());
    Stagaire updatedsStagaire = appUserRepository.save(stagaire);
		return ResponseEntity.ok(updatedsStagaire);
	}
 
  @PutMapping("/DefaultGroupe/{idStagiaire}")
  @PreAuthorize("hasAnyAuthority('SCOPE_assistant','SCOPE_admin')")
	public ResponseEntity<Stagaire> updateDefaultGroupe(@PathVariable long idStagiaire,Stagaire stagairedetaille){
	  Stagaire stagaire =appUserRepository.findByStagaire(idStagiaire);
		if (stagaire==null) throw new RuntimeException("question not exist with id :" + idStagiaire);
   // stagaire.setGroupe(stagairedetaille.getGroupe());
    Stagaire updatedsStagaire = appUserRepository.save(stagaire);
		return ResponseEntity.ok(updatedsStagaire);
	}
  @PutMapping("/stagaieGroupe/{idStagiaire}")
  @PreAuthorize("hasAnyAuthority('SCOPE_assistant','SCOPE_admin')")
	public ResponseEntity<Stagaire> updatestagiregroupe(@PathVariable long idStagiaire,Stagaire stagairedetaille){
	  Stagaire stagaire =appUserRepository.findByStagaire(idStagiaire);
		if (stagaire==null) throw new RuntimeException("question not exist with id :" + idStagiaire);
 //   stagaire.setGroupe(stagairedetaille.getGroupe());
    Stagaire updatedsStagaire = appUserRepository.save(stagaire);
		return ResponseEntity.ok(updatedsStagaire);
	}

}
