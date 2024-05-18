package com.example.demo.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dtos.QuestionsDTO;
import com.example.demo.dtos.SubjectDTO;
import com.example.demo.entites.Question;

@Service
public class MaperQuestion {
  @Autowired
  MaperSubject maperSubject;
      public QuestionsDTO fromsQuestion(Question question){
        SubjectDTO subjectDTO=maperSubject.fromsubject(question.getSubject());
           QuestionsDTO questionsDTO=new QuestionsDTO();
          questionsDTO.setSubjectdto(subjectDTO);
           BeanUtils.copyProperties(question,questionsDTO);
         
       return questionsDTO;

   }

  public List<QuestionsDTO> fromlistQuestion(List<Question> questions){
         List<QuestionsDTO> questionsDTO=new ArrayList<QuestionsDTO>();
      
        for(Question question:questions){
          QuestionsDTO questionsDTO2=new QuestionsDTO();
               BeanUtils.copyProperties(question,questionsDTO2);
               questionsDTO.add(questionsDTO2);
             }
        return questionsDTO;

    }
    
}
