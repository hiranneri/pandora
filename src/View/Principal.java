package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Controller.PrincipalController;
import Model.ItemVenda;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JDesktopPane;

public class Principal extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static Principal frame;
	public static JDesktopPane desktopPane;
	private static JMenuItem novoProduto;
	private static JMenuItem pesquisarUsuario;
	private static JMenuItem pesquisarProduto;
	private static JMenuItem novaVenda;
	private static JMenuItem caixa;
	private static JMenuItem novoUsuario;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static synchronized Principal getInstance() {
		if(frame==null) {
			frame = new Principal();
			
		}
		
		return frame;
	}
	/**
	 * Create the frame.
	 */
	private Principal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setSize(1024, 768);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnProdutosmenu = new JMenu("Produto");
		menuBar.add(mnProdutosmenu);
		
		novoProduto = new JMenuItem("Cadastrar");
		mnProdutosmenu.add(novoProduto);
		
		pesquisarProduto = new JMenuItem("Pesquisar");
		mnProdutosmenu.add(pesquisarProduto);
		
		novaVenda = new JMenuItem("Venda");
		menuBar.add(novaVenda);
		
		caixa = new JMenuItem("Caixa");
		menuBar.add(caixa);
		
		JMenu mnUsurio = new JMenu("Usu\u00E1rio");
		menuBar.add(mnUsurio);
		
		novoUsuario = new JMenuItem("Novo");
		mnUsurio.add(novoUsuario);
		
		pesquisarUsuario = new JMenuItem("Localizar");
		mnUsurio.add(pesquisarUsuario);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 0, 774, 522);
		contentPane.add(desktopPane);
		

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		actionListeners();
		
	}
	public static void actionListeners() {
		PrincipalController controllerPrincipal = new PrincipalController();
		novoProduto.addActionListener(controllerPrincipal);
		pesquisarProduto.addActionListener(controllerPrincipal);
		novaVenda.addActionListener(controllerPrincipal);
		caixa.addActionListener(controllerPrincipal);
		novoUsuario.addActionListener(controllerPrincipal);
		pesquisarUsuario.addActionListener(controllerPrincipal);
		
	}
	/*
	 * Resolução do problema para abrir várias vezes
	 * Resolver para os casos de editar e futuros casos em que terá duas telas no JDesktop
	 * Lembrete: Tela de Pesquisa não reseta quando aciono o removeAll();
	 */
	public static void adicionarDesktop(JInternalFrame internalFrame) {
		desktopPane.remove(internalFrame);
		desktopPane.add(internalFrame);
		desktopPane.setVisible(true);
		
		if (!internalFrame.isVisible()) {
			internalFrame.setVisible(true);
		}
		internalFrame.toFront();
		
		Dimension d = desktopPane.getSize();
		internalFrame.setLocation((d.width - internalFrame.getSize().width) 
				/ 2, (d.height - internalFrame.getSize().height) / 2);
	}
	public static void adicionarDesktop(PesquisaProdutoVenda internalFrame, List<ItemVenda> retornoProdutos,String valorPesquisado) {
		// TODO Auto-generated method stub
		desktopPane.remove(internalFrame);
		desktopPane.add(internalFrame);
		desktopPane.setVisible(true);
		
		if (!internalFrame.isVisible()) {
			internalFrame.setVisible(true);
		}
		
		//inserir objetos do retornoProdutos na tabela
		internalFrame.adicionarProdutosLocalizados(retornoProdutos);
		internalFrame.setCampoPesquisa(valorPesquisado);
		
		internalFrame.toFront();
		
		Dimension d = desktopPane.getSize();
		internalFrame.setLocation((d.width - internalFrame.getSize().width) 
				/ 2, (d.height - internalFrame.getSize().height) / 2);
		
		
		
	}


	
}
