package dao;

import java.util.ArrayList;
import java.util.List;

import model.Utente;

public class UtenteDao {
	List<Utente> utenti;
	
	public UtenteDao() {
		utenti= new ArrayList<Utente>();
	}
	
	public List<Utente> getUtenti() {
		return utenti;
	}
	
	public void aggiungiUtente(Utente u) {
		utenti.add(u);
	}
	
	public boolean verificaDatiUtente(String username){
		for(Utente u : utenti) {
			if(u.getUsername().equals(username)) return true;
		}
		return false;
	}
	
	public boolean modificaUtente(Utente utente) {
		for(Utente u : utenti) {
			if(u.getUsername().equals(utente.getUsername())) {
				utenti.remove(u);
				utenti.add(utente);
				return true;
			}
		}
		return false;
	}
}
