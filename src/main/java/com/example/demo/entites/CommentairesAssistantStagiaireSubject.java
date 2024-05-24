package com.example.demo.entites;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentairesAssistantStagiaireSubject {
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
private Long id;
@ManyToOne
Stagaire stagaire ;
@ManyToOne
Assistant assistant;
String commantaire;
@ManyToOne
Subject subject;
String Date;
    
}
