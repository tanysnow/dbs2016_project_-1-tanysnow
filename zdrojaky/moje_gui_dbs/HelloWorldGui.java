package moje_gui_dbs;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;

import connections.ElasticSearch;
import connections.PGDBConnection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.elasticsearch.search.SearchHit;


public class HelloWorldGui {

	List<List<String>> list = null;
	
	
	private JFrame frame;
	public static JScrollPane fruitListScrollPane;
	@SuppressWarnings("rawtypes")
	public static DefaultListModel listModel = new DefaultListModel();
	public static JList<Object> fruitList; 
	public static ListSelectionModel listSelectionModel;
	private int i = 0;

	private JTextField hladanie;

	public HelloWorldGui() {
		initialize();
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.PINK);
		frame.setBounds(100, 100, 843, 461);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		
		JComboBox comboBoxHladanie = new JComboBox();
		comboBoxHladanie.setModel(new DefaultComboBoxModel(
				new String[] {"meno zvieratka", "rasa", "typ", "datum prijatia", "pohlavie"}));
		comboBoxHladanie.setBounds(64, 389, 127, 20);
		frame.getContentPane().add(comboBoxHladanie);
		
		hladanie = new JTextField();
		hladanie.setBounds(201, 389, 164, 20);
		frame.getContentPane().add(hladanie);
		hladanie.setColumns(10);
		hladanie.addPropertyChangeListener(new PropertyChangeListener() {
	      public void stateChanged(ChangeEvent event) {
	    	Object result = ElasticSearch.jednoducheVyhladavanie("1", "animal", hladanie.getText(), 1);
			fruitListScrollPane = new JScrollPane(fruitList);
	      }

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			// TODO Auto-generated method stub
			
		}
		});
		
		ChangeListener changeListner = new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
			
				hladanie = new JTextField();
				String zadane = hladanie.getText();
				Object result = ElasticSearch.jednoducheVyhladavanie("1", "animal", hladanie.getText(), 1);
				fruitListScrollPane = new JScrollPane(fruitList);
			  }
			};

		
		/*
		final JSlider slider = new JSlider(0, 150, 0);
	    slider.setPreferredSize(new Dimension(150, 30));
	    slider.addChangeListener(new ChangeListener() {
	      public void stateChanged(ChangeEvent event) {
	        int value = slider.getValue();
	      
	    	Object result = ElasticSearch.jednoducheVyhladavanie("1", "animal", slider.getToolTipText(), 1);
			fruitListScrollPane = new JScrollPane(fruitList);
	      }
	    });
	    frame.add(slider);
	    //frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
		*/
		
		/*
		KeyListener arg0 = null;
		hladanie.addKeyListener(arg0);
		hladanie.addPropertyChangeListener((PropertyChangeListener) arg0);
		int vyraz = 0;
		SearchHit[] result = null;
		if(arg0 != null){
			if((String) comboBoxHladanie.getSelectedItem() == "meno zvieratka"){
				vyraz = 1;
			}
			else if((String) comboBoxHladanie.getSelectedItem() == "rasa"){
				vyraz = 2;
			}
			else if((String) comboBoxHladanie.getSelectedItem() == "typ"){
				vyraz = 3;
			}
			else if((String) comboBoxHladanie.getSelectedItem() == "datum prijatia"){
				vyraz = 4;
			}
			else if((String) comboBoxHladanie.getSelectedItem() == "pohlavie"){
				vyraz = 5;
			}
			result = ElasticSearch.jednoducheVyhladavanie("1", "animal", hladanie.getText(), vyraz);
			fruitListScrollPane = new JScrollPane(fruitList);
			
		}*/
		
		JButton btnHladaj = new JButton("Hladaj");
		btnHladaj.setBounds(375, 388, 89, 23);
		frame.getContentPane().add(btnHladaj);
		
		
		btnHladaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//list = ElasticSearch.searchByLogin("happie");
				//System.out.println(list);
				//fruitListScrollPane = new JScrollPane(fruitList);
				int vyraz = 0;
				SearchHit[] result = null;
				if((String) comboBoxHladanie.getSelectedItem() == "meno zvieratka"){
					vyraz = 1;
				}
				else if((String) comboBoxHladanie.getSelectedItem() == "rasa"){
					vyraz = 2;
				}
				else if((String) comboBoxHladanie.getSelectedItem() == "typ"){
					vyraz = 3;
				}
				else if((String) comboBoxHladanie.getSelectedItem() == "datum prijatia"){
					vyraz = 4;
				}
				else if((String) comboBoxHladanie.getSelectedItem() == "pohlavie"){
					vyraz = 5;
				}
				
				result = ElasticSearch.jednoducheVyhladavanie("1", "animal", hladanie.getText(), vyraz);

				fruitListScrollPane = new JScrollPane(fruitList);
			}
		});
		
		btnHladaj.setVisible(false);
		comboBoxHladanie.setVisible(false);
		hladanie.setVisible(false);
		
		JButton aktualizovat = new JButton("Aktualizovat");
		JButton btnDetailyvysetreni = new JButton("detaily vysetreni");
		JCheckBox checkBox1 = new JCheckBox("");
		checkBox1.setBounds(37, 112, 21, 15);
		frame.getContentPane().add(checkBox1);
		checkBox1.setVisible(false);
		checkBox1.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {
	        	 i = 1;
	        }
	      });

		
		JCheckBox checkBox4 = new JCheckBox("");
		checkBox4.setBounds(37, 170, 21, 15);
		frame.getContentPane().add(checkBox4);
		checkBox4.setVisible(false);
		checkBox4.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {
	        	 i = 4;
	         }           
	      });
		
		JCheckBox checkBox2 = new JCheckBox("");
		checkBox2.setBounds(37, 132, 21, 15);
		frame.getContentPane().add(checkBox2);
		checkBox2.setVisible(false);
		checkBox2.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {
	            i = 2;
	         }           
	      });
		
		JCheckBox checkBox3 = new JCheckBox("");
		checkBox3.setBounds(37, 150, 21, 15);
		frame.getContentPane().add(checkBox3);
		checkBox3.setVisible(false);
		checkBox3.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {
	            i = 3;
	         }           
	      });
		
		
		JButton detailZvieratka = new JButton("Detail venceni");
		detailZvieratka.setForeground(new Color(220, 20, 60));
		detailZvieratka.setBackground(new Color(255, 182, 193));
		detailZvieratka.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		detailZvieratka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = 0;
				while(true){
					if(listSelectionModel.isSelectedIndex(index)){
						break;
					}
					index++;
					if(index > 100){
						break;
					}
				}
				Object element = listModel.getElementAt(index);
				String pom_string = element.toString();
				int pom = 0;
				String pom_cislo = null;
				char pom_id = pom_string.charAt(0);
				while(pom_id != ' '){
					if (pom_cislo == null){
						pom_cislo = pom_id+"";
					}
					else{
						pom_cislo += pom_id;
					}
					pom++;
					pom_id = pom_string.charAt(pom);
				}
				int idElement = Integer.parseInt(pom_cislo);
				PGDBConnection.zobrazDetail(idElement);
				fruitListScrollPane = new JScrollPane(fruitList);
				checkBox2.setVisible(false);
				checkBox1.setVisible(false);
				checkBox4.setVisible(false);
				checkBox3.setVisible(false);
				btnDetailyvysetreni.setVisible(false);
				aktualizovat.setVisible(false);
				btnHladaj.setVisible(false);
				comboBoxHladanie.setVisible(false);
				hladanie.setVisible(false);
			}
		});
		
		detailZvieratka.setBounds(583, 162, 226, 23);
		frame.getContentPane().add(detailZvieratka);
		aktualizovat.setBounds(201, 388, 104, 23);
		frame.getContentPane().add(aktualizovat);
		aktualizovat.setVisible(false);
		aktualizovat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aktualizovat.setVisible(true);
				int index = 0;
				while(true){
					if(listSelectionModel.isSelectedIndex(index)){
						break;
					}
					index++;
					if(index > 100){
						break;
					}
				}
				Object element = listModel.getElementAt(index);
				String pom_string = element.toString();

				int pom = 0;
				String pom_cislo = null;
				char pom_id = pom_string.charAt(0);
				while(pom_id != ' '){
					if (pom_cislo == null){
						pom_cislo = pom_id+"";
					}
					else{
						pom_cislo += pom_id;
					}
					pom++;
					pom_id = pom_string.charAt(pom);
				}
				String pomMeno = pom_cislo;
				
				new Aktualizacia(pomMeno);
				PGDBConnection.aktualizacia();
				
				fruitListScrollPane = new JScrollPane(fruitList);
				checkBox2.setVisible(false);
				checkBox1.setVisible(false);
				checkBox4.setVisible(false);
				checkBox3.setVisible(false);
				btnDetailyvysetreni.setVisible(false);
				aktualizovat.setVisible(false);
				btnHladaj.setVisible(false);
				comboBoxHladanie.setVisible(false);
				hladanie.setVisible(false);
			}
		});
		
		JButton aktualizacia = new JButton("Aktualiz\u00E1cia z\u00E1znamu o \u010Dlenovi");
		aktualizacia.setBackground(new Color(255, 182, 193));
		aktualizacia.setForeground(new Color(220, 20, 60));
		aktualizacia.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		aktualizacia.setBounds(583, 124, 226, 23);
		frame.getContentPane().add(aktualizacia);
		aktualizacia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aktualizovat.setVisible(true);
				PGDBConnection.aktualizacia();
				fruitListScrollPane = new JScrollPane(fruitList);
				checkBox2.setVisible(false);
				checkBox1.setVisible(false);
				checkBox4.setVisible(false);
				checkBox3.setVisible(false);
				btnDetailyvysetreni.setVisible(false);
				btnHladaj.setVisible(false);
				comboBoxHladanie.setVisible(false);
				hladanie.setVisible(false);
			}
		});
		
		JButton vymazanieZvieratka = new JButton("Vymazanie zvieratka");
		vymazanieZvieratka.setBackground(new Color(255, 182, 193));
		vymazanieZvieratka.setForeground(new Color(220, 20, 60));
		vymazanieZvieratka.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		vymazanieZvieratka.setBounds(583, 56, 226, 23);
		frame.getContentPane().add(vymazanieZvieratka);
		vymazanieZvieratka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = 0;
				while(true){
					if(listSelectionModel.isSelectedIndex(index)){
						break;
					}
					index++;
					if(index > 100){
						break;
					}
				}
				Object element = listModel.getElementAt(index);
				String pom_string = element.toString();
				int pom = 0;
				String pom_cislo = null;
				char pom_id = pom_string.charAt(0);
				while(pom_id != ' '){
					if (pom_cislo == null){
						pom_cislo = pom_id+"";
					}
					else{
						pom_cislo += pom_id;
					}
					pom++;
					pom_id = pom_string.charAt(pom);
				}
				int idElement = Integer.parseInt(pom_cislo);
				
				PGDBConnection.vymaz(idElement);
				PGDBConnection.zobrazDBanimal();
				fruitListScrollPane = new JScrollPane(fruitList); 
				checkBox2.setVisible(false);
				checkBox1.setVisible(false);
				checkBox4.setVisible(false);
				checkBox3.setVisible(false);
				btnDetailyvysetreni.setVisible(false);
				aktualizovat.setVisible(false);
				btnHladaj.setVisible(false);
				comboBoxHladanie.setVisible(false);
				hladanie.setVisible(false);
			}
		});
		
	
		JButton zobrazeniePohlavie = new JButton("Zobrazenie zvieratiek");
		zobrazeniePohlavie.setForeground(new Color(220, 20, 60));
		zobrazeniePohlavie.setBackground(new Color(255, 182, 193));
		zobrazeniePohlavie.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		zobrazeniePohlavie.setBounds(583, 230, 226, 23);
		frame.getContentPane().add(zobrazeniePohlavie);
		zobrazeniePohlavie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PGDBConnection.zobrazDBanimal();
				fruitListScrollPane = new JScrollPane(fruitList);
				checkBox2.setVisible(false);
				checkBox1.setVisible(false);
				checkBox4.setVisible(false);
				checkBox3.setVisible(false);
				btnDetailyvysetreni.setVisible(false);
				aktualizovat.setVisible(false);
				btnHladaj.setVisible(false);
				comboBoxHladanie.setVisible(false);
				hladanie.setVisible(false);
			}
		});
		
		//zobraz detaily liecby daneho zvieratka
		JButton vyhladanieZvieratka = new JButton("Detail liecby");
		vyhladanieZvieratka.setForeground(new Color(220, 20, 60));
		vyhladanieZvieratka.setBackground(new Color(255, 182, 193));
		vyhladanieZvieratka.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		vyhladanieZvieratka.setBounds(583, 90, 226, 23);
		frame.getContentPane().add(vyhladanieZvieratka);
		vyhladanieZvieratka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkBox2.setVisible(false);
				checkBox1.setVisible(false);
				checkBox4.setVisible(false);
				checkBox3.setVisible(false);
				btnDetailyvysetreni.setVisible(false);
				aktualizovat.setVisible(false);
				btnHladaj.setVisible(false);
				comboBoxHladanie.setVisible(false);
				hladanie.setVisible(false);
				int index = 0;
				while(true){
					if(listSelectionModel.isSelectedIndex(index)){
						break;
					}
					index++;
					if(index > 100){
						break;
					}
				}
				Object element = listModel.getElementAt(index);
				String pom_string = element.toString();
				int pom = 0;
				String pom_cislo = null;
				char pom_id = pom_string.charAt(0);
				while(pom_id != ' '){
					if (pom_cislo == null){
						pom_cislo = pom_id+"";
					}
					else{
						pom_cislo += pom_id;
					}
					pom++;
					pom_id = pom_string.charAt(pom);
				}
				int idElement = Integer.parseInt(pom_cislo);
				PGDBConnection.zobrazVysetrenia(idElement);
			}
		});
		
		JButton bodovyRebricek = new JButton("Zobrazenie bodov\u00E9ho rebr\u00ED\u010Dka");
		bodovyRebricek.setForeground(new Color(220, 20, 60));
		bodovyRebricek.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		bodovyRebricek.setBackground(new Color(255, 182, 193));
		bodovyRebricek.setBounds(583, 265, 226, 23);
		frame.getContentPane().add(bodovyRebricek);
		bodovyRebricek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkBox2.setVisible(false);
				checkBox1.setVisible(false);
				checkBox4.setVisible(false);
				checkBox3.setVisible(false);
				btnDetailyvysetreni.setVisible(false);
				aktualizovat.setVisible(false);
				btnHladaj.setVisible(false);
				comboBoxHladanie.setVisible(false);
				hladanie.setVisible(false);
				
				PGDBConnection.zobrazenieBodov();
				fruitListScrollPane = new JScrollPane(fruitList); 
				
			}
		});

		JButton pridanieZvieratka = new JButton("Pridanie nov\u00E9ho zvieratka");
		pridanieZvieratka.setBackground(new Color(255, 182, 193));
		pridanieZvieratka.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		pridanieZvieratka.setForeground(new Color(220, 20, 60));
		pridanieZvieratka.setBounds(583, 303, 226, 23);
		frame.getContentPane().add(pridanieZvieratka);
		pridanieZvieratka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PridanieZvieratka();
				checkBox2.setVisible(false);
				checkBox1.setVisible(false);
				checkBox4.setVisible(false);
				checkBox3.setVisible(false);
				btnDetailyvysetreni.setVisible(false);
				aktualizovat.setVisible(false);
				btnHladaj.setVisible(false);
				comboBoxHladanie.setVisible(false);
				hladanie.setVisible(false);
			}
		});
		
		btnDetailyvysetreni.setBounds(189, 388, 138, 23);
		frame.getContentPane().add(btnDetailyvysetreni);
		btnDetailyvysetreni.setVisible(false);
		btnDetailyvysetreni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			PGDBConnection.detailVysetreni(i);
			fruitListScrollPane = new JScrollPane(fruitList);
			checkBox2.setVisible(false);
			checkBox1.setVisible(false);
			checkBox4.setVisible(false);
			checkBox3.setVisible(false);
			aktualizovat.setVisible(false);
			aktualizovat.setVisible(false);
			btnHladaj.setVisible(false);
			comboBoxHladanie.setVisible(false);
			hladanie.setVisible(false);
				
			}
		});
	
		JButton zobrezanieVeterinarov = new JButton("Zobrazenie zoznamu veterin\u00E1rov");
		zobrezanieVeterinarov.setBackground(new Color(255, 182, 193));
		zobrezanieVeterinarov.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		zobrezanieVeterinarov.setForeground(new Color(220, 20, 60));
		zobrezanieVeterinarov.setBounds(583, 196, 226, 23);
		frame.getContentPane().add(zobrezanieVeterinarov);
		zobrezanieVeterinarov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PGDBConnection.zobrazVeterinarov();
				fruitListScrollPane = new JScrollPane(fruitList);
				checkBox2.setVisible(true);
				checkBox1.setVisible(true);
				checkBox4.setVisible(true);
				checkBox3.setVisible(true);
				btnDetailyvysetreni.setVisible(true);
				aktualizovat.setVisible(false);
				btnHladaj.setVisible(false);
				comboBoxHladanie.setVisible(false);
				hladanie.setVisible(false);
			}
		});
		
		JLabel lblMenu = new JLabel("Menu:");
		lblMenu.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblMenu.setForeground(new Color(220, 20, 60));
		lblMenu.setBounds(655, 16, 76, 29);
		frame.getContentPane().add(lblMenu);
		
		JLabel lblVNaomMenu = new JLabel("V na\u0161om menu si \u010Falej vyberte, \u010Do by ste chceli vykona\u0165.");
		lblVNaomMenu.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblVNaomMenu.setForeground(new Color(220, 20, 60));
		lblVNaomMenu.setBounds(80, 56, 384, 43);
		frame.getContentPane().add(lblVNaomMenu);
		
		JLabel lblVitajteVAplikcii = new JLabel("Vitajte v aplik\u00E1cii ur\u010Denej pre evidenciu n\u00E1\u0161ho \u00FAtulku! ");
		lblVitajteVAplikcii.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblVitajteVAplikcii.setForeground(new Color(220, 20, 60));
		lblVitajteVAplikcii.setBounds(26, 11, 472, 40);
		frame.getContentPane().add(lblVitajteVAplikcii);
		
		JButton btnPavilony = new JButton("Pavilony");
		btnPavilony.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btnPavilony.setForeground(new Color(220, 20, 60));
		btnPavilony.setBackground(Color.PINK);
		btnPavilony.setBounds(583, 337, 226, 23);
		frame.getContentPane().add(btnPavilony);
		btnPavilony.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PGDBConnection.zobrazPavilony();
				fruitListScrollPane = new JScrollPane(fruitList);
				checkBox2.setVisible(false);
				checkBox1.setVisible(false);
				checkBox4.setVisible(false);
				checkBox3.setVisible(false);
				aktualizovat.setVisible(false);
				aktualizovat.setVisible(false);
				btnHladaj.setVisible(false);
				comboBoxHladanie.setVisible(false);
				hladanie.setVisible(false);
				
			}
		});
	
		
		JButton btnVyhadvanie = new JButton("Vyhladavanie");
		btnVyhadvanie.setForeground(new Color(220, 20, 60));
		btnVyhadvanie.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		btnVyhadvanie.setBackground(Color.PINK);
		btnVyhadvanie.setBounds(583, 370, 226, 23);
		frame.getContentPane().add(btnVyhadvanie);
		btnVyhadvanie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listModel.clear();
				listModel.addElement(null);
				fruitList = new JList<Object>(HelloWorldGui.listModel);
				fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				fruitList.setSelectedIndex(0);
				fruitList.setVisibleRowCount(3); 
				fruitList.setVisible(true);
				fruitListScrollPane = new JScrollPane(fruitList);
				checkBox2.setVisible(false);
				checkBox1.setVisible(false);
				checkBox4.setVisible(false);
				checkBox3.setVisible(false);
				aktualizovat.setVisible(false);
				aktualizovat.setVisible(false);
				btnHladaj.setVisible(true);
				comboBoxHladanie.setVisible(true);
				hladanie.setVisible(true);
				
			}
		});
		
		
		PGDBConnection.zobrazDBanimal();
		fruitListScrollPane = new JScrollPane(fruitList);
		fruitListScrollPane.setBounds(64, 112, 400, 262);
		frame.getContentPane().add(fruitListScrollPane);
		

		listSelectionModel = fruitList.getSelectionModel();
		listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
		
		
	}
}
