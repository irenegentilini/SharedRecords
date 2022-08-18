package dao;

import java.util.ArrayList;
import java.util.List;

import model.Messaggio;

public class MessaggioDao {
	List<Messaggio> messaggi;
	
	public MessaggioDao() {
		messaggi = new ArrayList<Messaggio>();
	}
	
	public void aggiungiMessaggio(Messaggio m) {
		messaggi.add(m);
	}
}
