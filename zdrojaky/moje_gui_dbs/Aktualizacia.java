package moje_gui_dbs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import connections.PGDBConnection;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class Aktualizacia {

	private JFrame frame;
	private JTextField meno;
	private JTextField priezvisko;
	private JTextField cisloOpTf;
	private JTextField datumNarodenia;
	private JTextField bodyZaVencenieTf;

	/**
	 * Create the application.
	 */
	public Aktualizacia(String meno) {
		initialize(meno);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String meno1) {
		frame = new JFrame();
		frame.setBounds(100, 100, 306, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAktualizaciaZaznamu = new JLabel("Aktualizacia zaznamu");
		lblAktualizaciaZaznamu.setHorizontalAlignment(SwingConstants.CENTER);
		lblAktualizaciaZaznamu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAktualizaciaZaznamu.setBounds(26, 11, 262, 43);
		frame.getContentPane().add(lblAktualizaciaZaznamu);
		
		meno = new JTextField();
		meno.setBounds(26, 124, 138, 20);
		frame.getContentPane().add(meno);
		meno.setColumns(10);
		
		priezvisko = new JTextField();
		priezvisko.setBounds(26, 171, 138, 20);
		frame.getContentPane().add(priezvisko);
		priezvisko.setColumns(10);
		
		cisloOpTf = new JTextField();
		cisloOpTf.setBounds(26, 217, 138, 20);
		frame.getContentPane().add(cisloOpTf);
		cisloOpTf.setColumns(10);
	
		JLabel lblMeno = new JLabel("Meno:");
		lblMeno.setBounds(26, 110, 46, 14);
		frame.getContentPane().add(lblMeno);
		
		JLabel lblPriezvisko = new JLabel("Priezvisko:");
		lblPriezvisko.setBounds(26, 155, 86, 14);
		frame.getContentPane().add(lblPriezvisko);
		
		JLabel cisloOp = new JLabel("Cislo OP:");
		cisloOp.setBounds(26, 202, 46, 14);
		frame.getContentPane().add(cisloOp);
		
		datumNarodenia = new JTextField();
		datumNarodenia.setBounds(26, 79, 138, 20);
		frame.getContentPane().add(datumNarodenia);
		datumNarodenia.setColumns(10);
		
		JLabel lblDatumNarodenia = new JLabel("Datum narodenia:");
		lblDatumNarodenia.setBounds(26, 65, 111, 14);
		frame.getContentPane().add(lblDatumNarodenia);
		
		JButton btnAktualizovat = new JButton("Aktualizovat");
		btnAktualizovat.setBackground(new Color(255, 182, 193));
		btnAktualizovat.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAktualizovat.setForeground(new Color(220, 20, 60));
		btnAktualizovat.setBounds(169, 216, 111, 23);
		frame.getContentPane().add(btnAktualizovat);
		
		bodyZaVencenieTf = new JTextField();
		bodyZaVencenieTf.setBounds(174, 79, 106, 20);
		frame.getContentPane().add(bodyZaVencenieTf);
		bodyZaVencenieTf.setColumns(10);
		
		JLabel lblBodyZaVencenie = new JLabel("Body za vencenie:");
		lblBodyZaVencenie.setBounds(182, 65, 98, 14);
		frame.getContentPane().add(lblBodyZaVencenie);
		frame.setVisible(true);
		
		btnAktualizovat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				PGDBConnection.aktualizacia2(meno1, meno.getText(), priezvisko.getText(), 
						cisloOpTf.getText(), datumNarodenia.getText(), Integer.parseInt(bodyZaVencenieTf.getText()));
				PGDBConnection.aktualizacia();
				frame.setVisible(false);
				//System.exit(0);
				
			}
		});
		
		
		
	}
}
