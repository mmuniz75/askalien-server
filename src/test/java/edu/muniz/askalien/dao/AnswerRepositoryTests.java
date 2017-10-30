package edu.muniz.askalien.dao;

import static org.junit.Assert.assertEquals;

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
		List<Integer> ids = Arrays.asList(1,2,3);
		List<AnswerSummary> answers = repo.findByIdIn(ids);
		assertEquals(answers.size(),3);
	}
}
