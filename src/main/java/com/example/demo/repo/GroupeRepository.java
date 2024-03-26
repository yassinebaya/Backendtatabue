package com.example.demo.repo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entites.Groupe;
public interface GroupeRepository extends JpaRepository<Groupe,Long> {

    Page<Groupe> findByNomLike(String nom, org.springframework.data.domain.Pageable pageable);
    @Query("SELECT a FROM Groupe a WHERE a.id= ?1 ")
    Groupe findByGroupe(Long id);

    

    
}
