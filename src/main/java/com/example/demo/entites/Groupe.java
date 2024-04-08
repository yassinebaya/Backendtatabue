package com.example.demo.entites;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PreRemove;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int nombre;
  
    @OneToOne
    private Assistant assistant;
   //@OneToMany(mappedBy = "groupe",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = false )
  //@JsonIgnore
    @OneToMany
    private Collection<Stagaire> stagaires;
   @OneToMany
   @JsonIgnore
    private Collection<Inscriptions> inscriptions;




    }
    

