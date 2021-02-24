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

import classi.Utente;
import controllers.PadreController;
import controllers.ControllerCliente;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinInfoUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField txt_email;
	private JPasswordField psw_password;
	private JTextField txt_nome;
	private JTextField txt_cognome;
	private JTextField txt_cellulare;
	private PadreController controller;

	
	/**
	 * Create the frame.
	 * @param controllerCliente 
	 */
	public WinInfoUpdate(PadreController con) {
		
		controller = con;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_email = new JLabel("Email:");
		lbl_email.setBounds(10, 26, 120, 14);
		contentPane.add(lbl_email);
		
		txt_email = new JTextField();
		txt_email.setText(controller.getUtente().getEmail());
		txt_email.setBounds(140, 23, 355, 20);
		contentPane.add(txt_email);
		txt_email.setColumns(10);
		
		psw_password = new JPasswordField();
		psw_password.setText(controller.getUtente().getPassword());
		psw_password.setBounds(140, 54, 355, 20);
		contentPane.add(psw_password);
		
		JLabel lbl_password = new JLabel("Password:");
		lbl_password.setBounds(10, 57, 120, 14);
		contentPane.add(lbl_password);
		
		txt_nome = new JTextField();
		txt_nome.setText(controller.getUtente().getNome());
		txt_nome.setBounds(140, 85, 355, 20);
		contentPane.add(txt_nome);
		txt_nome.setColumns(10);
		
		JLabel lbl_nome = new JLabel("Nome:");
		lbl_nome.setBounds(10, 88, 120, 14);
		contentPane.add(lbl_nome);
		
		txt_cognome = new JTextField();
		txt_cognome.setText(controller.getUtente().getCognome());
		txt_cognome.setBounds(140, 116, 355, 20);
		contentPane.add(txt_cognome);
		txt_cognome.setColumns(10);
		
		JLabel lbl_cognome = new JLabel("Cognome:");
		lbl_cognome.setBounds(10, 119, 120, 14);
		contentPane.add(lbl_cognome);
		
		txt_cellulare = new JTextField(controller.getUtente().getCellulare());
		txt_cellulare.setBounds(140, 147, 355, 20);
		contentPane.add(txt_cellulare);
		txt_cellulare.setColumns(10);
		
		JLabel lbl_cellulare = new JLabel("Cellulare: ");
		lbl_cellulare.setBounds(10, 150, 120, 14);
		contentPane.add(lbl_cellulare);
		
		JLabel lbl = new JLabel("Data di nascita:");
		lbl.setBounds(20, 178, 120, 14);
		contentPane.add(lbl);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Oggi");
		p.put("text.month", "Mese");
		p.put("text.year", "Anno");
		System.out.println(controller.getUtente().getDataN());
		int year = Integer.parseInt(controller.getUtente().getDataN().substring(0, 4));
		int month = Integer.parseInt(controller.getUtente().getDataN().substring(5, 7));
		int day = Integer.parseInt(controller.getUtente().getDataN().substring(8, 10));
		model.setDate(year, month, day);
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePanel.setSize(485, 180);
		datePanel.setLocation(10, 205);
		contentPane.add(datePanel);
		
		JButton btn_conferma = new JButton("Conferma");
		btn_conferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Utente utente = new Utente();
			utente.setCellulare(txt_cellulare.getText());
			utente.setNome(txt_nome.getText());
			utente.setCognome(txt_cognome.getText());
			Date data = (Date) datePanel.getModel().getValue();
			utente.setDataN((data.getMonth()+1)+"-"+data.getDate()+"-"+(data.getYear()+1900));
			utente.setPassword(psw_password.getText());
			utente.setEmail(txt_email.getText());
			controller.cambiaInfo(utente);
			}
		});
		btn_conferma.setBounds(406, 410, 89, 23);
		contentPane.add(btn_conferma);
		
		JButton btn_indietro = new JButton("Indietro");
		btn_indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.infoBack();
			}
		});
		btn_indietro.setBounds(10, 410, 89, 23);
		contentPane.add(btn_indietro);
	}
}
