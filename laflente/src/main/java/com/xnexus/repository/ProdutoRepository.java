package com.xnexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xnexus.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{
	
	

}
