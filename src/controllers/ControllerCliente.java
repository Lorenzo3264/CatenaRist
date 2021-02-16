package controllers;
import java.sql.SQLException;
import java.util.ArrayList;

import classi.*;
import classiDAO.*;
import finestre.*;


public class ControllerCliente extends PadreController{
	
	private Cliente cliente;
	private Acquisto acquisto;
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
		//effettuare fetch di cliente a partire dalle info di account
		winCliente = new WinCliente(this);
		winCliente.show();
	}
	

	
	public void ordina() {
		//fetch prodotti
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
}
