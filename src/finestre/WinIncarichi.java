package finestre;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import classi.Consegna;
import classi.Prodotto;
import controllers.ControllerManager;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.awt.event.ActionEvent;

public class WinIncarichi extends JFrame {

	private JPanel contentPane;
	private ControllerManager controller;
	private JTable tbl_ordini;
	

	/**
	 * Create the frame.
	 * @param controllerManager 
	 * @param consegne 
	 * @param prodotti 
	 */
	public WinIncarichi(ControllerManager controllerManager, ArrayList<Consegna> consegne, ArrayList<Prodotto> prodotti) {
		setTitle("Incarichi");
		setResizable(false);
		controller = controllerManager;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_indietro = new JButton("indietro");
		btn_indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_indietro.setBounds(10, 370, 141, 23);
		contentPane.add(btn_indietro);
		
		
		
		String[] colonne = { "codC", "via", "civico", "prezzo","pagamento","note", "" };
		Object[][] righe = new Object[consegne.size()][7];
		int i;
		for (i = 0; i < consegne.size(); i++) {
			righe[i][0] = consegne.get(i).getCodC();
			righe[i][1] = consegne.get(i).getVia();
			righe[i][2] = consegne.get(i).getCivico();
			righe[i][3] = consegne.get(i).getPrezzo();
			righe[i][4] = consegne.get(i).getMetodoP();
			if(Objects.isNull(consegne.get(i).getNote())) {
				righe[i][5] = "non ci sono note";
			}else {
				righe[i][5] = "vedi note";
			}
			righe[i][6] = "vedi spesa";
		}
		
		DefaultTableModel modello = new DefaultTableModel(righe, colonne) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				switch (column) {
				case 5, 6:
					return true;
				default:
					return false;
				}
			}
		};
		
		tbl_ordini = new JTable(modello);
		
		Action vediSpesa = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				String listaSpesa = new String();
				int i;
				for (i=0;i<prodotti.size();i++) {
				}
			}
			
		};
		
		TableColumnModel tcm = tbl_ordini.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		JScrollPane jsp_ordini = new JScrollPane(tbl_ordini);
		jsp_ordini.setBounds(10, 11, 614, 345);
		contentPane.add(jsp_ordini);
	}
}
