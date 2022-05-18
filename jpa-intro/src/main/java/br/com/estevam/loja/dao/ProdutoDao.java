package br.com.estevam.loja.dao;

import java.util.List;

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
	
	public void atualizar(Produto produto) {
		em.merge(produto);
	}

	public void remover(Produto produto) {
		produto = em.merge(produto);
		em.remove(produto);
	}
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}

	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome LIKE :nome";
		return em.createQuery(jpql, Produto.class).setParameter("nome", "%" + nome + "%").getResultList();
	}
}
