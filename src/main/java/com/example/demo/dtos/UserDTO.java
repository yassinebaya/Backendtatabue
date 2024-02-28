package com.example.demo.dtos;

import java.util.Collection;

import com.example.demo.entites.AppRole;

import lombok.Data;
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String Email;
    private String nom;
    private String tel;
    private Collection<AppRole> appRoles;
    private String jwt;
    
}
