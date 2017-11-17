package edu.muniz.askalien.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.muniz.askalien.model.Answer;
import edu.muniz.askalien.model.Question;
import edu.muniz.askalien.service.QuestionService;

@RestController
<<<<<<< HEAD
=======
@CrossOrigin(origins = {"http://localhost","http://askalien.men","http://localhost:4200", "http://aws-website-askalien-8enqo.s3-website-us-east-1.amazonaws.com","https://dtlfems0yypcj.cloudfront.net"})
>>>>>>> 5d4b3659a819c680d08131e3ecfbadc3769b45af
public class QuestionRest {
	
	@Autowired
	QuestionService service;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("/answer/{id}")
	public Answer getAnswer(@PathVariable Integer id,@RequestParam String question){
		return service.getAnswer(id, getClientIp(), question);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/feedback")
	public void sendFeedBack(@RequestBody Question question){
		service.sendFeedback(question);
	}
	
	
	private String getClientIp() {
		String ip = request.getHeader("X-Real-IP");
		if (null != ip && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (null != ip && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {
			// get first ip from proxy ip
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}
}
