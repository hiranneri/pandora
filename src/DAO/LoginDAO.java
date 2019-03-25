package DAO;

import Model.Usuario;
import java.sql.*;


public class LoginDAO {
	
	private Connection con;
	PreparedStatement ps = null;
	public boolean validarUsuario(Usuario user) {
		this.con = GerenciadorDeConexoes.getConnection();
		String sql = "SELECT * FROM usuarios where login= ? and senha=? and ativo = true";
		try { 
			ps = con.prepareStatement(sql);
			
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getSenha());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user.setNome(rs.getString("nome"));
				user.setId(rs.getInt("id"));
				user.setCpf(rs.getString("cpf"));
				return true;
			}
			
			GerenciadorDeConexoes.close(con, ps, rs);
		
		}catch (SQLException e) {
			// TODO: handle exception
			if(this.con!=null) {
				System.err.print("Ocorreu um erro ao consultar o banco de dados "+e);
				GerenciadorDeConexoes.close(con);
				
			}
				
			
		}
		return false;	
		
	}
	
	
}
