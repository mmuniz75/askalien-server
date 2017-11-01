package edu.muniz.askalien.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.muniz.askalien.model.Country;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryRepositoryTests {
	
	@Autowired
	private CountryRepository repo;

	@Test
	public void testCreateCountry(){
		final String IP = "123.456.789";
		final String COUNTRY = "BRAZIL";
		
		Country country = new Country(IP,COUNTRY);
		repo.save(country);
		
		country = null;
		
		country = repo.findByIp(IP);		
		
		assertEquals(country.getIp(),IP);
		assertEquals(country.getCountry(),COUNTRY);
		repo.delete(country.getId());
	}

}
