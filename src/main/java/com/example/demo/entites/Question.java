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
private String question1;
private String answer1;
private String answer2;
private String answer3;
private String answer4;
private String answer5;
private String order1;
private String type;
private String answerInput;
private String answerText;
@OneToMany(fetch = FetchType.EAGER)
private Collection<Stagairequsetion> Stagairequsetion;
@ManyToOne
private Subject subject;
}
