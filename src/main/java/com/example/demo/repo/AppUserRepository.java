package com.example.demo.repo;

import com.example.demo.entites.AppUser;
import com.example.demo.entites.Assistant;
import com.example.demo.entites.Groupe;
import com.example.demo.entites.Stagaire;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AppUserRepository extends JpaRepository<AppUser,Long>{

AppUser findByEmail(String email);
 // boolean existByEmail(String email);
AppUser findByUsername(String username);
Page<Assistant> findByUsernameLike(String username, Pageable pageable);

 @Query("SELECT a FROM Stagaire a WHERE a.id= ?1 ")
  Stagaire findByStagaire(Long id);


  @Query("SELECT a FROM Assistant a WHERE a.id= ?1 ")
  Assistant findByAssistant(Long id);

  
  @Query("SELECT a FROM Stagaire a ")
   Page<Stagaire> findByStagaires(Pageable pageable);

   @Query("SELECT a FROM Stagaire a WHERE a.groupe= ?1 and a.numeroDossier LIKE ?2%")
   Page<Stagaire> findByStagairesbyGroupe(Groupe groupe,String numeroDossier, Pageable pageable);


   @Query("SELECT a FROM Stagaire a WHERE a.groupe<> ?1 and a.numeroDossier LIKE ?2%")
   Page<Stagaire> findByStagairesbynoteGroupe(Groupe groupe,String numeroDossier, Pageable pageable);

   @Query("SELECT a FROM Stagaire a WHERE a.numeroDossier LIKE ?1%")
   Page<Stagaire> findByStagairesbykeyword(String numeroDossier, Pageable pageable);
   
   @Query("SELECT a FROM Assistant a ")
   Page<Assistant> findByAssistants(Pageable pageable);

   @Query("SELECT a FROM Stagaire a ")
   List<Stagaire> allStagaires();

   @Query("SELECT a FROM Assistant a ")
   List<Stagaire> allAssistent();
   
}
