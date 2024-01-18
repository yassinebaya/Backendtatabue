package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entites.Images;

public interface StorageRepository extends JpaRepository<Images,Long> {
    Optional<Images> findByName(String fileName);
    

    
}
