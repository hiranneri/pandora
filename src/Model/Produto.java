package Model;

import java.math.BigDecimal;
import java.util.Date;


public class Produto {
	
	private int id;
	private String nome;
	private BigDecimal precoVenda;
	private BigDecimal precoCompra;
	private Date dataCompra;
	private Date validade;
	private String observacao;
	
	public Produto() {
		
	}
	public Produto(String nome, Date validade, Date dataCompra) {
		this.nome = nome;
		
		this.validade = validade;
		this.dataCompra = dataCompra;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getprecovenda() {
		return precoVenda;
	}
	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}
	public BigDecimal getPrecoCompra() {
		return precoCompra;
	}
	public void setPrecoCompra(BigDecimal precoCompra) {
		this.precoCompra = precoCompra;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", precoVenda=" + precoVenda + ", precoCompra=" + precoCompra + ", dataCompra="
				+ dataCompra + ", validade=" + validade + ", observacao=" + observacao + "]";
	}
	
	public java.sql.Date getDateSQL(Date data) {
		java.sql.Date dataSQL = new java.sql.Date(data.getTime());
		return dataSQL;
	}
	
	public double getValorBR(String valorReal) {
		String valorStr = valorReal.replace(",",".");
		double valor = Double.parseDouble(valorStr);
		return valor;
	}
	/*
	public static void main (String args[]) {
		String valor = "2,75";
		System.out.println(Produto.getValor(valor));
		
	}
*/
}
