package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entites.Question;
import com.example.demo.repo.QuestionRepository;
import com.example.demo.repo.SubjectRepo;

@RestController
@CrossOrigin("*")
public class QustionController {
@Autowired
QuestionRepository questionRepository;
@Autowired
SubjectRepo subjectRepo;

    @PutMapping("/questions/{id}")
	public ResponseEntity<Question> updateEmployee(@PathVariable long id, Question questiondetail,Long idsubject){
		Question question = questionRepository.findByQuestion(id);
		if (question==null) throw new RuntimeException("question not exist with id :" + id);
     	question.setAnswer1(questiondetail.getAnswer1());
		question.setAnswer2(questiondetail.getAnswer2());
		question.setAnswer3(questiondetail.getAnswer3());
		question.setAnswer4(questiondetail.getAnswer5());
		question.setAnswer5(questiondetail.getAnswer5());
        question.setAnswerInput(questiondetail.getAnswerInput());
        question.setAnswerText(questiondetail.getAnswerText());
        question.setOrder1(questiondetail.getOrder1());
        question.setQuestion1(questiondetail.getQuestion1());
        question.setType(questiondetail.getType());
	    question.setSubject(questiondetail.getSubject());
		 Question updatedquestion = questionRepository.save(question);
		return ResponseEntity.ok(updatedquestion);
	}
}
