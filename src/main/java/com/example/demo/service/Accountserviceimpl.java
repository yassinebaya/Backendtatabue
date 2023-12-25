package com.example.demo.service;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.example.demo.entites.AppRole;
import com.example.demo.entites.AppUser;
import com.example.demo.repo.AppRoleRepository;
import com.example.demo.repo.AppUserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class Accountserviceimpl implements AccoubtService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;

    @Override
    public AppUser addNewUser(String username) {
  
       AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser!=null) throw new RuntimeException("this user existe déja");
        return null;
    }

    @Override
    public AppRole addnewRole(String rolname) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addnewRole'");
    }

    @Override
    public void addRoleToUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addRoleToUser'");
    }

    @Override
    public void removeRoleFromUser(String username, String Role) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeRoleFromUser'");
    }
    
}
