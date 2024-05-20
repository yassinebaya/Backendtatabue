package com.example.demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entites.Assistant;
import com.example.demo.entites.Groupe;
public interface GroupeRepository extends JpaRepository<Groupe,Long> {
    @Query("SELECT a FROM Groupe a WHERE a.assistant= ?1 ")
    Groupe findByAssistant(Assistant assistant);
    @Query("SELECT a FROM Groupe a WHERE a.id= ?1 ")
    Groupe findByGroupe(Long id);
    @Query("SELECT a FROM Groupe a WHERE a.assistant= ?1 ")
    Groupe findGroupeByAssistant(Assistant assistant);

    

    
}
