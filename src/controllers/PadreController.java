package controllers;

import classi.Utente;

public class PadreController { //permette una generalizzazione dei controller per utilizzare facilmente la finestra winInfoUpdate
	private Utente utente;
	
	public Utente getUtente() {
		return utente;
	}

	public void infoBack() {}
	public void cambiaInfo(Utente u) {}
}
