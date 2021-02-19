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
		for(i=0;i<acquisto.size();i++) {
			System.out.println("codcl = "+acquisto.get(i).getCodC()+" codP = "+acquisto.get(i).getCodP()+" quantità = "+acquisto.get(i).getQuantita());
		}
		System.out.println("");
	}
	
	public void ordina() {
		//fetch prodotti
		acquisto = new ArrayList<Acquisto>();
		prodottoDAO = new ProdottoDAO();
		try {
			prodotti = prodottoDAO.fetchProdotto();
			winOrdine = new WinOrdine(this,prodotti);
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
		winConferma.show();
	}
	
	public void indietroC() {
		winConferma.hide();
		winOrdine.show();
	}
	
	public void confermaC() {
		winConferma.dispose();
		winOrdine.dispose();
		//codice per l'inserimento dell'ordine nel database tramite DAO
		winCliente.show();
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
		for(i=0;i<acquisto.size();i++) {
			System.out.println("codcl = "+acquisto.get(i).getCodC()+" codP = "+acquisto.get(i).getCodP()+" quantità = "+acquisto.get(i).getQuantita());
		}
		System.out.println("");
	}
}
