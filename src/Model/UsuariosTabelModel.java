package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class UsuariosTabelModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	public static final int COL_NOME = 0;
    public static final int COL_CPF = 1;
    public static final int COL_LOGIN = 2;
    
    private List<Usuario> usuarios;
    
    public UsuariosTabelModel(){
    	
        this.usuarios = new ArrayList<Usuario>();
        /*
        listarProdutos.addAll(p);
        fireTableDataChanged();
        */
    }
    public int getRowCount(){
        return usuarios.size();
    }
    public int getColumnCount(){
        return 3;
    }
    public Object getValueAt(int linha, int colunas){
    	
        Usuario usuario  = this.usuarios.get(linha);
        if(colunas == COL_NOME) return usuario.getNome();
        if(colunas == COL_CPF) return usuario.getCpf();
        if(colunas == COL_LOGIN) return usuario.getLogin();
        
        return usuario;
        
    }
    public String getColumnName(int colunas){
        if(colunas == COL_NOME) return "Nome";
        if(colunas == COL_CPF) return "CPF";
        if(colunas == COL_LOGIN) return "Login";
       
        return "";
    }
    public Usuario get(int linha){
        return usuarios.get(linha);
    }
    
    public void addRow(Usuario p) {
    	this.usuarios.add(p);
    	fireTableDataChanged();
    }
    public void clear() {
    	usuarios.clear();
    	fireTableDataChanged();
    }

}
