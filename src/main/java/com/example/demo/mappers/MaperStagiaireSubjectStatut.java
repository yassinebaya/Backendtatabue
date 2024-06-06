package com.example.demo.mappers;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.example.demo.dtos.StagiaireSubjectStatutDTO;
import com.example.demo.entites.StagiaireSubjectStatut;

@Service
public class MaperStagiaireSubjectStatut {


 public List<StagiaireSubjectStatutDTO> fromListstagiareSubject(List<StagiaireSubjectStatut> stagiaireSubjectStatuts){
    List<StagiaireSubjectStatutDTO> StagiaireSubjectStatutDTO=new ArrayList<StagiaireSubjectStatutDTO>();
      
        for(StagiaireSubjectStatut stagiaireSubjectStatut:stagiaireSubjectStatuts){
            StagiaireSubjectStatutDTO stagiaireSubjectStatutDTO=new StagiaireSubjectStatutDTO();
              stagiaireSubjectStatutDTO.setIdStagiaire(stagiaireSubjectStatut.getIdStagiaire().getId());
              stagiaireSubjectStatutDTO.setIdSubject(stagiaireSubjectStatut.getIdSubject().getId());
               BeanUtils.copyProperties(stagiaireSubjectStatut,stagiaireSubjectStatutDTO);

               StagiaireSubjectStatutDTO.add(stagiaireSubjectStatutDTO);
             }
        return StagiaireSubjectStatutDTO;

    }

}
