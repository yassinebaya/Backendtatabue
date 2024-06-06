package com.example.demo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dtos.StagiaireSubjectStatutDTO;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.StagiaireSubjectStatut;
import com.example.demo.exeception.UserAlreadyExistsException;
import com.example.demo.repo.StagiaireSubjectStatutRepo;
import com.example.demo.service.StagiaireSubjectStatutService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@CrossOrigin("*")
public class StagiaireSubjectStatutController {
    @Autowired
    StagiaireSubjectStatutRepo stagiaireSubjectStatutRepo;
    @Autowired
    StagiaireSubjectStatutService stagiaireSubjectStatutService;

    @PostMapping("/StagiaireSubjectStatut")
   //  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
	public StagiaireSubjectStatut SaveStagiaireSubjectStatut(StagiaireSubjectStatut  stagiaireSubjectStatut) {
        StagiaireSubjectStatut stagiaireSubjectStatut1=stagiaireSubjectStatutRepo.findByStagiaireSujectsStatutS(stagiaireSubjectStatut.getIdStagiaire(),stagiaireSubjectStatut.getIdSubject());
        if (stagiaireSubjectStatut1!=null) throw new UserAlreadyExistsException("existe");
        stagiaireSubjectStatutRepo.save(stagiaireSubjectStatut);
       return stagiaireSubjectStatut ;
	}

    @PutMapping("StagiaireSubjectStatut")
    public StagiaireSubjectStatut updateStagiaireSubjectStatut(StagiaireSubjectStatut stagiaireSubjectStatut) {
       StagiaireSubjectStatut stagiaireSubjectStatut1=stagiaireSubjectStatutRepo.findByStagiaireSujectsStatutS(stagiaireSubjectStatut.getIdStagiaire(),stagiaireSubjectStatut.getIdSubject());
   	if (stagiaireSubjectStatut1==null) throw new UserAlreadyExistsException("StagiaireSubjectStatut not exist with id :");
       stagiaireSubjectStatut1.setIdStagiaire(stagiaireSubjectStatut.getIdStagiaire());
       stagiaireSubjectStatut1.setEtapeSubject(stagiaireSubjectStatut.getEtapeSubject());
       stagiaireSubjectStatut1.setIdSubject(stagiaireSubjectStatut.getIdSubject());
       stagiaireSubjectStatutRepo.save(stagiaireSubjectStatut1);
        return stagiaireSubjectStatut1;
     }
 @GetMapping("/StagiaireSubjectStatut")
public List<StagiaireSubjectStatutDTO> getStagiaireSubjectStatut(@RequestParam List<Stagaire> ids){
  

     List<StagiaireSubjectStatutDTO> stagiaireSubjectStatuts=stagiaireSubjectStatutService.SershtagiaireSubjectStatut(ids);

     return stagiaireSubjectStatuts;

 }



}
