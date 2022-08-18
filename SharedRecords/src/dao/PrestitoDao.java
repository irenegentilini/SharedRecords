package dao;

import java.util.ArrayList;
import java.util.List;

import model.Prestito;
import model.Utente;

public class PrestitoDao {
	List<Prestito> prestiti;
	
	public PrestitoDao() {
		prestiti = new ArrayList<Prestito>();
	}
	
	public void aggiungiPrestito(Prestito p) {
		prestiti.add(p);
	}
	
	public boolean modificaPrestito(Prestito prestito) {
		for(Prestito p : prestiti) {
			if(prestito.getUtente().equals(p.getUtente()) && prestito.getTitolo()==p.getTitolo()) {
				prestiti.remove(p);
				prestiti.add(prestito);
				return true;
			}
		}
		return false;
	}
	
	public void eliminaPrestito(Prestito prestito) {
		for(Prestito p : prestiti) {
			if(prestito.equals(p)) {
				prestiti.remove(p);
			}
		}
	}
	
	public List<Prestito> getPrestiti(Utente utente){
		List<Prestito> result=new ArrayList<Prestito>();
		for(Prestito p : prestiti) {
			if(p.getUtente().equals(utente.getUsername())) {
				result.add(p);
			}
		}
		return result;
	}
	
	/*public List<Prestito> getPrestiti(Stanza stanza){
		
	}*/
}
