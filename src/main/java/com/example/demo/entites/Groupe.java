package com.example.demo.entites;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private String nombre;
    @ManyToOne
    private Animateur animateur;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Assistant> assistants;
    @OneToMany(fetch = FetchType.EAGER)
private Collection<Stagaire> stagaires;

    
    
}
