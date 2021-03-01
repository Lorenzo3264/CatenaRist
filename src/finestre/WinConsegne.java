package finestre;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import classi.Acquisto;
import classi.Consegna;
import controllers.ControllerRider;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class WinConsegne extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tbl_consegne;

	/**
	 * Create the frame.
	 * 
	 * @param controllerRider
	 * @param consegne
	 */
	public WinConsegne(ControllerRider controllerRider, ArrayList<Consegna> consegne) {
		setResizable(false);
		setTitle("Consegne prese in carico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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
			righe[i][6] = "Completa";
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

		tbl_consegne = new JTable(modello);

		Action completa = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				int opzione = JOptionPane.showConfirmDialog(contentPane, "Sei sicuro?", "conferma",
						JOptionPane.YES_NO_OPTION);
				if (Integer.compare(opzione, JOptionPane.YES_OPTION) == 0) {
					JTable table = (JTable) e.getSource();
					int modelRow = Integer.valueOf(e.getActionCommand());
					String codC;
					codC = ((String) ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 0));
					((DefaultTableModel) table.getModel()).removeRow(modelRow);
					controllerRider.completaConsegna(codC);
					tbl_consegne.revalidate();
					if(Integer.compare(tbl_consegne.getModel().getRowCount(),1) < 0) {
						JOptionPane.showMessageDialog(contentPane, "Non ci sono più consegne", "Congratulazioni",
								JOptionPane.INFORMATION_MESSAGE);
						controllerRider.consegnaBack();
					}
				}
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
		
		tbl_consegne.getColumnModel().getColumn(1).setPreferredWidth(120);
		tbl_consegne.getColumnModel().getColumn(2).setPreferredWidth(100);
		tbl_consegne.getColumnModel().getColumn(3).setPreferredWidth(70);
		tbl_consegne.getColumnModel().getColumn(4).setPreferredWidth(120);
		tbl_consegne.getColumnModel().getColumn(5).setPreferredWidth(300);
		tbl_consegne.getColumnModel().getColumn(6).setPreferredWidth(300);

		ButtonColumn btn_aggiungi = new ButtonColumn(tbl_consegne, completa, 6);
		ButtonColumn btn_note = new ButtonColumn(tbl_consegne,vediNote, 5);
		TableColumnModel tcm = tbl_consegne.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		JScrollPane jsp_consegne = new JScrollPane(tbl_consegne);
		jsp_consegne.setBounds(10, 11, 606, 370);
		contentPane.add(jsp_consegne);

		JButton btn_indietro = new JButton("indietro");
		btn_indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerRider.consegnaBack();
			}
		});
		btn_indietro.setBounds(10, 392, 131, 23);
		contentPane.add(btn_indietro);
	}
}
