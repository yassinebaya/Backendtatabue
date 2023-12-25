package com.example.demo.service;

import com.example.demo.entites.AppRole;
import com.example.demo.entites.AppUser;

public interface AccoubtService  {
    AppUser addNewUser(String username);
    AppRole addnewRole(String rolname);
    void addRoleToUser();
    void removeRoleFromUser(String username,String Role);
}
