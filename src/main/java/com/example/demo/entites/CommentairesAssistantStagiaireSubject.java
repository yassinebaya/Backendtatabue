package com.example.demo.entites;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class CommentairesAssistantStagiaireSubject {
@Id
@ManyToOne
Stagaire stagaire ;
@ManyToOne
Assistant assistant;
String commantaire;
@ManyToOne
Subject subject;
String Date;
    
}
