package Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;



public class PesquisaItensTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	public static final int COL_NOME = 0;
    public static final int COL_PRECOVENDA = 1;
    public static final int COL_VALIDADE = 2;
    
    private List<ItemVenda> produtos;
    
    public PesquisaItensTableModel(){
    	
      
    	this.produtos = new ArrayList<ItemVenda>();
     
    }
    public int getRowCount(){
        return produtos.size();
    }
    public int getColumnCount(){
        return 2;
    }
    public Object getValueAt(int linha, int colunas){
    	
        ItemVenda produto  = this.produtos.get(linha);
        
        if(colunas == COL_NOME) return produto.getProduto().getNome();
        if(colunas == COL_PRECOVENDA) return produto.getProduto().getprecovenda();
        
        return produto;
        
    }
    public String getColumnName(int colunas){
        if(colunas == COL_NOME) return "Nome";
        if(colunas == COL_PRECOVENDA) return "Preco";
       
        return "";
    }
    public ItemVenda get(int linha){
        return produtos.get(linha);
    }
    
    public void addRow(ItemVenda p) {
    	this.produtos.add(p);
    	fireTableDataChanged();
    }
    public void clear() {
    	produtos.clear();
    	fireTableDataChanged();
    }

}
