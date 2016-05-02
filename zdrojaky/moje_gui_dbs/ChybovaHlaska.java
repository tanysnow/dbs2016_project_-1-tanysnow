package moje_gui_dbs;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChybovaHlaska {

	private JFrame frame;

	public ChybovaHlaska(int i) {
		initialize(i);
	}

	private void initialize(int i) {
		frame = new JFrame();
		frame.setBounds(100, 100, 335, 221);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		JLabel lblNieSuVyplnene;
		
		if(i == 1){
			lblNieSuVyplnene= new JLabel("Nie su vyplnene vsetky policka!");
		}
		else if(i == 2){
			lblNieSuVyplnene = new JLabel("Nie je oznaceny ziaden riadok!");
		}
		else{
			lblNieSuVyplnene = new JLabel("Nie je vybraty ziadny veterinar!");
		}
		lblNieSuVyplnene.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNieSuVyplnene.setHorizontalAlignment(SwingConstants.CENTER);
		lblNieSuVyplnene.setBounds(33, 11, 250, 82);
		frame.getContentPane().add(lblNieSuVyplnene);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(110, 101, 89, 23);
		frame.getContentPane().add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		
	}
}
