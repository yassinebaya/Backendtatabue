package com.example.demo.entites;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Stagairequsetion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String answer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Question question;
    @ManyToOne(fetch = FetchType.LAZY)
    private Stagaire stagaire;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
   private Subject subject;

}
