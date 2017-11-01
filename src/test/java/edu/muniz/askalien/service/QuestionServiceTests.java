package edu.muniz.askalien.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.muniz.askalien.dao.QuestionRepository;
import edu.muniz.askalien.model.Answer;
import edu.muniz.askalien.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceTests {
	
	@Autowired
	QuestionService service;
	
	@Autowired
	private QuestionRepository repo;
	
	@Test
	public void searchAnwers(){
		final String QUESTION = "test ** alien captured Brazil **";
		final String IP = "186.220.5.148";
		final String COUNTRY = "BRAZIL";
		final Integer ANSWER_ID = 181;
		
		Answer answer = service.getAnswer(ANSWER_ID, IP, QUESTION);
		
		Integer questionId = null;
		try {
		
			assertEquals(answer.getContent(),"This being is from Sirius, who was captured in Brazil and he was placed at the disposal of your government by the reptilians working with your government.&nbsp;<div>I do not know if he is the same guy, but it seems that he was the only crew&nbsp;<br style=\"color: rgb(51, 51, 51); font-family: Helvetica, Arial, 'Bitstream Vera Sans', Verdana, sans-serif; line-height: 21.309572219848633px;\"/>member that was lost.&nbsp;</div><div>They took him to the lunar base.</div>");
			assertEquals(answer.getId(),ANSWER_ID);
			assertEquals(answer.getSubject(),"What was the destiny of alien captured in Brazil ?");
			assertEquals(answer.getDate(),"04/22/2011");
			assertEquals(answer.getUrl(),"https://www.youtube.com/watch?v=V9LYir0j5MA");
				
			Question question = repo.findQuestionByAnswerIdAndText(ANSWER_ID, QUESTION);
			questionId = question.getId();
			assertTrue(answer.getQuestionId().compareTo(questionId) == 0);
			
			assertEquals(question.getText(),QUESTION);
			assertEquals(question.getIp(),IP);
			assertEquals(question.getCountry(),COUNTRY);
			assertEquals(question.getAnswer().getId(),ANSWER_ID);
		
		}finally{
			if (questionId!=null)
				repo.delete(questionId);
		}	
		
	}

}
