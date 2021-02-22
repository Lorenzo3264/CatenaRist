package classi;

public class Cliente extends Utente {

	//ATTRIBUTI
	private String codCl; //da int a String per permettere di usare "nextval("seq_codcl")" nell'inserimento a database
	private String nome;
	private String cognome;
	private String dataN;
	private String email;
	private String password;
	private String cellulare;
	
	public Cliente(Utente utente) {
		// TODO Auto-generated constructor stub
		this.nome = utente.getNome();
		this.cognome = utente.getCognome();
		this.dataN = utente.getDataN();
		this.email = utente.getEmail();
		this.password = utente.getPassword();
		this.cellulare = utente.getCellulare();
	}
	
	public Cliente() {}
	
	@Override
	public String toString() {
		return "Cliente [codCl=" + codCl + ", nome=" + nome + ", cognome=" + cognome + ", dataN=" + dataN + ", email="
				+ email + ", password=" + password + ", cellulare=" + cellulare + "]";
	}
	//METODI
	public String getCodCl() {
		return codCl;
	}
	public void setCodCl(String a) {
		this.codCl = a;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getDataN() {
		return dataN;
	}
	public void setDataN(String dataN) {
		this.dataN = dataN;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCellulare() {
		return cellulare;
	}
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	
	
	
}
