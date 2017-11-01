package edu.muniz.askalien.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.muniz.askalien.model.Answer;



@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerServiceTests {
	
	@Autowired
	AnswerService service;
	
	@Test
	public void searchQuestions(){
		List<Answer> answers = service.searchAnswers("zigs");
		
		assertTrue(answers.size()>=2);
		assertTrue(answers.get(0).getSubject().contains("Zigs"));
	}

}
