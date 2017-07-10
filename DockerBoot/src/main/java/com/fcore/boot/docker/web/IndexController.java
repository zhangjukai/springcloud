package com.fcore.boot.docker.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/index")  
	public String helloHtml(Map<String, Object> map,String str){ 
		System.out.println(str);
		map.put("hello", "from TemplateController.helloHtml");
		return "/index";
	}
}
