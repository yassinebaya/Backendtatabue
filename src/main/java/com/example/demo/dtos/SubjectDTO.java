package com.example.demo.dtos;

import com.example.demo.entites.Assistant;
import com.example.demo.entites.DocumentType;
import com.example.demo.entites.Projetphase;
import com.example.demo.entites.Projets;
import com.example.demo.entites.SubjectCategorie;
import lombok.Data;
@Data
public class SubjectDTO {
    private Long id;
    private String name;
    private String description;
    private String dateDebut;
    private String dateDernierDelais;
    private String commentaire;
    private String modeleVide;
    private Assistant assistantId;
    private boolean etatPublication;
    private String labelLien;
    private String titreLien;
    private String langue;
    private boolean used;
    SubjectCategorie  categorieId;
    Projets projets;
    Projetphase phaseId;
    DocumentType documentType;
}
