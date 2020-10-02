package com.xnexus.controller.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
		List<Produto> produtos = produtoRepository.findAll();

		return ResponseEntity.ok(produtos);
	}
	@GetMapping("/imagem/{codigo}")
	@ResponseBody
	public byte[] retornaImagem(@PathVariable Long codigo) {
		Optional<Produto> optional = produtoRepository.findById(codigo);

		if (optional.isPresent()) {
			Produto produtos = optional.get();

			File file = new File(produtos.getImagem());
			try {
				return Files.readAllBytes(file.toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}

}


