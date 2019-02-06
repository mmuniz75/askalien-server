package edu.muniz.askalien.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import edu.muniz.askalien.client.ElastickSearchClient;
import edu.muniz.askalien.client.response.SearchResponse;
import edu.muniz.askalien.dao.AnswerRepository;
import edu.muniz.askalien.model.Answer;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository repo;
	
	@Autowired
	private ElastickSearchClient search;
	
	@Value("${elastick.search.enable:false}")
	private Boolean elastickEanble;
	
	public List<Answer> searchAnswers(String question){
		
		return elastickEanble?getAnswersFromElastic(question):getAnswersFromDB(question);
	}
	
	private List<Answer> getAnswersFromElastic(String question){
		
		question = this.formatElasticjSearch(question);
		
		SearchResponse response = search.findByKeyWorlds(question);
		
		List<Answer> answers = Collections.emptyList();
		
		if(Objects.nonNull(response.getHits())) {
			answers = response.getHits().getHits().stream().map(
					   hit -> new Answer(hit.getId(),hit.getSource().getSubject())
				      ).collect(Collectors.toList());
		}	
		
		return answers;
		
	}
	
	private List<Answer> getAnswersFromDB(String question){
		question = this.formatDbSearch(question);
		
		List<Answer> answers = repo.findByKeyWorlds(question);
		
		return answers;
	}
	
	private String formatDbSearch(String question) {
		question = question.replaceAll("\"","'''");
		question = question.replaceAll("AND","&");
		question = question.replaceAll("OR","|");
		question = question.replaceAll("NOT","!");
		question = question.replaceAll("\\*",":*");
		
		if(!question.contains("&") && !question.contains("|") && !question.contains("!"))
			question = question.replaceAll(" "," | ");
		
		return question;
	}
	
	private String formatElasticjSearch(String question) {
		
		if(question.contains("\"") || question.contains("*"))
			return question;
			
		question = question.replaceAll(" ","~ ");
		question = question.replaceAll("AND\\~","AND");
		question = question.replaceAll("OR\\~","OR");
		question = question.replaceAll("NOT\\~","NOT");
		question = question.replaceAll("\\(\\~","(");
		question = question.replaceAll("\\)\\~",")");
		
		return question + "~";
	}
	
}
