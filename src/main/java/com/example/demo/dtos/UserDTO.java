package com.example.demo.dtos;

import java.util.Collection;

import com.example.demo.entites.AppRole;
import java.util.Map;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Data;
@Data
public class UserDTO {
    private Long id;
    private String username;
    private Collection<AppRole> appRoles;
    private String jwt;
    
}
