package com.xnexus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xnexus.model.UsuarioECommerce;


public interface UsuarioRepository extends JpaRepository<UsuarioECommerce, Long> {

	Optional<UsuarioECommerce> findByEmail(String email);
	Optional<UsuarioECommerce> findByCpf(String cpf);
}
