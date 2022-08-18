package model;

import java.time.LocalDateTime;
import java.util.Date;

public class Prestito {
	private String dataRiconsegna;
	private LocalDateTime dataOraRichiesta;
	private boolean isPrestitoAttivo;
	private String utente;
	private int titolo;
	
	public Prestito(String dataRiconsegna, LocalDateTime dataOraRichiesta, String utente, int titolo) {
		this.dataRiconsegna = dataRiconsegna;
		this.dataOraRichiesta = dataOraRichiesta;
		this.isPrestitoAttivo = false;
		this.utente = utente;
		this.titolo = titolo;
	}

	public String getDataRiconsegna() {
		return dataRiconsegna;
	}

	public void setDataRiconsegna(String dataRiconsegna) {
		this.dataRiconsegna = dataRiconsegna;
	}

	public LocalDateTime getDataOraRichiesta() {
		return dataOraRichiesta;
	}

	public void setDataOraRichiesta(LocalDateTime dataOraRichiesta) {
		this.dataOraRichiesta = dataOraRichiesta;
	}

	public boolean isPrestitoAttivo() {
		return isPrestitoAttivo;
	}

	public void setPrestitoAttivo(boolean isPrestitoAttivo) {
		this.isPrestitoAttivo = isPrestitoAttivo;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public int getTitolo() {
		return titolo;
	}

	public void setTitolo(int titolo) {
		this.titolo = titolo;
	}
	
	public void accettaRichiesta() {
		setPrestitoAttivo(true);
	}
}
