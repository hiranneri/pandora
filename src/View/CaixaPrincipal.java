package View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.toedter.calendar.JDateChooser;
import Controller.CaixaController;
import Model.ParametrosPesquisa;
import Model.RelatorioTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CaixaPrincipal extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblVendas;
	private CaixaController controller;
	private static CaixaPrincipal frame;
	public static List<Model.Venda> vendas;
	private RelatorioTableModel model;
	private JScrollPane scrollPane;
	private JLabel lblNnn;
	private JDateChooser dtFim;
	private JDateChooser dtInicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CaixaPrincipal frame = getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static synchronized CaixaPrincipal getInstance() {
		if(frame==null) {
			frame = new CaixaPrincipal();
		}
		return frame;
	}
	/**
	 * Create the frame.
	 */
	private CaixaPrincipal() {
		setTitle("Caixa");
		setBounds(100, 100, 576, 419);
		getContentPane().setLayout(null);
		
		controller = new CaixaController();
		scrollPane = new JScrollPane();
		tblVendas = new JTable();
		scrollPane.setViewportView(tblVendas);
		vendas = new ArrayList<Model.Venda>();
		model = new RelatorioTableModel();
		tblVendas.setModel(model);
		
		scrollPane.setBounds(10, 107, 540, 205);
		getContentPane().add(scrollPane);
		
		
		
		
		JLabel lblCaixa = new JLabel("Relat\u00F3rio de Vendas");
		lblCaixa.setBounds(153, 11, 159, 14);
		lblCaixa.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblCaixa);
		
		dtInicio = new JDateChooser("dd/MM/yyyy", "##/##/####", '-');
		dtInicio.setBounds(61, 37, 107, 20);
		getContentPane().add(dtInicio);
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setBounds(10, 42, 43, 14);
		lblInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(lblInicio);
		
		JLabel lblFim = new JLabel("Fim");
		lblFim.setBounds(197, 42, 43, 14);
		lblFim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(lblFim);
		
		dtFim = new JDateChooser("dd/MM/yyyy", "##/##/####", '-');
		dtFim.setBounds(248, 37, 107, 20);
		getContentPane().add(dtFim);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(390, 37, 107, 46);
		getContentPane().add(btnPesquisar);
		
		JLabel lblForamEncontrados = new JLabel("Foram encontrados");
		lblForamEncontrados.setBounds(10, 81, 122, 14);
		lblForamEncontrados.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(lblForamEncontrados);
		
		lblNnn = new JLabel("nnn");
		lblNnn.setBounds(197, 81, 43, 14);
		lblNnn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(lblNnn);
		
		JLabel lblVendas = new JLabel("vendas");
		lblVendas.setBounds(268, 81, 77, 14);
		lblVendas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(lblVendas);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(39, 324, 107, 46);
		getContentPane().add(btnFechar);
		
		
		
		btnPesquisar.addActionListener(controller);
		btnFechar.addActionListener(controller);

	}
	
	public ParametrosPesquisa getDatas() {
		Date dataInicio = dtInicio.getDate();
		Date dataFim = dtFim.getDate();
		
		ParametrosPesquisa param = new ParametrosPesquisa();
		param.setDataInicio(dataInicio);
		param.setDataFim(dataFim);
		return param;
	}
	public void preenche(List<Model.Venda> vendas) {
		for(Model.Venda v:vendas) {
			model.addRow(v);
			System.out.println(v.getHoraVenda());
		}
		int nr = vendas.size();
		lblNnn.setText(Integer.toString(nr));
	}

	public void reset() {
		// TODO Auto-generated method stub
		dtInicio.setDate(null);
		dtFim.setDate(null);
		model.clear();
		
	}
	public void apagaPesquisa() {
		model.clear();
	}
	

}
