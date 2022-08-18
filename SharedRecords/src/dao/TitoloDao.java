package dao;

import java.util.ArrayList;
import java.util.List;

import model.Prestito;
import model.Titolo;

public class TitoloDao {
	List<Titolo> titoli;
	
	public TitoloDao() {
		titoli=new ArrayList<Titolo>();
	}
	
	public void aggiungiTitolo(Titolo t) {
		titoli.add(t);
	}
	
	public void eliminaTitolo(Titolo t) {
		for(Titolo titolo : titoli) {
			if(titolo.equals(t)) titoli.remove(titolo);
		}
	}
	
	public boolean modificaTitolo(Titolo t) {
		for(Titolo titolo : titoli) {
			if(titolo.equals(t)) {
				titoli.remove(titolo);
				titoli.add(t);
				return true;
			}
		}
		return false;
	}
}
