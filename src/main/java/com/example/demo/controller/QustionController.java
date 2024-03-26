package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.QuestionsDTO;
import com.example.demo.entites.Question;
import com.example.demo.entites.Subject;
import com.example.demo.mappers.MaperQuestion;
import com.example.demo.mappers.MaperSubject;
import com.example.demo.repo.QuestionRepository;
import com.example.demo.repo.SubjectRepo;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@CrossOrigin("*")
public class QustionController {
@Autowired
QuestionRepository questionRepository;
@Autowired
SubjectRepo subjectRepo;
@Autowired
MaperQuestion maperQuestion;
@Autowired
MaperSubject maperSubject;

@PostMapping("/questions")
@PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant')")
public Question createQuestion(Question question) {
//	QuestionsDTO questionsDTO=maperQuestion.fromsQuestion(questionRepository.save(question));
    return questionRepository.save(question);
}

    @PutMapping("/questions/{id}")
	  @PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant')")
	public ResponseEntity<Question> updateEmployee(@PathVariable long id, Question questiondetail){
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

 @GetMapping("/questions")
 @PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant','SCOP_stagiaire')")
 public ResponseEntity<List<Question>> getquestion(){
    List<Question> question=questionRepository.findAll();
       return ResponseEntity.ok(question);
  }

     @GetMapping("/questionById")
	 @PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant')")
  public ResponseEntity<QuestionsDTO> questionById(@RequestParam Question question){
	QuestionsDTO questionDTO=maperQuestion.fromsQuestion(question);
		return ResponseEntity.ok(questionDTO);
   }
   @GetMapping("/questionBySubject")
   @PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant')")
   public ResponseEntity<List<QuestionsDTO>> questionBySubject(@RequestParam Subject subject){
         List<Question> questions=questionRepository.findBySubject(subject);
	     List<QuestionsDTO> questionDTO=maperQuestion.fromlistQuestion(questions);
		 return ResponseEntity.ok(questionDTO);
	}


     	
 @DeleteMapping("/questions")
 @PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant')")
	public ResponseEntity<Map<String, Boolean>> deletequestionbysubject(@RequestParam Subject subject){
		List<Question> question = questionRepository.findBySubject(subject);
		if (question==null) throw new RuntimeException("question not exist with id :" + subject);
		 questionRepository.deleteAll(question);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/questions/{id}")
	@PreAuthorize("hasAuthority('SCOPE_admin','SCOP_assistant')")
	public ResponseEntity<Map<String, Boolean>> deletequestionbyid(@PathVariable long id){
		Question question = questionRepository.findByQuestion(id);
		if (question==null) throw new RuntimeException("question not exist with id :" + id);
		questionRepository.delete(question);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
