package Model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Venda {
	
	private int idVenda;
	private Date dataVenda;
	private Date horaVenda;
	private String pdv;
	private BigDecimal totalVenda;
	private BigDecimal trocoVenda;
	private BigDecimal valorRecebido;
	private Usuario funcionario;
	private List<ItemVenda> itensVenda;
	private List<FormaPagamento> formasPagamento;
	
	public Venda() {
	}
	
	public int getIdVenda() {
		return idVenda;
		
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	public Date getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	public Date getHoraVenda() {
		return horaVenda;
	}
	public void setHoraVenda(Date horaVenda) {
		this.horaVenda = horaVenda;
	}
	public String getPdv() {
		return pdv;
	}
	public void setPdv(String pdv) {
		this.pdv = pdv;
	}
	public BigDecimal getTotalVenda() {
		return totalVenda;
	}
	public void setTotalVenda(BigDecimal totalVenda) {
		this.totalVenda = totalVenda;
	}
	public BigDecimal getTrocoVenda() {
		return trocoVenda;
	}
	public void setTrocoVenda(BigDecimal trocoVenda) {
		this.trocoVenda = trocoVenda;
	}
	public BigDecimal getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(BigDecimal valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public Usuario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Usuario funcionario) {
		this.funcionario = funcionario;
	}
	

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public List<FormaPagamento> getFormasPagamento() {
		return formasPagamento;
	}

	public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
		this.formasPagamento = formasPagamento;
	}
	
	
	

}
