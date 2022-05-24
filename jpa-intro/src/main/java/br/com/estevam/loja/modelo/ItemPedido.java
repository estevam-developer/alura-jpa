package br.com.estevam.loja.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;
	private Integer quantidade;
	
	@ManyToOne
	private Pedido pedido;
	
	@ManyToOne
	private Produto produto;

	public ItemPedido() {
	}
	
	public ItemPedido(Integer quantidade, Produto produto, Pedido pedido) {
		this.quantidade = quantidade;
		setPedido(pedido);
		setProduto(produto);
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Pedido getPedido() {
		return pedido;
	}
	
	private void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Produto getProduto() {
		return produto;
	}

	private void setProduto(Produto produto) {
		this.produto = produto;
		this.precoUnitario = produto.getPreco();
	}

	public BigDecimal getValor() {
		return this.precoUnitario.multiply(new BigDecimal(this.quantidade));
	}
	
}
