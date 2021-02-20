package controllers;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import classi.*;
import classiDAO.*;
import finestre.*;


public class ControllerCliente extends PadreController{
	
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


	public ControllerCliente(Account a, Controller c) {
		controller = c;
		clienteDAO = new ClienteDAO();
		try {
			cliente = clienteDAO.fetchCliente(c.getAccount().getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		winCliente = new WinCliente(this);
		winCliente.show();
	}
	

	
	public void ordina() {
		//fetch prodotti
		acquisto = new ArrayList<Acquisto>();
		prodottoDAO = new ProdottoDAO();
		attivitaDAO = new AttivitaDAO();
		try {
			prodotti = prodottoDAO.fetchProdotto();
			winOrdine = new WinOrdine(this,prodotti);
			attivita = attivitaDAO.fetchAttivita();
			winConferma = new WinConferma(this);
			winCliente.hide();
			winOrdine.show();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public void indietroO() {
		winOrdine.dispose();
		winCliente.show();
	}
	
	public void confermaO() {
		
		winOrdine.hide();
		winConferma.aggiornaprezzo();
		winConferma.show();
		
	}
	
	public void indietroC() {
		winConferma.hide();
		winOrdine.show();
	}
	
	public void confermaC(Consegna cons) {
		try {
			consegna = new Consegna();
			
			
			winConferma.dispose();
			winOrdine.dispose();
			winCliente.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void info() {
		winInfoUpdate = new WinInfoUpdate(this);
		winCliente.hide();
		winInfoUpdate.show();
	}
	
	public void logout() {
		winCliente.dispose();
		winInfoUpdate.dispose();
		winOrdine.dispose();
		winConferma.dispose();
		controller.logout();
	}

	public void cambiaInfo(Cliente cl) {
		//effettuare update cliente tramite DAO
		winInfoUpdate.hide();
		cliente = cl;
		winCliente.show();
	}


	public void aggiungi(Acquisto acq) { //acq ha solo codP
		int i = 0;
		boolean presente = false;
		
		while(i < acquisto.size() && !presente) {
			if(acquisto.get(i).getCodP() == acq.getCodP()) {
				presente = true;
				acquisto.get(i).setQuantita(acquisto.get(i).getQuantita() + 1);
			}
			i++;
		}
		if(!presente) {
			acq.setQuantita(1);
			acquisto.add(acq);
		}
	}
	
	public void rimuovi(Acquisto acq) {
		int i = 0;
		while(i<acquisto.size()) {
			if(acquisto.get(i).getCodP() == acq.getCodP()) {
				if(acquisto.get(i).getQuantita() == 1) {
					acquisto.remove(i);
				}else {
					acquisto.get(i).setQuantita(acquisto.get(i).getQuantita() - 1);
					//il caso in cui la quantità è uguale a 0 è già gestito all'interno di winOrdine
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
		// TODO Auto-generated method stub
		return acquisto.isEmpty();
	}


	public ArrayList<Attivita> getAttivita() {
		// TODO Auto-generated method stub
		return attivita;
	}


	public float getPrezzoTot() {
		// TODO Auto-generated method stub
		float prezzoTot = 0;
		boolean trovato;
		int j;
		for(Acquisto i: acquisto) {
			j = 0;
			trovato = false;
			while(j<prodotti.size() && !trovato) {
				if(prodotti.get(j).getCodP() == i.getCodP()) {
					prezzoTot = prezzoTot + (prodotti.get(j).getPrezzo()*i.getQuantita());
					trovato = true;
				}
				j++;
			}
		}
		return prezzoTot;
	}
}
