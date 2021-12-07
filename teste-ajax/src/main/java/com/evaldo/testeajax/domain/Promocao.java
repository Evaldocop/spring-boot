package com.evaldo.testeajax.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@SuppressWarnings("serial")
@Entity
@Table(name = "promocoes")
public class Promocao implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "link_promocao", nullable = false)
	private String linkPromocao;
	
	@Column(name = "site", nullable = false)
	private String site;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "link_imagem", nullable = false)
	private String linkImagem;
	
	@Column(name="likes")
	private int likes;
	
	
	@Column(name = "data_cadastro", nullable = false)
	private LocalDateTime dtCadastro;
	
	@ManyToOne
	@JoinColumn(name = "categoria_fk")
	private Categoria categoria;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getLinkPromocao() {
		return linkPromocao;
	}


	public void setLinkPromocao(String linkPromocao) {
		this.linkPromocao = linkPromocao;
	}


	public String getSite() {
		return site;
	}


	public void setSite(String site) {
		this.site = site;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getLinkImagem() {
		return linkImagem;
	}


	public void setLinkImagem(String linkImagem) {
		this.linkImagem = linkImagem;
	}


	public int getLikes() {
		return likes;
	}


	public void setLikes(int likes) {
		this.likes = likes;
	}


	public LocalDateTime getDtCadastro() {
		return dtCadastro;
	}


	public void setDtCadastro(LocalDateTime dtCadastro) {
		this.dtCadastro = dtCadastro;
	}


	@Override
	public String toString() {
		return "Promocao [id=" + id + ", title=" + title + ", linkPromocao=" + linkPromocao + ", site=" + site
				+ ", descricao=" + descricao + ", linkImagem=" + linkImagem + ", likes=" + likes + ", dtCadastro="
				+ dtCadastro + ", categoria=" + categoria + "]";
	}

}
