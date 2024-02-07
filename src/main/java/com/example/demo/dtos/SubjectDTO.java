package com.example.demo.dtos;

import lombok.Data;

@Data
public class SubjectDTO {
   private int id;
   private String name;
   private int documentType;
    private int categorieId;
    private int projetId;
    private int phaseId;
    private int etapeId;
   private String description;
  /* private dateDebut": "01/12/2022",
    "dateDernierDelais": "04/14/2023",
    "commentaire": "moiueee",
    "modeleVide": "ddddd",
    "assistantId":40,
    "etatPublication":true,
    "labelLien": "saidee",
    "titreLien": "aszzz",
    "langue": "fr",
    "used": false, */ 
}
