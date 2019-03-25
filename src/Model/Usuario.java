package Model;

public class Usuario {
	
	private int id;
	private String nome;
	private String login;
	private String senha;
	private String cpf;
	
	public Usuario() {
		
	}
	public Usuario(String usuario, String senha2) {
		// TODO Auto-generated constructor stub
		this.login = usuario;
		this.senha = senha2;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", login=" + login + ", senha=" + senha + ", cpf=" + cpf + "]";
	}
	public Usuario getUsuarioLogado() {
		return this;
	}
	
	


}
