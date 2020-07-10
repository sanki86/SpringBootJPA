package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public String addPerson(Person person) {
		repo.save(person);
		return "welcome.jsp";
	}

	@RequestMapping("/getMemberById")
	public ModelAndView getPerson(@RequestParam int id) {
		ModelAndView mv = new ModelAndView();
		System.out.println("Id is " + id);
		Person person = repo.findById(id).orElse(new Person());
		mv.addObject("personDetails", person);
		mv.setViewName("member.jsp");
		;
		return mv;
	}
}
