package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.PersonRepo;
import com.example.demo.model.Person;

@Controller
public class SampleController {

@Autowired	
PersonRepo repo;

@RequestMapping("/")	
 public String welcome() {
	
	 return "welcome.jsp";
 }

@RequestMapping("/addMember")
public String addPerson(Person per) {
	repo.save(per);
	return "welcome.jsp";
}
}
