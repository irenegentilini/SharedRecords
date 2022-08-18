package model;

public class Utente{
	
	private String username;
	private String email;
	private boolean isBloccato;
	private Inventari inventari;
	private Stanze stanzeAmministrate;
	private Conversazioni conversazioni;

	public Utente(String username, String email) {
		super();
		this.username = username;
		this.email = email;
		this.isBloccato = false;
		this.stanzeAmministrate=new Stanze();
		this.conversazioni=new Conversazioni();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isBloccato() {
		return isBloccato;
	}

	public void setBloccato(boolean isBloccato) {
		this.isBloccato = isBloccato;
	}
	
	public Inventari getInventari() {
		return inventari;
	}

	public void setInventari(Inventari inventari) {
		this.inventari = inventari;
	}

	public Stanze getStanzeAmministrate() {
		return stanzeAmministrate;
	}

	public void setStanzeAmministrate(Stanze stanzeAmministrate) {
		this.stanzeAmministrate = stanzeAmministrate;
	}
	
	public Conversazioni getConversazioni() {
		return conversazioni;
	}

	public void setConversazioni(Conversazioni conversazioni) {
		this.conversazioni = conversazioni;
	}
	
	
	
	public void BloccaUtente() {
		this.isBloccato = true;
	}
	
	public void SbloccaUtente() {
		this.isBloccato = false;
	}
	
	public void rimuoviStanzaAmministrata(Stanza stanza)
	{
		stanzeAmministrate.rimuoviStanzaAmministrata(stanza);
	}
	
	public void aggiungiStanzaAmministrata(Stanza stanza)
	{
		stanzeAmministrate.aggiungiStanzaAmministrata(stanza);
	}
	
	
}
