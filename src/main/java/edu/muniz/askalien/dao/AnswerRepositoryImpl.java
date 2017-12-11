package edu.muniz.askalien.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import edu.muniz.askalien.model.Answer;

@Component
public class AnswerRepositoryImpl implements AnswerCustomizedRepository {
	
	@PersistenceContext
	private EntityManager em;
	 
	
	public List<Answer> findByKeyWorlds(String keywords){
		
		//String sql = "select a.id,a.subject from Answer a where textsearchable_index_col @@ to_tsquery(:keyword) order by ts_rank_cd(textsearchable_index_col, to_tsquery(:keyword)) desc"; 
		String sql = "select a.id,a.subject from Answer a where to_tsvector(subject || ' ' || content) @@ to_tsquery(:keyword) order by ts_rank_cd(to_tsvector(subject || ' ' || content), to_tsquery(:keyword)) desc";
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("keyword", keywords);
		
		List<Object[]> tuples = query.getResultList();
			
		return tuples.stream().map(tuple -> new Answer((Integer)tuple[0] ,tuple[1].toString())
				                   ).collect(Collectors.toList());
	}
}
