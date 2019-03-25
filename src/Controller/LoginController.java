package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DAO.LoginDAO;
import View.Login;
import Model.Usuario;

public class LoginController implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Acessar") {
			Login login= Login.getInstance();
			
			Usuario acesso = login.getAcesso();
			//Fazer valida��o de em branco ou nulo
			boolean isUsuarioValido = validarDados(acesso);
			if(isUsuarioValido) {
				LoginDAO dao = new LoginDAO();
				boolean valida = false;
				valida = dao.validarUsuario(acesso);
				if(valida) {
					//abrir principal
					login.exibirMensagem(acesso,true);
				}else {
					login.exibirMensagem("Usuario e/ou senha inv�lidos");
				}
			}else {
				login.exibirMensagem("Digite um usu�rio e/ou senha v�lidos");
			}
			
		}
	}
		
	private boolean validarDados(Usuario usuario) {
		boolean valida = false;
		String usuarioTrim = usuario.getLogin().trim();
		String senha = usuario.getSenha().trim();
		
		if(usuarioTrim!="" && senha!="") {
			return true;
		}
		return valida;
	}
}
