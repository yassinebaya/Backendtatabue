package com.example.demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entites.Question;
import com.example.demo.entites.Subject;
import java.util.List;
public interface QuestionRepository extends JpaRepository<Question,Long> {
      @Query("SELECT a FROM Question a WHERE a.id= ?1 ")
      Question findByQuestion(Long id);
      List<Question> findBySubject(Subject subject);
  
}
