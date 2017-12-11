package edu.muniz.askalien.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.muniz.askalien.model.Answer;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerRepositoryTests {
	
	@Autowired
	private AnswerRepository repo;

	@Test
	public void testFindQuestions(){
		final String SUBJECT = "Is the planet Earth is undergoing a transformation in the near future?";
		List<AnswerSummary> answers = repo.findByText(SUBJECT);
		assertEquals(answers.get(0).getSubject(),SUBJECT);
	}
	
	@Test
	public void testFindContent(){
		final String CONTENT = "%contact can happen in your lifetime%";
		List<AnswerSummary> answers = repo.findByText(CONTENT);
		assertEquals(answers.get(0).getSubject(),"Is your time of interaction with us in the forum limited? How long? Also, will we meet you & others personally during our lifetime?");
	}
	
	@Test
	public void testFindAnwer(){
		Answer answer = repo.findOne(1);
		assertEquals(answer.getSubject(),"Is the planet Earth is undergoing a transformation in the near future?");
	}	
	
	@Test
	public void testFindKeyWords(){
		List<Answer> answers = repo.findByKeyWorlds("antart:*");
		assertTrue(answers.size()>=2);
	}
	
	@Test
	public void testFindKeyWords2(){
		List<Answer> answers = repo.findByKeyWorlds("zigs");
		assertTrue(answers.size()>=2);
	}
}
