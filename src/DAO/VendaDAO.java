package DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.mysql.jdbc.Statement;
import Model.FormaPagamento;
import Model.ItemVenda;
import Model.ParametrosPesquisa;
import Model.Produto;
import Model.Usuario;
import Model.Venda;
import Utilitarios.FormatarData;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class VendaDAO {
	private Connection con;
	PreparedStatement ps = null;

	public List<ItemVenda> pesquisarPorNome(ParametrosPesquisa pesquisar) {
		List<ItemVenda> itens = new ArrayList<ItemVenda>();
		this.con = GerenciadorDeConexoes.getConnection();
		String sql = "SELECT * FROM produtos where ativo = true and id<=15 and nome LIKE ?";
		try { 
			ps = con.prepareStatement(sql);
			ps.setString(1, '%'+pesquisar.getValor()+'%');
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				ItemVenda item = new ItemVenda();
				Produto produto = new Produto();
				item.setId(rs.getInt("id"));
				
				produto.setNome(rs.getString("nome"));
				produto.setPrecoVenda(rs.getBigDecimal("precovenda"));
				
				item.setProduto(produto);
				itens.add(item);
			}
			GerenciadorDeConexoes.close(con, ps, rs);
			return itens;
		}catch (SQLException e) {
			if(this.con!=null) {
				System.err.println("Ocorreu um erro ao consultar o banco de dados "+e);
				GerenciadorDeConexoes.close(con);
			}
		}
		return null;	
	}

	public boolean salvarVenda(Venda venda) {
		
		try { 
			this.con = GerenciadorDeConexoes.getConnection();
			con.setAutoCommit(false);
			String sql = "INSERT INTO venda (dtvenda,hrvenda,pdv,total,troco,valorpago,usuarios_id) "
					+ "VALUES (?, ?, ?, ?, ?,?,?)";
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			ps.setDate(1, FormatarData.formatarDataParaSQL(venda.getDataVenda())); 
			ps.setTime(2, FormatarData.formatarHoraParaSQL(venda.getHoraVenda()));
			ps.setString(3, venda.getPdv());
			ps.setBigDecimal(4, venda.getTotalVenda());
			ps.setBigDecimal(5, venda.getTrocoVenda());
			ps.setBigDecimal(6, venda.getValorRecebido());
			ps.setInt(7, venda.getFuncionario().getId());
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if(generatedKeys.next()) {
				venda.setIdVenda(generatedKeys.getInt(1));
				
			}
			salvarFormasPagto(venda);
			salvarItens(venda);
			
			con.commit();
		}catch (SQLException e) {
			if(this.con!=null) {
				try {
					System.err.println("Ocorreu um erro no banco de dados "+e);
					con.rollback();
					return false;
				}catch(SQLException ex) {
					System.err.print("Ocorreu um erro no banco de dados "+ex);
				}
				
				
			}
			
		}finally {
			if(this.con!=null) {
				GerenciadorDeConexoes.close(con);
				
			}
		}
		return true;


	}

	private void salvarFormasPagto(Venda venda) throws SQLException {
		// TODO Auto-generated method stub formapagamento_venda
		String sql = "INSERT INTO formapagamento_venda (idvenda,idforma,valorpago) "
				+ "VALUES (?, ?,?)";
		ps = con.prepareStatement(sql);
		List<FormaPagamento> formas = venda.getFormasPagamento();
		for(FormaPagamento forma:formas) {
			forma.setVenda(venda);
			ps.setInt(1, forma.getVenda().getIdVenda());
			ps.setInt(2, forma.getId());
			ps.setBigDecimal(3, forma.getValor());
			
			ps.executeUpdate();
		}
		
	}

	private void salvarItens(Venda venda) throws SQLException {
		// TODO Auto-generated method stub
		
			String sql = "INSERT INTO itens (descricao,quantidade,valorunitario,valortotal,venda_id,produtos_id) "
					+ "VALUES (?, ?, ?, ?, ?,?)";
			ps = con.prepareStatement(sql);
			List<ItemVenda> itens = venda.getItensVenda();
			for(ItemVenda item:itens) {
				ps.setString(1, item.getProduto().getNome());
				ps.setInt(2, item.getQuantidade());
				ps.setBigDecimal(3, item.getProduto().getprecovenda());
				ps.setBigDecimal(4, item.getSubTotal());
				
				item.setVenda(venda);
				
				ps.setInt(5, item.getVenda().getIdVenda());
				ps.setInt(6, item.getId());
				
				ps.executeUpdate();
			}
			
			
			
	
		
	}

	public List<Venda> pesquisarVendas(ParametrosPesquisa datas) {
		// TODO Auto-generated method stub
		/*
		 * select dtvenda,hrvenda,pdv,total,troco,nome from venda  inner join (usuarios) 
on (venda.usuarios_id = usuarios.id) where venda.id>3311 and dtvenda 
between '2019-03-08' and '2019-03-10';
		 */
		List<Venda> vendas = new ArrayList<Venda>();
		this.con = GerenciadorDeConexoes.getConnection();
		String sql = "select dtvenda,hrvenda,pdv,total,troco,nome from venda  inner join (usuarios) "+
				"on (venda.usuarios_id = usuarios.id) where venda.id>3311 and dtvenda " + 
				"between ? and ? ;";
		try { 
			ps = con.prepareStatement(sql);
			ps.setDate(1, FormatarData.formatarDataParaSQL(datas.getDataInicio()));
			ps.setDate(2, FormatarData.formatarDataParaSQL(datas.getDataFim()));
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Venda venda = new Venda();
				Usuario user = new Usuario();
				venda.setDataVenda(rs.getDate("dtvenda"));
				venda.setHoraVenda(FormatarData.formatarDataSQLParaDate((rs.getDate("hrvenda"))));
				venda.setHoraVenda(rs.getDate("hrvenda"));
				venda.setPdv(rs.getString("pdv"));
				venda.setTotalVenda(rs.getBigDecimal("total"));
				venda.setTrocoVenda(rs.getBigDecimal("troco"));
				
				user.setNome(rs.getString("nome"));
				venda.setFuncionario(user);
				
				vendas.add(venda);
			}
			GerenciadorDeConexoes.close(con, ps, rs);
			return vendas;
		}catch (SQLException e) {
			if(this.con!=null) {
				System.err.print("Ocorreu um erro ao consultar o banco de dados "+e);
				GerenciadorDeConexoes.close(con);
			}
		}
		return null;	
	}

	public boolean gerarNota(int idVenda) {
		/*
		try {
			
			JRResultSetDataSource jrRS = new JRResultSetDataSource(getResultSet(idVenda));
			//caminho do relatório
			InputStream caminho = this.getClass().getClassLoader().getResourceAsStream("MyReports/Nota66.jasper");
			HashMap map = new HashMap<String, Object>();
			map.put("SUBREPORT_DIR", "MyReports/");
			JasperPrint jasperPrint = JasperFillManager.fillReport(caminho,map , jrRS);
			JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\\\Users\\\\hiran\\\\Documents\\\\Estudos\\\\Nota65.pdf");
			File file = new File("C:\\\\Users\\\\hiran\\\\Documents\\\\Estudos\\\\Nota65.pdf");
			try {
				Desktop.getDesktop().open(file);
			}catch(Exception e) {
				JOptionPane.showConfirmDialog(null, e);
			}
			return true;
		}catch (SQLException e) {
			if(this.con!=null) {
				System.err.print("Ocorreu um erro ao consultar o banco de dados "+e);
				GerenciadorDeConexoes.close(con);
			}
		}catch (JRException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		*/
		 try {
			 JRResultSetDataSource jrRS = new JRResultSetDataSource(getResultSet(idVenda));
//           JasperReport compiledReport = (JasperReport) JRLoader.loadObject(ViewAssignmentsController.class.getResourceAsStream("/edu/ijse/gdse41/ams/reports/MarkingScheme.jasper"));
			InputStream caminho = this.getClass().getClassLoader().getResourceAsStream("MyReports/Nota66.jrxml"); 
           	JasperReport compiledReport = JasperCompileManager.compileReport(caminho);
           HashMap<String, Object> parameters = new HashMap<>();
           InputStream caminhoSubReport = this.getClass().getClassLoader().getResourceAsStream("MyReports/FormasPagto.jrxml"); 
           JasperReport subReport = JasperCompileManager.compileReport(caminhoSubReport);
           parameters.put("subReport", subReport);
           JasperPrint fillReport = JasperFillManager.fillReport(compiledReport, parameters,jrRS);
           JasperViewer.viewReport(fillReport, false);

       } catch (SQLException e) {
			if(this.con!=null) {
				System.err.print("Ocorreu um erro ao consultar o banco de dados "+e);
				GerenciadorDeConexoes.close(con);
			}
		}catch (JRException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return false;
		
		
	}
		
	/*
	 *  select formapagamento.forma, venda.total,venda.troco, venda.valorpago as totalvenda, 
		itens.descricao,itens.quantidade,
		valorunitario,itens.valortotal,formapagamento_venda.valorpago as ValorFormaPgto
		from (venda) inner join (itens) 
		on (venda.id=itens.venda_id)
		inner join (formapagamento_venda)
		on (formapagamento_venda.idvenda = venda.id)
		inner join (formapagamento)
		on (formapagamento_venda.idforma=formapagamento.id)
		where (venda.id=3327)
		group by formapagamento.forma;
		
	*/
	private ResultSet getResultSet(int idVenda) throws SQLException {
			this.con = GerenciadorDeConexoes.getConnection();
			/*
			String sql = "select  venda.id, descricao,quantidade,valorunitario, valortotal, "+ 
					"total as totalvenda from itens left join venda on (venda.id=itens.venda_id)"
					+ " where venda.id=?";
			*/
			String sql = "select formapagamento.forma, venda.total,venda.troco, venda.valorpago as totalvenda,"+ 
					"itens.descricao,itens.quantidade, valorunitario,itens.valortotal,formapagamento_venda.valorpago "
					+ "as ValorFormaPgto from (venda) inner join (itens) on (venda.id=itens.venda_id)"
					+"inner join (formapagamento_venda) on (formapagamento_venda.idvenda = venda.id)"
					+"inner join (formapagamento) on (formapagamento_venda.idforma=formapagamento.id)"
					+"where (venda.id=?) group by formapagamento.forma";
			ps = con.prepareStatement(sql);
			ps.setInt(1, idVenda); 		
			ResultSet rs = ps.executeQuery();
			return rs;
		}
	}