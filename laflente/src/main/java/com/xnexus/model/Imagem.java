package com.xnexus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;

@Entity
public class Imagem {
	
	private static final long serialVersionUID = 1L;

	public Imagem( String nome, byte[] imagem, long codigoProduto, String extensao) {
		this.nome = nome;
		this.imagem = imagem;
		this.codigoProduto = codigoProduto;
		this.extensao = extensao;
	}
	
	public Imagem( ) {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	
	@Lob
	@Type(type = "org.hibernate.type.ImageType")
	private byte[] imagem;
	
	private String extensao;
	
	private long codigoProduto;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public long getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

}
