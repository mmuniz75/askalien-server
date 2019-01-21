package edu.muniz.askalien.client.response;

import java.util.List;

import lombok.Data;

@Data
public class HitsDTO {
	
	private List<HitDTO> hits;
}
