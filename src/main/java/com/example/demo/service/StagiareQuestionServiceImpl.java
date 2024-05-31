package com.example.demo.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.StagaireQuestionDTO;
import com.example.demo.entites.Question;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.Stagairequsetion;
import com.example.demo.entites.Subject;
import com.example.demo.exeception.UserAlreadyExistsException;
import com.example.demo.repo.QuestionRepository;
import com.example.demo.repo.StagaireQuestionRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class StagiareQuestionServiceImpl implements StagiareQuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    StagaireQuestionRepo stagaireQuestionRepo;

    @Override
    @Async
    public void savestagairequestion(Subject subject ,Stagaire stagaire) {
           List<Question> questions=questionRepository.findBySubject(subject);
           List<Stagairequsetion> stagaires=new ArrayList<>();
             for(Question question:questions){
                Stagairequsetion  stagairequsetion= stagaireQuestionRepo.findByStagairequsetion(question,stagaire);
               if  (stagairequsetion!=null) throw new UserAlreadyExistsException("this  Stagairequsetion  existe déja");
               stagairequsetion=new Stagairequsetion();
              stagairequsetion.setQuestion(question);
              stagairequsetion.setSubject(subject);
              stagairequsetion.setStagaire(stagaire);
              stagaires.add(stagairequsetion);
             }
           stagaireQuestionRepo.saveAll(stagaires);
      

        
      
    }

    @Override
    @Async
    public void saveresponce(List<StagaireQuestionDTO> stagaireQuestionDTO) {
     List<Stagairequsetion> lesstagairesquestion=new ArrayList<>();
    for(StagaireQuestionDTO sQuestionDTO:stagaireQuestionDTO){
      Stagairequsetion  stagairequsetion= stagaireQuestionRepo.findByIdStagairequsetion(sQuestionDTO.getId());
    //  if  (stagairequsetion==null) throw new UserAlreadyExistsException("stagairequestion is null");
      stagairequsetion.setAnswer(sQuestionDTO.getAnswer());
      lesstagairesquestion.add( stagairequsetion);

    }
    stagaireQuestionRepo.saveAll(lesstagairesquestion);

    
     
    }

}
