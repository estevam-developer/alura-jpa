package br.com.estevam.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

	public Produto buscarProdutoComCategoria(Long id) {
		return em.createNamedQuery("Produto.BuscarProdutoComCategoria", Produto.class).setParameter("Id", id).getSingleResult();
	}
	
	public List<Produto> buscarProdutoPorCriterios(String nome, BigDecimal preco, LocalDate dataCadastro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filtros = builder.and();
		
		if (nome != null && !nome.trim().isEmpty()) {
			filtros = builder.and(filtros, builder.like(from.get("nome"), nome + "%"));
		}
		
		if (preco != null) {
			filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
		}
		
		if (dataCadastro != null) {
			filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
		}

		query.where(filtros);
		
		return em.createQuery(query).getResultList();
		
	}
}
