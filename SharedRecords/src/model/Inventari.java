package model;

import java.util.ArrayList;
import java.util.List;

public class Inventari {
	private List <Inventario> inventari;

	public Inventari() {
		this.inventari = new ArrayList<Inventario>();
	}

	public List<Inventario> getInventari() {
		return inventari;
	}

	public void setInventari(List<Inventario> inventari) {
		this.inventari = inventari;
	}
	
	public void aggiungiTitolo(Titolo titolo, String nomeUtente) {
		for(Inventario i : inventari) {
			if(i.getUtente().equals(nomeUtente)) {
				i.aggiungiTitolo(titolo);
			}
		}
	}
	
	public void rimuoviTitoli(Titolo titolo, String nomeUtente) {
		for(Inventario i : inventari) {
			if(i.getUtente().equals(nomeUtente)) {
				i.rimuoviTitolo(titolo);
			}
		}
	}
	
	public List<Titolo> mostraTitoli(String nomeUtente){
		List<Titolo> result = new ArrayList<Titolo>();
		for(Inventario i : inventari) {
			if(i.getUtente().equals(nomeUtente)) {
				result = i.getTitoli().getTitoli();
			}
		}
		return result;
	}
}
