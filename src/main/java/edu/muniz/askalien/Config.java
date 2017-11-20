package edu.muniz.askalien;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class Config {
	
	 @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**/**")
	                /*
	                .allowedOrigins("http://askalien.men",
	                				"http://localhost:4200",
	                				"http://localhost",
	                				"http://aws-website-askalien-8enqo.s3-website-us-east-1.amazonaws.com",
	                				"https://dtlfems0yypcj.cloudfront.net",
	                				"http://askalien-server.s3-website-us-east-1.amazonaws.com",
	                				"https://immense-depths-11692.herokuapp.com")
	                 */
	                ;
	            }
	        };
	    }

}
