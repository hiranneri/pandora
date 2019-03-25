package Model;

import java.util.Date;

public class ParametrosPesquisa {

	private String parametro;
	//public String[]parametros; -> Versão 2.0
	private String valor;
	private Date data;
	private java.sql.Date dataSQL;
	private static ParametrosPesquisa parametros;
	private Date dataInicio;
	private Date dataFim;
	
	public ParametrosPesquisa() {
		
	}
	
	public String getParametro() {
		return parametro;
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	public String getValor() {
		return valor;
	}
	public Date  getData() {
		return data;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public void setData(Date valor) {
		this.data = valor;
	}

	public java.sql.Date getDataSQL() {
		return dataSQL;
	}
	public void setDataSQL(java.sql.Date data) {
		dataSQL = data;
	}
	public static synchronized ParametrosPesquisa getInstance() {
		if(parametros==null) {
			parametros = new ParametrosPesquisa();
		}
		return parametros;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	
}
