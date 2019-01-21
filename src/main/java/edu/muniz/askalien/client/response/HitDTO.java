package edu.muniz.askalien.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HitDTO {
	
	@JsonProperty("_id")
	private Integer id;
	
	@JsonProperty("_source")
	private SourceDTO source;


}
