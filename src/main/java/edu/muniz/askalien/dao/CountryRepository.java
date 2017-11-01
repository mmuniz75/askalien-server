package edu.muniz.askalien.dao;

import org.springframework.data.repository.CrudRepository;

import edu.muniz.askalien.model.Country;

public interface CountryRepository extends CrudRepository<Country, Integer>{
	
	Country findByIp(String ip); 

}
