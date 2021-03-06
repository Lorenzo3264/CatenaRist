package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JOptionPane;

import classi.*;
import classiDAO.*;
import finestre.*;

public class ControllerCliente extends PadreController {

	private Cliente cliente;
	private ArrayList<Acquisto> acquisto;
	private ArrayList<Prodotto> prodotti;
	private Consegna consegna;
	private ClienteDAO clienteDAO;
	private AcquistoDAO acquistoDAO;
	private ProdottoDAO prodottoDAO;
	private ConsegnaDAO consegnaDAO;
	private WinConferma winConferma;
	private WinOrdine winOrdine;
	private WinCliente winCliente;
	private WinInfoUpdate winInfoUpdate;
	private Controller controller;
	private ArrayList<Attivita> attivita;
	private AttivitaDAO attivitaDAO;

	public ArrayList<Prodotto> getProdotti() {
		return prodotti;
	}

	public ControllerCliente(Controller controller) {
		this.controller = controller;
		clienteDAO = new ClienteDAO();
		try {
			cliente = clienteDAO.fetchCliente(controller.getAccount().getEmail());
			super.setUtente(cliente);
		} catch (SQLException e) {
			
		}

		winCliente = new WinCliente(this);
		winCliente.setVisible(true);
	}

	public void ordina() {
		// fetch prodotti
		acquisto = new ArrayList<Acquisto>();
		prodottoDAO = new ProdottoDAO();
		attivitaDAO = new AttivitaDAO();
		try {
			prodotti = prodottoDAO.fetchProdotto();
			winOrdine = new WinOrdine(this, prodotti);
			attivita = attivitaDAO.fetchAttivita();
			winCliente.setVisible(false);
			winOrdine.setVisible(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(winCliente, "C'è stato un errore nel database", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void indietroO() {
		winOrdine.dispose();
		winCliente.setVisible(true);
	}

	public void confermaO() {

		winOrdine.setVisible(false);
		winConferma = new WinConferma(this);
		winConferma.aggiornaprezzo();
		winConferma.setVisible(true);

	}

	public void indietroC() {
		winConferma.setVisible(false);
		winOrdine.setVisible(true);
	}

	public void confermaC(Consegna cons) {
		try {
			consegna = new Consegna();
			consegna = cons;
			consegna.setCodCl(cliente.getCodCl());
			consegnaDAO = new ConsegnaDAO();
			consegnaDAO.insertConsegna(consegna);
			int codC = consegnaDAO.fetchCurrentCodC();
			acquistoDAO = new AcquistoDAO();
			acquistoDAO.insertAcquisto(acquisto, codC);
			winConferma.dispose();
			winOrdine.dispose();
			winCliente.setVisible(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(winConferma, "Ci sono errori nei valori inseriti\n"+e.getLocalizedMessage(), "Errore di input",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void info() {
		winInfoUpdate = new WinInfoUpdate(this,false);
		winCliente.setVisible(false);
		winInfoUpdate.setVisible(true);
	}

	public void logout() {
		winCliente.dispose();
		if (!Objects.isNull(winInfoUpdate)) {
			winInfoUpdate.dispose();
		}
		if (!Objects.isNull(winOrdine)) {
			winOrdine.dispose();
		}
		if (!Objects.isNull(winConferma)) {
			winConferma.dispose();
		}
		controller.logout();
	}

	public void aggiungi(Acquisto acq) { // acq ha solo codP
		int i = 0;
		boolean presente = false;

		while (i < acquisto.size() && !presente) {
			if (acquisto.get(i).getCodP() == acq.getCodP()) {
				presente = true;
				acquisto.get(i).setQuantita(acquisto.get(i).getQuantita() + 1);
			}
			i++;
		}
		if (!presente) {
			acq.setQuantita(1);
			acquisto.add(acq);
		}
	}

	public void rimuovi(Acquisto acq) {
		int i = 0;
		while (i < acquisto.size()) {
			if (acquisto.get(i).getCodP() == acq.getCodP()) {
				if (acquisto.get(i).getQuantita() == 1) {
					acquisto.remove(i);
				} else {
					acquisto.get(i).setQuantita(acquisto.get(i).getQuantita() - 1);
					// il caso in cui la quantità è uguale a 0 è già gestito all'interno di
					// winOrdine
				}
				i = acquisto.size();
			}
			i++;
		}
	}

	public void resettaSpesa() {
		acquisto.clear();
	}

	public boolean isSpesaVuota() {
		return acquisto.isEmpty();
	}

	public ArrayList<Attivita> getAttivita() {
		return attivita;
	}

	public float getPrezzoTot() {
		float prezzoTot = 0;
		boolean trovato;
		int j;
		for (Acquisto i : acquisto) {
			j = 0;
			trovato = false;
			while (j < prodotti.size() && !trovato) {
				if (prodotti.get(j).getCodP() == i.getCodP()) {
					prezzoTot = prezzoTot + (prodotti.get(j).getPrezzo() * i.getQuantita());
					trovato = true;
				}
				j++;
			}
		}
		return prezzoTot;
	}
	
	@Override
	public void infoBack() {
		winInfoUpdate.setVisible(false);
		winCliente.setVisible(true);
	}
	
	@Override
	public void cambiaInfo(Utente utente) {
		clienteDAO = new ClienteDAO();
		Cliente clienteEdit = new Cliente(utente);
		clienteDAO = new ClienteDAO();
		try {
			clienteDAO.updateCliente(clienteEdit, cliente.getCodCl());
			cliente = clienteDAO.fetchCliente(clienteEdit.getEmail());
			super.setUtente(cliente);
			JOptionPane.showMessageDialog(winInfoUpdate, "Modifica effettuata con successo", "Messaggio",
					JOptionPane.INFORMATION_MESSAGE);
			winInfoUpdate.setVisible(false);
			winCliente.setVisible(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(winInfoUpdate, "Ci sono errori nei valori inseriti", "Errore di input",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
