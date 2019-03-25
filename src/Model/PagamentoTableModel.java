package Model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import View.Pagamento;

public class PagamentoTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	public static final int COL_TIPO = 0;
    public static final int COL_VALOR= 1;
    
    private List<FormaPagamento> formaspagto;
    
    public PagamentoTableModel() {
		// TODO Auto-generated constructor stub
    	this.formaspagto = Pagamento.listaPagamentos;
	}
    	
     
    public int getRowCount(){
    	if(formaspagto==null) {
    		return 0;
    	}
        return formaspagto.size();
    }
    public int getColumnCount(){
        return 2;
    }
    public Object getValueAt(int linha, int colunas){
    	
        FormaPagamento formaspagto  = this.formaspagto.get(linha);
        
        if(colunas == COL_TIPO) return formaspagto.getNome();
        if(colunas == COL_VALOR) return formaspagto.getValor();
        
        return formaspagto;
        
    }
    /*
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	// TODO Auto-generated method stub
    	super.setValueAt(aValue, rowIndex, columnIndex);
    }
    */
    public String getColumnName(int colunas){
        if(colunas == COL_TIPO) return "Tipo";
        if(colunas == COL_VALOR) return "Valor";
       
        return "";
    }
    public FormaPagamento get(int linha){
        return formaspagto.get(linha);
    }
    
    public void addRow(FormaPagamento tipo) {
    	/*
    	this.formaspagto.add(tipo);
    	fireTableDataChanged();
    	*/
    	if(tipo!=null) {
    		this.formaspagto.add(tipo);
    		fireTableDataChanged();
    	}
    }
    public void clear() {
    	formaspagto.clear();
    	fireTableDataChanged();
    }
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }
    public void remove(int index) {
    	if(index<=formaspagto.size() && index>=0) {
    		formaspagto.remove(index);
    		fireTableRowsDeleted(index, index);
    	}
    	
    }
}
