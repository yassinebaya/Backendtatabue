package com.example.demo.repo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entites.AppUser;
import com.example.demo.entites.Liens;
import com.example.demo.entites.Subject;

public interface LienRepo extends JpaRepository<Liens,Long> {
@Query("SELECT a FROM Liens a WHERE a.id= ?1 ")
Liens findBylien(Long id);

@Query("SELECT a FROM Liens a WHERE a.appUser= ?1 and a.subject= ?2 ")
List<Liens> findByUser(AppUser appUser,Subject subject);

}
