package com.example.demo.entites;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
import lombok.ToString;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String dateDebut;
    private String dateDernierDelais;
    private String commentaire;
    private String modeleVide;
    @ManyToOne
    private Assistant assistantId;
    private boolean etatPublication;
    private String labelLien;
    private String titreLien;
    private String langue;
    private boolean used;
    @ManyToOne
    SubjectCategorie  categorieId;
    @ManyToOne
    Projets projets;
    @ManyToOne
    Projetphase phaseId;
    @ManyToOne
    DocumentType documentType;
    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL,fetch = FetchType.EAGER )
    @JsonIgnore
      private Collection<Question> questions;
      @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
      @JsonIgnore
      private Collection<Stagairequsetion> stagairequsetions;
      @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
      @JsonIgnore
      private Collection<StagiaireSujects> stagiaireSujects;
    
}
