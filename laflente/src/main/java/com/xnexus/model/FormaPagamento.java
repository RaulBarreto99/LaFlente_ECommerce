package com.xnexus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FormaPagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String numeroCartao;
	
	private String codigoSeguranca;
	
	private String dtVencimento;
	
	private String tipo;
	
	private long parcelas;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public String getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(String dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public long getParcelas() {
		return parcelas;
	}

	public void setParcelas(long parcelas) {
		this.parcelas = parcelas;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
