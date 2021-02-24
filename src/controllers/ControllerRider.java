package controllers;
import java.sql.SQLException;
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
	private Collaborazione collaborazione;
	private CollaborazioneDAO collaborazioneDAO;
	private Attivita attivita;
	private AttivitaDAO attivitaDAO;
	private Consegna consegna;
	private ConsegnaDAO consegnaDAO;
	private Controller controller;
	
    public ControllerRider(Controller controller) {
    	this.controller=controller;
    	winRider = new WinRider(this); 
    	try {
    		rider = riderDAO.fetchRider(controller.getAccount().getEmail());
    		super.setUtente(rider);
    	}catch (SQLException e) {
    		e.printStackTrace();
    	};
    	winRider.show();
    };
	
	public void consegna() {
		winRider.hide();
		winConsegne = new WinConsegne(this);
		winConsegne.show();
	}

	public void ordini() {
		winRider.hide();
		winRiderOrdini = new WinRiderOrdini(this);
		winRiderOrdini.show();
	}

	public void info() {
		winRider.hide();
		winInfoUpdate = new WinInfoUpdate(this);
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
	
//	@Override
//	public void cambiaInfo(Utente utente) { 
//		riderDAO = new RiderDAO();
//		Rider riderEdit = new Rider(utente);
//		try {
//			clienteDAO.updateCliente(clienteEdit, cliente.getCodCl());
//			JOptionPane.showMessageDialog(winInfoUpdate, "Modifica effettuata con successo", "Messaggio",
//					JOptionPane.INFORMATION_MESSAGE);
//			winInfoUpdate.hide();
//			winCliente.show();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(winInfoUpdate, "Ci sono errori nei valori inseriti", "Errore di input",
//					JOptionPane.ERROR_MESSAGE);
//		}
//	}
	
	
	
}
