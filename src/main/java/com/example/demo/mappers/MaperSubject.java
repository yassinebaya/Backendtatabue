package com.example.demo.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.SubjectDTO;

import com.example.demo.entites.Subject;

@Service
public class MaperSubject {
    public List<SubjectDTO> fromlistsubject(List<Subject> subjects){
         List<SubjectDTO> subjectDTO=new ArrayList<SubjectDTO>();
      
        for(Subject subject:subjects){
            SubjectDTO subjectDTO2=new SubjectDTO();
               BeanUtils.copyProperties(subject,subjectDTO2);
                subjectDTO.add(subjectDTO2);
             }

        return subjectDTO;

    }

    public SubjectDTO fromsubject(Subject subject){
       
           SubjectDTO subjectDTO=new SubjectDTO();
           BeanUtils.copyProperties(subject,subjectDTO);
               
       return subjectDTO;

   }


}
