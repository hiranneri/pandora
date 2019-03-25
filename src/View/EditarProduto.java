package View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.math.BigDecimal;
import java.util.Date;

import javax.swing.JTextField;
import Utilitarios.JNumberFormatField;
import com.toedter.calendar.JDateChooser;

import Controller.EditarProdutoController;
import Model.Produto;

import javax.swing.JEditorPane;
import javax.swing.JButton;

public class EditarProduto extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTextField txtProduto;
	private static JNumberFormatField txtPrecoVenda;
	private static JDateChooser dtValidade;
	private static JDateChooser dtCompra;
	private static JNumberFormatField txtPrecoCompra;
	private static JEditorPane txtObservacao;
	private static JLabel lblId;
	private static EditarProduto frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarProduto frame = new EditarProduto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static synchronized EditarProduto getInstance(Produto p) {
		if(frame==null) {
			frame = new EditarProduto();
		}
		int id =p.getId();
		
		lblId.setText(String.valueOf(id));
		txtProduto.setText(p.getNome());
		
		txtPrecoCompra.setText(p.getPrecoCompra().toString());
		txtPrecoVenda.setText(p.getprecovenda().toString());
		txtObservacao.setContentType("text/plain");
		txtObservacao.setText(p.getObservacao());
		dtValidade.setDate(p.getValidade());
		dtCompra.setDate(p.getDataCompra());
		System.out.println(p.getPrecoCompra().toString());
		return frame;
	}
	public static synchronized EditarProduto getInstance() {
		if(frame==null) {
			frame = new EditarProduto();
		}
		
		return frame;
	}
	/**
	 * Create the frame.
	 */
	private EditarProduto() {
		setBounds(100, 100, 650, 367);
		getContentPane().setLayout(null);
		
		JLabel lblEdioDeProdutos = new JLabel("Edi\u00E7\u00E3o de Produtos");
		lblEdioDeProdutos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEdioDeProdutos.setBounds(184, 23, 201, 21);
		getContentPane().add(lblEdioDeProdutos);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduto.setBounds(10, 71, 57, 14);
		getContentPane().add(lblProduto);
		
		txtProduto = new JTextField();
		txtProduto.setColumns(10);
		txtProduto.setBounds(123, 69, 201, 20);
		getContentPane().add(txtProduto);
		
		JLabel lblPrecoVenda = new JLabel("Pre\u00E7o de Venda:");
		lblPrecoVenda.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecoVenda.setBounds(10, 99, 107, 14);
		getContentPane().add(lblPrecoVenda);
		
		txtPrecoVenda = new JNumberFormatField();
		txtPrecoVenda.setBounds(124, 97, 150, 20);
		getContentPane().add(txtPrecoVenda);
		
		JLabel lblValidade = new JLabel("Validade:");
		lblValidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblValidade.setBounds(378, 71, 63, 14);
		getContentPane().add(lblValidade);
		
		dtValidade = new JDateChooser("dd/MM/yyyy", "##/##/####", '-');
		dtValidade.setBounds(451, 71, 137, 20);
		getContentPane().add(dtValidade);
		
		JLabel lblDataCompra = new JLabel("Data da Compra:");
		lblDataCompra.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataCompra.setBounds(334, 107, 107, 14);
		getContentPane().add(lblDataCompra);
		
		dtCompra = new JDateChooser("dd/MM/yyyy", "##/##/####", '-');
		dtCompra.setBounds(451, 107, 137, 20);
		getContentPane().add(dtCompra);
		
		JLabel lblPrecoCompra = new JLabel("Pre\u00E7o de Compra:");
		lblPrecoCompra.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecoCompra.setBounds(334, 141, 107, 14);
		getContentPane().add(lblPrecoCompra);
		
		txtPrecoCompra = new JNumberFormatField();
		txtPrecoCompra.setBounds(451, 139, 150, 20);
		getContentPane().add(txtPrecoCompra);
		
		txtObservacao = new JEditorPane();
		txtObservacao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtObservacao.setBounds(10, 184, 578, 86);
		getContentPane().add(txtObservacao);
		
		JLabel lblObservacoes = new JLabel("Observa\u00E7\u00F5es:");
		lblObservacoes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblObservacoes.setBounds(10, 167, 92, 14);
		getContentPane().add(lblObservacoes);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(164, 295, 107, 31);
		getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(384, 295, 107, 31);
		getContentPane().add(btnCancelar);
		
		lblId = new JLabel("Id");
		lblId.setBounds(19, 44, 48, 14);
		getContentPane().add(lblId);
		
		EditarProdutoController controller = new EditarProdutoController();
		btnSalvar.addActionListener(controller);
		btnCancelar.addActionListener(controller);

	}
	public void reset() {
		lblId.setText("");
		txtProduto.setText("");
		txtObservacao.setText("");
		txtPrecoVenda.setText("");
		txtPrecoCompra.setText("");
		dtValidade.setDate(null);
		dtCompra.setDate(null);
		txtObservacao.setText("");
		
	}
	public Produto getProdutoEditar() {
		String id = lblId.getText();
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

		p.setId(Integer.parseInt(id));
		return p;
	}
	
	public void mensagem(String msg) {
		JOptionPane.showMessageDialog(this, msg);
		
	}
}
