package View;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.LoginController;
import Model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtLogin;
	private static Login frame;
	private JPasswordField txtSenha;
	public static Usuario usuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		});
	}
	
	public static synchronized Login getInstance() {
		if(frame==null) {
			frame = new Login();
		}
		return frame;
	}
	/**
	 * Create the frame.
	 */
	private  Login() {
		setResizable(false);
		setTitle("Sistema Comercial - Mercado Pandora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 300);
		getContentPane().setLayout(null);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(178, 72, 196, 23);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		JButton btnOk = new JButton("Acessar");
		
		btnOk.setBounds(220, 181, 120, 39);
		getContentPane().add(btnOk);
		
		JLabel lblMercadoPandora = new JLabel("Mercado Pandora");
		lblMercadoPandora.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMercadoPandora.setBounds(178, 11, 133, 23);
		getContentPane().add(lblMercadoPandora);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLogin.setBounds(103, 70, 53, 23);
		getContentPane().add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBounds(103, 121, 53, 23);
		getContentPane().add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(178, 124, 196, 23);
		getContentPane().add(txtSenha);
		 LoginController loginController= new LoginController();
		 btnOk.addActionListener(loginController);
		
		
	}
	public Usuario getAcesso() {
		String login = this.txtLogin.getText();
		String senha = this.txtSenha.getText();	
		usuario = new Usuario(login,senha);
		
		return usuario;
	}
	public void exibirMensagem(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem);
		txtSenha.setText("");
		txtLogin.requestFocus();
	}
	public  void exibirMensagem(Usuario usuario, boolean sucesso) {
		JOptionPane.showMessageDialog(this, "Seja Bem Vindo(a) "+usuario.getLogin());
		Principal.getInstance().setExtendedState(JFrame.MAXIMIZED_BOTH);
		Principal.getInstance().setVisible(true);
		frame.dispose();		
	}
	
	
	public static void fechar() {
		 frame.dispose();
	}
	
}
