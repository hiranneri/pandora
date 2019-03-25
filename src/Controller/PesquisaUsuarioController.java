package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import DAO.UsuarioDAO;
import Model.ParametrosPesquisa;
import Model.Usuario;
import View.PesquisarUsuario;

 


public class PesquisaUsuarioController implements ActionListener {
	 private UsuarioDAO usuarioDAO;
	 private ParametrosPesquisa parametros;
	 private List<Usuario> retornoUsuarios;
	 
	 public PesquisaUsuarioController() {
		// TODO Auto-generated constructor stub
		 usuarioDAO = new UsuarioDAO();
		 parametros = ParametrosPesquisa.getInstance();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Pesquisar") {
			PesquisarUsuario pesquisaUsuario = PesquisarUsuario.getInstance();
			pesquisaUsuario.apagarTabela();
			parametros = pesquisaUsuario.getParametros();
			if(parametros.getParametro()=="Nome") {
				pesquisaUsuario.apagarTabela();
				retornoUsuarios = usuarioDAO.pesquisarPorNome(parametros);
				pesquisaUsuario.preenche(retornoUsuarios);
			}
		}
	}

}
