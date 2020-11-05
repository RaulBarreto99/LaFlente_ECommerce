package com.xnexus.controller.RestController;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xnexus.model.Endereco;
import com.xnexus.model.UsuarioECommerce;
import com.xnexus.repository.UsuarioRepository;
import com.xnexus.validation.UsuarioValidation;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	public ResponseEntity<?> cadastrarUsuario(@RequestBody UsuarioECommerce usuario){
		
		Optional<UsuarioECommerce> emailOptional = usuarioRepository.findByEmail(usuario.getEmail());
		Optional<UsuarioECommerce> cpfOptional = usuarioRepository.findByCpf(usuario.getCpf());
		
		UsuarioValidation validation = new UsuarioValidation();
		
		List<String> erros = validation.validarUsuario(usuario, emailOptional, cpfOptional);
		
		if(erros.size() > 0) {
			return ResponseEntity.badRequest().body(erros);
		}
		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		usuario.setSenha(bcrypt.encode(usuario.getPassword()));
		usuarioRepository.save(usuario);
		
		return ResponseEntity.created(null).build();
	}
	
	
}
