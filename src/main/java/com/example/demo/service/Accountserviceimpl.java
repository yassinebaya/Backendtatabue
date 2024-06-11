package com.example.demo.service;
import java.security.SignatureException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ChangePasswordRequestDTO;
import com.example.demo.dtos.Responce;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entites.AppRole;
import com.example.demo.entites.AppUser;
import com.example.demo.entites.Assistant;
import com.example.demo.entites.Inscriptions;
import com.example.demo.entites.Stagaire;
import com.example.demo.entites.StagiaireSujects;
import com.example.demo.entites.Subject;
import com.example.demo.exeception.UserAlreadyExistsException;
import com.example.demo.repo.AppRoleRepository;
import com.example.demo.repo.AppUserRepository;
import com.example.demo.repo.InscriptionRepository;
import com.example.demo.repo.StagiaireSubjectsRepository;
import com.example.demo.repo.SubjectRepo;
import com.example.demo.services.EmailService;

import ch.qos.logback.core.subst.Token;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class Accountserviceimpl implements AccoubtService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    private InscriptionRepository inscriptionRepository;
    private SubjectRepo subjectRepo;
      @Autowired
   StagiaireSubjectsRepository stagiaireSubjectsRepository;
    @Lazy
     @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtEncoder jwtEncoder;
   
    @Autowired
     private JwtDecoder jwtDecoder;
     @Lazy
         @Autowired
    private EmailService emailService;
  
    @Override
    public AppUser addNewStagaire(String username,String password) {
  
       AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser!=null) throw new UserAlreadyExistsException("this user existe déja");
        Stagaire user=new Stagaire();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        Stagaire savedAppUser=appUserRepository.save(user);
        return savedAppUser;
    }
    @Override
    public AppUser addNewAssistant(String username,String password) {
       AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser!=null) throw new UserAlreadyExistsException("this user existe déja");
        Assistant user=new Assistant();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
       Assistant savedAppUser=appUserRepository.save(user);
        return savedAppUser;
    }

    @Override
    public AppRole addnewRole(String rolname) {
      AppRole appRole=appRoleRepository.findByRolename(rolname);
      if (appRole!=null) throw new UserAlreadyExistsException("this role existe déja");
       appRole=AppRole.builder()
              .rolename(rolname)
              .build();
       return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username,String rol) {
      AppUser appUser=appUserRepository.findByUsername(username);
    AppRole appRole=appRoleRepository.findByRolename(rol);
        
        appUser.getAppRoles().add(appRole);
 
    }

    @Override
    public void removeRoleFromUser(String username, String Role) {
          AppUser appUser=appUserRepository.findByUsername(username);
           AppRole appRole=appRoleRepository.findByRolename(Role);
        appUser.getAppRoles().remove(appRole);
   
    }

    @Override
    public AppUser loadAppUserByname(String username) {
       return appUserRepository.findByUsername(username);
    }
  
    @Override
   
    public Responce Activercompte(String dossier,String email,String password) {
      Responce responce;
   try {
    Inscriptions inscriptions= findInscriptions(dossier,email);
           if (inscriptions==null) {
        
             responce = new Responce("non");
           }else{
       Stagaire  stagaire =createStagiaire(dossier,email,password,inscriptions);
            responce = new Responce("oui");
            publierStagiareSubject(stagaire);
        
           }
           return responce;
         
        } catch (Exception e) {
      responce = new Responce("email or username existe déja");
      return responce;
    }

            
    }
    @Override
    public Assistant createAssistant(Assistant assistant) {
      assistant.setPassword(passwordEncoder.encode((assistant.getPassword())));
		return appUserRepository.save(assistant);
	}
  @Override

  public Stagaire createStagiaire(String dossier,String email,String password,Inscriptions inscriptions) {
    Stagaire user=new Stagaire();
    user.setUsername(dossier);
    user.setPassword(passwordEncoder.encode(password));
    user.setEmail(email);
    user.setNom(inscriptions.getNom());
    user.setTel(inscriptions.getTel());
    user.setStatut("Débuté");
    user.setProjectTitle(inscriptions.getProjectTitle());
    user.setGroupe(inscriptions.getGroupe());
    user.setPrenom(inscriptions.getPrenome());
    inscriptions.setStatut("Débuté");
 Stagaire appUser=appUserRepository.save(user); 
    inscriptionRepository.save(inscriptions);
  return appUser;
}
	
    @Override
    public Inscriptions findInscriptions(String dossier, String email) {
    Inscriptions inscriptions=inscriptionRepository.findByEmailandNumeroDossier(email,dossier);
     if (inscriptions==null) ;
     return inscriptions;
    }
    @Override
    public void publierStagiareSubject(Stagaire stagaire) {

        List<Subject> lessubject=subjectRepo.findByEtatPublication();
       
         List<StagiaireSujects> stagaires=new ArrayList<>();
             for(Subject subject:lessubject){

              if(stagaire.getProjectTitle().getId()!=subject.getProjets().getId()) {
                System.out.println("this projet incorrect");
               }else{

              StagiaireSujects  stagairesucjts=new StagiaireSujects();
                   stagairesucjts.setStagaire(stagaire);
                   stagairesucjts.setSubject(subject);
                   stagairesucjts.setSubjectEtape("1");
                  stagaires.add(stagairesucjts);
               }
             }
             stagiaireSubjectsRepository.saveAll(stagaires);
           
    
    }
   

      @Override
      public void changePassword(ChangePasswordRequestDTO request,String Token) {
                 AppUser appUser= passwordChangeBytocken(Token);
          
        //  if (!passwordEncoder.matches(request.currentPassword(),appUser.getPassword()))
             // throw new RuntimeException("The current password is incorrect");
          if(!request.newPassword().equals(request.confirmPassword())){
              throw new RuntimeException("Confirmed password not match");
          }
          appUser.setPassword(passwordEncoder.encode(request.newPassword()));
          appUserRepository.save(appUser);
      }

  @Override
  @Async
  public void envoyerEmailToken(String email){
       AppUser appUser=appUserRepository.findByEmail(email);
       
           Instant instant=Instant.now();
           JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(100, ChronoUnit.MINUTES))
                .subject(email)
                .claim("email",appUser.getEmail())
                .build();
        JwtEncoderParameters jwtEncoderParameters=JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(),jwtClaimsSet);
     String jwt=jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
     String emailContent=String.format("To activate yous account click this link : http://localhost:4200/resetPassword?token="+jwt );

      emailService.sendSimpleMessage(email,"Modification du mot de passe",emailContent);


        }


       @Override
    public AppUser passwordChangeBytocken(String token) {
        try {
            Jwt decode = jwtDecoder.decode(token);
            String subject = decode.getSubject();
            AppUser appUser=appUserRepository.findByEmail(subject);
            
            return appUser;
        } catch (JwtException e){
            e.printStackTrace();
            return null;
        }
    }
   
     
    }
    
    
  


