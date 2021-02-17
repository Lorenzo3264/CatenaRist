package finestre;
import classi.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import controllers.ControllerCliente;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

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
	public WinOrdine(ControllerCliente con, ArrayList<Prodotto> prodotti) {
		setResizable(false);
		controller = con;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
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
		btn_conferma.setBounds(521, 390, 89, 23);
		contentPane.add(btn_conferma);
		
		JButton btn_indietro = new JButton("Indietro");
		btn_indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.indietroO();
			}
		});
		btn_indietro.setBounds(10, 390, 89, 23);
		contentPane.add(btn_indietro);
		
	//prova per la tabella
		String[] colonne = {"nome", "descrizione","prezzo","tipo","quantità"};
		Object[][] righe;
		int numProdotti = 0;
		Iterator<Prodotto> prodotto = prodotti.iterator();
		int i = 0;
		for(i=0;i<prodotti.size();i++) {
			numProdotti = numProdotti + 1;
			System.out.println(prodotti.get(i).getNome());
		}
		righe = new Object[numProdotti][5];
		//deve essere inizializzato di nuovo l'iteratore (?)
		prodotto = prodotti.iterator();
		
		//controllare da qui la prossima volta
		for(i=0;i<numProdotti;i++) {
//			 p = prodotto.next();
			righe[i][0] = prodotti.get(i).getNome();
			righe[i][1] = prodotti.get(i).getDescrizione();
			righe[i][2] = prodotti.get(i).getPrezzo();
			righe[i][3] = prodotti.get(i).getTipo();
			righe[i][4] = "0";
//			i++;
		}
	
		DefaultTableModel modello = new DefaultTableModel(righe, colonne) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				if(column == 4) {
					return true;
				}else {
					return false;
				}
			}
		};
		modello.setColumnIdentifiers(colonne);
		
		tbl_prodotti = new JTable(righe, colonne);
		tbl_prodotti.setModel(modello);
		tbl_prodotti.setEnabled(false);
		tbl_prodotti.setBounds(10, 64, 600, 304);
		contentPane.add(tbl_prodotti);
	//fine prova per la tabella	
	}
}
