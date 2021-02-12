package controllers;
import finestre.*;
import classi.Account;
import classiDAO.AccountDAO;

public class Controller {

	private ControllerCliente controllerCliente;
	private ControllerRider controllerRider;
	private ControllerManager controllerManager;
	private Account account;
	private AccountDAO accountDAO;
	private WinLogin winLogin;
	private WinSignin winSignin;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller c = new Controller();
	}
	
	public Controller() {
		//corpo procedurale del progetto con altri controller e finestre
		winLogin = new WinLogin(this);
		winLogin.show();
	}
	
	public void login() {
		//codice per confrontare i dati in account
		//bisogna fare richiesta al database di trovare la riga giusta di account, sollevare un eccezione in caso non ci fosse
		//passare alla schermata giusta in base al tipo di permessi, mostrare un messaggio in caso di eccezione
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

	public void signin_ok() {
		// TODO Auto-generated method stub
		//metodi di inserimento della riga al database
	}
}
