package model;

import java.util.ArrayList;
import java.util.List;

public class Stanze {
	
	private List<Stanza> stanze;

	public Stanze() {
		this.stanze = new ArrayList<Stanza>();
	}

	public List<Stanza> getStanze() {
		return stanze;
	}

	public void setStanze(List<Stanza> stanze) {
		this.stanze = stanze;
	}
	
	public void rimuoviStanzaAmministrata(Stanza stanza) {
		stanze.remove(stanza);
	}
	
	public void aggiungiStanzaAmministrata(Stanza stanza) {
		stanze.add(stanza);
	}
	
	public void eliminaStanza(Stanza stanza) {
		rimuoviStanzaAmministrata(stanza);
	}
}
