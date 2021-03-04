package controllers;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JOptionPane;

import classi.*;
import classiDAO.*;
import finestre.*;

public class ControllerRider extends PadreController {
	private WinRider winRider;
	private WinConsegne winConsegne;
	private WinRiderOrdini winRiderOrdini;
	private WinInfoUpdate winInfoUpdate;
	private Rider rider;
	private RiderDAO riderDAO;
	
	//mantenuti per conservare il riferimento con il database
	@SuppressWarnings("unused")
	private ArrayList<Collaborazione> collaborazioni;
	@SuppressWarnings("unused")
	private CollaborazioneDAO collaborazioneDAO;
	@SuppressWarnings("unused")
	private ArrayList<Attivita> attivita;
	
	private AttivitaDAO attivitaDAO;
	private ArrayList<Consegna> consegne;
	private ConsegnaDAO consegnaDAO;
	private Controller controller;
	
    public ControllerRider(Controller controller) {
    	this.controller=controller;
    	winRider = new WinRider(this); 
    	try {
    		riderDAO = new RiderDAO();
    		rider = riderDAO.fetchRider(controller.getAccount().getEmail());
    		super.setUtente(rider);
    		attivitaDAO = new AttivitaDAO();
    		attivita = attivitaDAO.fetchAttivita(rider);
    	}catch (SQLException e) {
    		e.printStackTrace();
    	};
    	winRider.setVisible(true);
    };
	
	public void consegna() {
		try {
			consegnaDAO = new ConsegnaDAO();
			consegne = consegnaDAO.fetchConsegna(rider);
			winRider.setVisible(false);
			winConsegne = new WinConsegne(this, consegne);
			winConsegne.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ordini() {
		try {
			consegnaDAO = new ConsegnaDAO();
			consegne = consegnaDAO.fetchOrdini(rider);
			winRider.setVisible(false);
			winRiderOrdini = new WinRiderOrdini(this, consegne);
			winRiderOrdini.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void info() {
		winRider.setVisible(false);
		winInfoUpdate = new WinInfoUpdate(this,true);
		winInfoUpdate.setVisible(true);
	}

	public void logout() {
		winRider.dispose();
		if (!Objects.isNull(winInfoUpdate)) {
			winInfoUpdate.dispose();
		}
		if (!Objects.isNull(winRiderOrdini)) {
			winRiderOrdini.dispose();
		}
		if (!Objects.isNull(winConsegne)) {
			winConsegne.dispose();
		}
		controller.logout();
	}
	
	@Override
	public void infoBack() {
		winInfoUpdate.setVisible(false);
		winRider.setVisible(true);
	}
	
	@Override
	public void cambiaInfo(Utente utente) { 
		riderDAO = new RiderDAO();
		Rider riderEdit = new Rider(utente);
		try {
			riderDAO.updateRider(riderEdit, rider.getCodR());
			JOptionPane.showMessageDialog(winInfoUpdate, "Modifica effettuata con successo", "Messaggio",
					JOptionPane.INFORMATION_MESSAGE);
			winInfoUpdate.setVisible(false);
			winRider.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(winInfoUpdate, "Ci sono errori nei valori inseriti", "Errore di input",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void completaConsegna(int codC) {
		consegnaDAO = new ConsegnaDAO();
		try {
			consegnaDAO.completaConsegna(codC);
		}catch(SQLException e){
			JOptionPane.showMessageDialog(winConsegne, "C'è stato un errore nel completamento.\n"+e.getLocalizedMessage(), "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void consegnaBack() {
		winConsegne.setVisible(false);
		winRider.setVisible(true);
	}

	public void prendiOrdine(int codC) {
		consegnaDAO = new ConsegnaDAO();
		try {
			consegnaDAO.prendiOrdine(codC, rider.getCodR());
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(winRiderOrdini, "C'è stato un errore nel tentativo di prendere l'ordine.\n"+e.getLocalizedMessage(), "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void ordineBack() {
		winRiderOrdini.setVisible(false);
		winRider.setVisible(true);
	}
	
	
	
}
