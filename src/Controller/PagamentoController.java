package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.VendaDAO;
import View.Pagamento;
import View.Principal;
import View.Venda;

public class PagamentoController implements ActionListener {
	
	public Pagamento telaPagamento;
	private View.FormasPagamento telaFormaPagamento;
	private VendaDAO vendaDAO;
	private Venda telaVenda;
	private Model.Venda vendaCriada;
	
	public PagamentoController() {
		// TODO Auto-generated constructor stub
		vendaDAO = new VendaDAO();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		telaPagamento = Pagamento.getInstance();
		telaVenda = Venda.getInstance();
		if(e.getActionCommand()=="Confirmar") {
			//Salvar Venda
			vendaCriada = telaPagamento.criarAGigantescaVenda();
			boolean venda = vendaDAO.salvarVenda(vendaCriada);
			if(venda) {
				telaPagamento.mensagem("Venda salva com sucesso");
				encerrarVenda();
			}else {
				telaPagamento.mensagem("Ocorreu um erro ao salvar a venda. Tente novamente.");
				encerrarVenda();
			}
		}
		if(e.getActionCommand()=="Cancelar") {
			telaPagamento.reset();
			telaPagamento.dispose();
		}
		if(e.getActionCommand()=="+") {
			telaFormaPagamento = View.FormasPagamento.getInstance();
			Principal.adicionarDesktop(telaFormaPagamento);
		}
		if(e.getActionCommand()=="-") {
			telaPagamento.excluirFormaPagamento();
		}
		
	}
	private void encerrarVenda() {
		telaPagamento.reset(); //resetar telaPagamento
		telaPagamento.setVisible(false); 
		telaVenda.resetTela();
		//imprimirNotaFiscal(vendaCriada); //COnsiderar na próxima versão
	}
	
	/*
	 * Método não implementado por completo, resolver a query: Quando traz todos os itens, 
	 *  mas não traz todas as formas de pagamento e vice e versa
	 *  O subreport - para as formas de pagamento não está funcionando corretamente.
	 */
/*
	private boolean imprimirNotaFiscal(Model.Venda vendaRealizada) {
		int idVenda = vendaRealizada.getIdVenda();
		return vendaDAO.gerarNota(idVenda);
	}
*/
}
