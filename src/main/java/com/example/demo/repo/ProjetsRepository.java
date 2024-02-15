package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.Projets;


public interface ProjetsRepository extends JpaRepository<Projets,Long>{
     @Query("SELECT a FROM Projets a WHERE a.id= ?1 ")
     Projets findByProjets(Long id);
}
