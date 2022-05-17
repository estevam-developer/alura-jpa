package br.com.estevam.loja.app;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.estevam.loja.modelo.Produto;

public class Principal {

	public static void main(String[] args) {
	
		Produto celular = new Produto();
		
		celular.setNome("Xiaome Redmi Note 9");
		celular.setDescicao("Smartphone Xiaomi Redmi Note 9 - 64GB/4GB Ram Versão Global");
		celular.setPreco(new BigDecimal(1149));
		
		/**********************************************************************************/
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(celular);
		em.getTransaction().commit();
		em.close();

	}

}
