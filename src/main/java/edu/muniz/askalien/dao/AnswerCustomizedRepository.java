package edu.muniz.askalien.dao;

import java.util.List;

import edu.muniz.askalien.model.Answer;

public interface AnswerCustomizedRepository  {

	public List<Answer> findByKeyWorlds(String keywords); 

}
