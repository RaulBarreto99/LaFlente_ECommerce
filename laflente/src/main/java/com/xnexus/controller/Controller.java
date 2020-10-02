package com.xnexus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping("/detalharProduto")
	@GetMapping
	public ModelAndView detalharProduto() {
		ModelAndView mv = new ModelAndView("detalharProduto.html");
		return mv;
	}
	@RequestMapping("/produto")
	@GetMapping
	public ModelAndView produto() {
		ModelAndView mv = new ModelAndView("produtos.html");
		return mv;
	}
	
	
}
