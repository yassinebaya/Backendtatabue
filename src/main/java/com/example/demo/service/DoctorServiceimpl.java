package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entites.Subject;
import com.example.demo.repo.SubjectRepo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class DoctorServiceimpl implements DoctorService{

    SubjectRepo subjectRepo;
    @Override
    public Subject createSubject(Subject subject) {
    return subjectRepo.save(subject);
    }
    
}
