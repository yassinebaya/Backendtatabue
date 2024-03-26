package com.example.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entites.Notifications;


public interface NotificationRepo extends JpaRepository<Notifications,Long> {

    @Query("SELECT a FROM Notifications a WHERE a.id= ?1 ")
    Notifications findByNotifications(Long id);

    
}
