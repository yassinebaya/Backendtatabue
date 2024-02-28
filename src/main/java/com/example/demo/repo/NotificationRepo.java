package com.example.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entites.Notifications;

public interface NotificationRepo extends JpaRepository<Notifications,Long> {

    

    
}
