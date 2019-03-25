package View;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.math.BigDecimal;
import Controller.FormaPagamentoController;
import javax.swing.JComboBox;
import Utilitarios.JNumberFormatField;
import javax.swing.JButton;
import Model.FormaPagamento;

public class FormasPagamento extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JComboBox<String> cbFormas;
	private static JNumberFormatField txtValor;
	private static JButton btnConfirmar;
	private static JButton btnCancelar;
	private static FormasPagamento frame;
	private static FormaPagamentoController controller;
	public static  List<FormaPagamento> formasPagamento;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormasPagamento frame = getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static synchronized FormasPagamento getInstance() {
		if(frame==null) {
			frame = new FormasPagamento();
			
		}
		
		return frame;
	}
	private static void actionListeners() {
		btnCancelar.addActionListener(controller);
		btnConfirmar.addActionListener(controller);
	}
	/**
	 * Create the frame.
	 */
	private FormasPagamento() {
		setTitle("Forma de Pagamento");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblFormaPagamento = new JLabel("Forma Pagamento");
		lblFormaPagamento.setBounds(10, 11, 145, 28);
		getContentPane().add(lblFormaPagamento);
		
		cbFormas = new JComboBox(new Vector<String>());
		cbFormas.setBounds(206, 14, 150, 22);
		getContentPane().add(cbFormas);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(10, 75, 45, 28);
		getContentPane().add(lblValor);
		
		txtValor = new JNumberFormatField();
		txtValor.setBounds(206, 79, 150, 20);
		getContentPane().add(txtValor);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(20, 163, 135, 38);
		getContentPane().add(btnConfirmar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(206, 163, 150, 38);
		getContentPane().add(btnCancelar);
		
		controller = new FormaPagamentoController();
		formasPagamento = new ArrayList<FormaPagamento>();
		
		//carregaTipoPagamento();
		actionListeners();

	}
	public void reset() {
		// TODO Auto-generated method stub
		txtValor.setText("0,00");
		
	}
	public void carregaTipoPagamento() {
		List<FormaPagamento> retornoTipos = new ArrayList<FormaPagamento>();
		retornoTipos = controller.pesquisarTiposPagamento();
		preencheComboTela(retornoTipos);
	}
	
	private void preencheComboTela(List<FormaPagamento> tiposPagto) {
		if(formasPagamento.isEmpty()) {
			for(FormaPagamento tipo : tiposPagto) { 
				cbFormas.addItem(tipo.getNome());
			}
			formasPagamento = tiposPagto;
		}
		
	}
	public FormaPagamento  getFormaPagamento(){
		
		
		int formaIndex = cbFormas.getSelectedIndex();
		for(int i=0;i<formasPagamento.size();i++) { 
			System.out.println(formasPagamento.get(i).getNome());
		}
		
		String valorBR = txtValor.getText().trim();
		String valorCerto = valorBR.replace(',', '.');
		BigDecimal valorPgto = new BigDecimal(valorCerto);
		
		FormaPagamento formaPgto = new FormaPagamento();
		formaPgto.setNome(formasPagamento.get(formaIndex).getNome());
		formaPgto.setValor(valorPgto);
		formaPgto.setId(formasPagamento.get(formaIndex).getId());
		return formaPgto;
		
	}
}
