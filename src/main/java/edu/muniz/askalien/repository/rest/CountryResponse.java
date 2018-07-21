package edu.muniz.askalien.repository.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryResponse {
		
	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	@JsonProperty("country_name")
	private String Country;

}
