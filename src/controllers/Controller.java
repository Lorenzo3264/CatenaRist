package controllers;
import finestre.*;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import classi.*;
import classiDAO.*;

public class Controller {

	private ControllerCliente controllerCliente;
	private ControllerRider controllerRider;
	private ControllerManager controllerManager;
	private Account account;
	private AccountDAO accountDAO;
	private ClienteDAO clienteDAO; //necessario per il signin
	private WinLogin winLogin;
	private WinSignin winSignin;
	
	
	
	public Account getAccount() {
		return account;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			
		}
		
		Controller c = new Controller();
	}
	
	public Controller() {
		//corpo procedurale del progetto con altri controller e finestre
		winLogin = new WinLogin(this);
		winLogin.show();
	}
	
	public void login(String email, String password) {
		//codice per confrontare i dati in account
		//bisogna fare richiesta al database di trovare la riga giusta di account, sollevare un eccezione in caso non ci fosse
		//passare alla schermata giusta in base al tipo di permessi, mostrare un messaggio in caso di eccezione
		try {
			accountDAO = new AccountDAO();
			account = accountDAO.fetchAccount(email, password);
			if(account.getPermessi().equals("codCl")) {
				winLogin.hide();
				controllerCliente = new ControllerCliente(account, this);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(winLogin,
				    "Inserisci Email e Password corretti",
				    "Attenzione",
				    JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	public void signin() {
		winLogin.hide();
		winSignin = new WinSignin(this);
		winSignin.show();
	}

	public void signin_back() {
		// TODO Auto-generated method stub
		winSignin.hide();
		winLogin.show();
	}

	public void signin_ok(Cliente cl) {
		// TODO Auto-generated method stub
		clienteDAO = new ClienteDAO();
		winSignin.hide();
		//metodi di inserimento della riga al database
		clienteDAO.insertCliente(cl);
		winLogin.show();
	}
	
	public void logout() {
		//supponendo che sono state già chiuse tutte le finestre precedenti
		winLogin.show();
	}
}
