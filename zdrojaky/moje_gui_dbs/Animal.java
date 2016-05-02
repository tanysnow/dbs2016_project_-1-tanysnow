package moje_gui_dbs;

public class Animal {
	
	int id;
	String meno; 
	String odKedyVUtulku;
	String pohlavie;
	String typ;
	String rasa;


	public Animal(int id, String meno, String odKedyVUtulku, String pohlavie, String typ, String rasa){
		//super();
		this.id = id;
		this.meno = meno;
		this.odKedyVUtulku = odKedyVUtulku;
		this.pohlavie = pohlavie;
		this.typ = typ; 
		this.rasa = rasa;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMeno() {
		return meno;
	}
	public void setMeno(String meno) {
		this.meno = meno;
	}
	public String getOdKedyVUtulku() {
		return odKedyVUtulku;
	}
	public void setOdKedyVUtulku(String odKedyVUtulku) {
		this.odKedyVUtulku = odKedyVUtulku;
	}
	public String getPohlavie() {
		return pohlavie;
	}
	public void setPohlavie(String pohlavie) {
		this.pohlavie = pohlavie;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public String getRasa() {
		return rasa;
	}
	public void setRasa(String rasa) {
		this.rasa = rasa;
	}


	public static Animal get(int row) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
