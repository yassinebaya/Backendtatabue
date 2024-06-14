package com.example.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.example.demo.entites.Message;

public interface MessageRepository extends JpaRepository<Message,Long> {

@Query("SELECT a FROM Message a WHERE a.id= ?1 ")
Message findByMessage(Long id);




}
