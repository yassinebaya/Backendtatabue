package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Assistant;
import com.example.demo.entites.Groupe;
import com.example.demo.repo.GroupeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin("*")
public class GroupController {
    @Autowired
    GroupeRepository groupeRepository;

    @GetMapping("/AllGroupe")
     @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN','SCOPE_STAGIAIRE')")
    public List<Groupe> getMethodName() {
    List<Groupe> groupe=groupeRepository.findAll();
        return groupe;
    }
     
    @GetMapping("/searchGroupes")
  @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN')")
    public Page<Groupe> searchGroupes(@RequestParam String nom,@RequestParam int page,@RequestParam int size) {
        Pageable pageable = PageRequest.of(page,size);
       Page<Groupe> pageGroupe=groupeRepository.sherchGroupe(nom,pageable);
        return pageGroupe;
    }

    @DeleteMapping("/groupe/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Groupe groupe = groupeRepository.findByGroupe(id);
        if (groupe==null) throw new RuntimeException("groupe not exist with id :" + id);
		groupeRepository.delete(groupe);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

    @PostMapping("/groupe")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	public Groupe creatgroupe(Groupe groupe) {
       return groupeRepository.save(groupe);
	}

    @GetMapping("/groupe/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_ASSISTANT','SCOPE_STAGIAIRE')")
    public Groupe getgroupe(@PathVariable Long id) {
        Groupe groupe = groupeRepository.findByGroupe(id);
        return groupe;
    }

    
    @GetMapping("/groupe")
    @PreAuthorize("hasAnyAuthority('SCOPE_ASSISTANT','SCOPE_ADMIN')")
    public Groupe GroupeAssistant(@RequestParam Assistant assistant) {
        System.out.println(assistant);
        Groupe groupe = groupeRepository.findGroupeByAssistant(assistant);
        System.out.println(groupe);
        return groupe;
    }

      @PutMapping("/groupe/{id}")
      @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	     public ResponseEntity<Groupe> updateGroupe(@PathVariable long id,Groupe groupeDetaille){
	    Groupe groupe =groupeRepository.findByGroupe(id);
		if (groupe==null) throw new RuntimeException("groupe not exist with id :" + id);
             groupe.setNom(groupeDetaille.getNom());
             groupe.setNombre(groupeDetaille.getNombre());
             System.out.println(groupeDetaille.getAssistant());
             groupe.setAssistant(groupeDetaille.getAssistant());
        Groupe updatedIndicateurs = groupeRepository.save(groupe);
		return ResponseEntity.ok(updatedIndicateurs);
	}
    @GetMapping("/incrimenter/{idGroupe}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	public Groupe incrimenter(@PathVariable long idGroupe) {
        Groupe groupe =groupeRepository.findByGroupe(idGroupe);
        if (groupe==null) throw new RuntimeException("groupe not exist with id :" + idGroupe);
          groupe.setNombre(groupe.getNombre()+1);
          Groupe updategroupe=groupeRepository.save(groupe);
           return updategroupe;
	}
    
    @GetMapping("/dicrimenter/{idGroupe}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	public Groupe dicrimenter(@PathVariable long idGroupe) {
        Groupe groupe =groupeRepository.findByGroupe(idGroupe);
        if (groupe==null) throw new RuntimeException("groupe not exist with id :" + idGroupe);
          groupe.setNombre(groupe.getNombre()-1);
          Groupe updategroupe=groupeRepository.save(groupe);
           return updategroupe;
	}

    
    
   }
