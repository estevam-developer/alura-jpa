package br.com.estevam.loja.app;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.estevam.loja.dao.CategoriaDao;
import br.com.estevam.loja.dao.ClienteDao;
import br.com.estevam.loja.dao.EntityManagerFactory;
import br.com.estevam.loja.dao.PedidoDao;
import br.com.estevam.loja.dao.ProdutoDao;
import br.com.estevam.loja.modelo.Categoria;
import br.com.estevam.loja.modelo.Cliente;
import br.com.estevam.loja.modelo.Pedido;
import br.com.estevam.loja.modelo.Produto;

public class Principal {

	public static void main(String[] args) {

		popularTabelas();
		
		consultas();
		
	}

	private static void consultas() {
		EntityManager em = EntityManagerFactory.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);
		
		Produto produto = produtoDao.buscarPorId(1L);
		System.out.println(produto.getNome());
		
		List<Produto> produtos;
		
		System.out.println(" -- BUSCAR TODOS -- ");
		produtos = produtoDao.buscarTodos();
		produtos.forEach(p -> System.out.println(p.getDescicao()));
		
		System.out.println(" -- BUSCAR POR NOME -- ");
		produtos = produtoDao.buscarPorNome("Xia");
		produtos.forEach(p -> System.out.println(p.getDescicao()));
		
		System.out.println(" -- CONSULTAS JPQL -- ");
		
		System.out.println("TOTAL VENDIDO = " + pedidoDao.valorTotalVendido());

	}

	private static void popularTabelas() {
		EntityManager em = EntityManagerFactory.getEntityManager();

		CategoriaDao categoriaDao = new CategoriaDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);
		
		Categoria celulares = new Categoria("CELULARES");
		Categoria informatica = new Categoria("INFORMATICA");
		Categoria eletrodomesticos = new Categoria("ELETRODOMESTICOS");
		
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
		
		Produto notebookDell = new Produto(
				"Novo Notebook Inspiron 15 3000", 
				"Notebook de 15,6\" com as mais recentes opções de processador Intel® Core™ de 11ª geração", 
				new BigDecimal(2849), 
				informatica);

		
		Produto notebookLenovo = new Produto(
				"Notebook Lenovo", 
				"Notebook Lenovo 15.6 I3-10110u 4gb 256gbssd Linux - 82bss00100", 
				new BigDecimal(5148.71), 
				informatica);

		Cliente cliente = new Cliente("Leonardo Alessandro Estevam", "004.090.099-11");
		
		Pedido pedido_1 = new Pedido(cliente);
		
		pedido_1.adicionarItem(2, celularSansung);
		pedido_1.adicionarItem(3, notebookLenovo);

		Pedido pedido_2 = new Pedido(cliente);

		pedido_2.adicionarItem(2, celularXiaomi);
		pedido_2.adicionarItem(1, notebookDell);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(informatica);
		
		produtoDao.cadastrar(celularXiaomi);
		produtoDao.cadastrar(celularSansung);
		produtoDao.cadastrar(notebookDell);
		produtoDao.cadastrar(notebookLenovo);
		
		clienteDao.cadastrar(cliente);
		pedidoDao.cadastrar(pedido_1);
		pedidoDao.cadastrar(pedido_2);
		
		em.getTransaction().commit();
				
		em.close();
		
	}
}
