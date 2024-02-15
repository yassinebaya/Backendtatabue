package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.Projetphase;


public interface ProjetphaseRepository extends JpaRepository<Projetphase,Long> {
      @Query("SELECT a FROM Projetphase a WHERE a.id= ?1 ")
      Projetphase findByProjetphase(Long id);
}
