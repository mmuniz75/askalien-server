package edu.muniz.askalien.rest;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class AnswerRestTests {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void testSearch() throws Exception{
		String URL4="/ask?question=zigs";
				
		this.mvc.perform(get(URL4))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Zigs")));
			;
		
	}

}
