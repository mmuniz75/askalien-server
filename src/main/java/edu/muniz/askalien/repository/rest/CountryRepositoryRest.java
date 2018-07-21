package edu.muniz.askalien.repository.rest;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Lazy
public class CountryRepositoryRest {
	
	  @Autowired
	  private RestTemplate restTemplate;
	  
	  @Value("${rest.country.server}")
	  private String serverRest;
	  
	  @Value("${rest.country.access.token}")
	  private String accessToken;

	  public String getCountryByIP(String ip) {
	      String country = "Unknown Country";
		  try{
		    final UriComponentsBuilder builder =
		        UriComponentsBuilder.fromHttpUrl(serverRest + String.format("?access_key=%s&format=1",accessToken));
	
		    ResponseEntity<CountryResponse> response = restTemplate.exchange(builder.buildAndExpand(Collections.singletonMap("ip", ip)).encode().toUri(), HttpMethod.GET, new HttpEntity<>(createHttpHeaders()),
		    		CountryResponse.class);
		    
		    country = response.getBody().getCountry().toUpperCase();
		  }catch(Exception ex){
			System.out.println("not possible find country for IP = " + ip);
			ex.printStackTrace();
		  }
	    
	    return country;
	  }
	  
	  private HttpHeaders createHttpHeaders() {
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("charset", "UTF-8");
	    return headers;
	  }
}
