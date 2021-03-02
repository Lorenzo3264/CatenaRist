package finestre;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Date;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import classi.Dipendente;
import controllers.ControllerManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinDipInsert extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nome;
	private JTextField txt_cognome;
	private JTextField txt_email;
	private JTextField txt_cellulare;
	private JTextField txt_via;
	private JTextField txt_civico;

	

	/**
	 * Create the frame.
	 * @param controllerManager 
	 */
	public WinDipInsert(ControllerManager controllerManager) {
		setTitle("Assumi dipendente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txt_nome = new JTextField();
		txt_nome.setBounds(122, 11, 382, 20);
		contentPane.add(txt_nome);
		txt_nome.setColumns(10);
		
		txt_cognome = new JTextField();
		txt_cognome.setBounds(122, 42, 382, 20);
		contentPane.add(txt_cognome);
		txt_cognome.setColumns(10);
		
		txt_email = new JTextField();
		txt_email.setBounds(122, 73, 382, 20);
		contentPane.add(txt_email);
		txt_email.setColumns(10);
		
		txt_cellulare = new JTextField();
		txt_cellulare.setBounds(122, 104, 382, 20);
		contentPane.add(txt_cellulare);
		txt_cellulare.setColumns(10);
		
		txt_via = new JTextField();
		txt_via.setBounds(122, 135, 382, 20);
		contentPane.add(txt_via);
		txt_via.setColumns(10);
		
		txt_civico = new JTextField();
		txt_civico.setBounds(122, 166, 382, 20);
		contentPane.add(txt_civico);
		txt_civico.setColumns(10);
		
		JLabel lbl_nome = new JLabel("Nome");
		lbl_nome.setBounds(10, 14, 102, 14);
		contentPane.add(lbl_nome);
		
		JLabel lbl_cognome = new JLabel("Cognome");
		lbl_cognome.setBounds(10, 45, 102, 14);
		contentPane.add(lbl_cognome);
		
		JLabel lbl_email = new JLabel("Email");
		lbl_email.setBounds(10, 76, 102, 14);
		contentPane.add(lbl_email);
		
		JLabel lbl_cellulare = new JLabel("Cellulare");
		lbl_cellulare.setBounds(10, 107, 102, 14);
		contentPane.add(lbl_cellulare);
		
		JLabel lbl_via = new JLabel("Via");
		lbl_via.setBounds(10, 138, 102, 14);
		contentPane.add(lbl_via);
		
		JLabel lbl_civico = new JLabel("Civico");
		lbl_civico.setBounds(10, 169, 102, 14);
		contentPane.add(lbl_civico);
		
		String[] ruoli = {"chef", "cuoco", "lavapiatti","cameriere","direttore di sala"}; //direttore di sala = direttoreSala
		JComboBox cb_ruolo = new JComboBox(ruoli);
		cb_ruolo.setBounds(122, 362, 382, 20);
		contentPane.add(cb_ruolo);
		
		JLabel lbl_ruolo = new JLabel("Ruolo");
		lbl_ruolo.setBounds(10, 365, 102, 14);
		contentPane.add(lbl_ruolo);
		
		JButton btn_indietro = new JButton("indietro");
		btn_indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerManager.dipInsertExit();
			}
		});
		btn_indietro.setBounds(10, 417, 102, 23);
		contentPane.add(btn_indietro);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Oggi");
		p.put("text.month", "Mese");
		p.put("text.year", "Anno");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePanel.setSize(494, 154);
		datePanel.setLocation(10, 197);
		contentPane.add(datePanel);
		
		JButton btn_conferma = new JButton("Assumi");
		btn_conferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dipendente dipendente = new Dipendente();
				dipendente.setNome(txt_nome.getText());
				dipendente.setCognome(txt_cognome.getText());
				dipendente.setVia(txt_via.getText());
				dipendente.setCivico(txt_civico.getText());
				dipendente.setEmail(txt_email.getText());
				dipendente.setCellulare(txt_cellulare.getText());
				if(((String)cb_ruolo.getSelectedItem()).equals("direttore di sala")) {
					dipendente.setRuolo("direttoreSala");
				}else {
					dipendente.setRuolo((String)cb_ruolo.getSelectedItem());
				}
				try {
					Date data = (Date) datePanel.getModel().getValue();
					if(data.after(new Date())){
						JOptionPane.showMessageDialog(contentPane,
							    "Inserisci una data precedente a quella di oggi",
							    "Errore di input",
							    JOptionPane.ERROR_MESSAGE);
					}else {
						dipendente.setDataN(((data.getYear()+1900))+"-"+(data.getMonth()+1)+"-"+data.getDate());
						controllerManager.assumi(dipendente);
					}
					
				} catch (NullPointerException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, "Inserisci una data di nascita", "Errore di input",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_conferma.setBounds(308, 417, 196, 23);
		contentPane.add(btn_conferma);
	}
}
