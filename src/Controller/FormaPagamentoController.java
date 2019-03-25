package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import DAO.FormaPagamentoDAO;
import Model.FormaPagamento;
import View.FormasPagamento;
import View.Pagamento;

public class FormaPagamentoController implements ActionListener {
	
	private FormasPagamento telaFormaPagamento;
	private FormaPagamentoDAO formaDAO;
	private Pagamento telaPagamento;

	public FormaPagamentoController() {
		// TODO Auto-generated constructor stub
		formaDAO = new FormaPagamentoDAO();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		telaFormaPagamento = FormasPagamento.getInstance();
		telaPagamento = Pagamento.getInstance();
		
		if(e.getActionCommand()=="Confirmar") {
			//Manda a forma e valor para a tela de pagamento
			
			Model.FormaPagamento pag =  telaFormaPagamento.getFormaPagamento();
			telaPagamento.adicionarFormaPagamento(pag);
			telaFormaPagamento.reset();
			telaFormaPagamento.setVisible(false);
			
		}
		if(e.getActionCommand()=="Cancelar") {
			
			
			telaFormaPagamento.reset();
			telaFormaPagamento.setVisible(false);
			
		}
		
	}
	public List<Model.FormaPagamento> pesquisarTiposPagamento() {
		List<FormaPagamento> formas = new ArrayList<Model.FormaPagamento>();
		formas = formaDAO.pesquisarTiposPagamento();
		return formas;
	}

}
