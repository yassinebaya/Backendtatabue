package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.Indicateurs;
import com.example.demo.entites.Stagaire;

public interface IndicateurRepository extends JpaRepository<Indicateurs,Long> {
    @Query("SELECT a FROM Indicateurs a WHERE a.id= ?1 ")
    Indicateurs findByIndicateurs(long id);

    @Query("SELECT a FROM Indicateurs a WHERE a.stagaire= ?1 ")
    List<Indicateurs> findByStagiaire(Stagaire stagaire);

    @Query("SELECT a FROM Indicateurs a  ")
    List<Indicateurs> getAllIndicateurs();

       
}
