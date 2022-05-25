package br.com.estevam.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.estevam.loja.modelo.Pedido;
import br.com.estevam.loja.vo.RelatorioVendasVo;

public class PedidoDao {
	
	private EntityManager em;
	
	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		em.persist(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
	
	public List<RelatorioVendasVo> relatorioVendas() {
		return em.createNamedQuery("Pedido.RelatorioVendas", RelatorioVendasVo.class).getResultList() ;
	}

}
