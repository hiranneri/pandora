package View;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.util.Date;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.math.BigDecimal;
import com.toedter.calendar.JDateChooser;
import Controller.CadastroProdutoController;
import Model.Produto;
import Utilitarios.JNumberFormatField;
import javax.swing.JFrame;

public class CadastroProduto extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTextField txtProduto;
	private static JNumberFormatField txtPrecoVenda;
	private static CadastroProduto frame;
	private static JEditorPane txtObservacao;
	private static JNumberFormatField txtPrecoCompra;
	private static JDateChooser dtValidade;
	private static JDateChooser dtCompra;
	
	private static JButton btnCancelar;
	private static JButton btnSalvar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProduto frame = CadastroProduto.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static synchronized CadastroProduto getInstance() {
		if(frame==null) {
			frame = new CadastroProduto();
			actionListeners();
		}
		return frame;
	}
	/*
	public static synchronized CadastroProduto getInstance(Produto p) {
		if(frame==null) {
			frame = new CadastroProduto();
		}
		txtProduto.setText(p.getNome());
		txtPrecoCompra.setText(Double.toString(p.getPrecoCompra()));
		txtPrecoVenda.setText(Double.toString(p.getPrecoVenda()));
		txtObservacao.setText(p.getObservacao());
		dtValidade.setDate(p.getValidade());
		dtCompra.setDate(p.getDataCompra());
		
		return frame;
	}
	*/

	/**
	 * Create the frame.
	 */
	private CadastroProduto() {
		setTitle("Cadastro de Produtos");
		setResizable(true);	
		setClosable(true);
		setBounds(100, 100, 692, 337);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JLabel lblCadastroDeProdutos = new JLabel("Cadastro de Produtos");
		lblCadastroDeProdutos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCadastroDeProdutos.setBounds(210, 11, 201, 21);
		getContentPane().add(lblCadastroDeProdutos);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduto.setBounds(10, 55, 57, 14);
		getContentPane().add(lblProduto);
		
		JLabel lblValor = new JLabel("Data da Compra:");
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblValor.setBounds(334, 91, 107, 14);
		getContentPane().add(lblValor);
		
		JLabel lblValidade = new JLabel("Validade:");
		lblValidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblValidade.setBounds(378, 55, 63, 14);
		getContentPane().add(lblValidade);
		
		JLabel lblPreoDeVenda = new JLabel("Pre\u00E7o de Venda:");
		lblPreoDeVenda.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPreoDeVenda.setBounds(10, 83, 107, 14);
		getContentPane().add(lblPreoDeVenda);
		
		JLabel lblPreoDeCompra = new JLabel("Pre\u00E7o de Compra:");
		lblPreoDeCompra.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPreoDeCompra.setBounds(334, 125, 107, 14);
		getContentPane().add(lblPreoDeCompra);
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es:");
		lblObservaes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblObservaes.setBounds(10, 151, 92, 14);
		getContentPane().add(lblObservaes);
		
		this.txtObservacao = new JEditorPane();
		txtObservacao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtObservacao.setBounds(10, 168, 578, 86);
		getContentPane().add(txtObservacao);
		
		txtProduto = new JTextField();
		txtProduto.setBounds(123, 53, 201, 20);
		getContentPane().add(txtProduto);
		txtProduto.setColumns(10);
		
		 btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(93, 265, 107, 31);
		getContentPane().add(btnSalvar);
		
		 btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(318, 265, 107, 31);
		getContentPane().add(btnCancelar);
		
		this.dtValidade = new JDateChooser("dd/MM/yyyy","##/##/####",'-');
		dtValidade.setBounds(451, 55, 137, 20);
		getContentPane().add(dtValidade);
		
		this.dtCompra = new JDateChooser("dd/MM/yyyy", "##/##/####", '-');
		dtCompra.setBounds(451, 91, 137, 20);
		getContentPane().add(dtCompra);
		
		txtPrecoVenda = new JNumberFormatField();
		txtPrecoVenda.setColumns(10);
		txtPrecoVenda.setBounds(124,81,150,20);
		getContentPane().add(txtPrecoVenda);
		
		txtPrecoCompra = new JNumberFormatField();
		txtPrecoCompra.setBounds(451, 123, 150, 20);
		getContentPane().add(txtPrecoCompra);
		

	}
	public static void actionListeners() {
		CadastroProdutoController prodController = new CadastroProdutoController();
		btnSalvar.addActionListener(prodController);
		btnCancelar.addActionListener(prodController);
	}
	public Produto getProduto() {
		String nome= txtProduto.getText();
		String obs = txtObservacao.getText();
		String vlVenda = txtPrecoVenda.getText().replace(',', '.').trim();
		String vlCompra = txtPrecoCompra.getText().replace(',', '.').trim();
		Date validade = dtValidade.getDate();
		Date dataCompra = dtCompra.getDate();
		Produto p = new Produto(nome, validade, dataCompra);
		
		BigDecimal precoVenda = new BigDecimal(vlVenda);
		BigDecimal precoCompra = new BigDecimal(vlCompra);
		
		p.setPrecoVenda(precoVenda);
		p.setPrecoCompra(precoCompra);
		p.setObservacao(obs);
		
		/* Verificar a transformação para BR
		 * 
		BigDecimal precoVenda = p.getValor(venda);
		double precoCompra = p.getValor(compra);
		p.setPrecoVenda(precoVenda);
		p.setPrecoCompra(precoCompra);
		p.setObservacao(obs);
		*/
		
		return p;
	}
	public void mensagem(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem);
	}

	public void reset() {
		txtProduto.setText("");
		txtObservacao.setText("");
		txtPrecoVenda.setText("");
		txtPrecoCompra.setText("");
		dtValidade.setDate(null);
		dtCompra.setDate(null);
		txtObservacao.setText("");
		//getContentPane().remove(this);
		//Principal.desktopPane.remove(CadastroProduto.getInstance());
		
	}
	
}
