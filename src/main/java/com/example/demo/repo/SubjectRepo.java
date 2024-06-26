package com.example.demo.repo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entites.Assistant;
import com.example.demo.entites.Subject;

public interface SubjectRepo extends JpaRepository<Subject,Long>  {
    @Query("SELECT a FROM Subject a WHERE a.id= ?1 ")
    Subject findBySubject(Long id);
    @Query("SELECT a FROM Subject a WHERE a.assistantId= ?1 ")
    List<Subject> findByAssistantSubjects(Assistant assistant);
    @Query("SELECT a FROM Subject a WHERE a.etatPublication=true")
    List<Subject> findByEtatPublication();

   // List<Subject> findByProjets(Projets projets);
    
}
