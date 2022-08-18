package model;

import java.util.ArrayList;
import java.util.List;

public class Prestiti {
	private List<Prestito> prestiti;

	public Prestiti() {
		this.prestiti=new ArrayList<Prestito>();
	}

	public List<Prestito> getPrestiti() {
		return prestiti;
	}

	public void setPrestiti(List<Prestito> prestiti) {
		this.prestiti = prestiti;
	}
	
	
}
