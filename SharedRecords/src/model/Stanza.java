package model;

import java.util.ArrayList;
import java.util.List;

public class Stanza {
	private String nome;
	private String usernameAmministratore;
	List<String> tag;
	private Prestiti prestiti;
	private Inventari inventari;
	private Conversazione conversazione;
	
	public Stanza(String nome, String usernameAmministratore) {
		this.nome = nome;
		this.usernameAmministratore = usernameAmministratore;
		this.tag = new ArrayList<String>();
		this.prestiti = new Prestiti();
		this.inventari = new Inventari();
		this.conversazione = new Conversazione(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsernameAmministratore() {
		return usernameAmministratore;
	}

	public void setUsernameAmministratore(String usernameAmministratore) {
		this.usernameAmministratore = usernameAmministratore;
	}

	public List<String> getTag() {
		return tag;
	}

	public void setTag(List<String> tag) {
		this.tag = tag;
	}

	public Prestiti getPrestiti() {
		return prestiti;
	}

	public void setPrestiti(Prestiti prestiti) {
		this.prestiti = prestiti;
	}

	public Inventari getInventari() {
		return inventari;
	}

	public void setInventari(Inventari inventari) {
		this.inventari = inventari;
	}

	public Conversazione getConversazione() {
		return conversazione;
	}

	public void setConversazione(Conversazione conversazioni) {
		this.conversazione = conversazioni;
	}
	
	public void nominaAmministratore(String nome) {
		setUsernameAmministratore(nome);
	}
	
	public void rimuoviUtente(String nome) {
		for(Inventario i : inventari.getInventari()) {
			if(i.getUtente().equals(nome)) {
				inventari.getInventari().remove(i);
				return;
			}
		}
	}
	
	public void aggiungiUtente(Inventario inv) {
		inventari.getInventari().add(inv);
	}
	
	public List<Prestito> listaPrestiti(){
		return prestiti.getPrestiti();
	}
	
	public void modificatag(String vecchio, String nuovo) {
		for(String t : tag) {
			if(t.equals(vecchio)) {
				t = nuovo;
			}
		}
	}
	
	public void aggiungiTag(String nuovo) {
		tag.add(nuovo);
	}
	
	public void rimuoviInventario(Inventario i) {
		inventari.getInventari().remove(i);
	}
	
	public void aggiungiInventario(Inventario i) {
		inventari.getInventari().add(i);
	}
	
	public List<Titolo> mostraTitoli(){
		List<Titolo> result=new ArrayList<Titolo>();
		for(Inventario i : inventari.getInventari()) {
			result.addAll(i.getTitoli().getTitoli());
		}
		return result;
	}
	
	public void concludiPrestito(Prestito prestito) {
		prestiti.getPrestiti().remove(prestito);
	}
	
	public void rifiutaRichiesta(Prestito prestito) {
		concludiPrestito(prestito);
	}
	
	public List<Titolo> getTitoli(){
		List<Titolo> titoli= new ArrayList<Titolo>();
		for(Inventario i : getInventari().getInventari()) {
			titoli.addAll(i.getTitoli().getTitoli());
		}
		return titoli;
	}
	
	public List<Prestito> getRichieste(String utente){
		List<Prestito> result = new ArrayList<Prestito>();
		for(Inventario i : inventari.getInventari()) {
			if(i.getUtente().equals(utente)) {
				for(Titolo t : i.getTitoli().getTitoli()) {
					for(Prestito p : prestiti.getPrestiti()) {
						if(t.getId()==p.getTitolo() && !p.isPrestitoAttivo()) result.add(p);
					}
				}
				return result;
			}
		}
		return result;
	}
	
	public Titolo getTitolo(int id) {
		for(Inventario i : getInventari().getInventari()) {
			for(Titolo t : i.getTitoli().getTitoli()) {
				if(t.getId() == id) return t;
			}
		}
		return null;
	}
	
	public Inventario getInventario(String utente) {
		for(Inventario i : inventari.getInventari()) {
			if(i.getUtente().equals(utente)) return i;
		}
		return null;
	}
}

