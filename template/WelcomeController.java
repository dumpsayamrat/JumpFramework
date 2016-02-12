package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
	
	@RequestMapping(value = "/")
	public ModelAndView index(){
		
		ModelAndView model = new ModelAndView("index");		
		return model;
		
	}
	
	@RequestMapping(value = "/about")
	public ModelAndView about(){
		
		ModelAndView model = new ModelAndView("about");		
		return model;
		
	}
	
	@RequestMapping(value = "/contact")
	public ModelAndView contact(){
		
		ModelAndView model = new ModelAndView("contact");		
		return model;
		
	}
	
}
