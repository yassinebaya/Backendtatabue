package com.example.demo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.Groupe;
import com.example.demo.entites.Inscriptions;
import com.example.demo.entites.Stagaire;

import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscriptions,Long> {

    @Query("SELECT a FROM Inscriptions a WHERE a.email= ?1 and a.numeroDossier= ?2")
    Inscriptions findByEmailandNumeroDossier(String email,String numeroDossier );

    @Query("SELECT a FROM Inscriptions a WHERE a.groupe= ?1 and a.numeroDossier LIKE ?2%")
   Page<Inscriptions> findByInscriptionsbyGroupe(Groupe groupe,String numeroDossier, Pageable pageable);

   @Query("SELECT a FROM Inscriptions a WHERE a.numeroDossier LIKE ?1%")
   Page<Inscriptions> findByInscriptionsbykeyword(String numeroDossier, Pageable pageable);
    
} 
