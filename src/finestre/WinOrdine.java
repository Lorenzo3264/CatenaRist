package finestre;
import classi.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.ControllerCliente;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class WinOrdine extends JFrame {

	private JPanel contentPane;
	private ControllerCliente controller;
	private JTable tbl_prodotti;
	private JTextField txt_prezzomin;
	private JTextField txt_prezzomax;
	

	/**
	 * Create the frame.
	 * @param controller 
	 */
	public WinOrdine(ControllerCliente con) {
		setResizable(false);
		controller = con;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
	//prova per la tabella
//		String[] colonne = {"nome", "descrizione","prezzo","tipo","quantità"};
//		Object[][] righe;
//		int numProdotti = 0;
//		Iterator<Prodotto> prodotto = controller.getProdotti().iterator();
//		int i = 0;
//		while(prodotto.hasNext()) {
//			numProdotti = numProdotti + 1;
//		}
//		righe = new Object[5][numProdotti];
//		//deve essere inizializzato di nuovo l'iteratore (?)
//		while(prodotto.hasNext()) {
//			righe[1][i] = prodotto.next().getNome();
//			righe[2][i] = prodotto.next().getDescrizione();
//			righe[3][i] = prodotto.next().getPrezzo();
//			righe[4][i] = prodotto.next().getTipo();
//			righe[5][i] = new String("0");
//			i++;
//		}
	
//		tbl_prodotti = new JTable(righe, colonne);
//		tbl_prodotti.setBounds(10, 64, 600, 275);
//		contentPane.add(tbl_prodotti);
	//fine prova per la tabella	
		
		
		txt_prezzomin = new JTextField();
		txt_prezzomin.setBounds(39, 33, 86, 20);
		contentPane.add(txt_prezzomin);
		txt_prezzomin.setColumns(10);
		
		txt_prezzomax = new JTextField();
		txt_prezzomax.setBounds(237, 33, 86, 20);
		contentPane.add(txt_prezzomax);
		txt_prezzomax.setColumns(10);
		
		JButton btn_prezzo = new JButton("mostra prezzi");
		btn_prezzo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer prezzomin = Integer.getInteger(txt_prezzomin.getText());
					Integer prezzomax = Integer.getInteger(txt_prezzomax.getText());
					//cambia prezzi nella tabella
				}catch(Exception ex){
					
				}
				
			}
		});
		btn_prezzo.setBounds(430, 32, 136, 23);
		contentPane.add(btn_prezzo);
		
		JButton btn_conferma = new JButton("Avanti");
		btn_conferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.confermaO(); //devo passare lista acquisti
			}
		});
		btn_conferma.setBounds(521, 389, 89, 23);
		contentPane.add(btn_conferma);
		
		JButton btn_indietro = new JButton("Indietro");
		btn_indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.indietroO();
			}
		});
		btn_indietro.setBounds(10, 389, 89, 23);
		contentPane.add(btn_indietro);
	}
}
