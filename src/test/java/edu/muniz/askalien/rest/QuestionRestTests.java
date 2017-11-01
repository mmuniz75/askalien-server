package edu.muniz.askalien.rest;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import edu.muniz.askalien.dao.QuestionRepository;
import edu.muniz.askalien.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionRestTests {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Autowired
	private QuestionRepository repo;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	private Question question = null;
	@Test
	public void testDetailAnwer() throws Exception{
		final String QUESTION = "test ** alien captured Brazil **";
		final Integer ANSWER_ID = 181;
		
		String URL="/answer/" + ANSWER_ID + "?question=" + QUESTION;
		
		try {
					
			this.mvc.perform(get(URL))
				.andExpect(status().isOk())
				.andExpect(jsonPath("number", equalTo(ANSWER_ID)))
			    .andExpect(jsonPath("content", equalTo("This being is from Sirius, who was captured in Brazil and he was placed at the disposal of your government by the reptilians working with your government.&nbsp;<div>I do not know if he is the same guy, but it seems that he was the only crew&nbsp;<br style=\"color: rgb(51, 51, 51); font-family: Helvetica, Arial, 'Bitstream Vera Sans', Verdana, sans-serif; line-height: 21.309572219848633px;\"/>member that was lost.&nbsp;</div><div>They took him to the lunar base.</div>")))
			    .andExpect(jsonPath("question", equalTo("What was the destiny of alien captured in Brazil ?")))
			    .andExpect(jsonPath("date", equalTo("04/22/2011")))
			    .andExpect(jsonPath("link", equalTo("https://www.youtube.com/watch?v=V9LYir0j5MA")))
			    .andExpect(jsonPath("questionId", equalTo(getQuestion(ANSWER_ID,QUESTION).getId())))
				;
		
		}finally{
			if(question !=null)
				repo.delete(question.getId());
		}		
			
			
	}
	
	private Question getQuestion(Integer id,String question){
		this.question = repo.findQuestionByAnswerIdAndText(id,question);
		return this.question;
	}

}
