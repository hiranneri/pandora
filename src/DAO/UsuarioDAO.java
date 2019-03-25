package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.ParametrosPesquisa;
import Model.Usuario;

public class UsuarioDAO {
	
	private Connection con;
	PreparedStatement ps = null;

	public String cadastrar(Usuario usuario) {
		this.con = GerenciadorDeConexoes.getConnection();
		String sql = "INSERT INTO usuarios (nome,cpf,login,senha) "
				+ "VALUES (?, ?, ?, ?)";
		try { 
			ps = con.prepareStatement(sql);
			
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getCpf());
			ps.setString(3, usuario.getLogin());
			ps.setString(4, usuario.getSenha());
			
			int resultado = ps.executeUpdate();
			if(resultado>0) {
				GerenciadorDeConexoes.close(con, ps);
				return "Cadastro realizado com sucesso";
			}
		
		}catch (SQLException e) {
			// TODO: handle exception
			if(this.con!=null) {
				System.err.print("Ocorreu um erro no banco de dados "+e);
				GerenciadorDeConexoes.close(con);
				
			}
			
		}
		return "Não foi possível realizar o cadastro";	
	}

	public List<Usuario> pesquisarPorNome(ParametrosPesquisa parametros) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		this.con = GerenciadorDeConexoes.getConnection();
		String sql = "SELECT * FROM usuarios where nome LIKE ?";
		try { 
			ps = con.prepareStatement(sql);
			ps.setString(1, '%'+parametros.getValor()+'%');
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Usuario u = new Usuario();
				u.setNome(rs.getString("nome"));
				u.setCpf(rs.getString("cpf"));
				u.setLogin(rs.getString("login"));
				
				usuarios.add(u);
			}
			GerenciadorDeConexoes.close(con, ps, rs);
			return usuarios;
		}catch (SQLException e) {
			if(this.con!=null) {
				System.err.print("Ocorreu um erro ao consultar o banco de dados "+e);
				GerenciadorDeConexoes.close(con);
			}
		}
		return null;	
	}

}
