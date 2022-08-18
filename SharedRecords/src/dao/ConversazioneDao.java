package dao;

import java.util.ArrayList;
import java.util.List;

import model.Conversazione;

public class ConversazioneDao {
	List<Conversazione> conversazioni;
	
	public ConversazioneDao() {
		conversazioni = new ArrayList<Conversazione>();
	}
	
	public void aggiungiConversazione(Conversazione c) {
		conversazioni.add(c);
	}
	
	public Conversazione getConversazione(int id) {
		for(Conversazione c : conversazioni) {
			if(c.getId()==id) return c;
		}
		return null;
	}
}
