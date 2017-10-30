package edu.muniz.askalien.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.muniz.askalien.dao.AnswerSummary;
import edu.muniz.askalien.dao.AnswerRepository;
import edu.muniz.askalien.model.Answer;
import edu.muniz.askalien.util.IndexingHelper;
import edu.muniz.askalien.util.LuceneHelper;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository repo;
	
	public List<Answer> searchAnswers(String question){
		IndexingHelper indexing = LuceneHelper.getInstance();
		Map<Integer,Float> scoredIds = indexing.getIdsFromSearch(question);
		Set<Integer> ids = scoredIds.keySet();
				
		List<AnswerSummary> answersDtos = repo.findByIdIn(ids);
		
		List<Answer> answers = new ArrayList<>();
		
		for(AnswerSummary answerDto:answersDtos){
			Answer answer = new Answer(answerDto.getId(),answerDto.getSubject());
			answer.setScore(scoredIds.get(answer.getId()));
			answers.add(answer);
		}	
				
		Set<Answer> orderdAnswers = new TreeSet<Answer>(answers);
		return new ArrayList<Answer>(orderdAnswers);
	}
	
}
