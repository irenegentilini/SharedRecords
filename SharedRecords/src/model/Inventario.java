package model;

public class Inventario {
	private String Utente;
	private Titoli titoli;
	
	public Inventario(String utente) {
		Utente = utente;
		titoli = new Titoli();
	}

	public String getUtente() {
		return Utente;
	}

	public void setUtente(String utente) {
		Utente = utente;
	}

	public Titoli getTitoli() {
		return titoli;
	}

	public void setTitoli(Titoli titoli) {
		this.titoli = titoli;
	}
	
	public void aggiungiTitolo(Titolo titolo) {
		titoli.aggiungiTitolo(titolo);
	}
	
	public void rimuoviTitolo(Titolo titolo) {
		titoli.rimuoviTitolo(titolo);
	}
}
