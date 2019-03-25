package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Model.Venda;
import DAO.VendaDAO;
import View.CaixaPrincipal;

public class CaixaController implements ActionListener {
	
	private VendaDAO vendaDAO;
	private CaixaPrincipal telaCaixa;
	private List<Venda> retornoVendas;

	public CaixaController() {
		// TODO Auto-generated constructor stub
		vendaDAO = new VendaDAO();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Pesquisar") {
			
			telaCaixa = CaixaPrincipal.getInstance();
			retornoVendas = vendaDAO.pesquisarVendas(telaCaixa.getDatas());
			telaCaixa.apagaPesquisa();
			telaCaixa.preenche(retornoVendas);
			
		}
		if(e.getActionCommand()=="Fechar") {
			telaCaixa.reset();
			telaCaixa.setVisible(false);
		}
		
	}

}
