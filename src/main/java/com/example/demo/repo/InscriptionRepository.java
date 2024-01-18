package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entites.Inscriptions;
import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscriptions,Long> {
    Inscriptions findByDossier(String dossier);
    
} 
