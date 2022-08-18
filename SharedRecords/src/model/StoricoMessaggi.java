package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoricoMessaggi {
	
	private List<Messaggio> messaggi;

	public StoricoMessaggi() {
		messaggi = new ArrayList<Messaggio>();
	}

	public List<Messaggio> getMessaggi() {
		return messaggi;
	}

	public void setMessaggi(List<Messaggio> messaggi) {
		this.messaggi = messaggi;
	}
	
	public void aggiungiMessaggio(Messaggio m) {
		messaggi.add(m);
	}
}
