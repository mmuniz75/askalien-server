package edu.muniz.askalien.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.muniz.askalien.client.response.SearchResponse;


@FeignClient(name = "elastick-search-askalien", url = "${uri.elasticksearch.host}")
public interface ElastickSearchClient {
	
	@GetMapping(value = "/answers-v3/_search?filter_path=hits.hits._id,hits.hits._source.subject")
	public SearchResponse findByKeyWorlds(@RequestParam(name="q") String question); 

}
