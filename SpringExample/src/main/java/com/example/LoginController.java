package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping("/user")
	public ModelAndView login(@RequestParam("userName")String name, @RequestParam("password")String password) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("display");
		mv.addObject("loggedIn",name);
		System.out.println("I am inside");
		return mv;
	}
}
