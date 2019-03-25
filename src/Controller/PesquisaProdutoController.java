package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import DAO.ProdutoDAO;
import Model.ParametrosPesquisa;
import Model.Produto;
import View.EditarProduto;
import View.PesquisarProduto;
import View.Principal;

public class PesquisaProdutoController implements ActionListener {
	
	private ProdutoDAO produtoDAO;
	private ParametrosPesquisa parametros;
	private Produto produto;
	private List<Produto> retornoProdutos;
	public Principal telaPrincipal;
	
	public PesquisaProdutoController() {
		produto = new Produto();
		produtoDAO = new ProdutoDAO();
		telaPrincipal = Principal.getInstance();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		PesquisarProduto pesquisaProduto = PesquisarProduto.getInstance();
		
		if(e.getActionCommand()=="Pesquisar") {
			pesquisaProduto.apagarTabela();
			parametros = pesquisaProduto.getParametros();
			if(parametros.getParametro()=="Nome") {
				retornoProdutos = produtoDAO.pesquisarPorNome(parametros);
			}else {
				java.sql.Date data = produto.getDateSQL(parametros.getData());
				parametros.setDataSQL(data);
				if(parametros.getParametro()=="Validade") {
					retornoProdutos = produtoDAO.pesquisarPelaValidade(parametros);
				}else {
					retornoProdutos = produtoDAO.pesquisarPelaDataCompra(parametros);
				}
			}
			pesquisaProduto.preenche(retornoProdutos);
			pesquisaProduto.mostrarResultado(retornoProdutos);
		}
		if(e.getActionCommand()=="Nome"|| e.getActionCommand()=="Validade"||e.getActionCommand()=="Data da Compra" ) {
			pesquisaProduto.mostrarCampos();
		}
		if(e.getActionCommand()=="Fechar") {
			pesquisaProduto.resetPesquisa();
			pesquisaProduto.setVisible(false);
			
		}
		if(e.getActionCommand()=="Editar") {
			Produto prod = pesquisaProduto.getProdutoSelecionado();
			if(prod!=null) {
				EditarProduto editarProduto = EditarProduto.getInstance(prod);
				Principal.adicionarDesktop(editarProduto);
			}
			
		}
		if(e.getActionCommand()=="Excluir") { //Excluir na tabela
			Produto prod = pesquisaProduto.getProdutoSelecionado();
			if(prod!=null) {
				boolean sc = produtoDAO.desativarProduto(prod);
				String mensagem="";
				if(sc) {
					mensagem = "Excluído com sucesso";
				}else {
					mensagem = "Não foi possível excluir";
				}
				pesquisaProduto.mensagem(mensagem);
			}
			
		}
	}


}
