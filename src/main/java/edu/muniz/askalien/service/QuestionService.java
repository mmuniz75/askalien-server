package edu.muniz.askalien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.muniz.askalien.dao.AnswerRepository;
import edu.muniz.askalien.dao.QuestionRepository;
import edu.muniz.askalien.model.Answer;
import edu.muniz.askalien.model.Question;

@Service
public class QuestionService{
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private AnswerRepository answerRepo;
	
	@Autowired
	private QuestionRepository questionRepo;
	
	private String getCountryFromIp(String ip) {
		String country = "Unknown Country";

		if(!ip.equals(""))
			country = countryService.getCountryFromIP(ip);
		return country;
	}

	
	public Answer getAnswer(Integer answerID,String ip,String questionAsk) {
		Answer answer = answerRepo.findAnswerById(answerID); 
		
		Question question = new Question();
		question.setText(questionAsk);
		question.setIp(ip);
		question.setCountry(this.getCountryFromIp(ip));
		question.setAnswer(answer);
		
		questionRepo.save(question);
		
		answer.setQuestionId(question.getId());
		
		return answer;
	}


	
	public void sendFeedback(Question questionForm) {
		Question question = questionRepo.findOne(questionForm.getId());
		question.setCreator(questionForm.getCreator());
		question.setEmail(questionForm.getEmail());
		question.setFeedback(questionForm.getFeedback());
		
		questionRepo.save(question);
		
	}
	
	

	
}
