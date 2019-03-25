package View;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Controller.CadastroUsuarioController;
import Model.Usuario;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;

public class CadastroUsuario extends JInternalFrame {
	/**
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JPasswordField txtConfirmeSenha;
	private static CadastroUsuario cadastroUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroUsuario frame = new CadastroUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static synchronized CadastroUsuario getInstance() {
		// TODO Auto-generated method stub
		if(cadastroUsuario==null) {
			cadastroUsuario = new CadastroUsuario();
		}
		return cadastroUsuario;
	}
	/**
	 * Create the frame.
	 */
	private CadastroUsuario() {
		setTitle("Cadastro de Usu\u00E1rios");
		setBounds(100, 100, 582, 300);
		getContentPane().setLayout(null);
		
		JLabel lblCadastroDeUsurios = new JLabel("Cadastro de Usu\u00E1rios");
		lblCadastroDeUsurios.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCadastroDeUsurios.setBounds(175, 11, 173, 20);
		getContentPane().add(lblCadastroDeUsurios);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setBounds(10, 65, 55, 14);
		getContentPane().add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCpf.setBounds(10, 105, 34, 14);
		getContentPane().add(lblCpf);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLogin.setBounds(293, 65, 55, 14);
		getContentPane().add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSenha.setBounds(293, 105, 55, 14);
		getContentPane().add(lblSenha);
		
		txtNome = new JTextField();
		txtNome.setBounds(75, 62, 144, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setColumns(10);
		txtCPF.setBounds(75, 102, 144, 20);
		getContentPane().add(txtCPF);
		
		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(375, 63, 144, 20);
		getContentPane().add(txtLogin);
		
		JLabel lblConfimeSenha = new JLabel("Confime a senha:");
		lblConfimeSenha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblConfimeSenha.setBounds(240, 139, 119, 14);
		getContentPane().add(lblConfimeSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(375, 103, 144, 20);
		getContentPane().add(txtSenha);
		
		txtConfirmeSenha = new JPasswordField();
		txtConfirmeSenha.setBounds(375, 137, 144, 20);
		getContentPane().add(txtConfirmeSenha);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(72, 208, 114, 34);
		getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(285, 208, 114, 34);
		getContentPane().add(btnCancelar);
		
		CadastroUsuarioController controller = new CadastroUsuarioController();
		btnSalvar.addActionListener(controller);
		btnCancelar.addActionListener(controller);
		
	}
	
	public boolean validarSenha() {
		String senha1 = txtSenha.getText();
		String senha2 = txtConfirmeSenha.getText();
		
		if(!senha1.equals(senha2)) {
			String mensagem = "As senhas não coincidem";
			JOptionPane.showMessageDialog(this, mensagem);
			return false;
		}
		return true;
	}
	public Usuario getUsuario() {
		String nome= txtNome.getText();
		String cpf = txtCPF.getText();
		String login = txtLogin.getText();
		String senha = txtSenha.getText();
		
		Usuario usuario = new Usuario(login,senha);
		usuario.setNome(nome);
		usuario.setCpf(cpf);
		return usuario;
	}
	public void reset() {
		txtNome.setText("");
		txtCPF.setText("");
		txtLogin.setText("");
		txtSenha.setText("");
		txtConfirmeSenha.setText("");
		
	}
	public void mensagem(String mensagem) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, mensagem);
		
	}

}
