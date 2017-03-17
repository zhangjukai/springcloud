package com.fcore.cloud.config.client;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@RefreshScope
@EnableDiscoveryClient
public class Application {
	@Value("${spring.datasource.type}")
	private String value;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public @PostConstruct void init() {
		System.out.println(value);
	}
	
	@RequestMapping("/test")
	public String test(){
		System.out.println("===================="+value+"============================");
		return value;
	}
}
