package View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.math.BigDecimal;

import Controller.PagamentoController;
import Model.FormaPagamento;
import Model.PagamentoTableModel;

import javax.swing.JButton;
import java.awt.Color;

public class Pagamento extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblFormasPgto;
	private static Pagamento frame;
	private static PagamentoController controller;
	private static JButton btnConfirmar;
	private static JButton btnCancelar;
	private PagamentoTableModel model;
	private static JLabel lblValorTotal;
	private static JButton btnAdicionarPagto;
	public static List<FormaPagamento> listaPagamentos;
	private JLabel lblTroco;
	private JLabel lblValorRecebido;
	private static JButton btnExcluirPagto;
	private JLabel lblTxtFalta;
	private JLabel lblFalta;
	private JLabel lblInstrucoes;
	private JLabel lblTxtTroco;
	private JLabel lblTxtInstrucoes;
	private Model.Venda vendaRealizada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pagamento frame = getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static synchronized Pagamento getInstance() {
		if(frame==null) {
			frame = new Pagamento();
		}
		return frame;
	}
	private static void actionListeners() {
		
		btnConfirmar.addActionListener(controller);
		btnCancelar.addActionListener(controller);
		btnAdicionarPagto.addActionListener(controller);
		btnExcluirPagto.addActionListener(controller);
	}
	/**
	 * Create the frame.
	 */
	private Pagamento() {
		setTitle("Pagamento");
		setBounds(100, 100, 692, 427);
		getContentPane().setLayout(null);
		
		controller = new PagamentoController();
		
		
		JLabel lblPagamento = new JLabel("Pagamento");
		lblPagamento.setBounds(10, 11, 110, 20);
		lblPagamento.setFont(new Font("Tahoma", Font.BOLD, 16));
		getContentPane().add(lblPagamento);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(4, 116, 519, 136);
		getContentPane().add(scrollPane);
		
		tblFormasPgto = new JTable();
		scrollPane.setViewportView(tblFormasPgto);
		listaPagamentos = new ArrayList<FormaPagamento>();
		model = new PagamentoTableModel();
		tblFormasPgto.setModel(model);
		tblFormasPgto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblTotalVenda = new JLabel("Total Venda: R$");
		lblTotalVenda.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalVenda.setBounds(266, 11, 152, 30);
		getContentPane().add(lblTotalVenda);
		
		lblValorTotal = new JLabel("0,00");
		lblValorTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblValorTotal.setBounds(428, 9, 110, 30);
		getContentPane().add(lblValorTotal);
		
		lblInstrucoes = new JLabel("Instru\u00E7\u00F5es:");
		lblInstrucoes.setForeground(Color.RED);
		lblInstrucoes.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInstrucoes.setBounds(20, 54, 100, 23);
		getContentPane().add(lblInstrucoes);
		
		lblTroco = new JLabel("0,00");
		lblTroco.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTroco.setBounds(89, 300, 64, 23);
		getContentPane().add(lblTroco);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(41, 333, 145, 40);
		getContentPane().add(btnConfirmar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(345, 333, 132, 40);
		getContentPane().add(btnCancelar);
		
		btnAdicionarPagto = new JButton("+");
		btnAdicionarPagto.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdicionarPagto.setBounds(577, 116, 77, 30);
		getContentPane().add(btnAdicionarPagto);
		
		JLabel lblTxtValorRecebido = new JLabel("Valor Recebido R$:");
		lblTxtValorRecebido.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTxtValorRecebido.setBounds(236, 284, 181, 20);
		getContentPane().add(lblTxtValorRecebido);
		
		lblValorRecebido = new JLabel("0,00");
		lblValorRecebido.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblValorRecebido.setBounds(428, 284, 95, 20);
		getContentPane().add(lblValorRecebido);
		
		btnExcluirPagto = new JButton("-");
		btnExcluirPagto.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExcluirPagto.setBounds(577, 169, 77, 30);
		getContentPane().add(btnExcluirPagto);
		
		lblTxtFalta = new JLabel("Falta:");
		lblTxtFalta.setForeground(Color.RED);
		lblTxtFalta.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTxtFalta.setBounds(14, 267, 86, 23);
		getContentPane().add(lblTxtFalta);
		
		lblFalta = new JLabel("0,00");
		lblFalta.setForeground(Color.RED);
		lblFalta.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFalta.setBounds(89, 267, 64, 23);
		getContentPane().add(lblFalta);
		
		lblTxtTroco = new JLabel("Troco:");
		lblTxtTroco.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTxtTroco.setBounds(14, 301, 86, 23);
		getContentPane().add(lblTxtTroco);
		
		lblTxtInstrucoes = new JLabel("No bot\u00E3o \"+\" ao lado direito da tela, escolha a(s) "
				+ "forma(s) de pagamento desta venda.");
		lblTxtInstrucoes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTxtInstrucoes.setBounds(20, 76, 528, 23);
		getContentPane().add(lblTxtInstrucoes);
		
		lblTxtFalta.setVisible(false);
		lblFalta.setVisible(false);
		
		lblTxtTroco.setVisible(false);
		lblTroco.setVisible(false);
		btnConfirmar.setVisible(false);
		
		actionListeners();
		

	}
	public void reset() {
		// TODO Auto-generated method stub
		lblTroco.setText("0,00");
		lblValorRecebido.setText("0,00");
		lblValorTotal.setText("0,00");
		lblFalta.setText("0,00");
		lblFalta.setVisible(false);
		lblTxtFalta.setVisible(false);
		lblTroco.setVisible(false);
		lblTxtTroco.setVisible(false);
		listaPagamentos.clear();
		
	}
	
	public void adicionarFormaPagamento(FormaPagamento pgto) {
		BigDecimal v = pgto.getValor();
		if(v.compareTo(BigDecimal.ZERO)>0) {
			model.addRow(pgto);
			somarPagamentos();
			calcularTroco();
		}
		
	}
	private void somarPagamentos() {
		BigDecimal valorTotal = BigDecimal.ZERO;
		for(FormaPagamento pagto:listaPagamentos) {
				valorTotal = valorTotal.add(pagto.getValor()); 	
			}
		lblValorRecebido.setText(valorTotal.toString());
	}
	/* Seguindo a lógica do vendedor
	 * ValorDaVenda - ValorRecebido
	 */
	
	private void calcularTroco() {
		
		BigDecimal troco = BigDecimal.ZERO;
		String totVenda = lblValorTotal.getText();
		BigDecimal totalVenda = new BigDecimal(totVenda);
		String vRec = lblValorRecebido.getText();
		BigDecimal vlRecebido = new BigDecimal(vRec);
		
		troco = totalVenda.subtract(vlRecebido);
		if(troco.compareTo(BigDecimal.ZERO)<=0) {
			troco = troco.multiply(new BigDecimal("-1"));
			lblTxtTroco.setVisible(true);
			lblTroco.setVisible(true);
			lblTroco.setText(troco.toString());
			
			lblTxtFalta.setVisible(false);
			lblFalta.setVisible(false);
			
			btnConfirmar.setVisible(true);
		}else if(troco.compareTo(BigDecimal.ZERO)>0) {
			//Regra que falta pagar mais
			lblTroco.setVisible(false);
			lblTxtTroco.setVisible(false);
			lblTxtFalta.setVisible(true);
			lblFalta.setVisible(true);
			lblFalta.setText(troco.toString());
		}
		
	}
	public void excluirFormaPagamento() {
		
		int linha = tblFormasPgto.getSelectedRow();
		
		if(linha<=listaPagamentos.size() && linha>=0) {
			listaPagamentos.remove(linha);
			model.remove(linha);
			atualizarValores();
    	}
		
		
	}
	private void atualizarValores() {
		somarPagamentos();
		calcularTroco();
	}
	public void getVenda(Model.Venda venda) {
		vendaRealizada = venda;
		lblValorTotal.setText(vendaRealizada.getTotalVenda().toString());
	}
	
	public Model.Venda criarAGigantescaVenda() {
		BigDecimal troco = new BigDecimal(lblTroco.getText());
		BigDecimal valorRecebido = new BigDecimal(lblValorRecebido.getText());
		vendaRealizada.setTrocoVenda(troco);
		vendaRealizada.setValorRecebido(valorRecebido);
		vendaRealizada.setFormasPagamento(listaPagamentos);
		
		return vendaRealizada;
	}
	public void mensagem(String mensagem) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, mensagem);
		
	}
	
}

