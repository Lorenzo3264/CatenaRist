package finestre;
import classi.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import controllers.ControllerCliente;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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
		setBounds(100, 100, 912, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		txt_prezzomin = new JTextField();
		txt_prezzomin.setBounds(39, 33, 86, 20);
		contentPane.add(txt_prezzomin);
		txt_prezzomin.setColumns(10);
		
		txt_prezzomax = new JTextField();
		txt_prezzomax.setBounds(157, 33, 86, 20);
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
		btn_prezzo.setBounds(273, 32, 136, 23);
		contentPane.add(btn_prezzo);
		
		JButton btn_conferma = new JButton("Avanti");
		btn_conferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.confermaO(); //devo passare lista acquisti
			}
		});
		btn_conferma.setBounds(797, 390, 89, 23);
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
		String[] colonne = {"codP","nome", "descrizione","prezzo","tipo","","quantità",""};
		Object[][] righe;
		int i = 0;
		righe = new Object[prodotti.size()][8];
		
		//controllare da qui la prossima volta
		for(i=0;i<prodotti.size();i++) {

			righe[i][0] = prodotti.get(i).getCodP();
			righe[i][1] = prodotti.get(i).getNome();
			righe[i][2] = prodotti.get(i).getDescrizione();
			righe[i][3] = "€"+prodotti.get(i).getPrezzo();
			righe[i][4] = prodotti.get(i).getTipo();
			righe[i][5] = "+";
			righe[i][6] = 0;
			righe[i][7] = "-";

		}
	
		DefaultTableModel modello = new DefaultTableModel(righe, colonne) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				switch(column) {
				case 5:
					return true;
				case 7:
					return true;
				default:
					return false;
				}
			}
		};
		modello.setColumnIdentifiers(colonne);
		
		Action aggiungi = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					 
					 JTable table = (JTable)e.getSource();
				     int modelRow = Integer.valueOf( e.getActionCommand() );
				     table.setValueAt((int)((DefaultTableModel)table.getModel()).getValueAt(modelRow, 6) + 1, modelRow, 5);
				     Acquisto acquisto = new Acquisto();
				     acquisto.setCodP((int)((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));
				     controller.aggiungi(acquisto);
				     
				}
		};
		
		Action rimuovi = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				
				 JTable table = (JTable)e.getSource();
			     int modelRow = Integer.valueOf( e.getActionCommand() );
			     if ((int) ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 6) > 0) {
					table.setValueAt((int) ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 6) - 1, modelRow,5);
					Acquisto acquisto = new Acquisto();
				    acquisto.setCodP((int)((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));
				    controller.rimuovi(acquisto);
				 }
				
			     
			}
	};
		
		tbl_prodotti = new JTable(modello);
		
		ButtonColumn btn_aggiungi = new ButtonColumn(tbl_prodotti, aggiungi, 5);
		ButtonColumn btn_rimuovi = new ButtonColumn(tbl_prodotti, rimuovi, 7);
		tbl_prodotti.setBounds(10, 64, 876, 304);
		tbl_prodotti.getColumnModel().getColumn(1).setPreferredWidth(90);
		tbl_prodotti.getColumnModel().getColumn(2).setPreferredWidth(500);
		tbl_prodotti.getColumnModel().getColumn(3).setPreferredWidth(15);
		tbl_prodotti.getColumnModel().getColumn(4).setPreferredWidth(50);
		tbl_prodotti.getColumnModel().getColumn(5).setPreferredWidth(30);
		tbl_prodotti.getColumnModel().getColumn(7).setPreferredWidth(30);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tbl_prodotti.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
		TableColumnModel tcm = tbl_prodotti.getColumnModel();
		tcm.removeColumn( tcm.getColumn(0) );
		contentPane.add(tbl_prodotti);
	//fine prova per la tabella	
	}
}
