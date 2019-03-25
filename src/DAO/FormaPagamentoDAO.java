package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.FormaPagamento;

public class FormaPagamentoDAO {
	
	private Connection con;
	PreparedStatement ps = null;
	
	public List<Model.FormaPagamento> pesquisarTiposPagamento () {
		List<FormaPagamento> formas = new ArrayList<FormaPagamento>();
		this.con = GerenciadorDeConexoes.getConnection();
		String sql = "SELECT * FROM formapagamento";
		try { 
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				FormaPagamento forma = new FormaPagamento();
				forma.setNome(rs.getString("forma"));
				forma.setId(rs.getInt("id"));
				
				formas.add(forma);
			}
			GerenciadorDeConexoes.close(con, ps, rs);
			return formas;
		}catch (SQLException e) {
			if(this.con!=null) {
				System.err.print("Ocorreu um erro ao consultar o banco de dados "+e);
				GerenciadorDeConexoes.close(con);
			}
		}
		return null;	
	}

}
