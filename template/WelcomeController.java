package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
	
	@RequestMapping(value = "/")
	public ModelAndView index(){
		
		ModelAndView model = new ModelAndView("index");
		String title = "Home";
		model.addObject("title", title);
		return model;
		
	}
	
	@RequestMapping(value = "/about")
	public ModelAndView about(){
		
		ModelAndView model = new ModelAndView("about");
		String title = "About";
		model.addObject("title", title);
		return model;
		
	}
	
	@RequestMapping(value = "/contact")
	public ModelAndView contact(){
		
		ModelAndView model = new ModelAndView("contact");
		String title = "Contact";
		model.addObject("title", title);
		return model;
		
	}
	
}
