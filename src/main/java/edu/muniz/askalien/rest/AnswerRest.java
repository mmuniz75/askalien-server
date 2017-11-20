package edu.muniz.askalien.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.muniz.askalien.model.Answer;
import edu.muniz.askalien.service.AnswerService;

@RestController
@CrossOrigin
public class AnswerRest {
	
	@Autowired
	AnswerService service;
	
	@RequestMapping("/ask")
	public List<Answer> getAnnswer(@RequestParam String question){
		return service.searchAnswers(question);
	}

}
