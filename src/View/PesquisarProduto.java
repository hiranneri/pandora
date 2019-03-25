package View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import Controller.PesquisaProdutoController;
import Model.ParametrosPesquisa;
import Model.Produto;
import Utilitarios.ProdutosTabelModel;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class PesquisarProduto extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private static PesquisarProduto frame;
	private JRadioButton rdbtnNome;
	private JRadioButton rdbtnValidade;
	private JRadioButton rdbtnDataCompra;
	private JDateChooser data;
	private JLabel lblRegistros;
	private JButton btnPesquisar;
	private JTable tblProdutos;
	private ProdutosTabelModel model;
	private JButton btnEditar;
	private JButton btnExcluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PesquisarProduto frame = new PesquisarProduto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static synchronized PesquisarProduto getInstance() {
		if(frame==null) {
			frame = new PesquisarProduto();
		}
		return frame;
	}

	/**
	 * Create the frame.
	 */
	private PesquisarProduto() {
		setTitle("Pesquisa de Produtos");
		setBounds(100, 100, 572, 355);
		
		JLabel lblPesquisaDeProdutos = new JLabel("Pesquisa de Produtos");
		lblPesquisaDeProdutos.setBounds(193, 11, 154, 14);
		lblPesquisaDeProdutos.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtNome = new JTextField();
		txtNome.setBounds(16, 67, 212, 29);
		txtNome.setColumns(10);
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(399, 59, 98, 37);
		rdbtnNome = new JRadioButton("Nome");
		rdbtnNome.setBounds(33, 37, 62, 23);
		
		rdbtnValidade = new JRadioButton("Validade");
		rdbtnValidade.setBounds(153, 37, 109, 23);
		
		rdbtnDataCompra = new JRadioButton("Data da Compra");
		rdbtnDataCompra.setBounds(276, 37, 117, 23);
		
		data = new JDateChooser("dd/MM/yyyy","##/##/####",'-');
		data.setBounds(238, 67, 139, 27);
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(16, 285, 117, 29);
		getContentPane().add(btnFechar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(352, 105, 109, 29);
		getContentPane().add(btnExcluir);
		
		ButtonGroup grupoParametros = new ButtonGroup();
		grupoParametros.add(rdbtnNome);
		grupoParametros.add(rdbtnValidade);
		grupoParametros.add(rdbtnDataCompra);
		
		txtNome.setVisible(false);
		data.setVisible(false);
		btnPesquisar.setVisible(false);
		btnExcluir.setVisible(false);
		JLabel lblForamEncontrados = new JLabel("Foram encontrados:");
		lblForamEncontrados.setBounds(6, 112, 109, 14);
		
		lblRegistros = new JLabel("000");
		lblRegistros.setBounds(108, 112, 18, 14);
		
		JLabel lblTexto2 = new JLabel("registros");
		lblTexto2.setBounds(139, 112, 43, 14);
		getContentPane().setLayout(null);
		getContentPane().add(lblPesquisaDeProdutos);
		getContentPane().add(rdbtnNome);
		getContentPane().add(rdbtnValidade);
		getContentPane().add(rdbtnDataCompra);
		getContentPane().add(txtNome);
		getContentPane().add(data);
		getContentPane().add(btnPesquisar);
		getContentPane().add(lblForamEncontrados);
		getContentPane().add(lblRegistros);
		getContentPane().add(lblTexto2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 140, 540, 134);
		getContentPane().add(scrollPane);
		
		tblProdutos = new JTable();
		
		scrollPane.setViewportView(tblProdutos);
		
		PesquisaProdutoController controller = new PesquisaProdutoController();
		btnPesquisar.addActionListener(controller);
		rdbtnDataCompra.addActionListener(controller);
		rdbtnNome.addActionListener(controller);
		rdbtnValidade.addActionListener(controller);
		btnFechar.addActionListener(controller);
		
		model = new ProdutosTabelModel();
		tblProdutos.setModel(model);
		tblProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(238, 105, 109, 28);
		getContentPane().add(btnEditar);
		
		btnEditar.setVisible(false);
		btnEditar.addActionListener(controller);
		
		btnExcluir.addActionListener(controller);
		lblForamEncontrados.setVisible(false);
		lblRegistros.setVisible(false);
		lblTexto2.setVisible(false);

	}
	public ParametrosPesquisa getParametros() { //Caso não seja nenhum lançar excessão com a mensagem: "Por favor escolha um parâmetro"
		ParametrosPesquisa parametros = ParametrosPesquisa.getInstance();
		System.out.println(data.getDate());
		if(rdbtnNome.isSelected()) {
			parametros.setParametro("Nome");
			parametros.setValor(txtNome.getText());
			return parametros;
		}
		if(rdbtnValidade.isSelected()) {
			System.out.println("validade");
			parametros.setParametro("Validade");
			parametros.setData(data.getDate());
			return parametros;
		}
		if(rdbtnDataCompra.isSelected()) {
			System.out.println("data");
			parametros.setParametro("Data da Compra");
			parametros.setData(data.getDate());
			return parametros;
		}
		return null;
	}

	public void mostrarResultado(List<Produto> produtosPesquisa) {
		if(produtosPesquisa.size()>=1) {
			btnEditar.setVisible(true);
			btnExcluir.setVisible(true);
		}
		String res = Integer.toString(produtosPesquisa.size());
		lblRegistros.setText(res);
	}
	public void mostrarCampos() {
		if(rdbtnNome.isSelected()) {
			data.setVisible(false);
			txtNome.setVisible(true);
			btnPesquisar.setVisible(true);
		}
		if(rdbtnDataCompra.isSelected() || rdbtnValidade.isSelected()) {
			txtNome.setVisible(false);
			data.setVisible(true);
			btnPesquisar.setVisible(true);
		}
	}
	public void preenche(List<Produto> produtos) {
		for(Produto p:produtos) {
			model.addRow(p);
		}
		//model.fireTableDataChanged();
	}
	
	public void apagarTabela() {
		model.clear();
	}
	
	public void zeraResultado() {
		lblRegistros.setText("0");
	}
	public Produto getProdutoSelecionado() {
		int index = tblProdutos.getSelectedRow();
		Produto produtoSelecionado=null;
		if(index>=0) {
			produtoSelecionado = model.get(index);
		}
		
		return produtoSelecionado;
		
		//System.out.println("Linha selecionanda "+tblProdutos.getSelectedRow());
	}
	public void abrirTela() {
		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().add(this);
	}
	
	public void resetPesquisa() {
		txtNome.setText("");
		data.setDate(null);
		zeraResultado();
		btnEditar.setVisible(false);
		btnExcluir.setVisible(false);
		apagarTabela();
	}

	public void atualizarTabela() {
		// TODO Auto-generated method stub
		tblProdutos.revalidate();
		model.fireTableDataChanged();
	}

	public void mensagem(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
}
