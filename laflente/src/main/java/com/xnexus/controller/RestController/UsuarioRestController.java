package com.xnexus.controller.RestController;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xnexus.model.Endereco;
import com.xnexus.model.UsuarioECommerce;
import com.xnexus.repository.EnderecoRepository;
import com.xnexus.repository.UsuarioRepository;
import com.xnexus.validation.UsuarioValidation;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Transactional
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
	
	@Transactional
	@PostMapping("/enderecos/{idUsuario}")
	public ResponseEntity<?> cadastrarEndereco(@RequestBody Endereco endereco, @PathVariable long idUsuario){

		endereco.setId(0);
		enderecoRepository.save(endereco);
		
		Optional<UsuarioECommerce> optional = usuarioRepository.findById(idUsuario);
		
		if(optional.isPresent()) {
			UsuarioECommerce usuario = optional.get();
			
			usuario.addEndereco(endereco);
			usuarioRepository.flush();
		}
		
		return ResponseEntity.created(null).build();
	}
	
	@GetMapping
	public ResponseEntity<?> getUsuario(){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String nome;    

		if (principal instanceof UserDetails) {
		    nome = ((UserDetails)principal).getUsername();
		} else {
		    nome = principal.toString();
		}
		
		System.out.println(nome);
		
		Optional<UsuarioECommerce> usuOptional = usuarioRepository.findByEmail(nome);
		
		if(usuOptional.isPresent()) {
			return ResponseEntity.ok().body(usuOptional.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	@PutMapping
	public ResponseEntity<?> alterarUsuario(@RequestBody UsuarioECommerce usuario){
		
		Optional<UsuarioECommerce> optional = usuarioRepository.findByEmail(usuario.getEmail());

		UsuarioValidation validation = new UsuarioValidation();
		
		List<String> erros = validation.validarPutUsuario(usuario);
		
		if(erros.size() > 0) {
			return ResponseEntity.badRequest().body(erros);
		}
		
		if(optional.isPresent()) {
			UsuarioECommerce usuarioBD = optional.get();
			
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			
			
			usuarioBD.setNome(usuario.getNome());
			usuarioBD.setTelefone(usuario.getTelefone());
			usuarioBD.setSenha(bcrypt.encode(usuario.getPassword()));
			usuarioBD.setEnderecos(usuarioBD.getEnderecos());
			
			usuarioRepository.flush();
		}
		
		
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/enderecos/{id}")
	@Transactional
	public ResponseEntity<?> removerEndereco(@PathVariable Long id) {

		Optional<Endereco> optional = enderecoRepository.findById(id);

		if (optional.isPresent()) {
			
			enderecoRepository.delete(optional.get());
			enderecoRepository.flush();
			

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
