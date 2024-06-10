package com.example.demo.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.StagiaireSujects;
import com.example.demo.entites.Subject;
import com.example.demo.repo.AppUserRepository;
import com.example.demo.repo.StagiaireSubjectsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
@Service
@Transactional
@AllArgsConstructor
public class SujetServiceImpl implements SujetService {
     @Autowired
    AppUserRepository appUserRepository;
    @Autowired
   StagiaireSubjectsRepository stagiaireSubjectsRepository;


    @Override
    @Async
    public void publiersujet(Subject subject) {
         List<Stagaire> stagaire=appUserRepository.allStagaires();
         List<StagiaireSujects> stagaires=new ArrayList<>();
             for(Stagaire appUser:stagaire){
              if(appUser.getProjectTitle().getId()!=subject.getProjets().getId()) {
               System.out.println("this projet incorrect");
              }
              else{
              StagiaireSujects  stagairesucjts=new StagiaireSujects();
                   stagairesucjts.setStagaire(appUser);
                   stagairesucjts.setSubject(subject);
                   stagairesucjts.setSubjectEtape("1");
                  stagaires.add(stagairesucjts);
              }
             }
             stagiaireSubjectsRepository.saveAll(stagaires);
      

    }

}
