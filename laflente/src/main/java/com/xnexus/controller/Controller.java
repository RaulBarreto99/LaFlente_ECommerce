package com.xnexus.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

	@GetMapping
	public ModelAndView hello() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String nome;

		if (principal instanceof UserDetails) {
			nome = ((UserDetails) principal).getUsername();
		} else {
			nome = principal.toString();
		}

		ModelAndView mv = new ModelAndView("index.html");

		return mv;
	}

	@RequestMapping("/detalharProduto/{codigo}")
	@GetMapping
	public ModelAndView detalharProduto(@PathVariable Long codigo) {
		ModelAndView mv = new ModelAndView("detalharProduto.html");
		mv.addObject("codigo", codigo);
		return mv;
	}

	@RequestMapping("/produto")
	@GetMapping
	public ModelAndView produto() {
		ModelAndView mv = new ModelAndView("produtos.html");
		return mv;
	}

	@RequestMapping("/carrinho")
	@GetMapping
	public ModelAndView carrinho() {
		ModelAndView mv = new ModelAndView("carrinho.html");
		return mv;
	}

	@RequestMapping("/login")
	@GetMapping
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login.html");
		return mv;
	}

	@RequestMapping("/criarConta")
	@GetMapping
	public ModelAndView criarConta() {
		ModelAndView mv = new ModelAndView("criarConta.html");
		return mv;
	}

	@RequestMapping("/editarConta")
	@GetMapping
	public ModelAndView editarConta() {
		ModelAndView mv = new ModelAndView("editarConta.html");
		return mv;
	}

	@RequestMapping("/contato")
	@GetMapping
	public ModelAndView contato() {
		ModelAndView mv = new ModelAndView("contato.html");
		return mv;
	}

	@RequestMapping("/compra")
	@GetMapping
	public ModelAndView compra() {
		ModelAndView mv = new ModelAndView("compra.html");
		return mv;
	}

	@RequestMapping("/sucesso")
	@GetMapping
	public ModelAndView sucesso() {
		ModelAndView mv = new ModelAndView("sucesso.html");
		return mv;
	}

}
