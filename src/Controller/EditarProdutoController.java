package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.ProdutoDAO;
import Model.Produto;
import View.EditarProduto;
import View.PesquisarProduto;


public class EditarProdutoController implements ActionListener {
	
	private ProdutoDAO produtoDAO;
	public EditarProdutoController() {
		// TODO Auto-generated constructor stub
		produtoDAO = new ProdutoDAO();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		EditarProduto editaProduto = EditarProduto.getInstance();
		
		if(e.getActionCommand()=="Salvar") {
			Produto prod = editaProduto.getProdutoEditar();
			PesquisarProduto pesquisaProduto = PesquisarProduto.getInstance();
			boolean sucesso = produtoDAO.editarProduto(prod);
			String mensagem;
			if(sucesso) {
				mensagem = "Produto alterado com sucesso";
			}else {
				mensagem = "Ocorreu um erro ao salvar";
			}
			editaProduto.mensagem(mensagem);
			editaProduto.reset();
			editaProduto.setVisible(false);
			
			//ação para atualizar a tabela
			pesquisaProduto.atualizarTabela();
			
		}
		
		if(e.getActionCommand()=="Cancelar") {
			 editaProduto.reset();
			 editaProduto.setVisible(false);
			 
		}
	}

}
