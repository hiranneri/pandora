package View;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultFormatter;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import Controller.VendaController;
import Model.ItemVenda;
import Model.ParametrosPesquisa;
import Model.Usuario;
import Model.VendaTableModel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Venda frame;
	public static JTextField txtProduto;
	private JTextField txtPreco;
	public static JSpinner txtQuantidade;
	private static JLabel lblTotalVenda;
	public static JButton btnExcluir;
	public static JButton btnSalvar;
	public static JButton btnCancelar;
	private static VendaController controller;
	public ItemVenda item;
	public static List<ItemVenda> itens;
	private int nr;
	private JTable tblProdutos;
	private VendaTableModel model;
	private Model.Venda vendaRealizada;
	private JLabel lblData;
	private JLabel lblHora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Venda frame = getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static synchronized Venda getInstance() {
		if(frame==null) {
			frame = new Venda();
		}
		return frame;
	}

	/**
	 * Create the frame.
	 */
	private Venda() {
		setTitle("Venda");
		setBounds(100, 100, 769, 439);
		getContentPane().setLayout(null);
		
		controller = new VendaController();
		/*
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 0, 774, 522);
		contentPane.add(desktopPane);
		*/
		getContentPane().setLayout(null);
		JLabel lblUsuario = new JLabel("Usu\u00E1rio");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsuario.setBounds(588, 11, 165, 25);
		getContentPane().add(lblUsuario);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProduto.setBounds(5, 43, 264, 14);
		getContentPane().add(lblProduto);
		
		txtProduto = new JTextField();
		txtProduto.setColumns(10);
		txtProduto.setBounds(5, 69, 328, 25);
		getContentPane().add(txtProduto);
		txtProduto.setActionCommand("txtProduto");
		
		txtProduto.addKeyListener(controller);
		
		 txtQuantidade = new JSpinner();
		txtQuantidade.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		txtQuantidade.setBounds(343, 68, 75, 25);
		getContentPane().add(txtQuantidade);
		
		JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor)txtQuantidade.getEditor();
		DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
		
		txtPreco = new JTextField();
		txtPreco.setEnabled(false);
		txtPreco.setColumns(10);
		txtPreco.setBounds(428, 68, 125, 25);
		getContentPane().add(txtPreco);
		
		btnExcluir = new JButton("Excluir Item");
		btnExcluir.setBounds(587, 63, 108, 35);
		getContentPane().add(btnExcluir);
		
		JLabel lblPreco = new JLabel("Pre\u00E7o");
		lblPreco.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPreco.setBounds(437, 43, 148, 14);
		getContentPane().add(lblPreco);
		
		 btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(31, 324, 148, 50);
		getContentPane().add(btnSalvar);
		btnSalvar.setVisible(false);
		
		 btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(214, 324, 133, 50);
		getContentPane().add(btnCancelar);
		
		JLabel label_3 = new JLabel("Total R$:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		label_3.setBounds(448, 335, 118, 25);
		getContentPane().add(label_3);
		
		 lblTotalVenda = new JLabel("0,00");
		lblTotalVenda.setFont(new Font("Tahoma", Font.BOLD, 31));
		lblTotalVenda.setBounds(572, 322, 125, 42);
		getContentPane().add(lblTotalVenda);
		
		lblData = new JLabel("Data");
		lblData.setBounds(514, 375, 88, 14);
		getContentPane().add(lblData);
		
		lblHora = new JLabel("Hora");
		lblHora.setBounds(619, 372, 77, 14);
		getContentPane().add(lblHora);
		
		lblData.setText(getData());
		lblHora.setText(getHora());
		lblUsuario.setText(Login.usuario.getUsuarioLogado().getNome());
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 121, 689, 192);
		getContentPane().add(scrollPane);
		
		tblProdutos = new JTable();
		
		scrollPane.setViewportView(tblProdutos);
		itens = new ArrayList<ItemVenda>();
		
		model = new VendaTableModel();
		tblProdutos.setModel(model);
		tblProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		actionListeners();

	}

	public String getData() {
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		String dataBR = formatador.format(data);
		return dataBR;
	}
	public String getHora() {
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");
		String horaBR = formatador.format(data);
		return horaBR;
	}
	
	public Date getDataVenda() {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		Date data= new Date();
		try {
			data = formatador.parse(lblData.getText());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}
	public Date getHoraVenda() {
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");
		try {
			data = formatador.parse(lblHora.getText());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}
	
	public Usuario getUsuario() {
		return Login.usuario.getUsuarioLogado();
	}
	
	private static void actionListeners() {
		btnSalvar.addMouseListener(controller);
		btnCancelar.addMouseListener(controller);
		btnExcluir.addMouseListener(controller);
	}
	public void adicionarItemNaTabela() {
		if(item!=null) {
			String q = ((JSpinner.DefaultEditor) ((JSpinner) txtQuantidade).getEditor()).getTextField().getText();
			int qtde = Integer.parseInt(q);
			item.setQuantidade(qtde);
			criarNrItem();
			itens.add(item);
			model.addRow(item);
			System.out.println(item.getId());//Erro no id do Produto ao salvar venda
			calculaValorVenda();
		}
		
	}
	
	private void criarNrItem() {
		if(!itens.isEmpty()) {
			++nr;
		}else {
			nr=1;
		}
		item.setNrItem(nr);
	}
	
	private void atualizarNrItem() {
		int qtde=0;
		for(ItemVenda i:itens) {
			qtde++;
			i.setNrItem(qtde);
		}
		model.atualizaTabela();
		
		
	}

	public void calculaValorVenda() {
		BigDecimal valorTotal= BigDecimal.ZERO;
		if(!itens.isEmpty()){
			for(ItemVenda item: itens) {
				valorTotal = valorTotal.add(item.getSubTotal()); 	
			}
			
			String vlTotal = valorTotal.toString();
			lblTotalVenda.setText(vlTotal);
		}else {
			lblTotalVenda.setText("0,00");
		}
		
		if(valorTotal.compareTo(new BigDecimal("0"))>0) {
			btnSalvar.setVisible(true);
		}
	}
	public String getValorTotalVenda(){
		return lblTotalVenda.getText();
	}

	
	public void selecionarQtde() {
		((JSpinner.DefaultEditor) ((JSpinner) txtQuantidade).getEditor()).getTextField().requestFocus();
        new Thread(new Runnable() {
               @Override
               public void run() {
                   try {
                       Thread.sleep(25);
                   } catch (InterruptedException ex) {
                   }
                   SwingUtilities.invokeLater(new Runnable() {
                       @Override
                       public void run() {
                           ((JSpinner.DefaultEditor) ((JSpinner) txtQuantidade).getEditor()).getTextField().selectAll();
                           ((JSpinner.DefaultEditor) ((JSpinner) txtQuantidade).getEditor()).getTextField().addKeyListener(controller);
                       }
                   });
               }
           }).start();
	}
	public ParametrosPesquisa pesquisar() {
		if(txtProduto.getText()!=null && txtProduto.getText().trim()!="") {
			String pesq = txtProduto.getText();
			ParametrosPesquisa pesquisa = ParametrosPesquisa.getInstance();
			pesquisa.setValor(pesq);
			return pesquisa;
		}
		return null;
	}
	
	public static int getNrItem() {
		return itens.size();
	}

	public void adicionarProduto(ItemVenda prod) {
		item = prod;
		BigDecimal preco = item.getProduto().getprecovenda();
		String precoString = preco.toString();
		txtProduto.setText(item.getProduto().getNome());
		txtPreco.setText(precoString);
		selecionarQtde();
		
	}
	public void resetTela() {
		txtProduto.setText("");
		txtPreco.setText("");
		((JSpinner.DefaultEditor) ((JSpinner) txtQuantidade).getEditor()).getTextField().setText("1");
		lblTotalVenda.setText("0,00");
		apagarTabela();
		this.nr = 0;
		btnSalvar.setVisible(false);
	}
	public void apagarFormasPagamento() {
		// TODO Auto-generated method stub
		FormasPagamento.formasPagamento.clear();
		
	}
	public void apagaCampos() {
		txtProduto.setText("");
		txtPreco.setText("");
		((JSpinner.DefaultEditor) ((JSpinner) txtQuantidade).getEditor()).getTextField().setText("1");
		txtProduto.requestFocus();
		item = null;
	}
	public void apagarTabela() {
		model.clear();
	}
	public static int getQtdeItem(int nrItem) {
		// TODO Auto-generated method stub
		return itens.get(nrItem).getQuantidade();
	}
	public void excluirItem() {
		model.remove(tblProdutos.getSelectedRow());
		atualizarNrItem();
		calculaValorVenda();
	}
	
	
	
	public String getPDV() {
		try {
			String nome = InetAddress.getLocalHost().getHostName();
			return nome;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	public Model.Venda criarAGigantescaVenda() {
		vendaRealizada = new Model.Venda();
		BigDecimal totalVenda = new BigDecimal(lblTotalVenda.getText());
		//Venda
		vendaRealizada.setDataVenda(getDataVenda());
		vendaRealizada.setFuncionario(Login.usuario.getUsuarioLogado());
		vendaRealizada.setHoraVenda(getHoraVenda());
		vendaRealizada.setPdv(getPDV());
		vendaRealizada.setTotalVenda(totalVenda);
		
		
		vendaRealizada.setItensVenda(itens);
		
		
		return vendaRealizada;
	}

}
