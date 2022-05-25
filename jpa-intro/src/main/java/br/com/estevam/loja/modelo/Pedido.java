package br.com.estevam.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQuery(name = "Pedido.RelatorioVendas", query = "SELECT NEW br.com.estevam.loja.vo.RelatorioVendasVo(prod.nome, "
														+ "SUM(item.quantidade) as quant, "
														+ "MAX(ped.data)) "
													+ "FROM Pedido ped "
														+ "JOIN ped.itensPedido item "
														+ "JOIN item.produto prod "
													+ "GROUP BY prod.nome "
													+ "ORDER BY quant DESC")

@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private LocalDate data = LocalDate.now();
	
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itensPedido = new ArrayList<>();
	
	public Pedido() {
	}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void adicionarItem(Integer quantidade, Produto produto) {
		ItemPedido item = new ItemPedido(quantidade, produto, this);
		
		itensPedido.add(item);
		valorTotal = valorTotal.add(item.getValor());
	}
}