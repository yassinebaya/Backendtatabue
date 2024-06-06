package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.StagiaireSubjectStatutDTO;
import com.example.demo.entites.Stagaire;




public interface StagiaireSubjectStatutService {

    public List<StagiaireSubjectStatutDTO> SershtagiaireSubjectStatut(List<Stagaire> stagaires);

}
