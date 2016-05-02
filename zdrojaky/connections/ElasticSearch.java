package connections;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import moje_gui_dbs.HelloWorldGui;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

public class ElasticSearch {
	
	private int port = 9300;
	private String host = "localhost";
	public static Client client = null;
	
	public ElasticSearch(){
		try {
			client = TransportClient.builder().build()
					.addTransportAddress(new InetSocketTransportAddress
							(InetAddress.getByName(host), port));
			System.out.println("Klient pripojeny...\n");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Klient sa nepripojil\n");
		}
		//inicializacia("animal", "1");
		
	}
	
	public void inicializacia(String type, String index){
		ResultSet rs = PGDBConnection.celaDB();
		XContentBuilder source = null;
		
		try {
			while(rs.next()){
				try {
					source = jsonBuilder().startObject()
							.field("menoAnimal", rs.getString("meno"))
							.field("odKedyUtulok", rs.getString("od_kedy_v_utulku"))
							.field("pohlavie", rs.getString("pohlavie"))
							.field("nazovRasy", rs.getString("nazov"))
							.field("popis", rs.getString("popis"))
							.field("typ", rs.getString("typ"))
							.endObject();
					
					client.prepareIndex(index, type).setSource(source).execute().actionGet();
					//odosleme Jsonovi
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Nenainicializoval jsona...\n");
				}
					
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Nie je rs vo while...\n");
		}
		
		
	}
	//type nazov tabulky a hladany vyraz je menoAnimal
	@SuppressWarnings("unchecked")
	public static SearchHit[] jednoducheVyhladavanie(String index, String type, 
			String hladanyVyraz, int vyraz){
		SearchResponse sr = null;
		if(vyraz == 1){
			sr = client.prepareSearch(index).
					setTypes(type).setSearchType(SearchType.QUERY_AND_FETCH)
					.setQuery(QueryBuilders.matchPhrasePrefixQuery("menoAnimal", hladanyVyraz))
					.setFrom(0).setSize(60).setExplain(true).execute().actionGet();
		}
		if(vyraz == 4){
			sr = client.prepareSearch(index).
					setTypes(type).setSearchType(SearchType.QUERY_AND_FETCH)
					.setQuery(QueryBuilders.matchPhrasePrefixQuery("odKedyUtulok", hladanyVyraz))
					.setFrom(0).setSize(60).setExplain(true).execute().actionGet();
		}
		if(vyraz == 5){
			sr = client.prepareSearch(index).
					setTypes(type).setSearchType(SearchType.QUERY_AND_FETCH)
					.setQuery(QueryBuilders.matchPhrasePrefixQuery("pohlavie", hladanyVyraz))
					.setFrom(0).setSize(60).setExplain(true).execute().actionGet();
		}
		if(vyraz == 2){
			sr = client.prepareSearch(index).
					setTypes(type).setSearchType(SearchType.QUERY_AND_FETCH)
					.setQuery(QueryBuilders.matchPhrasePrefixQuery("nazovRasy", hladanyVyraz))
					.setFrom(0).setSize(60).setExplain(true).execute().actionGet();
		}
		if(vyraz == 3){
			sr = client.prepareSearch(index).
					setTypes(type).setSearchType(SearchType.QUERY_AND_FETCH)
					.setQuery(QueryBuilders.matchPhrasePrefixQuery("typ", hladanyVyraz))
					.setFrom(0).setSize(60).setExplain(true).execute().actionGet();
		}
		
		
		//System.out.println(sr+"\n");
		SearchHit[] result = sr.getHits().getHits();

		HelloWorldGui.listModel.clear();
		for(SearchHit sh: result){
			//System.out.println("**********");
			Map<String, Object> rs = sh.getSource();
			Collection<Object> hodnoty = rs.values();
			String pom = "";
			for(Object s : hodnoty){
				//System.out.println(s);
				pom = pom + " " + s;
			}
			
			HelloWorldGui.listModel.addElement(pom);
			HelloWorldGui.fruitList = new JList<Object>(HelloWorldGui.listModel);
			HelloWorldGui.fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			HelloWorldGui.fruitList.setSelectedIndex(0);
			HelloWorldGui.fruitList.setVisibleRowCount(3); 
			HelloWorldGui.fruitList.setVisible(true);
			
			//System.out.println(rs + "\n id z elasticu: "+ sh.getId());
		}
		return result;
	}
	/*
	public SearchHit[] vyhladavanie(String menoAnimal, String odKedyUtulok){
		
	}*/
	


}
