package model;

import java.util.List;

public class Conversazione {

	private static int cont=1;
	private int id;
	private StoricoMessaggi storicoMessaggi;
	private String nomeStanza;
	
	public Conversazione(String nomeStanza) {
		this.id = cont;
		cont++;
		this.storicoMessaggi = new StoricoMessaggi();
		this.nomeStanza = nomeStanza;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StoricoMessaggi getStoricoMessaggi() {
		return storicoMessaggi;
	}

	public void setStoricoMessaggi(StoricoMessaggi storicoMessaggi) {
		this.storicoMessaggi = storicoMessaggi;
	}
	
	public void invioMessaggio(Messaggio m) {
		storicoMessaggi.aggiungiMessaggio(m);
	}
	
	public List<Messaggio> caricaConversazione(){
		return storicoMessaggi.getMessaggi();
	}
	
	public void setNomeStanza(String nomeStanza) {
		this.nomeStanza = nomeStanza;
	}
	
	public String getNomeStanza() {
		return this.nomeStanza;
	}
}
