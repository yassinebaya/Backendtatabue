package com.example.demo.entites;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @ManyToOne
    private Question question;
    @ManyToOne
    private Stagaire stagaire;
    @ManyToOne
   private Subject subject;

}
