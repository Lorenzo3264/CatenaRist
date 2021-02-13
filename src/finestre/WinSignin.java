package finestre;
import controllers.*;
import classi.Cliente;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class WinSignin extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private JTextField txt_nome;
	private JTextField txt_cognome;
	private JTextField txt_email;
	private JPasswordField psw_password;
	private JTextField txt_cellulare;
	
	

	/**
	 * Create the frame.
	 */
	public WinSignin(Controller c) {
		setTitle("Registrati");
		setResizable(false);
		controller = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Oggi");
		p.put("text.month", "Mese");
		p.put("text.year", "Anno");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePanel.setSize(546, 180);
		datePanel.setLocation(10, 201);
		contentPane.add(datePanel);
		
		txt_nome = new JTextField();
		txt_nome.setBounds(135, 21, 421, 20);
		contentPane.add(txt_nome);
		txt_nome.setColumns(10);
		
		txt_cognome = new JTextField();
		txt_cognome.setBounds(135, 52, 421, 20);
		contentPane.add(txt_cognome);
		txt_cognome.setColumns(10);
		
		txt_email = new JTextField();
		txt_email.setText("");
		txt_email.setBounds(135, 83, 421, 20);
		contentPane.add(txt_email);
		txt_email.setColumns(10);
		
		psw_password = new JPasswordField();
		psw_password.setBounds(135, 114, 421, 20);
		contentPane.add(psw_password);
		
		txt_cellulare = new JTextField();
		txt_cellulare.setBounds(135, 145, 421, 20);
		contentPane.add(txt_cellulare);
		txt_cellulare.setColumns(10);
		
		JLabel lbl_nome = new JLabel("Nome");
		lbl_nome.setBounds(22, 24, 103, 14);
		contentPane.add(lbl_nome);
		
		JLabel lbl_cognome = new JLabel("Cognome");
		lbl_cognome.setBounds(22, 55, 103, 14);
		contentPane.add(lbl_cognome);
		
		JLabel lbl_email = new JLabel("Email");
		lbl_email.setBounds(22, 86, 103, 14);
		contentPane.add(lbl_email);
		
		JLabel lbl_password = new JLabel("Password");
		lbl_password.setBounds(22, 117, 103, 14);
		contentPane.add(lbl_password);
		
		JLabel lbl_cellulare = new JLabel("Cellulare");
		lbl_cellulare.setBounds(22, 148, 103, 14);
		contentPane.add(lbl_cellulare);
		
		JButton btn_conferma = new JButton("Crea Account");
		btn_conferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Cliente cl = new Cliente();
					cl.setCodCl("nextval(seq_codcl)");
					cl.setNome(txt_nome.getText());
					cl.setCognome(txt_cognome.getText());
					cl.setEmail(txt_email.getText());
					cl.setCellulare(txt_cellulare.getText());
					cl.setPassword(psw_password.getText());
					System.out.println(cl.getPassword());
					Date data = (Date) datePanel.getModel().getValue();
					cl.setDataN((data.getDate())+"-"+(data.getMonth()+1)+"-"+(data.getYear()+1900));
					System.out.println(cl.getDataN());
					c.signin_ok(cl);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(contentPane,
						    "Ci sono errori nei dati inseriti",
						    "Errore di input",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_conferma.setBounds(397, 408, 159, 33);
		contentPane.add(btn_conferma);
		
		JButton btn_indietro = new JButton("Torna indietro");
		btn_indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.signin_back();
			}
		});
		btn_indietro.setBounds(10, 408, 158, 33);
		contentPane.add(btn_indietro);
		
		JLabel lbl_dataN = new JLabel("Inserisci la data di nascita");
		lbl_dataN.setBounds(22, 176, 233, 14);
		contentPane.add(lbl_dataN);
		

	}
}
