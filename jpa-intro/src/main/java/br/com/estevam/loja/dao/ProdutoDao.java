package br.com.estevam.loja.dao;

import javax.persistence.EntityManager;

import br.com.estevam.loja.modelo.Produto;

public class ProdutoDao {
	
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		em.persist(produto);
	}
}
