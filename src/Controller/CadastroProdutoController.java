package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.ProdutoDAO;
import Model.Produto;
import View.CadastroProduto;

public class CadastroProdutoController implements ActionListener{

	private ProdutoDAO produtoDAO;
	private Produto produto;
	
	
	 public CadastroProdutoController() {
		 this.produtoDAO = new ProdutoDAO();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CadastroProduto telaCadastro = CadastroProduto.getInstance();
		if(e.getActionCommand()=="Salvar") {
			produto = telaCadastro.getProduto();
			String resultado = produtoDAO.inserir(produto);
			telaCadastro.mensagem(resultado);
			telaCadastro.reset();
			telaCadastro.setVisible(false);
		}
		if(e.getActionCommand()=="Cancelar") {
			telaCadastro.reset();
			telaCadastro.hide();
		}
			
		
	}

}
