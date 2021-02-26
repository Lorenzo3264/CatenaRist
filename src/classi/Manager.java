package classi;

public class Manager extends Utente {

	//ATTRIBUTI
	private int codM;
	private int codA;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String cellulare;
	private String dataN;
	
	public Manager(Utente utente) {
		nome = utente.getNome();
		cognome = utente.getCognome();
		email = utente.getEmail();
		password = utente.getPassword();
		cellulare = utente.getCellulare();
		dataN = utente.getDataN();
	}
	public Manager() {}
	
	//METODI
	public int getCodM() {
		return codM;
	}
	public void setCodM(int codM) {
		this.codM = codM;
	}
	public int getCodA() {
		return codA;
	}
	public void setCodA(int codA) {
		this.codA = codA;
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
	public String getDataN() {
		return dataN;
	}
	public void setDataN(String dataN) {
		this.dataN = dataN;
	}
	
	
	
}
