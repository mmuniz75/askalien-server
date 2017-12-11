package edu.muniz.askalien.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.muniz.askalien.model.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Integer>,AnswerCustomizedRepository {
	
	@Query("select answer from Answer answer JOIN FETCH answer.video video where answer.id=?1")
	public Answer findAnswerById(Integer id);
	
	
	@Query("select answer from Answer answer where answer.subject like ?1 or answer.content like ?1")
	public List<AnswerSummary> findByText(String text); 
	 

}
