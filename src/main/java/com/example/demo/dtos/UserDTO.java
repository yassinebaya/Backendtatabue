package com.example.demo.dtos;

import java.util.Collection;

import com.example.demo.entites.AppRole;
import com.example.demo.entites.Groupe;

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
    private Groupe groupe;
    private String statut;
    private boolean checked;
    private boolean notification;
    private String projectTitle;
    private String prenom;

}
