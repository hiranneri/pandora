package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.UsuarioDAO;
import Model.Usuario;
import View.CadastroUsuario;

public class CadastroUsuarioController implements ActionListener {
	
	private UsuarioDAO usuarioDAO;
	private Usuario usuario;
	
	public CadastroUsuarioController() {
		
		usuarioDAO = new UsuarioDAO();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		CadastroUsuario cadastroUsuario = CadastroUsuario.getInstance();
		if(e.getActionCommand()=="Salvar") {
			usuario = cadastroUsuario.getUsuario();
			String mensagem = usuarioDAO.cadastrar(usuario);
			cadastroUsuario.mensagem(mensagem);
			cadastroUsuario.reset();
			cadastroUsuario.dispose();
		}
		if(e.getActionCommand()=="Cancelar") {
			cadastroUsuario.reset();
			cadastroUsuario.dispose();
		}
	}

	
}
