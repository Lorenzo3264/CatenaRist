package finestre;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import classi.Dipendente;
import controllers.ControllerManager;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;

public class WinDipendenti extends JFrame {

	private JPanel contentPane;
	private JTable tbl_dipendenti;

	/**
	 * Create the frame.
	 * 
	 * @param dipendenti
	 * @param controllerManager
	 */
	public WinDipendenti(ControllerManager controllerManager, ArrayList<Dipendente> dipendenti) {
		setTitle("Dipendenti");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btn_indietro = new JButton("indietro");
		btn_indietro.setBounds(10, 384, 140, 23);
		contentPane.add(btn_indietro);

		String[] colonne = { "codD", "Nome e Cognome", "ruolo", "", "", "" };
		Object[][] righe = new Object[dipendenti.size()][6];
		int i;
		for (i = 0; i < dipendenti.size(); i++) {
			righe[i][0] = dipendenti.get(i).getCodD();
			righe[i][1] = dipendenti.get(i).getNome() + " " + dipendenti.get(i).getCognome();
			righe[i][2] = dipendenti.get(i).getRuolo();
			righe[i][3] = "visualizza";
			righe[i][4] = "modifica";
			righe[i][5] = "licenzia";

		}

		DefaultTableModel modello = new DefaultTableModel(righe, colonne) {
			public boolean isCellEditable(int row, int column) {
				switch (column) {
				case 3, 4, 5:
					return true;
				default:
					return false;
				}
			}
		};

		Action visualizzaDipendente = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				String dettagliDip = new String("");
				dettagliDip = dettagliDip + "Nome:" + dipendenti.get(modelRow).getNome() + "\n";
				dettagliDip = dettagliDip + "Cognome:" + dipendenti.get(modelRow).getCognome() + "\n";
				dettagliDip = dettagliDip + "Ruolo:" + dipendenti.get(modelRow).getRuolo() + "\n";
				dettagliDip = dettagliDip + "Cellulare:" + dipendenti.get(modelRow).getCellulare() + "\n";
				dettagliDip = dettagliDip + "Email:" + dipendenti.get(modelRow).getEmail() + "\n";
				dettagliDip = dettagliDip + "Indirizzo:" + dipendenti.get(modelRow).getVia() + " "
						+ dipendenti.get(modelRow).getCivico() + "\n";
				dettagliDip = dettagliDip + "Data di nascita:" + dipendenti.get(modelRow).getDataN() + "\n";
				JOptionPane.showMessageDialog(contentPane, dettagliDip, "Dettagli dipendente",
						JOptionPane.INFORMATION_MESSAGE);
			}

		};
		
		Action licenziaDipendente = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				int opzione = JOptionPane.showConfirmDialog(contentPane, "Sei sicuro?", "conferma",
						JOptionPane.YES_NO_OPTION);
				if(Integer.compare(opzione, JOptionPane.YES_OPTION) == 0) {
					controllerManager.licenziaDipendente(dipendenti.get(modelRow).getCodD());
					dipendenti.remove(modelRow);
					((DefaultTableModel)table.getModel()).removeRow(modelRow);
					table.revalidate();
				}
			}
			
		};
		
		Action modificaDipendente = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int modelRow = Integer.valueOf(e.getActionCommand());
				controllerManager.modificaDipendente(dipendenti.get(modelRow));
			}
			
		};
		
		
		tbl_dipendenti = new JTable(modello);
		ButtonColumn btn_visualizzaDipendente = new ButtonColumn(tbl_dipendenti,visualizzaDipendente, 3);
		ButtonColumn btn_modificaDipendente = new ButtonColumn(tbl_dipendenti, modificaDipendente, 4);
		ButtonColumn btn_licenziaDipendente = new ButtonColumn(tbl_dipendenti, licenziaDipendente, 5);
		JScrollPane jsp_dipendenti = new JScrollPane(tbl_dipendenti);
		jsp_dipendenti.setBounds(10, 11, 629, 358);
		contentPane.add(jsp_dipendenti);
		
		JButton btn_assumi = new JButton("Assumi dipendente");
		btn_assumi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerManager.assumiDipendente();
			}
		});
		btn_assumi.setBounds(438, 384, 201, 23);
		contentPane.add(btn_assumi);
	}
}
