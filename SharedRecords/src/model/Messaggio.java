package model;

import java.time.LocalDateTime;

public class Messaggio {
	private String contenuto;
	private LocalDateTime DataOra;
	private String mittente;
	
	public Messaggio(String contenuto, LocalDateTime dataOra, String mittente) {
		this.contenuto = contenuto;
		DataOra = dataOra;
		this.mittente = mittente;
	}

	public String getContenuto() {
		return contenuto;
	}

	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}

	public String getDataOra() {
		return ""+DataOra.getHour()+":"+DataOra.getMinute();
	}

	public void setDataOra(LocalDateTime dataOra) {
		DataOra = dataOra;
	}

	public String getMittente() {
		return mittente;
	}

	public void setMittente(String mittente) {
		this.mittente = mittente;
	}
}
