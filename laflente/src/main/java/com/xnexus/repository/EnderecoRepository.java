package com.xnexus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xnexus.model.Endereco;
import com.xnexus.model.UsuarioECommerce;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
