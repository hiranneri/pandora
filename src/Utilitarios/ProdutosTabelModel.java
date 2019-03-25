package Utilitarios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import Model.Produto;

public class ProdutosTabelModel extends AbstractTableModel {
	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public static final int COL_NOME = 0;
	    public static final int COL_PRECOVENDA = 1;
	    public static final int COL_VALIDADE = 2;
	    public static final int COL_DATACOMPRA = 3;
	    public static final int COL_PRECOCOMPRA = 4;
	    
	    private List<Produto> produtos;
	    
	    public ProdutosTabelModel(){
	    	
	        this.produtos = new ArrayList<Produto>();
	        /*
	        listarProdutos.addAll(p);
	        fireTableDataChanged();
	        */
	    }
	    public int getRowCount(){
	        return produtos.size();
	    }
	    public int getColumnCount(){
	        return 5;
	    }
	    public Object getValueAt(int linha, int colunas){
	    	
	        Produto produto  = this.produtos.get(linha);
	        if(colunas == COL_NOME) return produto.getNome();
	        if(colunas == COL_PRECOVENDA) return produto.getprecovenda();
	        if(colunas == COL_VALIDADE) return produto.getValidade();
	        if(colunas == COL_DATACOMPRA) return produto.getDataCompra();
	        if(colunas == COL_PRECOCOMPRA) return produto.getPrecoCompra();
	        
	        return produto;
	        
	    }
	    public String getColumnName(int colunas){
	        if(colunas == COL_NOME) return "Nome";
	        if(colunas == COL_PRECOVENDA) return "Preço de Venda";
	        if(colunas == COL_VALIDADE) return "Validade";
	        if(colunas == COL_DATACOMPRA) return "Data Compra";
	        if(colunas == COL_PRECOCOMPRA) return "Preco de Compra";
	        return "";
	    }
	    public Produto get(int linha){
	        return produtos.get(linha);
	    }
	    
	    public void addRow(Produto p) {
	    	this.produtos.add(p);
	    	fireTableDataChanged();
	    }
	    
	    public void clear() {
	    	produtos.clear();
	    	fireTableDataChanged();
	    }
	    
}
