package finestre;
import controllers.*;
import classi.Account;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinLogin extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private JTextField txt_email;
	private JPasswordField psw_password;
	private JButton btn_login;
	private JButton btn_signin;
	private JLabel lbl_email;
	private JLabel lbl_password;
	private JLabel lbl_signin;

	/**
	 * Create the frame.
	 */
	public WinLogin(Controller contr) {
		controller = contr;
		setResizable(false);
		setTitle("LogIn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txt_email = new JTextField();
		txt_email.setToolTipText("Inserisci Email");
		txt_email.setBounds(198, 110, 241, 32);
		contentPane.add(txt_email);
		txt_email.setColumns(10);
		
		psw_password = new JPasswordField();
		psw_password.setBounds(198, 205, 241, 32);
		contentPane.add(psw_password);
		
		btn_login = new JButton("Accedi");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.login(txt_email.getText(),psw_password.getText()); //bisogna aggiungere attributi per la ricerca al database
			}
		});
		btn_login.setBounds(198, 274, 241, 23);
		contentPane.add(btn_login);
		
		btn_signin = new JButton("Crea nuovo account");
		btn_signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.signin();
			}
		});
		btn_signin.setBounds(198, 365, 241, 23);
		contentPane.add(btn_signin);
		
		lbl_email = new JLabel("Inserisci Email");
		lbl_email.setBounds(198, 85, 241, 14);
		contentPane.add(lbl_email);
		
		lbl_password = new JLabel("Inserisci password");
		lbl_password.setBounds(198, 180, 241, 14);
		contentPane.add(lbl_password);
		
		lbl_signin = new JLabel("Sei un nuovo cliente?");
		lbl_signin.setBounds(198, 340, 241, 14);
		contentPane.add(lbl_signin);
	}
}
