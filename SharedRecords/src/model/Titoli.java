package model;

import java.util.ArrayList;
import java.util.List;

public class Titoli {
	private List<Titolo> titoli;
	
	public Titoli() {
		this.titoli = new ArrayList<Titolo>();
	}

	public List<Titolo> getTitoli() {
		return titoli;
	}

	public void setTitoli(List<Titolo> titoli) {
		this.titoli = titoli;
	}
	
	public void aggiungiTitolo(Titolo titolo) {
		titoli.add(titolo);
	}
	
	public void rimuoviTitolo(Titolo titolo) {
		titoli.remove(titolo);
	}
}
