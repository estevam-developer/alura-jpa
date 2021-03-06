package br.com.estevam.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name = "Produto.BuscarProdutoComCategoria", query = "SELECT prod FROM Produto AS prod JOIN FETCH prod.categoria WHERE prod.id = :Id")
@Entity
@Table(name = "produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descicao;
	private BigDecimal preco;
	private LocalDate dataCadastro = LocalDate.now();
	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoria;

	public Produto() {}

	public Produto(String nome, String descicao, BigDecimal preco, Categoria categoria) {
		this.nome = nome;
		this.descicao = descicao;
		this.preco = preco;
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescicao() {
		return descicao;
	}

	public void setDescicao(String descicao) {
		this.descicao = descicao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", preco=" + preco + ", dataCadastro=" + dataCadastro + "]";
	}

}
