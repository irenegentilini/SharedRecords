package model;

import java.util.Date;

public class Titolo {
	private static int cont=1;
	
	private int id;
	private String nome;
	private int statoDintegrita;
	private int contatorePrestiti;
	private int contatoreVoti;
	private enum StatoDisponibilitaEnum {DISPONIBILE, NONDISPONIBILE, INPRESTITO, RICHIESTO};
	private String dataRiconsegna;
	private StatoDisponibilitaEnum statoDisponibilita;
	
	public Titolo(String nome, String date) {
		this.id = cont;
		cont++;
		this.nome = nome;
		this.statoDintegrita = -1;
		this.contatorePrestiti = 0;
		this.contatoreVoti = 0;
		this.statoDisponibilita=StatoDisponibilitaEnum.DISPONIBILE;
		this.dataRiconsegna = date;
	}

	public String getStatoDisponibilita() {
		return statoDisponibilita.toString();
	}

	public void setStatoDisponibilita(String statoDisponibilita) {
		this.statoDisponibilita = StatoDisponibilitaEnum.valueOf(statoDisponibilita);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getStatoDintegrita() {
		return statoDintegrita;
	}

	public void setStatoDintegrita(int statoDintegrita) {
		this.statoDintegrita = statoDintegrita;
	}

	public int getContatorePrestiti() {
		return contatorePrestiti;
	}

	public void setContatorePrestiti(int contatorePrestiti) {
		this.contatorePrestiti = contatorePrestiti;
	}

	public int getContatoreVoti() {
		return contatoreVoti;
	}

	public void setContatoreVoti(int contatoreVoti) {
		this.contatoreVoti = contatoreVoti;
	}
	
	public String getDataRiconsegna() {
		return dataRiconsegna;
	}

	public void setDataRiconsegna(String dataRiconsegna) {
		this.dataRiconsegna = dataRiconsegna;
	}

	public void setStatoDisponibilita(StatoDisponibilitaEnum statoDisponibilita) {
		this.statoDisponibilita = statoDisponibilita;
	}

	public void incrementaPrestiti() {
		contatorePrestiti++;
	}
	
	public void modificaStatoDisponibilita(String s) {
		setStatoDisponibilita(s);
	}
	
	public int calcolaStatoIntegrita(int voto) {
		int temp=statoDintegrita*contatoreVoti+voto;
		contatoreVoti++;
		statoDintegrita = temp/contatoreVoti;
		return statoDintegrita;
	}
	
	public void modificaDataRiconsegna(String date) {
		setDataRiconsegna(date);
	}
	
}
