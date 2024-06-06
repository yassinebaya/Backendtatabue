package com.example.demo.entites;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class StagiaireSubjectStatut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @org.hibernate.annotations.Index(name = "stagiare_id_index")
    Stagaire idStagiaire;
   @ManyToOne(fetch = FetchType.LAZY)
   @org.hibernate.annotations.Index(name = "subject_id_index")
   Subject idSubject;
   Long  etapeSubject;


    
}
