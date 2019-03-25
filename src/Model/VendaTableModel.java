package Model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import View.Venda;

public class VendaTableModel extends  DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public static final int COL_NRITEM = 0;
    public static final int COL_PRODUTO = 1;
    public static final int COL_QTDEITEM = 2;
    public static final int COL_VLUNITARIO = 3;
    public static final int COL_VLTOTALUNIT = 4;
    
    
    private List<ItemVenda> itens;
    
    public VendaTableModel(){
    	
        this.itens = Venda.itens;
        /*
        listarProdutos.addAll(p);
        fireTableDataChanged();
        */
    }
    public int getRowCount(){
    	if(itens==null) {
    		return 0;
    	}
        return itens.size();
    }
    public int getColumnCount(){
        return 5;
    }
    public Object getValueAt(int linha, int colunas){
    	
        ItemVenda item  = this.itens.get(linha);
        
        if(colunas == COL_NRITEM) return item.getNrItem();
        if(colunas == COL_PRODUTO) return item.getProduto().getNome();
        if(colunas == COL_QTDEITEM) return item.getQuantidade(); //colcando o objeto da tela - Overflow
        if(colunas == COL_VLUNITARIO) return item.getProduto().getprecovenda();
        if(colunas == COL_VLTOTALUNIT) return item.getSubTotal();
        
        return item;
        
    }
  
    public String getColumnName(int colunas){
    	 if(colunas == COL_NRITEM) return "Item";
         if(colunas == COL_PRODUTO) return "Produto";
         if(colunas == COL_QTDEITEM) return "Qtde";
         if(colunas == COL_VLUNITARIO) return "Valor";
         if(colunas == COL_VLTOTALUNIT) return "Total Unit";
       
        return "";
    }
    public ItemVenda get(int linha){
        return itens.get(linha);
    }

    public void addRow(ItemVenda i) {
    	if(i!=null) {
    		fireTableDataChanged();
    	}
    	
    }
    public void clear() {
    	itens.clear();
    	fireTableDataChanged();
    }
    public void atualizaTabela() {
    	fireTableDataChanged();
    }
    
    public void remove(int index) {
    	if(index<=itens.size() && index>=0) {
    		itens.remove(index);
    		fireTableRowsDeleted(index, index);
    	}
    	
    }
}
