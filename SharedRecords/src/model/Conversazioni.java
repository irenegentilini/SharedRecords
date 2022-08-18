package model;

import java.util.ArrayList;
import java.util.List;

public class Conversazioni {
	
	private List<Conversazione> conversazioni;

	public Conversazioni() {
		this.conversazioni = new ArrayList<Conversazione>();
	}

	public List<Conversazione> getConversazioni() {
		return conversazioni;
	}

	public void setConversazioni(List<Conversazione> conversazioni) {
		this.conversazioni = conversazioni;
	}
	
}
