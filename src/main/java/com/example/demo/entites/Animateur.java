package com.example.demo.entites;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("admi")
public class Animateur extends AppUser {
    
}
