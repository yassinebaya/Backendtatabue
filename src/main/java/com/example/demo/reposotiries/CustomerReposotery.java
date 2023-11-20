package com.example.demo.reposotiries;

import com.example.demo.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerReposotery extends JpaRepository<Customer,Long> {

}
