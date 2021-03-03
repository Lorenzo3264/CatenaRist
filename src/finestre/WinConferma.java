package finestre;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.postgresql.util.PSQLException;

import classi.Attivita;
import classi.Consegna;
import controllers.ControllerCliente;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;

public class WinConferma extends JFrame {

	private JPanel contentPane;
	private ControllerCliente controller;
	private JTextField txt_via;
	private JTextField txt_civico;
	private JTextField txt_codcarta;
	private JLabel lbl_prezzotot;

	/**
	 * Create the frame.
	 * 
	 * @param controllerCliente
	 */
	public WinConferma(ControllerCliente controllerCliente) {
		setResizable(false);
		controller = controllerCliente;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ArrayList<Attivita> attivita = controller.getAttivita();

		JEditorPane txt_note = new JEditorPane();
		txt_note.setBounds(10, 300, 414, 114);
		contentPane.add(txt_note);
		
		float prezzoTot = controller.getPrezzoTot();
		// prova per le attività

		String[] att = new String[attivita.size()];
		int i;
		for (i = 0; i < attivita.size(); i++) {
			att[i] = attivita.get(i).getVia() + " " + attivita.get(i).getCivico() + " ~ " + attivita.get(i).getOrariA();
		}
		
		JComboBox cb_attivita = new JComboBox(att);
		cb_attivita.setBounds(207, 225, 217, 23);
		contentPane.add(cb_attivita);

		JRadioButton rb_contanti = new JRadioButton("Contanti");
		rb_contanti.setBounds(10, 141, 279, 23);
		contentPane.add(rb_contanti);

		JRadioButton rb_cartaC = new JRadioButton("Carta di credito");
		rb_cartaC.setBounds(10, 167, 265, 23);
		contentPane.add(rb_cartaC);

		ButtonGroup group = new ButtonGroup();
		group.add(rb_contanti);
		group.add(rb_cartaC);

		JLabel lbl_metodoP = new JLabel("Inserire metodo di pagamento");
		lbl_metodoP.setBounds(14, 118, 410, 14);
		contentPane.add(lbl_metodoP);

		lbl_prezzotot = new JLabel("Prezzo totale: €" + prezzoTot);
		lbl_prezzotot.setBounds(10, 11, 279, 14);
		contentPane.add(lbl_prezzotot);

		JLabel lbl_via = new JLabel("inserisci via");
		lbl_via.setBounds(14, 55, 105, 14);
		contentPane.add(lbl_via);

		txt_via = new JTextField();
		txt_via.setBounds(129, 52, 299, 20);
		contentPane.add(txt_via);
		txt_via.setColumns(10);

		txt_civico = new JTextField();
		txt_civico.setBounds(129, 83, 299, 20);
		contentPane.add(txt_civico);
		txt_civico.setColumns(10);

		JLabel lbl_civico = new JLabel("inserisci civico");
		lbl_civico.setBounds(14, 86, 105, 14);
		contentPane.add(lbl_civico);

		JButton btn_conferma = new JButton("Conferma");
		btn_conferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consegna consegna = new Consegna();
				try {
					//non è necessario settare il codC di una nuova consegna
					consegna.setCodA(attivita.get(cb_attivita.getSelectedIndex()).getCodA());
					consegna.setCivico(txt_civico.getText());
					consegna.setVia(txt_via.getText());
					consegna.setPrezzo((float) 0.0);// il prezzo totale è gestito dal database
					consegna.setDataO("current_timestamp");
					consegna.setNote(txt_note.getText());
					if (rb_contanti.isSelected()) {
						consegna.setMetodoP("contanti");
					} else if (rb_cartaC.isSelected()) {
						consegna.setMetodoP("cartaC");
						
						if(txt_codcarta.getText().length()  != 16) {
							txt_codcarta.setText("");
						}else {
							// necessario per fare in modo che ci siano solo numeri
							String.valueOf(Long.parseLong(txt_codcarta.getText()));
							consegna.setCodCarta(txt_codcarta.getText());
						}
						

					}
					if (consegna.getCivico().isBlank() || consegna.getVia().isBlank() || (rb_cartaC.isSelected() && txt_codcarta.getText().isBlank())) {
						JOptionPane.showMessageDialog(contentPane, "Inserisci i dati mancanti nel giusto formato", "Attenzione",
								JOptionPane.WARNING_MESSAGE);
					} else {

						
						controller.confermaC(consegna);
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(contentPane, "Inserisci solo numeri nel campo 'codice carta'",
							"Errore di input", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

			}
		});
		btn_conferma.setBounds(306, 425, 118, 23);
		contentPane.add(btn_conferma);

		JButton btn_indietro = new JButton("Indietro");
		btn_indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.indietroC();
			}
		});
		btn_indietro.setBounds(10, 425, 118, 23);
		contentPane.add(btn_indietro);

		JLabel lbl_cartac = new JLabel("Inserisci codice carta");
		lbl_cartac.setBounds(10, 200, 187, 14);
		contentPane.add(lbl_cartac);
		lbl_cartac.hide();

		txt_codcarta = new JTextField();
		txt_codcarta.setBounds(207, 197, 217, 20);
		contentPane.add(txt_codcarta);
		txt_codcarta.hide();
		txt_codcarta.setColumns(10);

		JLabel lbl_attivita = new JLabel("Seleziona filiale");
		lbl_attivita.setBounds(10, 229, 187, 14);
		contentPane.add(lbl_attivita);

		JLabel lbl_note = new JLabel("Inserisci delle note (opzionale):");
		lbl_note.setBounds(10, 275, 414, 14);
		contentPane.add(lbl_note);

		rb_cartaC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txt_codcarta.show();
				lbl_cartac.show();
			}
		});

		rb_contanti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txt_codcarta.hide();
				lbl_cartac.hide();
			}
		});

		rb_contanti.setSelected(true);
		
		

	}

	public void aggiornaprezzo() {
		// TODO Auto-generated method stub
		float prezzoTot = controller.getPrezzoTot();
		contentPane.remove(lbl_prezzotot);
		lbl_prezzotot = new JLabel("Prezzo totale: €" + prezzoTot);
		lbl_prezzotot.setBounds(10, 11, 279, 14);
		contentPane.add(lbl_prezzotot);
	}
}
