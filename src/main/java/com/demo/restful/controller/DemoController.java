package com.demo.restful.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/demo")
public class DemoController {

	@PostMapping("")
	public String createPackage(@RequestBody String content) {
		
		//insert content to DB?
		
		return "abc";
	}
	
	@GetMapping("{id}")
	public String getStatus(@PathVariable("id") String id) {
		return "demo "+id;
	}
}
