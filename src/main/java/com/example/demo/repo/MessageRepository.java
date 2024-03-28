package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.Groupe;
import com.example.demo.entites.Message;

public interface MessageRepository extends JpaRepository<Message,Long> {

@Query("SELECT a FROM Message a WHERE a.id= ?1 ")
Message findByMessage(Long id);

@Query("SELECT a FROM Message a WHERE a.groupe= ?1 ")
 List<Message> findByMessageByGroupe(Groupe groupe);


}
