package com.xnexus.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;

	private String nome;

	private int quantidade;

	@ManyToMany(cascade  = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private List<PalavraChave> palavraChave;

	
	private String descricaoCurta;

	private String descricaoDetalhada;

	private double preco;

	@OneToMany
	private List<Imagem> imagens;

	private String status;

	
	@OneToMany(cascade= CascadeType.ALL)
	private List<PerguntaResposta> perguntaResposta;
	

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public List<PalavraChave> getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(List<PalavraChave> palavraChave) {
		this.palavraChave = palavraChave;
	}

	public String getDescricaoCurta() {
		return descricaoCurta;
	}

	public void setDescricaoCurta(String descricaoCurta) {
		this.descricaoCurta = descricaoCurta;
	}

	public String getDescricaoDetalhada() {
		return descricaoDetalhada;
	}

	public void setDescricaoDetalhada(String descricaoDetalhada) {
		this.descricaoDetalhada = descricaoDetalhada;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<PerguntaResposta> getPerguntaResposta() {
		return perguntaResposta;
	}

	public void setPerguntaResposta(List<PerguntaResposta> perguntaResposta) {
		this.perguntaResposta = perguntaResposta;
	}
	
	public List<Imagem> getImagem() {
		return imagens;
	}

	public void setImagem(List<Imagem> imagem) {
		this.imagens = imagem;
	}

	public void addImagem(Imagem imagem){
		imagens.add(imagem);
	}
	
	
}