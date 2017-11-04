package edu.muniz.askalien.rest;


import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.muniz.askalien.dao.QuestionRepository;
import edu.muniz.askalien.model.Question;
import static org.junit.Assert.*;


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
		assertTrue(true);

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
	
	
	@Test
	public void testFeedBack() throws Exception{
		assertTrue(true);
		final String NAME = "Mythi";
		final String EMAIL = "mythi@mhythi.copm.br";
		final String COMMENTS = "very good unswer";
		
		Integer questionId = null;
		try {
		
			Question question = new Question();
			question.setIp("1.2.3.4");
			question.setText("just a test");
			repo.save(question);
			questionId = question.getId();
						
			question = new Question();
			question.setCreator(NAME);
			question.setEmail(EMAIL);
			question.setFeedback(COMMENTS);
			question.setId(questionId);
			
			ObjectMapper mapper = new ObjectMapper();
		    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		    String requestJson=ow.writeValueAsString(question);
			
		    String URL="/feedback";
			this.mvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
					                  .content(requestJson))	
				.andExpect(status().isOk())
			;
			
			question = null;
			
			question = repo.findOne(questionId);
			assertEquals(question.getCreator(),NAME);
			assertEquals(question.getEmail(),EMAIL);
			assertEquals(question.getFeedback(),COMMENTS);
			
		
		}finally{
			if(questionId !=null)
				repo.delete(questionId);
		}		
			
			
	}
	
	private Question getQuestion(Integer id,String question){
		this.question = repo.findQuestionByAnswerIdAndText(id,question);
		return this.question;
	}

}
