package com.example.demo.repo;


import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entites.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe,Long> {

   
    Page<Groupe> findByNomLike(String nom, org.springframework.data.domain.Pageable pageable);

    

    
}
