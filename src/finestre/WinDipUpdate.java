package finestre;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import classi.Dipendente;
import controllers.ControllerManager;

public class WinDipUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nome;
	private JTextField txt_cognome;
	private JTextField txt_email;
	private JTextField txt_cellulare;
	private JTextField txt_via;
	private JTextField txt_civico;
	private ControllerManager controllerManager;
	
	public WinDipUpdate(ControllerManager controller, Dipendente dipendente) {
		controllerManager = controller;
		setTitle("Assumi dipendente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txt_nome = new JTextField();
		txt_nome.setBounds(122, 31, 382, 20);
		txt_nome.setText(dipendente.getNome());
		contentPane.add(txt_nome);
		txt_nome.setColumns(10);
		
		txt_cognome = new JTextField();
		txt_cognome.setBounds(122, 62, 382, 20);
		txt_cognome.setText(dipendente.getCognome());
		contentPane.add(txt_cognome);
		txt_cognome.setColumns(10);
		
		txt_email = new JTextField();
		txt_email.setBounds(122, 93, 382, 20);
		txt_email.setText(dipendente.getEmail());
		contentPane.add(txt_email);
		txt_email.setColumns(10);
		
		txt_cellulare = new JTextField();
		txt_cellulare.setBounds(122, 124, 382, 20);
		txt_cellulare.setText(dipendente.getCellulare());
		contentPane.add(txt_cellulare);
		txt_cellulare.setColumns(10);
		
		txt_via = new JTextField();
		txt_via.setBounds(122, 155, 382, 20);
		txt_via.setText(dipendente.getVia());
		contentPane.add(txt_via);
		txt_via.setColumns(10);
		
		txt_civico = new JTextField();
		txt_civico.setBounds(122, 186, 382, 20);
		txt_civico.setText(dipendente.getCivico());
		contentPane.add(txt_civico);
		txt_civico.setColumns(10);
		
		JLabel lbl_nome = new JLabel("Nome");
		lbl_nome.setBounds(10, 34, 102, 14);
		contentPane.add(lbl_nome);
		
		JLabel lbl_cognome = new JLabel("Cognome");
		lbl_cognome.setBounds(10, 65, 102, 14);
		contentPane.add(lbl_cognome);
		
		JLabel lbl_email = new JLabel("Email");
		lbl_email.setBounds(10, 96, 102, 14);
		contentPane.add(lbl_email);
		
		JLabel lbl_cellulare = new JLabel("Cellulare");
		lbl_cellulare.setBounds(10, 127, 102, 14);
		contentPane.add(lbl_cellulare);
		
		JLabel lbl_via = new JLabel("Via");
		lbl_via.setBounds(10, 158, 102, 14);
		contentPane.add(lbl_via);
		
		JLabel lbl_civico = new JLabel("Civico");
		lbl_civico.setBounds(10, 189, 102, 14);
		contentPane.add(lbl_civico);
		
		String[] ruoli = {"chef", "cuoco", "lavapiatti","cameriere","direttore di sala"}; //direttore di sala = direttoreSala
		JComboBox<String> cb_ruolo = new JComboBox<String>(ruoli);
		if(dipendente.getRuolo().equals("direttoreSala")) {
			cb_ruolo.setSelectedItem("direttore di sala");
		}else {
			cb_ruolo.setSelectedItem(dipendente.getRuolo());
		}
		cb_ruolo.setBounds(122, 396, 382, 20);
		contentPane.add(cb_ruolo);
		
		JLabel lbl_ruolo = new JLabel("Ruolo");
		lbl_ruolo.setBounds(10, 399, 102, 14);
		contentPane.add(lbl_ruolo);
		
		JButton btn_indietro = new JButton("indietro");
		btn_indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerManager.dipUpdateExit();
			}
		});
		btn_indietro.setBounds(10, 437, 102, 23);
		contentPane.add(btn_indietro);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Oggi");
		p.put("text.month", "Mese");
		p.put("text.year", "Anno");
		int year = Integer.parseInt(dipendente.getDataN().substring(0, 4));
		int month = Integer.parseInt(dipendente.getDataN().substring(5, 7));
		int day = Integer.parseInt(dipendente.getDataN().substring(8, 10));
		model.setDate(year, month, day);
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePanel.setSize(494, 154);
		datePanel.setLocation(10, 217);
		contentPane.add(datePanel);
		
		JButton btn_conferma = new JButton("Modifica");
		btn_conferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
						controllerManager.modifica(dipendente);
					}
					
				} catch (NullPointerException e1) {
					controllerManager.modifica(dipendente);
				}
			}
		});
		btn_conferma.setBounds(308, 437, 196, 23);
		contentPane.add(btn_conferma);
		
		JLabel lbl_dataN = new JLabel("data di nascita: "+dipendente.getDataN());
		lbl_dataN.setBounds(10, 374, 494, 14);
		contentPane.add(lbl_dataN);

	}
}
