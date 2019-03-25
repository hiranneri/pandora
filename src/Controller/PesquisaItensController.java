package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import DAO.VendaDAO;
import Model.ItemVenda;
import View.Venda;
import View.PesquisaProdutoVenda;

public class PesquisaItensController implements ActionListener {
	
	private PesquisaProdutoVenda pesquisaProduto;
	private VendaDAO vendaDAO;
	private List<ItemVenda> retornoProdutos;
	private Venda telaVenda;

	public PesquisaItensController() {
		vendaDAO = new VendaDAO();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		pesquisaProduto = PesquisaProdutoVenda.getInstance();
		
		if(e.getActionCommand()=="Pesquisar") {
			
			pesquisaProduto.apagaTabela();
			retornoProdutos= vendaDAO.pesquisarPorNome(pesquisaProduto.getPesquisa());
			pesquisaProduto.preenche(retornoProdutos);
			
			
		}else if(e.getActionCommand()=="Adicionar Item") {
			
			telaVenda = Venda.getInstance();
			ItemVenda item = pesquisaProduto.getProdutoSelecionado();
			if(item!=null) {
				telaVenda.adicionarProduto(item);
				pesquisaProduto.reset();
				pesquisaProduto.setVisible(false);
			}
			
		}else if(e.getActionCommand()=="Cancelar") {
			
			pesquisaProduto.reset();
			pesquisaProduto.setVisible(false);
			
		}
		
	}
	
}
