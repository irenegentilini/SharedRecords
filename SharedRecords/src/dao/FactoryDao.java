package dao;

import dao.*;

public class FactoryDao {
	private UtenteDao utenti;
	private TitoloDao titoli;
	private StanzaDao stanze;
	private PrestitoDao prestiti;
	private MessaggioDao messaggi;
	private InventarioDao inventari;
	private ConversazioneDao conversazioni;
	
	public UtenteDao getUtentiDao() {
		if(utenti == null) utenti = new UtenteDao();
		return utenti;
	}
	
	public TitoloDao getTitoliDao() {
		if(titoli == null) titoli = new TitoloDao();
		return titoli;
	}
	
	public StanzaDao getStanzeDao() {
		if(stanze == null) stanze = new StanzaDao();
		return stanze;
	}
	
	public PrestitoDao getPrestiDao() {
		if(prestiti == null) prestiti = new PrestitoDao();
		return prestiti;
	}
	
	public MessaggioDao getMessaggioDao() {
		if(messaggi == null) messaggi = new MessaggioDao();
		return messaggi;
	}
	
	public InventarioDao getInventarioDao() {
		if(inventari == null) inventari = new InventarioDao();
		return inventari;
	}
	
	public ConversazioneDao getConversazioneDao() {
		if(conversazioni == null) conversazioni = new ConversazioneDao();
		return conversazioni;
	}
}

