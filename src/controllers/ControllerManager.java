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
	private Acquisto acquisto;
	private AcquistoDAO acquistoDAO;
	private ArrayList<Prodotto> prodotti;
	private ProdottoDAO prodottoDAO;
	private Dipendente dipendente;
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
			winInfoUpdate.hide();
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
		winManager.hide();
		winInfoUpdate.setVisible(true);
	}

	public void visualizzaOrdini() {
		consegnaDAO = new ConsegnaDAO();
		prodottoDAO = new ProdottoDAO();
		try {
			consegne = consegnaDAO.fetchConsegne(manager.getCodA());
			prodotti = prodottoDAO.fetchProdotto(manager.getCodA());
			//fare fetch per la classe acquisto (deve diventare ArrayList)
			winIncarichi = new WinIncarichi(this, consegne, prodotti);
			winManager.setVisible(false);
			winIncarichi.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    
}
