package com.example.demo.service;
import java.util.List;
import com.example.demo.dtos.StagaireQuestionDTO;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.Subject;

public interface StagiareQuestionService {

    void savestagairequestion(Subject subject,Stagaire stagaire);
    void saveresponce(List<StagaireQuestionDTO> stagaireQuestionDTO);
 
}
