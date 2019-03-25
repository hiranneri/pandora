package View;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import Controller.PesquisaItensController;
import Model.ItemVenda;
import Model.ParametrosPesquisa;
import Model.PesquisaItensTableModel;
import javax.swing.JScrollPane;

public class PesquisaProdutoVenda extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static PesquisaProdutoVenda frame;
	private static JTable tblProdutos;
	private static JTextField txtProduto;
	private static JButton btnCancelar;
	private static JButton btnAdicionar;
	private static JButton btnPesquisar;
	private PesquisaItensTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PesquisaProdutoVenda frame = new PesquisaProdutoVenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static synchronized PesquisaProdutoVenda getInstance() {
		if(frame==null) {
			frame = new PesquisaProdutoVenda();
		}
		actionListeners();
		return frame;
	}
	/**
	 * Create the frame.
	 */
	private PesquisaProdutoVenda() {
		setTitle("Pesquisa de Itens");
		setBounds(100, 100, 450, 333);
		getContentPane().setLayout(null);
		
		JLabel lblPesquisaDeItens = new JLabel("Pesquisa de Itens");
		lblPesquisaDeItens.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPesquisaDeItens.setBounds(110, 11, 154, 14);
		getContentPane().add(lblPesquisaDeItens);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(20, 256, 102, 33);
		getContentPane().add(btnCancelar);
		
		tblProdutos = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(400, 150);
		scrollPane.setLocation(10, 90);
		scrollPane.setViewportView(tblProdutos);
		
		model = new PesquisaItensTableModel();
		tblProdutos.setModel(model);
		tblProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduto.setBounds(10, 33, 76, 14);
		getContentPane().add(lblProduto);
		
		btnAdicionar = new JButton("Adicionar Item");
		btnAdicionar.setBounds(257, 43, 154, 33);
		getContentPane().add(btnAdicionar);
		
		txtProduto = new JTextField();
		txtProduto.setBounds(10, 54, 237, 20);
		getContentPane().add(txtProduto);
		txtProduto.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(309, 8, 102, 22);
		getContentPane().add(btnPesquisar);
		btnAdicionar.setVisible(false);
		
		
		
		
		getContentPane().add(scrollPane);
		
		
		
		 

	}
	public void reset() {
		// TODO Auto-generated method stub
		txtProduto.setText("");
		btnAdicionar.setVisible(false);
		model.clear();
		
	}
	private static void actionListeners() {
		PesquisaItensController controller = new PesquisaItensController();
		btnAdicionar.addActionListener(controller);
		btnCancelar.addActionListener(controller);
		btnPesquisar.addActionListener(controller);
	}
	public ParametrosPesquisa getPesquisa() {
		String pesq = txtProduto.getText();
		ParametrosPesquisa pesquisa = ParametrosPesquisa.getInstance();
		pesquisa.setValor(pesq);
		return pesquisa;
	}

	public void preenche(List<ItemVenda> produtos) {
		for(ItemVenda p:produtos) {
			model.addRow(p);
		}
		if(!produtos.isEmpty()) {
			btnAdicionar.setVisible(true);
		}
	}
	
	public ItemVenda getProdutoSelecionado() {
		int row =tblProdutos.getSelectedRow();
		if(row>=0) {
			ItemVenda produtoSelecionado = model.get(row);
			return produtoSelecionado;
		}
		
		return null;
	}
	public void adicionarProdutosLocalizados(List<ItemVenda> retornoProdutos) {
		// TODO Auto-generated method stub
		preenche(retornoProdutos);
		
	}
	public void setCampoPesquisa(String valorPesquisado) {
		txtProduto.setText(valorPesquisado);
	}
	public void apagaTabela() {
		// TODO Auto-generated method stub
		model.clear();
	}
}
