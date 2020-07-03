package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExampleController {

	@RequestMapping("example")
	
	public String example() {
		System.out.println("Inside action controller");
		return "example.jsp";
	}
}
