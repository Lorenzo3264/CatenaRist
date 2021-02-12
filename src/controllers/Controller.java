package controllers;
import finestre.*;
import classi.Account;
import classiDAO.AccountDAO;

public class Controller {

	private ControllerCliente controllerCliente = new ControllerCliente();
	private ControllerRider controllerRider = new ControllerRider();
	private ControllerManager controllerManager = new ControllerManager();
	private Account account = new Account();
	private AccountDAO accountDAO = new AccountDAO();
	private WinLogin winLogin = new WinLogin();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller c = new Controller();
	}
	
	public Controller() {
		//corpo procedurale del progetto con altri controller e finestre
		//mostra finestra login
	}
	
	public void login() {
		//codice per confrontare i dati in account
	}
}
