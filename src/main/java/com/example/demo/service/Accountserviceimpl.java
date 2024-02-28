package com.example.demo.service;

import java.util.List;

import javax.management.RuntimeErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public AppUser addNewUser(String username,String password) {
  
       AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser!=null) throw new RuntimeException("this user existe déja");
        Stagaire user=new Stagaire();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        Stagaire savedAppUser=appUserRepository.save(user);
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
    public AppUser Activercompte(String dossier,String email,String password) {
                  String active;
            Inscriptions inscriptions= findInscriptions(dossier,email);
           AppUser appUser=appUserRepository.findByEmail(email);

           if (inscriptions==null || appUser!=null ) {
           appUser=null;
           }else{
           Stagaire user=new Stagaire();
            user.setUsername(dossier);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setNom(inscriptions.getNom());
            user.setTel(inscriptions.getTel());
            appUserRepository.save(user); 
             appUser=appUserRepository.findByEmail(email);
           }
           System.out.println(appUser);
             return appUser;
    }
    @Override
    public Assistant createAssistant(Assistant assistant) {
      assistant.setPassword(passwordEncoder.encode((assistant.getPassword())));
		return appUserRepository.save(assistant);
	}
	

    @Override
    public Inscriptions findInscriptions(String dossier, String email) {
      System.out.println(email);
      System.out.println(dossier);

    Inscriptions inscriptions=inscriptionRepository.findByEmailandNumeroDossier(email,dossier);
    System.out.println(inscriptions);
     if (inscriptions==null) inscriptions=null;
     return inscriptions;
    }
    
  

}
