package controllers;
import finestre.*;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import classi.*;
import classiDAO.*;

public class Controller {

	private Account account;
	private AccountDAO accountDAO;
	private ClienteDAO clienteDAO; //necessario per il signin
	private WinLogin winLogin;
	private WinSignin winSignin;

	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
			new Controller();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Controller() {
		//corpo procedurale del progetto con altri controller e finestre
		winLogin = new WinLogin(this);
		winLogin.setVisible(true);;
	}
	
	public void login(String email, String password) {
		//codice per confrontare i dati in account
		//bisogna fare richiesta al database di trovare la riga giusta di account, sollevare un eccezione in caso non ci fosse
		try {
			accountDAO = new AccountDAO();
			account = accountDAO.fetchAccount(email, password);
			if(account.getPermessi().equals("codCl")) {
				winLogin.setVisible(false);
				new ControllerCliente(this);
			} 
			else if (account.getPermessi().equals("codR")) {
				winLogin.setVisible(false);
				new ControllerRider(this);
			}
			else if (account.getPermessi().equals("codM")) {
				winLogin.setVisible(false);
				new ControllerManager(this);
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
		winLogin.setVisible(false);
		winSignin = new WinSignin(this);
		winSignin.setVisible(true);
	}

	public void signin_back() {
		winSignin.setVisible(false);
		winLogin.setVisible(true);
	}

	public void signin_ok(Cliente cl) {
		System.out.println(cl.toString());
		clienteDAO = new ClienteDAO();
		try {
			clienteDAO.insertCliente(cl);
			JOptionPane.showMessageDialog(winSignin,
				    "Registrazione effettuata con successo",
				    "Messaggio",
				    JOptionPane.INFORMATION_MESSAGE);
			winSignin.setVisible(false);
			winLogin.setVisible(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(winSignin,
				    "Inserisci correttamente i dati",
				    "Attenzione",
				    JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void logout() {
		//supponendo che sono state già chiuse tutte le finestre precedenti
		winLogin.dispose();
		winLogin = new WinLogin(this);
		winLogin.setVisible(true);
	}
	
	public Account getAccount() {
		return account;
	}
}
