package com.example.demo.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",length = 4)
public abstract class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true, unique = true)
    private String username;
    @Column(nullable = true, unique = true)
    private String email;
    @Column(nullable = true)
    private String password;
    private String nom;
    private String tel;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> appRoles;
}
