package Model;

import java.math.BigDecimal;

public class ItemVenda {
	
	private int id;
	private int nrItem;
	private int quantidade;
	private BigDecimal vltotalunit;
	private Produto produto;
	private Venda venda;
	
	
	public ItemVenda() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNrItem() {
		return nrItem;
	}
	public void setNrItem(int nrItem) {
		this.nrItem = nrItem;
	}

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public BigDecimal getSubTotal() {
		
		BigDecimal quantidade = new BigDecimal(getQuantidade());
		this.vltotalunit = this.produto.getprecovenda().multiply(quantidade);
		return vltotalunit;
		
	}
	public void setVltotalunit(BigDecimal vltotalunit) {
		this.vltotalunit = vltotalunit;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	
	
	
}
