package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import DAO.VendaDAO;
import Model.ItemVenda;
import Model.ParametrosPesquisa;
import View.Venda;
import View.Pagamento;
import View.PesquisaProdutoVenda;
import View.Principal;
import static View.Venda.txtProduto;

public class VendaController implements KeyListener, MouseListener {
	
	private VendaDAO vendaDAO;
	private PesquisaProdutoVenda telaPesquisaProdutoVenda;
	public List<ItemVenda> retornoItens;
	public Pagamento telaPagto;
	
	public VendaController() {
		// TODO Auto-generated constructor stub
		vendaDAO = new VendaDAO();
	}

	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		Venda telaVenda = Venda.getInstance();
		if(e.getKeyCode()==KeyEvent.VK_ENTER ){
			if(e.getSource().equals(txtProduto)){
				telaPesquisaProdutoVenda = PesquisaProdutoVenda.getInstance();
				boolean isPesquisa = validarPesquisa(telaVenda.pesquisar());
				if(isPesquisa){
					retornoItens = vendaDAO.pesquisarPorNome(telaVenda.pesquisar());
					if(retornoItens.size()>=2){
						Principal.adicionarDesktop(telaPesquisaProdutoVenda,retornoItens,telaVenda.pesquisar().getValor());
					}else if(retornoItens.size()==1) {
						telaVenda.adicionarProduto(retornoItens.get(0));
					}
				}else {
					JOptionPane.showMessageDialog(null, "Digite o nome do produto");
				}

			}	
		
			
			if(e.getSource() instanceof JFormattedTextField) {
				telaVenda.adicionarItemNaTabela();
				telaVenda.apagaCampos();
			}
			
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Venda telaVenda = Venda.getInstance();
		Object source = e.getComponent();
		
		if(source==telaVenda.btnSalvar) {
			System.out.println("salvou");
			telaPagto = Pagamento.getInstance();
			Principal.adicionarDesktop(telaPagto);
			telaPagto.getVenda(telaVenda.criarAGigantescaVenda());
		}
		if(source==telaVenda.btnCancelar) {
			telaVenda.resetTela();
			telaVenda.apagarFormasPagamento();
			telaVenda.setVisible(false);
		}
		if(source==telaVenda.btnExcluir) {
			telaVenda.excluirItem();
		}

	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	private boolean validarPesquisa(ParametrosPesquisa pesquisa) {
		// TODO Auto-generated method stub
		if(pesquisa.getValor().trim().equals("")) {
			return false;
		}
		return true;
		
	}

}
