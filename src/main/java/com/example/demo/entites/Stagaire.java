package com.example.demo.entites;
import java.util.Collection;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Stag")
@ToString
public class Stagaire extends AppUser {
private String statut;
private boolean checked;
private boolean notification;
private String projectTitle;
private String prenom;
private String numeroDossier;
@ManyToOne
Groupe groupe;
@OneToMany(fetch = FetchType.EAGER)
    private Collection<Stagairequsetion> Stagairequsetion;
@OneToMany(fetch = FetchType.EAGER)
private Collection<StagiaireSujects> stagiaireSujects;
    
@OneToMany(fetch = FetchType.EAGER)
private Collection<Indicateurs> indicateurs;
    
}
