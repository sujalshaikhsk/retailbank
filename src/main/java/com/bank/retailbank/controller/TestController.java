package com.bank.retailbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping(value ="/test" )
	public String test(){
		return "SUCCESS";
	}
	
	@PostMapping(value ="/test1" )
	public String test1(){
		return "test success";
	}
	
}
