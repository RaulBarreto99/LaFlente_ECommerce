package com.xnexus.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Carrinho {
	
	public Carrinho() {
		this.itens = new ArrayList<ItemCarrinho>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToMany(cascade  = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private List<ItemCarrinho> itens;
	
	
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ItemCarrinho> getItens() {
		return itens;
	}

	public void setItens(List<ItemCarrinho> itens) {
		this.itens = itens;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void addItem(ItemCarrinho item) {
		itens.add(item);
	}
	
}
