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
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    SubjectCategorie subjectCategorie;
    @ManyToOne
    Projets projets;
    @ManyToOne
    Projetphase projetphase;
    @ManyToOne
    DocumentType documentType;
    @OneToMany(fetch = FetchType.EAGER)
      private Collection<Question> questions;
    @OneToMany(fetch = FetchType.EAGER)
      private Collection<Stagairequsetion> stagairequsetions;
      @OneToMany(fetch = FetchType.EAGER)
      private Collection<StagiaireSujects> stagiaireSujects;
    
}
