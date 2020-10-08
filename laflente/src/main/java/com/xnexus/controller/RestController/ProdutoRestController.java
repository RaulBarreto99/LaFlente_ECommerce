package com.xnexus.controller.RestController;

import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xnexus.model.Produto;
import com.xnexus.repository.ProdutoRepository;

@RequestMapping("/produto")
@RestController
public class ProdutoRestController {
	
	@Autowired
	public ProdutoRepository produtoRepository;

	@GetMapping("/{codigo}")
	public ResponseEntity<Produto> getProduto(@PathVariable long codigo) {
		Optional<Produto> optional = produtoRepository.findById(codigo);

		if (optional.isPresent()) {
			Produto produtos = optional.get();

			return ResponseEntity.ok(produtos);
		}

		return ResponseEntity.notFound().build();
	}
}
