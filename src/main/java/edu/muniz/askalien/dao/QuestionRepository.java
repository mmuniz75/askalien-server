package edu.muniz.askalien.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.muniz.askalien.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer>{
	
	@Query("select question from Question question JOIN FETCH question.answer answer where question.id=?1")
	public Question findQuestionById(Integer id);
	
	@Query("select question from Question question JOIN FETCH question.answer answer where answer.id=?1 and text=?2")
	public Question findQuestionByAnswerIdAndText(Integer anwerId,String question);

}
