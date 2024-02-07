package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entites.Projets;

public interface ProjetsRepository extends JpaRepository<Projets,Long>{
    
    
}
