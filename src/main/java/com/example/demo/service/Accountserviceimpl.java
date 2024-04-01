package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.Responce;
import com.example.demo.entites.AppRole;
import com.example.demo.entites.AppUser;
import com.example.demo.entites.Assistant;
import com.example.demo.entites.Inscriptions;
import com.example.demo.entites.Stagaire;
import com.example.demo.repo.AppRoleRepository;
import com.example.demo.repo.AppUserRepository;
import com.example.demo.repo.InscriptionRepository;
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
  

 
 
    

    @Override
    public AppUser addNewStagaire(String username,String password) {
  
       AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser!=null) throw new RuntimeException("this user existe déja");
        Stagaire user=new Stagaire();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        Stagaire savedAppUser=appUserRepository.save(user);
        return savedAppUser;
    }
    @Override
    public AppUser addNewAssistant(String username,String password) {
       AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser!=null) throw new RuntimeException("this user existe déja");
        Assistant user=new Assistant();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
       Assistant savedAppUser=appUserRepository.save(user);
        return savedAppUser;
    }

    @Override
    public AppRole addnewRole(String rolname) {
      AppRole appRole=appRoleRepository.findByRolename(rolname);
      if (appRole!=null) throw new RuntimeException("this role existe déja");
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
    @Async
    public void Activercompte(String dossier,String email,String password) {
      try {
        Inscriptions inscriptions= findInscriptions(dossier,email);
            AppUser appUser=appUserRepository.findByEmail(email);
           if (inscriptions==null || appUser!=null ) {
        
                    appUser=null;
           }else{
            createStagiaire(dossier,email,password,inscriptions);
    
        
           }
        
      } catch (Exception e) {
        // TODO: handle exception
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
    appUserRepository.save(user); 
  return appUserRepository.save(user);
}
	

    @Override
    public Inscriptions findInscriptions(String dossier, String email) {
    Inscriptions inscriptions=inscriptionRepository.findByEmailandNumeroDossier(email,dossier);
     if (inscriptions==null) ;
     return inscriptions;
    }
    
  

}
