package moje_gui_dbs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Pavilony {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Create the application.
	 */
	public Pavilony() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPavilony = new JLabel("Pavilony");
		lblPavilony.setForeground(new Color(220, 20, 60));
		lblPavilony.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPavilony.setHorizontalAlignment(SwingConstants.CENTER);
		lblPavilony.setBounds(10, 11, 414, 44);
		frame.getContentPane().add(lblPavilony);
		
		JLabel lblHladatVPavilonoch = new JLabel("Hladat v pavilonoch:");
		lblHladatVPavilonoch.setBounds(30, 66, 135, 20);
		frame.getContentPane().add(lblHladatVPavilonoch);
		
		textField = new JTextField();
		textField.setBounds(30, 93, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnHladaj = new JButton("Hladaj");
		btnHladaj.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHladaj.setForeground(new Color(220, 20, 60));
		btnHladaj.setBackground(new Color(255, 182, 193));
		btnHladaj.setBounds(152, 93, 89, 23);
		frame.getContentPane().add(btnHladaj);
		
		JButton btnZobrazitVsetkyPavilony = new JButton("Zobrazit vsetky pavilony");
		btnZobrazitVsetkyPavilony.setBackground(new Color(255, 182, 193));
		btnZobrazitVsetkyPavilony.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnZobrazitVsetkyPavilony.setForeground(new Color(220, 20, 60));
		btnZobrazitVsetkyPavilony.setBounds(30, 212, 197, 23);
		frame.getContentPane().add(btnZobrazitVsetkyPavilony);
		
		JLabel lblZobrazitKonkretnyTyp = new JLabel("Zobrazit konkretny typ pavilonu:");
		lblZobrazitKonkretnyTyp.setBounds(30, 138, 222, 14);
		frame.getContentPane().add(lblZobrazitKonkretnyTyp);
		
		textField_1 = new JTextField();
		textField_1.setBounds(30, 167, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnZobraz = new JButton("Zobraz");
		btnZobraz.setForeground(new Color(220, 20, 60));
		btnZobraz.setBackground(new Color(255, 182, 193));
		btnZobraz.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnZobraz.setBounds(152, 166, 89, 23);
		frame.getContentPane().add(btnZobraz);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
