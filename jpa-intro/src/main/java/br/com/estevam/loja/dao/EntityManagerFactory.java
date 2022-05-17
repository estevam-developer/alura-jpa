package br.com.estevam.loja.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactory {

	private static final javax.persistence.EntityManagerFactory emf;
	private static EntityManager em;

	static {
		emf = Persistence.createEntityManagerFactory("loja");
		em = emf.createEntityManager();
	}

	private EntityManagerFactory() {
	}

	public static EntityManager getEntityManager() {

		if (em.isOpen())
			return em;

		return emf.createEntityManager();
	}
}
