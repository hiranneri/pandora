package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.ParametrosPesquisa;
import Model.Produto;
import Utilitarios.FormatarData;


public class ProdutoDAO {
	private Connection con;
	PreparedStatement ps = null;
	
	public String inserir(Produto p) {
		this.con = GerenciadorDeConexoes.getConnection();
		String sql = "INSERT INTO produtos (nome,precoVenda,precoCompra,validade,dtCompra,observacao,ativo) "
				+ "VALUES (?, ?, ?, ?, ?, ?,?)";
		try { 
			ps = con.prepareStatement(sql);
			
			
			ps.setString(1, p.getNome());
			ps.setDouble(2, p.getprecovenda().doubleValue());
			ps.setDouble(3, p.getPrecoCompra().doubleValue());
			ps.setDate(4, p.getDateSQL(p.getValidade()));
			ps.setDate(5, p.getDateSQL(p.getDataCompra()));
			ps.setString(6, p.getObservacao());
			ps.setBoolean(7, true);
			
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
	
	public List<Produto> pesquisarPorNome(ParametrosPesquisa parametros) {
		List<Produto> produtos = new ArrayList<Produto>();
		this.con = GerenciadorDeConexoes.getConnection();
		String sql = "SELECT * FROM produtos where ativo = true and nome LIKE ?";
		try { 
			ps = con.prepareStatement(sql);
			ps.setString(1, '%'+parametros.getValor()+'%');
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Produto p = new Produto();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setPrecoVenda(rs.getBigDecimal("precovenda"));
				p.setPrecoCompra(rs.getBigDecimal("precocompra"));
				p.setValidade(rs.getDate("validade"));
				p.setDataCompra(rs.getDate("dtcompra"));
				p.setObservacao(rs.getString("observacao"));
				produtos.add(p);
			}
			GerenciadorDeConexoes.close(con, ps, rs);
			return produtos;
		}catch (SQLException e) {
			if(this.con!=null) {
				System.err.print("Ocorreu um erro ao consultar o banco de dados "+e);
				GerenciadorDeConexoes.close(con);
			}
		}
		return null;	
	}
	
	public List<Produto> pesquisarPelaDataCompra(ParametrosPesquisa parametros) {
		List<Produto> produtos = new ArrayList<Produto>();
		this.con = GerenciadorDeConexoes.getConnection();
		String sql = "SELECT * FROM produtos where ativo = true and dtcompra LIKE ?";
		try { 
			ps = con.prepareStatement(sql);
			ps.setDate(1, parametros.getDataSQL());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Produto p = new Produto();
				p.setNome(rs.getString("nome"));
				p.setPrecoVenda(rs.getBigDecimal("precovenda"));
				p.setPrecoCompra(rs.getBigDecimal("precocompra"));
				p.setValidade(rs.getDate("validade"));
				p.setDataCompra(rs.getDate("dtcompra"));
				produtos.add(p);
			}
			GerenciadorDeConexoes.close(con, ps, rs);
			return produtos;
		}catch (SQLException e) {
			if(this.con!=null) {
				System.err.print("Ocorreu um erro ao consultar o banco de dados "+e);
				GerenciadorDeConexoes.close(con);
			}
		}
		return null;
		
	}

	public List<Produto> pesquisarPelaValidade(ParametrosPesquisa parametros) {
		List<Produto> produtos = new ArrayList<Produto>();
		this.con = GerenciadorDeConexoes.getConnection();
		String sql = "SELECT * FROM produtos where ativo = true and validade LIKE ?";
		try { 
			ps = con.prepareStatement(sql);
			ps.setDate(1, parametros.getDataSQL());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Produto p = new Produto();
				p.setNome(rs.getString("nome"));
				p.setPrecoVenda(rs.getBigDecimal("precovenda"));
				p.setPrecoCompra(rs.getBigDecimal("precocompra"));
				p.setValidade(rs.getDate("validade"));
				p.setDataCompra(rs.getDate("dtcompra"));
				produtos.add(p);
			}
			GerenciadorDeConexoes.close(con, ps, rs);
			return produtos;
		}catch (SQLException e) {
			if(this.con!=null) {
				System.err.print("Ocorreu um erro ao consultar o banco de dados "+e);
				GerenciadorDeConexoes.close(con);
			}
		}
		return null;
	}

	public boolean editarProduto(Produto produto) {
		// TODO Auto-generated method stub
		 try {
			this.con = GerenciadorDeConexoes.getConnection();
			
			this.ps = con.prepareStatement(
					"UPDATE produtos SET nome = ?, precovenda= ?, validade=?, "
					+ "dtcompra=?, observacao=? WHERE id = ?");
			
			ps.setString(1, produto.getNome());
			ps.setBigDecimal(2, produto.getprecovenda());
			ps.setDate(3, FormatarData.formatarDataParaSQL(produto.getValidade()));
			ps.setDate(4, FormatarData.formatarDataParaSQL(produto.getDataCompra()));
			ps.setString(5, produto.getObservacao());
			ps.setInt(6, produto.getId());
			
			ps.executeUpdate();
			ps.close();
			
			return true;
		} catch (SQLException e) {
			if(this.con!=null) {
				System.err.print("Ocorreu um erro ao consultar o banco de dados "+e);
				GerenciadorDeConexoes.close(con);
			}
		}
		
		return false;
	}
	
	public boolean desativarProduto(Produto produto) {
		 try {
				this.con = GerenciadorDeConexoes.getConnection();
				this.ps = con.prepareStatement(
						"UPDATE produtos SET ativo = ? WHERE id = ?");
				ps.setBoolean(1, false);
				ps.setInt(2, produto.getId());
				ps.executeUpdate();
				ps.close();
				return true;
			} catch (SQLException e) {
				if(this.con!=null) {
					System.err.print("Ocorreu um erro ao desativar. Erro no banco de dados "+e);
					GerenciadorDeConexoes.close(con);
				}
			}
			
			return false;
	}
	

}
