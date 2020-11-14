package com.xnexus.controller.RestController;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xnexus.model.Carrinho;
import com.xnexus.model.ItemCarrinho;
import com.xnexus.repository.CarrinhoRepository;

@RestController
@RequestMapping("/carrinho-rest")
public class CarrinhoRestController {

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Transactional
	@PostMapping
	public ResponseEntity<?> addItem(@RequestBody ItemCarrinho item) {

		Optional<Carrinho> optional = carrinhoRepository.findByStatus("ABERTO");

		if (optional.isPresent()) {
			Carrinho carrinho = optional.get();

			carrinho.addItem(item);
			carrinhoRepository.flush();
			return ResponseEntity.created(null).build();
		} else {
			Carrinho carrinho = new Carrinho();
			carrinho.setStatus("ABERTO");
			carrinho.addItem(item);

			carrinhoRepository.save(carrinho);

			return ResponseEntity.created(null).build();
		}
	}
	
	@GetMapping
	public List<Carrinho> getCarrinho() {
		List<Carrinho> carrinhos = carrinhoRepository.findAll();
		
		return carrinhos;
	}
}
