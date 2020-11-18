package com.xnexus.controller.RestController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xnexus.model.UsuarioECommerce;
import com.xnexus.repository.UsuarioRepository;

@RestController
@RequestMapping("/compra-rest")
public class CompraRestController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public ResponseEntity<?> getUsuario() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String nome;

		if (principal instanceof UserDetails) {
			nome = ((UserDetails) principal).getUsername();
		} else {
			nome = principal.toString();
		}

		System.out.println(nome);

		Optional<UsuarioECommerce> usuOptional = usuarioRepository.findByEmail(nome);

		if (usuOptional.isPresent()) {
			return ResponseEntity.ok().body(usuOptional.get().getEnderecos());
		}

		return ResponseEntity.notFound().build();
	}
}
