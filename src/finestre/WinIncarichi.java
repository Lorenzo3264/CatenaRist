package finestre;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import classi.Acquisto;
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
	 * 
	 * @param controllerManager
	 * @param consegne
	 * @param prodotti
	 * @param acquisti
	 */
	public WinIncarichi(ControllerManager controllerManager, ArrayList<Consegna> consegne, ArrayList<Prodotto> prodotti,
			ArrayList<Acquisto> acquisti) {
		setTitle("Incarichi");
		setResizable(false);
		controller = controllerManager;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btn_indietro = new JButton("indietro");
		btn_indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.incarichiBack();
			}
		});
		btn_indietro.setBounds(10, 370, 141, 23);
		contentPane.add(btn_indietro);

		String[] colonne = { "codC", "via", "civico", "prezzo", "pagamento", "stato", "", ""};
		Object[][] righe = new Object[consegne.size()][8];
		int i;
		for (i = 0; i < consegne.size(); i++) {
			righe[i][0] = consegne.get(i).getCodC();
			righe[i][1] = consegne.get(i).getVia();
			righe[i][2] = consegne.get(i).getCivico();
			righe[i][3] = consegne.get(i).getPrezzo();
			righe[i][4] = consegne.get(i).getMetodoP();
			if (Integer.compare(consegne.get(i).getCodR(), 0) == 0) {
				righe[i][5] = "ordinato";
			} else if (Objects.isNull(consegne.get(i).getDataC())) { 
				righe[i][5] = "in consegna";
			} else {
				righe[i][5] = "consegnato";
			}
			if (Objects.isNull(consegne.get(i).getNote()) || consegne.get(i).getNote().isBlank()) {
				righe[i][6] = "non ci sono note";
			} else {
				righe[i][6] = "vedi note";
			}
			righe[i][7] = "vedi spesa";
		}

		DefaultTableModel modello = new DefaultTableModel(righe, colonne) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				switch (column) {
				case 6, 7:
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
				int i;
				int j;
				int listaSize = 0;
				int codC = Integer.parseInt((String) ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 0));
				ArrayList<String> nomi = new ArrayList<String>();
				ArrayList<Integer> quantita = new ArrayList<Integer>();
				ArrayList<Float> prezzi = new ArrayList<Float>();
				for (i = 0; i < acquisti.size(); i++) {
					j = 0;	
					while (j < prodotti.size()) {
						if (Integer.compare(acquisti.get(i).getCodC(), codC) == 0 && Integer.compare(acquisti.get(i).getCodP(), prodotti.get(j).getCodP())==0) {

							nomi.add(prodotti.get(j).getNome());
							quantita.add(acquisti.get(i).getQuantita());
							prezzi.add(prodotti.get(j).getPrezzo() * acquisti.get(i).getQuantita());
							listaSize++;
							j = prodotti.size();
						}
						j++;
					}
				}
				Object[][] righeLista = new Object[listaSize][3];
				for(i=0;i<listaSize;i++) {
					righeLista[i][0] = nomi.get(i);
					righeLista[i][1] = quantita.get(i);
					righeLista[i][2] = prezzi.get(i);
				}
				String[] colonneLista = {"nome","quantita","prezzo"};
				DefaultTableModel modelloLista = new DefaultTableModel(righeLista,colonneLista);
				JTable listaSpesa = new JTable(modelloLista);
				JScrollPane jsp_listaSpesa = new JScrollPane(listaSpesa);
				jsp_listaSpesa.setPreferredSize(new Dimension(500,103));
				JOptionPane.showMessageDialog(contentPane, jsp_listaSpesa, "lista della spesa",
						JOptionPane.INFORMATION_MESSAGE);
			}

		};

		Action vediNote = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				if(Objects.isNull(consegne.get(modelRow).getNote())) 
				{
				JOptionPane.showMessageDialog(contentPane, "non ci sono note", "nota",
						JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(contentPane, consegne.get(modelRow).getNote(), "nota",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		};

		tbl_ordini.getColumnModel().getColumn(6).setPreferredWidth(120);
		tbl_ordini.getColumnModel().getColumn(7).setPreferredWidth(120);
		ButtonColumn btn_vediSpesa = new ButtonColumn(tbl_ordini, vediSpesa, 7);
		ButtonColumn btn_vediNote = new ButtonColumn(tbl_ordini, vediNote, 6);
		TableColumnModel tcm = tbl_ordini.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		JScrollPane jsp_ordini = new JScrollPane(tbl_ordini);
		jsp_ordini.setBounds(10, 11, 681, 345);
		contentPane.add(jsp_ordini);
	}
}
