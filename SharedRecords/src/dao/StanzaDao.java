package dao;

import java.util.ArrayList;
import java.util.List;

import model.Inventario;
import model.Stanza;
import model.Utente;

public class StanzaDao {
	List<Stanza> stanze;
	
	public StanzaDao() {
		stanze = new ArrayList<Stanza>();
	}
	
	public void aggiungiStanza(Stanza s) {
		stanze.add(s);
	}
	
	public void eliminaStanza(Stanza s) {
		for(Stanza stanza : stanze) {
			if(stanza.equals(s)) stanze.remove(s);
		}
	}
	
	public void modificaStanza(Stanza s) {
		for(Stanza stanza : stanze) {
			if(stanza.getNome().equals(s.getNome())) {
				stanze.remove(stanza);
				stanze.add(s);
			}
		}
	}
	
	public Stanza getStanza(String nome) {
		for(Stanza stanza : stanze) {
			if(stanza.getNome().equals(nome)) {
				return stanza;
			}
		}
		return null;
	}
	
	public List<Stanza> getStanze(String tag) {
		List<Stanza> result = new ArrayList<Stanza>();
		for(Stanza stanza : stanze) {
			for(String t : stanza.getTag()) {
				if(t.equals(tag)) result.add(stanza);
			}
		}
		return result;
	}
	
	public List<Stanza> getStanze(){
		return stanze;
	}
	
	/*public boolean modificaInventarioInventario(String nome, Inventario inventario) {
		for(Stanza stanza : stanze) {
			if(stanza.getNome().equals(nome)) {
				for(Inventario i : stanza.getInventari().getInventari()) {
					if(i.getUtente().equals(inventario.getUtente())) {
						stanza.getInventari().getInventari().remove(i);
						stanza.getInventari().getInventari().remove(inventario);
					}
				}
			}
		}
		return false;
	}*/
	
}
