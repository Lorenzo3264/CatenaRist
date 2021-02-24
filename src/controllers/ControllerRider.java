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
	private ArrayList<Collaborazione> collaborazioni;
	private CollaborazioneDAO collaborazioneDAO;
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
    	winRider.show();
    };
	
	public void consegna() {
		try {
			consegnaDAO = new ConsegnaDAO();
			consegne = consegnaDAO.fetchConsegna(rider);
			winRider.hide();
			winConsegne = new WinConsegne(this, consegne);
			winConsegne.show();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ordini() {
		try {
			consegnaDAO = new ConsegnaDAO();
			consegne = consegnaDAO.fetchOrdini(rider);
			winRider.hide();
			winRiderOrdini = new WinRiderOrdini(this, consegne);
			winRiderOrdini.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void info() {
		winRider.hide();
		winInfoUpdate = new WinInfoUpdate(this,true);
		winInfoUpdate.show();
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
		winInfoUpdate.hide();
		winRider.show();
	}
	
	@Override
	public void cambiaInfo(Utente utente) { 
		riderDAO = new RiderDAO();
		Rider riderEdit = new Rider(utente);
		try {
			riderDAO.updateRider(riderEdit, rider.getCodR());
			JOptionPane.showMessageDialog(winInfoUpdate, "Modifica effettuata con successo", "Messaggio",
					JOptionPane.INFORMATION_MESSAGE);
			winInfoUpdate.hide();
			winRider.show();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(winInfoUpdate, "Ci sono errori nei valori inseriti", "Errore di input",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
}
