package finestre;
import controllers.*;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class WinCliente extends JFrame {

	private JPanel contentPane;
	private ControllerCliente controller;
	


	/**
	 * Create the frame.
	 */
	public WinCliente(ControllerCliente contr) {
		controller = contr;
		setTitle("Men\u00F9 Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 369, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Cambia informazioni account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.info();
			}
		});
		
		JButton btn_ordina = new JButton("Ordina");
		btn_ordina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ordina();
			}
		});
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(btn_ordina);
		contentPane.add(btnNewButton);
		
		JButton btn_logout = new JButton("Esci");
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.logout();
			}
		});
		contentPane.add(btn_logout);
	}
}
