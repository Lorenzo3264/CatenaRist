package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JOptionPane;

import classi.*;
import classiDAO.*;
import finestre.*;

public class ControllerManager extends PadreController {
	
	private Controller controller;
	private ArrayList<Consegna> consegne;
	private ConsegnaDAO consegnaDAO;
	private Attivita attivita;
	private AttivitaDAO attivitaDAO;
	private ArrayList<Acquisto> acquisti;
	private AcquistoDAO acquistoDAO;
	private ArrayList<Prodotto> prodotti;
	private ProdottoDAO prodottoDAO;
	private ArrayList<Dipendente> dipendenti;
	private DipendenteDAO dipendenteDAO;
	private Manager manager;
	private ManagerDAO managerDAO;
	private WinDipendenti winDipendenti;
	private WinIncarichi winIncarichi;
	private WinInfoUpdate winInfoUpdate;  
    private WinManager winManager;
    private WinDipInsert winDipInsert;
    private WinDipUpdate winDipUpdate;
    
    public ControllerManager(Controller controller2) {
    	controller = controller2;
    	try {
    		managerDAO = new ManagerDAO();
    		manager = managerDAO.fetchManager(controller.getAccount().getEmail());
    		super.setUtente(manager);
    	}catch (SQLException e) {
    		e.printStackTrace();
    	};
    	winManager = new WinManager(this);
    	winManager.setVisible(true);
    }

    @Override
	public void cambiaInfo(Utente utente) { 
		managerDAO = new ManagerDAO();
		Manager managerEdit = new Manager(utente);
		try {
			managerDAO.updateManager(managerEdit, manager.getCodM());
			JOptionPane.showMessageDialog(winInfoUpdate, "Modifica effettuata con successo", "Messaggio",
					JOptionPane.INFORMATION_MESSAGE);
			winInfoUpdate.setVisible(false);
			winManager.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(winInfoUpdate, "Ci sono errori nei valori inseriti", "Errore di input",
					JOptionPane.ERROR_MESSAGE);
		}
	}
    
	public void logout() {
		winManager.dispose();
		if (!Objects.isNull(winInfoUpdate)) {
			winInfoUpdate.dispose();
		}
		if (!Objects.isNull(winDipendenti)) {
			winDipendenti.dispose();
		}
		if (!Objects.isNull(winIncarichi)) {
			winIncarichi.dispose();
		}
		if (!Objects.isNull(winDipInsert)) {
			winDipInsert.dispose();
		}
		if (!Objects.isNull(winDipUpdate)) {
			winDipUpdate.dispose();
		}
		controller.logout();
	}

	public void info() {
		winInfoUpdate = new WinInfoUpdate(this, false);
		winManager.setVisible(false);
		winInfoUpdate.setVisible(true);
	}

	public void visualizzaOrdini() {
		consegnaDAO = new ConsegnaDAO();
		prodottoDAO = new ProdottoDAO();
		acquistoDAO = new AcquistoDAO();
		try {
			consegne = consegnaDAO.fetchConsegne(manager.getCodA());
			prodotti = prodottoDAO.fetchProdotto();
			acquisti = acquistoDAO.fetchAcquisto(manager.getCodA());
			winIncarichi = new WinIncarichi(this, consegne, prodotti, acquisti);
			winManager.setVisible(false);
			winIncarichi.setVisible(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(winManager, "C'è stato un errore nel database", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void incarichiBack() {
		winIncarichi.setVisible(false);
		winManager.setVisible(true);
	}

	public void visualizzaDipendenti() {
		dipendenteDAO = new DipendenteDAO();
		try {
			dipendenti = dipendenteDAO.fetchDipendete(manager.getCodA());
			winDipendenti = new WinDipendenti(this,dipendenti);
			winManager.setVisible(false);
			winDipendenti.setVisible(true);
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(winManager, "C'è stato un errore nel database", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void licenziaDipendente(int codD) {
		dipendenteDAO = new DipendenteDAO();
		try {
			dipendenteDAO.deleteDipendente(codD);
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(winManager, "C'è stato un errore nel database", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void modificaDipendente(Dipendente dipendente) {
		winDipendenti.setVisible(false);
		winDipUpdate = new WinDipUpdate(this, dipendente);
		winDipUpdate.setVisible(true);
	}
	
	public void infoBack() {
	    winInfoUpdate.setVisible(false);
	    winManager.setVisible(true);
	}

	public void assumiDipendente() {
		winDipendenti.setVisible(false);
		winDipInsert = new WinDipInsert(this);
		winDipInsert.setVisible(true);
	}

	public void dipInsertExit() {
		dipendenteDAO = new DipendenteDAO();
		try {
			dipendenti = dipendenteDAO.fetchDipendete(manager.getCodA());
			winDipendenti = new WinDipendenti(this,dipendenti);
			winDipInsert.setVisible(false);
			winDipendenti.setVisible(true);
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(winManager, "C'è stato un errore nel database", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void assumi(Dipendente dipendente) {
		dipendente.setCodA(manager.getCodA());
		dipendenteDAO = new DipendenteDAO();
		try {
			dipendenteDAO.insertDipendente(dipendente);
			JOptionPane.showMessageDialog(winDipInsert, "Dipendente assunto con successo", "Messaggio",
					JOptionPane.INFORMATION_MESSAGE);
			dipendenti = dipendenteDAO.fetchDipendete(manager.getCodA());
			winDipendenti = new WinDipendenti(this,dipendenti);
			winDipInsert.setVisible(false);
			winDipendenti.setVisible(true);
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(winManager, "C'è stato un errore nel database: "+e.getLocalizedMessage(), "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void modifica(Dipendente dipendente) {
		dipendente.setCodA(manager.getCodA());
		dipendenteDAO = new DipendenteDAO();
		try {
			dipendenteDAO.updateDipendente(dipendente);
			JOptionPane.showMessageDialog(winDipInsert, "Dipendente modificato con successo", "Messaggio",
					JOptionPane.INFORMATION_MESSAGE);
			dipendenti = dipendenteDAO.fetchDipendete(manager.getCodA());
			winDipendenti = new WinDipendenti(this,dipendenti);
			winDipUpdate.setVisible(false);
			winDipendenti.setVisible(true);
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(winManager, "C'è stato un errore nel database: "+e.getLocalizedMessage(), "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
    
}
