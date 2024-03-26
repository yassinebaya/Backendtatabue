package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entites.Indicateurs;
import com.example.demo.entites.Stagaire;
import com.example.demo.repo.IndicateurRepository;

@RestController
@CrossOrigin("*")
public class IndicateursController {
    @Autowired
    IndicateurRepository indicateurRepository;
     @PutMapping("/indicateurs/{id}")
	   @PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant')")
	public ResponseEntity<Indicateurs> updateIndecateures(@PathVariable long id,@RequestBody Indicateurs indicateursdetaille){
	     Indicateurs indicateurs =indicateurRepository.findByIndicateurs(id);
         System.out.println(indicateursdetaille.getEtat());
		if (indicateurs==null) throw new RuntimeException("question not exist with id :" + id);
        indicateurs.setEtat(indicateursdetaille.getEtat());
        Indicateurs updatedIndicateurs = indicateurRepository.save(indicateurs);
		return ResponseEntity.ok(updatedIndicateurs);
	}

    @GetMapping("/indicateurs/{stagaire}")
	@PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant')")
	public ResponseEntity<List<Indicateurs>> getIndecateures(@PathVariable Stagaire stagaire){
	     List<Indicateurs> indicateurs =indicateurRepository.findByStagiaire(stagaire);
		return ResponseEntity.ok(indicateurs);
	}


}
