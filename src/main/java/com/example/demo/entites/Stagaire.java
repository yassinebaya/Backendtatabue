package com.example.demo.entites;
import java.util.Collection;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Stag")
public class Stagaire extends AppUser {

@OneToMany(fetch = FetchType.EAGER)
    private Collection<Stagairequsetion> Stagairequsetion;

@OneToMany(fetch = FetchType.EAGER)
private Collection<StagiaireSujects> stagiaireSujects;
    
}
