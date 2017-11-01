package edu.muniz.askalien.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.muniz.askalien.dao.CountryRepository;
import edu.muniz.askalien.model.Country;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryServiceTests {
	
	@Autowired
	CountryService service;
	
	@Autowired
	private CountryRepository repo;
	
	@Test
	public void findCountryExists(){
		String country = service.getCountryFromIP("186.220.5.148");
		assertEquals(country,"BRAZIL");
	}
	
	
	@Test
	public void findCountryNotExists(){
		final String IP = "1.1.1.1";
		final String COUNTRY = "AUSTRALIA";
		
		String countryName = service.getCountryFromIP(IP);
		assertEquals(countryName,COUNTRY);
		
		Country country = repo.findByIp(IP);
		assertEquals(countryName,COUNTRY);
		
		repo.delete(country.getId());
	}

}
