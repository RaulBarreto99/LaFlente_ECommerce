package com.xnexus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {
	
	@GetMapping
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView("index.html");
		return mv;
	}
}
