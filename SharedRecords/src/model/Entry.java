package model;

import java.time.LocalDateTime;

public class Entry {
	
	private LocalDateTime dataOra;
	private String contenuto;
	
	public Entry(LocalDateTime dataOra, String contenuto) {
		super();
		this.dataOra = dataOra;
		this.contenuto = contenuto;
	}

	public LocalDateTime getDataOra() {
		return dataOra;
	}

	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}

	public String getContenuto() {
		return contenuto;
	}

	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}
	
}
