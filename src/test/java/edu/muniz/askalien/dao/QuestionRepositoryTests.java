package edu.muniz.askalien.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.muniz.askalien.model.Answer;
import edu.muniz.askalien.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionRepositoryTests {
	
	@Autowired
	private QuestionRepository repo;
	
	@Autowired
	private AnswerRepository answerRepo;

	@Test
	public void testSaveQuestion(){
		
		final String QUESTION = "planet earth future";
		final String IP = "186.220.5.148";
		final String COUNTRY = "BRAZIL";
		final Integer ANSWER_ID = 1;
		
		Answer answer = answerRepo.findOne(ANSWER_ID); 
		
		Question question = new Question();
		question.setText(QUESTION);
		question.setIp(IP);
		question.setCountry(COUNTRY);
		question.setAnswer(answer);
		
		repo.save(question);
		Integer id = question.getId();
		
		question = null;
		
		question = repo.findQuestionById(id);
		
		assertEquals(question.getText(),QUESTION);
		assertEquals(question.getIp(),IP);
		assertEquals(question.getCountry(),COUNTRY);
		assertEquals(question.getAnswer().getId(),ANSWER_ID);
		
		repo.delete(question.getId());
	}

}
