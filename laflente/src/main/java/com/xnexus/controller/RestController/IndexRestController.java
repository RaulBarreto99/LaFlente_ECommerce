package com.xnexus.controller.RestController;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.xnexus.model.Produto;
import com.xnexus.repository.ProdutoRepository;

@RestController
@RequestMapping("/index")
public class IndexRestController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos() {
		List<Produto> produtos = produtoRepository.findByStatus("ATIVO");

		return ResponseEntity.ok(produtos);
	}
	

}


