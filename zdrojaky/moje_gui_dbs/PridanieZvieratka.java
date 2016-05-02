package moje_gui_dbs;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;

import connections.PGDBConnection;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PridanieZvieratka {

	private JFrame frame;
	private JTextField menoTf;
	private JTextField datumPrijatiaTf;
	private JTextField pohlavieTf;
	private JTextField typTf;
	Statement state = null;

	public PridanieZvieratka() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTuMozetePridat = new JLabel("Tu mozete pridat novy zaznam: (zvieratko)");
		lblTuMozetePridat.setForeground(new Color(220, 20, 60));
		lblTuMozetePridat.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTuMozetePridat.setHorizontalAlignment(SwingConstants.CENTER);
		lblTuMozetePridat.setBounds(10, 11, 414, 49);
		frame.getContentPane().add(lblTuMozetePridat);
		
		JLabel lblMeno = new JLabel("Meno:");
		lblMeno.setBounds(36, 113, 75, 14);
		frame.getContentPane().add(lblMeno);
		
		menoTf = new JTextField();
		menoTf.setBounds(36, 131, 198, 20);
		frame.getContentPane().add(menoTf);
		menoTf.setColumns(10);
		
		JLabel lblRasa = new JLabel("Rasa:");
		lblRasa.setBounds(36, 57, 75, 20);
		frame.getContentPane().add(lblRasa);
		
		JLabel lblTypmackaPes = new JLabel("Typ: (macka, pes)");
		lblTypmackaPes.setBounds(242, 57, 118, 20);
		frame.getContentPane().add(lblTypmackaPes);
		
		typTf = new JTextField();
		typTf.setBounds(244, 82, 129, 20);
		frame.getContentPane().add(typTf);
		typTf.setColumns(10);
		
		JLabel lblPohlavie = new JLabel("Pohlavie:");
		lblPohlavie.setBounds(36, 162, 63, 14);
		frame.getContentPane().add(lblPohlavie);
		
		pohlavieTf = new JTextField();
		pohlavieTf.setBounds(36, 179, 198, 20);
		frame.getContentPane().add(pohlavieTf);
		pohlavieTf.setColumns(10);
		
		JLabel lblDatumPrijatia = new JLabel("Datum prijatia:");
		lblDatumPrijatia.setBounds(36, 198, 101, 20);
		frame.getContentPane().add(lblDatumPrijatia);
		
		datumPrijatiaTf = new JTextField();
		datumPrijatiaTf.setBounds(36, 219, 198, 20);
		frame.getContentPane().add(datumPrijatiaTf);
		datumPrijatiaTf.setColumns(10);
		
		JButton btnNewButton = new JButton("Ulozit");
		btnNewButton.setForeground(new Color(220, 20, 60));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBackground(new Color(255, 182, 193));
		btnNewButton.setBounds(284, 218, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(
				new String[] {"nemecky ovciak", "labrador", "jazvecik", "ine", "poulicna rasa"}));
		comboBox.setBounds(34, 82, 200, 20);
		frame.getContentPane().add(comboBox);
		frame.setVisible(true);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				state = PGDBConnection.vratState(1);
				String meno = menoTf.getText();
				String od_kedy_v_utulku = datumPrijatiaTf.getText();
				String pohlavie = pohlavieTf.getText();
				int rasa = 0;
				if((String) comboBox.getSelectedItem() == "nemecky ovciak"){
					rasa = 1;
				}
				else if((String) comboBox.getSelectedItem() == "labrador"){
					rasa = 2;
				}
				else if((String) comboBox.getSelectedItem() == "jazvecik"){
					rasa = 3;
				}
				else if((String) comboBox.getSelectedItem() == "ine"){
					rasa = 4;
				}
				else if((String) comboBox.getSelectedItem() == "poulicna rasa"){
					rasa = 5;
				}
				String sql = "INSERT INTO animal(meno, od_kedy_v_utulku, pohlavie, rasa_id) "
						+ "values ('"+ meno +"', '"+ od_kedy_v_utulku +"', '"+ pohlavie +"', '"+rasa+"')";
				try {
					state.executeUpdate(sql);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				PGDBConnection.zobrazDBanimal();
				HelloWorldGui.fruitListScrollPane = new JScrollPane(HelloWorldGui.fruitList); 
			
				
			}
		});
	
	}
}
