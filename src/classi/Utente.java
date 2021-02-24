package classi;

public class Utente { //creata per permettere la modifica delle informazioni di manager, cliente e rider facilmente
	private String nome;
	private String cognome;
	private String dataN;
	private String email;
	private String password;
	private String cellulare;
	private String mezzo; //solo per il rider
	
	
	public String getMezzo() {
		return mezzo;
	}
	public void setMezzo(String mezzo) {
		this.mezzo = mezzo;
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
