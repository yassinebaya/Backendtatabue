package com.example.demo.dtos;

import com.example.demo.entites.Subject;

import lombok.Data;

@Data
public class QuestionsDTO {
private Long id;
private String question1;
private String answer1;
private String answer2;
private String answer3;
private String answer4;
private String answer5;
private String order1;
private String type;
private String answerInput;
private String answerText;
private SubjectDTO subjectdto;
    
}
