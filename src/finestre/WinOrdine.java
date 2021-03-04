package finestre;

import classi.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import controllers.ControllerCliente;
import controllers.ControllerRider;

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

	public WinOrdine(ControllerCliente con, ArrayList<Prodotto> prodotti) {
		setResizable(false);
		controller = con;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 912, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txt_prezzomin = new JTextField();
		txt_prezzomin.setBounds(126, 12, 86, 20);
		contentPane.add(txt_prezzomin);
		txt_prezzomin.setColumns(10);

		txt_prezzomax = new JTextField();
		txt_prezzomax.setBounds(367, 12, 86, 20);
		contentPane.add(txt_prezzomax);
		txt_prezzomax.setColumns(10);

		String[] colonne = { "codP", "nome", "descrizione", "prezzo", "tipo", "", "quantità", "" };
		Object[][] righe;
		int i = 0;
		righe = new Object[prodotti.size()][8];

		for (i = 0; i < prodotti.size(); i++) {

			righe[i][0] = prodotti.get(i).getCodP();
			righe[i][1] = prodotti.get(i).getNome();
			righe[i][2] = prodotti.get(i).getDescrizione();
			righe[i][3] = "€" + prodotti.get(i).getPrezzo();
			righe[i][4] = prodotti.get(i).getTipo();
			righe[i][5] = "-";
			righe[i][6] = 0;
			righe[i][7] = "+";

		}

		DefaultTableModel modello = new DefaultTableModel(righe, colonne) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				switch (column) {
				case 5:
					return true;
				case 7:
					return true;
				default:
					return false;
				}
			}

			@Override
			public String getColumnName(int i) {
				return colonne[i].toString();
			}
		};

		JButton btn_prezzo = new JButton("mostra prezzi");
		btn_prezzo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Float prezzomin = Float.parseFloat(txt_prezzomin.getText());
					Float prezzomax = Float.parseFloat(txt_prezzomax.getText());
					controller.resettaSpesa();
					// cambia prezzi nella tabella
					int i;
					modello.setRowCount(0);

					for (i = 0; i < prodotti.size(); i++) {
						if (prodotti.get(i).getPrezzo() <= prezzomax && prodotti.get(i).getPrezzo() >= prezzomin) {
							modello.addRow(new Object[] { prodotti.get(i).getCodP(), prodotti.get(i).getNome(),
									prodotti.get(i).getDescrizione(), "€" + prodotti.get(i).getPrezzo(),
									prodotti.get(i).getTipo(), "-", 0, "+" });
						}
					}
					tbl_prodotti.revalidate();
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, "Inserisci numeri nei campi", "Errore di input",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btn_prezzo.setBounds(505, 11, 136, 23);
		contentPane.add(btn_prezzo);

		JButton btn_conferma = new JButton("Avanti");
		btn_conferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (controller.isSpesaVuota()) {
					JOptionPane.showMessageDialog(contentPane, "Inserisci prodotti nel carrello", "Errore nell'ordine",
							JOptionPane.ERROR_MESSAGE);
				} else {
					controller.confermaO();
				}
			}
		});
		btn_conferma.setBounds(797, 435, 89, 23);
		contentPane.add(btn_conferma);

		JButton btn_indietro = new JButton("Indietro");
		btn_indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.indietroO();
			}
		});
		btn_indietro.setBounds(10, 435, 89, 23);
		contentPane.add(btn_indietro);

		modello.setColumnIdentifiers(colonne);

		Action aggiungi = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {

				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				table.setValueAt((int) ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 6) + 1, modelRow, 5);
				Acquisto acquisto = new Acquisto();
				acquisto.setCodP((int) ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 0));
				controller.aggiungi(acquisto);

			}
		};

		Action rimuovi = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {

				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				if ((int) ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 6) > 0) {
					table.setValueAt((int) ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 6) - 1, modelRow,
							5);
					Acquisto acquisto = new Acquisto();
					acquisto.setCodP((int) ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 0));
					controller.rimuovi(acquisto);
				}
			}
		};

		tbl_prodotti = new JTable(modello);
		tbl_prodotti.getTableHeader().setReorderingAllowed(false);
		ButtonColumn btn_aggiungi = new ButtonColumn(tbl_prodotti, aggiungi, 7);
		ButtonColumn btn_rimuovi = new ButtonColumn(tbl_prodotti, rimuovi, 5);

		tbl_prodotti.getColumnModel().getColumn(1).setPreferredWidth(100);
		tbl_prodotti.getColumnModel().getColumn(2).setPreferredWidth(490);
		tbl_prodotti.getColumnModel().getColumn(3).setPreferredWidth(25);
		tbl_prodotti.getColumnModel().getColumn(4).setPreferredWidth(50);
		tbl_prodotti.getColumnModel().getColumn(5).setPreferredWidth(35);
		tbl_prodotti.getColumnModel().getColumn(7).setPreferredWidth(35);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tbl_prodotti.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		TableColumnModel tcm = tbl_prodotti.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		JScrollPane jsp_prodotti = new JScrollPane(tbl_prodotti);
		jsp_prodotti.setBounds(10, 64, 876, 360);
		contentPane.add(jsp_prodotti);

		JLabel lbl_prezzomin = new JLabel("Prezzo minimo");
		lbl_prezzomin.setBounds(30, 15, 86, 14);
		contentPane.add(lbl_prezzomin);

		JLabel lbl_prezzomax = new JLabel("Prezzo massimo");
		lbl_prezzomax.setBounds(249, 15, 108, 14);
		contentPane.add(lbl_prezzomax);

	}

}
