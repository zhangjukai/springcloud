package com.fcore.cloud.ribbon.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ComputeService {
	
	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "addServiceFallback")
	public String addService() {
        /*HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        
        JSONObject jsonObj = JSONObject.fromObject(params);
        
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);

        String result = restTemplate.postForObject(url, formEntity, String.class);*/
		return restTemplate.getForEntity("http://COMPUTE-SERVICE/add?a=10&b=20", String.class).getBody();
	}

	public String addServiceFallback() {
		return "error";
	}
}
