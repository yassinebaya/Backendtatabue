package com.example.demo.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inscriptions {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true, unique = true)
    private String numeroDossier;
    @Column(nullable = true, unique = true)
    private String statut;
    private String email;
    private String nom;
    private String prenome;
    private String Tel;
       @ManyToOne
     Groupe groupe;
    private String projettitle;
    private String emailEnvoie;
   
    
    
}
