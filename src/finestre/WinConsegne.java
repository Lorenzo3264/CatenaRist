package finestre;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classi.Consegna;
import controllers.ControllerRider;

public class WinConsegne extends JFrame {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 * @param controllerRider 
	 * @param consegne 
	 */
	public WinConsegne(ControllerRider controllerRider, ArrayList<Consegna> consegne) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
