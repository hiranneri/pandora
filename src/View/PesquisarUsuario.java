package View;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import Controller.PesquisaUsuarioController;
import Model.ParametrosPesquisa;
import Model.Usuario;
import Model.UsuariosTabelModel;
import javax.swing.JButton;
import javax.swing.JTable;

public class PesquisarUsuario extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
	private JTable tblUsuario;
	private static PesquisarUsuario frame;
	private UsuariosTabelModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PesquisarUsuario frame = new PesquisarUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static synchronized PesquisarUsuario getInstance() {
		if(frame==null) {
			frame = new PesquisarUsuario();
		}
		return frame;
	} 

	/**
	 * Create the frame.
	 */
	private PesquisarUsuario() {
		setClosable(true);
		setTitle("Pesquisa de Usu\u00E1rios");
		setBounds(100, 100, 450, 326);
		getContentPane().setLayout(null);
		
		JLabel lblPesquisaDeUsurios = new JLabel("Pesquisa de Usu\u00E1rios");
		lblPesquisaDeUsurios.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPesquisaDeUsurios.setBounds(123, 11, 172, 22);
		getContentPane().add(lblPesquisaDeUsurios);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(74, 62, 131, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(16, 65, 48, 14);
		getContentPane().add(lblNome);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(245, 61, 89, 23);
		getContentPane().add(btnPesquisar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 420, 159);
		getContentPane().add(scrollPane);
		
		tblUsuario = new JTable();
		scrollPane.setViewportView(tblUsuario);
		
		
		PesquisaUsuarioController controller = new PesquisaUsuarioController();
		txtUsuario.addActionListener(controller);
		btnPesquisar.addActionListener(controller);
		
		model = new UsuariosTabelModel();
		tblUsuario.setModel(model);
		tblUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	public ParametrosPesquisa getParametros() { 
		ParametrosPesquisa parametros = ParametrosPesquisa.getInstance();
		parametros.setParametro("Nome");
		parametros.setValor(txtUsuario.getText());
		return parametros;
	}
	public void preenche(List<Usuario> usuarios) {
		for(Usuario u:usuarios) {
			model.addRow(u);
		}
	}
	public void apagarTabela() {
		model.clear();
	}
	public void reset() {
		txtUsuario.setText("");
		
	}
}
