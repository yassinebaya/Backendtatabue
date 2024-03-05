package com.example.demo.service;

import com.example.demo.entites.AppRole;
import com.example.demo.entites.AppUser;
import com.example.demo.entites.Assistant;
import com.example.demo.entites.Inscriptions;

public interface AccoubtService  {
    AppUser addNewStagaire(String username,String password);
    AppUser addNewAssistant(String username,String password);
    AppRole addnewRole(String rolname);
    void addRoleToUser(String username,String role);
    void removeRoleFromUser(String username,String Role);
    AppUser loadAppUserByname(String username);
    AppUser Activercompte(String dossier,String email,String password );
    Inscriptions findInscriptions(String dossier,String email);
    Assistant createAssistant(Assistant assistant);

    
}
