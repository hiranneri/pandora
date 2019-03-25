package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;

import View.Principal;
import View.CadastroProduto;
import View.CadastroUsuario;
import View.CaixaPrincipal;
import View.Configuracoes;
import View.FormasPagamento;
import View.Venda;
import View.PesquisarProduto;
import View.PesquisarUsuario;

public class PrincipalController implements ActionListener {
	
	public Principal telaPrincipal;
	public static CadastroProduto cadastroProduto;
	public CadastroUsuario cadastroUsuario;
	public PesquisarProduto pesquisaProduto;
	public PesquisarUsuario pesquisaUsuario;
	public Venda efetuarVenda;
	private FormasPagamento telaFormasPagto;
	private CaixaPrincipal telaRelatorio;
	private Configuracoes telaConfiguracoes;
	
	public PrincipalController() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand()=="Cadastrar") {
			cadastroProduto = CadastroProduto.getInstance();
			Principal.adicionarDesktop(cadastroProduto);
			
		}
		
		if(e.getActionCommand()=="Pesquisar") {
			pesquisaProduto = PesquisarProduto.getInstance();
			Principal.adicionarDesktop(pesquisaProduto);

		}
		if(e.getActionCommand()=="Venda") {
			efetuarVenda = Venda.getInstance();
			Principal.adicionarDesktop(efetuarVenda);
			//carregarFormasPagto();
			telaFormasPagto = FormasPagamento.getInstance();
			telaFormasPagto.carregaTipoPagamento();
			
		}
		
		
		if(e.getActionCommand()=="Caixa") {
			telaRelatorio = CaixaPrincipal.getInstance();
			Principal.adicionarDesktop(telaRelatorio);
		}
		if(e.getActionCommand()=="Novo") {
			cadastroUsuario = CadastroUsuario.getInstance();
			Principal.adicionarDesktop(cadastroUsuario);
		}
		
		if(e.getActionCommand()=="Localizar") {
			pesquisaUsuario = PesquisarUsuario.getInstance();
			Principal.adicionarDesktop(pesquisaUsuario);
			
		}
		if(e.getActionCommand()=="Configurações") {
			telaConfiguracoes = Configuracoes.getInstance();
			Principal.adicionarDesktop(telaConfiguracoes);
		}
		
	}

	public static JInternalFrame getCadastroProduto() {
		return cadastroProduto;
	}
	

}
