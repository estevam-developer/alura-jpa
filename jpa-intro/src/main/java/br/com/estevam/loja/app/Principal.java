package br.com.estevam.loja.app;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.estevam.loja.dao.CategoriaDao;
import br.com.estevam.loja.dao.EntityManagerFactory;
import br.com.estevam.loja.dao.ProdutoDao;
import br.com.estevam.loja.modelo.Categoria;
import br.com.estevam.loja.modelo.Produto;

public class Principal {

	public static void main(String[] args) {

		cadastrarProdutos();
		
	}

	private static void cadastrarProdutos() {
		EntityManager em = EntityManagerFactory.getEntityManager();

		CategoriaDao categoriaDao = new CategoriaDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);

		// INCLUSAO
		
		Categoria celulares = new Categoria("INFORMATICA");
		Categoria informatica = new Categoria("INFORMATICA");
				
		Produto celularXiaomi = new Produto(
				"Xiaome Redmi Note 9",
				"Smartphone Xiaomi Redmi Note 9 - 64GB/4GB Ram Versão Global",
				new BigDecimal(1149),
				celulares);

		Produto celularSansung = new Produto(
				"Sansung Galaxy A71",
				"Smartphone Samsung Galaxy A71 128GB 4G Wi-Fi Tela 6.7'' Dual Chip 6GB RAM Câmera Quádrupla + Selfie 32MP - Preto",
				new BigDecimal(2114.91),
				celulares);

		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(informatica);
		
		produtoDao.cadastrar(celularXiaomi);
		produtoDao.cadastrar(celularSansung);
		
		// ALTERACAO

		em.clear();

		celulares.setNome("CELULARES");
		categoriaDao.atualizar(celulares);
		
		em.flush();
		
		// EXCLUSAO
					
		em.clear();
		
		categoriaDao.remover(informatica);
				
		em.getTransaction().commit();
		em.close();
	}

}
