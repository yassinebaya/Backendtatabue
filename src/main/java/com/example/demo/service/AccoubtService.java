package com.example.demo.service;

import com.example.demo.entites.AppRole;
import com.example.demo.entites.AppUser;

public interface AccoubtService  {
    AppUser addNewUser(String username,String password);
    AppRole addnewRole(String rolname);
    void addRoleToUser(String username,String role);
    void removeRoleFromUser(String username,String Role);
    AppUser loadAppUserByname(String username);
}
