package Model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import View.CaixaPrincipal;

public class RelatorioTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	public static final int COL_DTVENDA = 0;
    public static final int COL_HRVENDA = 1;
    public static final int COL_PDV = 2;
    public static final int COL_TOTAL = 3;
    public static final int COL_FUNCIONARIO = 4;
    
    
    private List<Model.Venda> vendas;
    
    public RelatorioTableModel() {
		// TODO Auto-generated constructor stub
	
    	
        this.vendas = CaixaPrincipal.vendas;
        /*
        listarProdutos.addAll(p);
        fireTableDataChanged();
        */
    }
    public int getRowCount(){
    	return vendas.size();
    }
        
    public int getColumnCount(){
        return 5;
    }
    public Object getValueAt(int linha, int colunas){
    	
        Model.Venda venda  = this.vendas.get(linha);
        
        if(colunas == COL_DTVENDA) return venda.getDataVenda();
        if(colunas == COL_HRVENDA) return venda.getHoraVenda();
        if(colunas == COL_PDV) return venda.getPdv();
        if(colunas == COL_TOTAL) return venda.getTotalVenda();
        if(colunas == COL_FUNCIONARIO) return venda.getFuncionario().getNome();
        
        return venda;
        
    }
  
    public String getColumnName(int colunas){
    	 if(colunas == COL_DTVENDA) return "Data";
         if(colunas == COL_HRVENDA) return "Hora";
         if(colunas == COL_PDV) return "PDV";
         if(colunas == COL_TOTAL) return "Total";
         if(colunas == COL_FUNCIONARIO) return "Funcionário";
       
        return "";
    }
    public Model.Venda get(int linha){
        return vendas.get(linha);
    }

    public void addRow(Model.Venda i) {
    		this.vendas.add(i);
    		fireTableDataChanged();
    	
    }
    public void clear() {
    	vendas.clear();
    	fireTableDataChanged();
    }
    public void atualizaTabela() {
    	fireTableDataChanged();
    }
    
    public void remove(int index) {
    	if(index<=vendas.size() && index>=0) {
    		vendas.remove(index);
    		fireTableRowsDeleted(index, index);
    	}
    	
    }
}
