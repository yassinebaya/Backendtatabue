package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.DocumentType;


public interface DocumentypeRepository extends JpaRepository<DocumentType,Long>  {
   @Query("SELECT a FROM DocumentType a WHERE a.id= ?1 ")
     DocumentType findByDocumentType(Long id);
    
}
