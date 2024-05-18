package com.example.demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.Liens;


public interface LienRepo extends JpaRepository<Liens,Long> {
@Query("SELECT a FROM Liens a WHERE a.id= ?1 ")
Liens findBylien(Long id);

}
