package com.xnexus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xnexus.model.Carrinho;
import com.xnexus.model.Produto;


public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{
	Optional<Carrinho> findByStatus(String status);
}
