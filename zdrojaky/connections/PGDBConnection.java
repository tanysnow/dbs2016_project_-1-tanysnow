package connections;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import moje_gui_dbs.Animal;
import moje_gui_dbs.ChybovaHlaska;
import moje_gui_dbs.HelloWorldGui;

public class PGDBConnection {
	
	public final static String DRIVER_CLASS		= "org.postgresql.Driver";
	
	private final static String USER 			= "postgres";
	private final static String PASS 			= "Amanda003";
	private final static String URL				= "localhost/";
	private final static String DB				= "myDB";
	private final static String PREFIX			= "jdbc:postgresql://";
	
	private static Connection conn;
	private static Statement  state;
	
	public static String user;
	public static String url;
	public static String db;
	public static String pass;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new PGDBConnection();
					new HelloWorldGui();
					new ElasticSearch();
					//new PGDBConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public PGDBConnection(){
		this(URL, DB, USER, PASS);
	}
	
	public PGDBConnection(String url, String db, String user, String pass){
		this.user 	= user;
		this.pass 	= pass;
		this.url 	= url;
		this.db 	= db;
		
		conn  = getConnection();
		state = getStatement(conn, 1);
		
		
		//showDatabaseSize("myDB");
		
	}
	
	//x znaci, co chcem s danou db robit
	//x == 1 update
	private static Statement getStatement(Connection connection, int x){
		if(connection == null){
			System.out.println("Connection je NULL");
			return null;
		}
			
		try {
			if (x == 1){
				return connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
						ResultSet.CONCUR_UPDATABLE);
			}
			else{
				return connection.createStatement();
			}
		} catch (SQLException e) {
			System.out.println("Nepodarilo sa ziskat Statement" + e);
		}
		return null;
	}
	
	private static Connection getConnection(){
		try {
			Class.forName(DRIVER_CLASS);
			return DriverManager.getConnection(PREFIX + url + db, user, pass);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Nepodarilo sa ziskat Connection" + e);
		}
		return null;
	}
	
	public static Statement vratState(int x){
		conn  = getConnection();
		state = getStatement(conn, x);
		return state;
	}
	
	protected final void showDatabaseSize(String databaseName){
		try {
			ResultSet rs = state.executeQuery("SELECT pg_size_pretty(pg_database_size('" + databaseName + "'))");
			while(rs.next())
				System.out.println(rs.getString(1));
		} catch (SQLException e) {
			System.out.println("Nepodarilo sa ziskat velkost db: " + databaseName + e);
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static void zobrazDBanimal(){
		String sql = "select * from animal a "
				+ "join rasa r on r.id = a.rasa_id ";
		
		try {
			ResultSet rs = state.executeQuery(sql);
			HelloWorldGui.listModel.clear();
			while(rs.next()){
				String rasa = rs.getString("nazov");
				//System.out.println(rasa);
				int id = rs.getInt("id");
				String meno = rs.getString("meno");
				String od_kedy_v_utulku = rs.getString("od_kedy_v_utulku");
				String pohlavie = rs.getString("pohlavie");
				String fk_rasa_id = rasa;
				String typ = rs.getString("typ");
				
				Animal animal = new Animal(id, meno, od_kedy_v_utulku, pohlavie, typ, fk_rasa_id);
				HelloWorldGui.listModel.addElement(animal.getId()+" "+animal.getMeno() +" "+animal.getOdKedyVUtulku()
				+" "+animal.getPohlavie()+" "+animal.getRasa()+" "+animal.getTyp());
				
				HelloWorldGui.fruitList = new JList<Object>(HelloWorldGui.listModel);
				HelloWorldGui.fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				HelloWorldGui.fruitList.setSelectedIndex(0);
				HelloWorldGui.fruitList.setVisibleRowCount(3); 
				HelloWorldGui.fruitList.setVisible(true);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void vymaz(int i){
		Savepoint savepoint1 = null;
		try {
			conn.setAutoCommit(false);
			String sql = "DELETE FROM animal WHERE id =" + i;
			String sql1 = "DELETE FROM vysetrenie WHERE animal_id =" + i;
			String sql2 = "DELETE FROM vencenie WHERE animal_id =" + i;
			String sql3 = "DELETE FROM animal_pavilon WHERE animal_id =" + i;
			savepoint1 = conn.setSavepoint("Savepoint1");
			state.executeUpdate(sql1);
			state.executeUpdate(sql2);
			state.executeUpdate(sql3);
			state.executeUpdate(sql);
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//
			e.printStackTrace();
			try {
				conn.rollback(savepoint1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				new ChybovaHlaska(2); 
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void zobrazVeterinarov(){
		String sql = "SELECT * FROM veterinar";
		try {
			ResultSet rs = state.executeQuery(sql);
			HelloWorldGui.listModel.clear();
			while(rs.next()){
				String meno = rs.getString("meno_veterinara");
				String priezvisko = rs.getString("priezvisko");
				HelloWorldGui.listModel.addElement(meno + " " + priezvisko);
				
				HelloWorldGui.fruitList = new JList<Object>(HelloWorldGui.listModel);
				HelloWorldGui.fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				HelloWorldGui.fruitList.setSelectedIndex(0);
				HelloWorldGui.fruitList.setVisibleRowCount(3); 
				HelloWorldGui.fruitList.setVisible(true);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static void detailVysetreni(int i){
		String sql = "select v.vysledok_vysetrenia, v.cena_vysetrenia, a.meno, t.meno_veterinara, t.id "
				+ "from vysetrenie v "
				+ "join animal a on a.id = v.animal_id join veterinar t on t.id = v.veterinar_id "
				+ "where veterinar_id =" + i;
	
		try {
			ResultSet rs = state.executeQuery(sql);
			HelloWorldGui.listModel.clear();
			while(rs.next()){
				String vysledokVysetrenia = rs.getString("vysledok_vysetrenia");
				String meno1 = rs.getString("meno");
				int cenaVysetrenia = rs.getInt("cena_vysetrenia");
				HelloWorldGui.listModel.addElement("Meno      Vysledok vysetrenia     Cena vysetrenia");
				HelloWorldGui.listModel.addElement(meno1 + "     " +vysledokVysetrenia + "     " + cenaVysetrenia);
				HelloWorldGui.fruitList = new JList<Object>(HelloWorldGui.listModel);
				HelloWorldGui.fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				HelloWorldGui.fruitList.setSelectedIndex(0);
				HelloWorldGui.fruitList.setVisibleRowCount(3); 
				HelloWorldGui.fruitList.setVisible(true);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			new ChybovaHlaska(3);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void zobrazVysetrenia(int i){
		String sql = "select v.vysledok_vysetrenia, l.popis "
				+ "from navrh_liecby n "
				+ "join vysetrenie v on v.id = n.vysetrenie_id "
				+ "join liecba l on l.id = n.liecba_id "
				+ "left join animal a on a.id = v.animal_id "
				+ "where animal_id =" + i;
	
		try {
			ResultSet rs = state.executeQuery(sql);
			HelloWorldGui.listModel.clear();
			while(rs.next()){
				String vysledokVysetrenia = rs.getString("vysledok_vysetrenia");
				String popis = rs.getString("popis");
				HelloWorldGui.listModel.addElement("Vysledok vysetrenia    		 popis");
				HelloWorldGui.listModel.addElement(vysledokVysetrenia + "     " + popis);
				HelloWorldGui.fruitList = new JList<Object>(HelloWorldGui.listModel);
				HelloWorldGui.fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				HelloWorldGui.fruitList.setSelectedIndex(0);
				HelloWorldGui.fruitList.setVisibleRowCount(3); 
				HelloWorldGui.fruitList.setVisible(true);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();

			new ChybovaHlaska(2);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void aktualizacia(){
		String sql = "SELECT * FROM person";
		try {
			ResultSet rs = state.executeQuery(sql);
			HelloWorldGui.listModel.clear();
			while(rs.next()){
				String meno = rs.getString("meno");
				String priezvisko = rs.getString("priezvisko");
				String cisloOp = rs.getString("cislo_op");
				String datumNarodenia = rs.getString("datum_narodenia");
				int bodyZaVencenie = rs.getInt("body_za_vencenie");
				HelloWorldGui.listModel.addElement(meno + " " + priezvisko+
						" "+ cisloOp +" "+ datumNarodenia + bodyZaVencenie);
				
				HelloWorldGui.fruitList = new JList<Object>(HelloWorldGui.listModel);
				HelloWorldGui.fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				HelloWorldGui.fruitList.setSelectedIndex(0);
				HelloWorldGui.fruitList.setVisibleRowCount(3); 
				HelloWorldGui.fruitList.setVisible(true);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			new ChybovaHlaska(1);
		}	
	}
	
	@SuppressWarnings("unchecked")
	public static void zobrazDetail(int i){
		String sql = "SELECT v.od_kedy, v.do_kedy, v.priebeh_vencenia, v.body_za_vencenie, p.meno, p.priezvisko "
				+ "FROM vencenie v "
				+ "join animal a on a.id = v.animal_id "
				+ "join person p on p.id = v.person_id "
				+ "where animal_id =" + i;
		try {
			ResultSet rs = state.executeQuery(sql);
			HelloWorldGui.listModel.clear();
			while(rs.next()){
				String od = rs.getString("od_kedy");
				String doKedy = rs.getString("do_kedy");
				String priebehVencenia = rs.getString("priebeh_vencenia");
				int body = rs.getInt("body_za_vencenie");
				String meno = rs.getString("meno");
				String priezvisko = rs.getString("priezvisko");
				
				
				HelloWorldGui.listModel.addElement(od+ "  " + doKedy +"  "+ priebehVencenia +"  "+ body +" "+meno +" "+priezvisko);
				
				HelloWorldGui.fruitList = new JList<Object>(HelloWorldGui.listModel);
				HelloWorldGui.fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				HelloWorldGui.fruitList.setSelectedIndex(0);
				HelloWorldGui.fruitList.setVisibleRowCount(3); 
				HelloWorldGui.fruitList.setVisible(true);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();

			new ChybovaHlaska(2);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static void zobrazenieBodov(){
		String sql = "select p.meno, sum(v.body_za_vencenie) from vencenie v "
				+ "join person p on p.id = v.person_id group by p.meno";
		try {
			ResultSet rs = state.executeQuery(sql);
			HelloWorldGui.listModel.clear();
			while(rs.next()){
				String meno = rs.getString("meno");
				int body = rs.getInt("sum");
				
				HelloWorldGui.listModel.addElement(meno+"  "+ body);
				
				HelloWorldGui.fruitList = new JList<Object>(HelloWorldGui.listModel);
				HelloWorldGui.fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				HelloWorldGui.fruitList.setSelectedIndex(0);
				HelloWorldGui.fruitList.setVisibleRowCount(3); 
				HelloWorldGui.fruitList.setVisible(true);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static void zobrazPavilony(){
		String sql = "select a.meno, p.nazov, t.nazov, t.popis from animal_pavilon ap "
				+ "join animal a on a.id = ap.animal_id "
				+ "join pavilon p on p.id = ap.pavilon_id "
				+ "left join typ_pavilonu t on t.id = p.typ_pavilonu_id";
		try {
			ResultSet rs = state.executeQuery(sql);
			HelloWorldGui.listModel.clear();
			while(rs.next()){
				String meno = rs.getString("meno");
				String nazov1 = rs.getString("nazov");
				String nazov2 = rs.getString("nazov");
				String popis = rs.getString("popis");
				
				HelloWorldGui.listModel.addElement(meno+"  "+ nazov1 + "  " + nazov2 + "   " + popis);
				HelloWorldGui.fruitList = new JList<Object>(HelloWorldGui.listModel);
				HelloWorldGui.fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				HelloWorldGui.fruitList.setSelectedIndex(0);
				HelloWorldGui.fruitList.setVisibleRowCount(3); 
				HelloWorldGui.fruitList.setVisible(true);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void aktualizacia2(String meno1, String meno, String priezvisko, String cisloOp,
			String datumNarodenia, int bodyZaVencenie) {
		// TODO Auto-generated method stub
		Savepoint savepoint1 = null;
		 try {
			conn.setAutoCommit(false);
			savepoint1 = conn.setSavepoint("Savepoint1");
			String sql = "UPDATE person " +
                   "SET meno ='" + meno +"', priezvisko ='"+ priezvisko+
                   "' , cislo_op ='"+ cisloOp +"' , datum_narodenia= '"+datumNarodenia+
                   "', body_za_vencenie ="+bodyZaVencenie+
                   "WHERE meno ='" + meno1+"'";
			state.executeUpdate(sql);
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback(savepoint1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	
	public static ResultSet celaDB(){
		String sql = "select a.meno, a.od_kedy_v_utulku, a.pohlavie, r.nazov, r.popis, r.typ, p.nazov "
				+ "from animal a "
				+ "join rasa r on a.rasa_id = r.id "
				+ "join animal_pavilon ap on ap.animal_id = a.id "
				+ "join pavilon p on ap.pavilon_id = p.id ";
		
		ResultSet rs = null;
		try {
			rs = state.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rs);
		return rs;
		
	}

}
