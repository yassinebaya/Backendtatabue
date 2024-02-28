package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.Inscriptions;
import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscriptions,Long> {

    @Query("SELECT a FROM Inscriptions a WHERE a.email= ?1 and a.numeroDossier= ?2")
    Inscriptions findByEmailandNumeroDossier(String email,String numeroDossier );

    
    
} 
