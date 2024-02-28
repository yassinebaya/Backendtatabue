package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entites.Indicateurs;
import com.example.demo.repo.IndicateurRepository;

@RestController
@CrossOrigin("*")
public class IndicateursController {
    @Autowired
    IndicateurRepository indicateurRepository;
     @PutMapping("/indicateurs/{id}")
	public ResponseEntity<Indicateurs> updateIndecateures(@PathVariable long id,Indicateurs indicateursdetaille){
	     Indicateurs indicateurs =indicateurRepository.findByIndicateurs(id);
		if (indicateurs==null) throw new RuntimeException("question not exist with id :" + id);
        indicateurs.setEtat(indicateurs.getEtat());
        Indicateurs updatedIndicateurs = indicateurRepository.save(indicateurs);
		return ResponseEntity.ok(updatedIndicateurs);
	}


}
