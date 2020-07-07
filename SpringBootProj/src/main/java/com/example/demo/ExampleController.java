package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExampleController {

	@RequestMapping("example")
	public ModelAndView example(Person person) {
		System.out.println("Inside action controller");
		ModelAndView mv= new ModelAndView();
		mv.addObject("person",person);
		mv.setViewName("example");
		return mv;
	}
}
