package com.example.demo.entites;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Question {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;

@OneToMany(fetch = FetchType.EAGER)
private Collection<Stagairequsetion> Stagairequsetion;
@ManyToOne
private Subject subject;
}