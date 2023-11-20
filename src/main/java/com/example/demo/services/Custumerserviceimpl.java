package com.example.demo.services;

import com.example.demo.entites.Customer;
import com.example.demo.reposotiries.CustomerReposotery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class Custumerserviceimpl implements CostumerService{

   @Autowired
    private CustomerReposotery customerReposotery;
    @Override
    public Customer savecustumer(Customer customer) {
        log.info("saving new customer");
        return null;
    }
}
