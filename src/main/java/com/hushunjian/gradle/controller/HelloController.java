package com.hushunjian.gradle.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController("HelloController")
@RequestMapping(value = "/hello")
@Api(value = "Sample", description = "示例",produces = MediaType.ALL_VALUE)
public class HelloController extends BaseController {

	@ResponseBody
	@GetMapping(value = "sayHello")
	public Object sayHello(){
		return success("Hello World!");
	}
}
