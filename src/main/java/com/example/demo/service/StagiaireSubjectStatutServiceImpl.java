package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dtos.StagiaireSubjectStatutDTO;

import com.example.demo.entites.Stagaire;
import com.example.demo.entites.StagiaireSubjectStatut;
import com.example.demo.mappers.MaperStagiaireSubjectStatut;
import com.example.demo.repo.AppUserRepository;
import com.example.demo.repo.StagiaireSubjectStatutRepo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class StagiaireSubjectStatutServiceImpl implements StagiaireSubjectStatutService {
    @Autowired
    StagiaireSubjectStatutRepo stagiaireSubjectStatutRepo;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    MaperStagiaireSubjectStatut maperStagiaireSubjectStatut;
    @Override
    public List<StagiaireSubjectStatutDTO> SershtagiaireSubjectStatut(List<Stagaire> stagaires) {

      List<StagiaireSubjectStatutDTO> ListstagiaireSubjectStatut=new ArrayList<>();
    for(Stagaire stagaire:stagaires){
       
       List<StagiaireSubjectStatut> StagiaireSubjectStatut=stagiaireSubjectStatutRepo.findByStagiaireSujectsStatut(stagaire);
       List<StagiaireSubjectStatutDTO> stagiaireSubjectStatutDTOs=maperStagiaireSubjectStatut.fromListstagiareSubject(StagiaireSubjectStatut);
      ListstagiaireSubjectStatut.addAll(stagiaireSubjectStatutDTOs);
      
    }

    return  ListstagiaireSubjectStatut;

    }

        // TODO Auto-generated method stub
     
    }


