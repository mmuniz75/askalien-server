package edu.muniz.askalien.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.muniz.askalien.dao.AnswerRepository;
import edu.muniz.askalien.model.Answer;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository repo;
	
	public List<Answer> searchAnswers(String question){
		
				
		question = question.replaceAll("\"","'''");
		question = question.replaceAll("AND","&");
		question = question.replaceAll("OR","|");
		question = question.replaceAll("NOT","!");
		question = question.replaceAll("\\*",":*");
		
		if(!question.contains("&") && !question.contains("|") && !question.contains("!"))
			question = question.replaceAll(" "," | ");
			
		List<Answer> answers = repo.findByKeyWorlds(question);
			
		return answers;
	}
	
}
