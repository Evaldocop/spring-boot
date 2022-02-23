package com.evaldo.curso.boot.domain;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "ENDERECOS")
public class Endereco extends AbstractEntity<Long> {

	@Column( nullable = false,unique = true)
	private String logradouro;
	

	@Column( nullable = false,unique = true)
	private String bairro;
	
	

	@Column( nullable = false,unique = true)
	private String cidade;
	
	@Column( nullable = false, length = 9)
	private String cep;
	
	@Column
	private String complemento;
	
	@Column
	private Integer numero;
	
	@Column( nullable = false , length = 2)
	@Enumerated(EnumType.STRING)
	private UF uf;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}
	
	
	
	
	
}
