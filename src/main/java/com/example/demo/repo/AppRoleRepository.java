package com.example.demo.repo;

import com.example.demo.entites.AppRole;
import com.example.demo.entites.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
   
   AppRole findByRolename(String rolename);
   
}
