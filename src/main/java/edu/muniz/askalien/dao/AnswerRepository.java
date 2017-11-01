package edu.muniz.askalien.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.muniz.askalien.model.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Integer> {
	
	List<AnswerSummary> findByIdIn(Collection<Integer> ids); 
	
	@Query("select answer from Answer answer JOIN FETCH answer.video video where answer.id=?1")
	public Answer findAnswerById(Integer id);

}
