package com.xnexus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xnexus.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Long>{

	List<Pedido> findByIdUsuario(long idUsuario);
}
