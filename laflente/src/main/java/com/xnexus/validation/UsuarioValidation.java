package com.xnexus.validation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.xnexus.model.Endereco;
import com.xnexus.model.UsuarioECommerce;

public class UsuarioValidation {
	public List<String> validarUsuario(UsuarioECommerce usuario, Optional<UsuarioECommerce> emailOptional,
			Optional<UsuarioECommerce> cpfOptional) {
		List<String> erros = new ArrayList<String>();

		String[] nomeSplit = usuario.getNome().split(" ");

		boolean fiscal = false;

		for (Endereco endereco : usuario.getEnderecos()) {
			if (endereco.isFiscal()) {
				fiscal = true;
			}
		}

		if (!fiscal) {
			erros.add("Se deve ter no minimo 1 endereço Fiscal.");
		}

		if (nomeSplit.length < 2) {
			erros.add("O nome deve no minimo ter duas palavras");
		}

		if (cpfOptional.isPresent()) {
			erros.add("Usuario com este CPF já existente");
		}

		if (emailOptional.isPresent()) {
			erros.add("Usuario com este Email já existente");
		}
		
		return erros;
	}
	
	public List<String> validarPutUsuario(UsuarioECommerce usuario) {
		List<String> erros = new ArrayList<String>();

		String[] nomeSplit = usuario.getNome().split(" ");

		if (nomeSplit.length < 2) {
			erros.add("O nome deve no minimo ter duas palavras");
		}
		
		return erros;
	}
}
