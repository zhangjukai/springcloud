package com.fcore.boot.docker.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fcore.boot.docker.bean.ConfigBean;

import net.sf.json.JSONObject;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	@Autowired
	private ConfigBean bean;

	@RequestMapping(value = "restTem", method = RequestMethod.GET)
	public JSONObject restTem() {
		return JSONObject.fromObject(bean);
	}

	@RequestMapping(value="getValue",method = RequestMethod.GET)
	public String getValue(@Value("${config.bean.value}")String value) {
		return value;
	}
}
