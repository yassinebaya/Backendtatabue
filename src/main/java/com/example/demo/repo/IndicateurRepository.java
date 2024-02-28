package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.Indicateurs;

public interface IndicateurRepository extends JpaRepository<Indicateurs,Long> {
    @Query("SELECT a FROM Indicateurs a WHERE a.id= ?1 ")
    Indicateurs findByIndicateurs(long id);
       
}
