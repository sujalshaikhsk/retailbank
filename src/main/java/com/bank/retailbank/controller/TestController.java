package com.bank.retailbank.controller;

import java.util.ArrayList;
import java.util.List;

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

	@PostMapping(value ="/test2" )
	public String test2(){
		return "test success";
	}
	
	@PostMapping(value ="/testpost" )
	public List testPost(){
		List<String> list =new ArrayList();
		list.add("XXXX");
		list.add("XYZ");
		return list;
	}
	
}
