package finestre;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classi.Dipendente;
import controllers.ControllerManager;

public class WinDipUpdate extends JFrame {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 * @param dipendente 
	 * @param controllerManager 
	 */
	public WinDipUpdate(ControllerManager controllerManager, Dipendente dipendente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
