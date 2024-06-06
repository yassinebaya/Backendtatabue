package com.example.demo.dtos;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.Subject;
import lombok.Data;

@Data
public class StagiaireSubjectStatutDTO {
    private Long id;
   Long idStagiaire;
   Long idSubject;
   Long  etapeSubject;


}
