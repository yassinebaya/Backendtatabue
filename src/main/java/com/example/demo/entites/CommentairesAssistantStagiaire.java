package com.example.demo.entites;
import java.util.Collection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentairesAssistantStagiaire {
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
private Long id;
@ManyToOne
Stagaire stagaire ;
@ManyToOne
Assistant assistant;
String commantaire;
String Date;
    
}
