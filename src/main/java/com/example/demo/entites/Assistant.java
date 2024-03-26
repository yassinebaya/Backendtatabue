package com.example.demo.entites;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Assi")
public class Assistant extends AppUser {
    @OneToMany
    @JsonIgnore
    private Collection<Groupe> groupes;
   @OneToMany(fetch = FetchType.EAGER)
   @JsonIgnore
    private Collection<Subject> subjects;

}


