package dao;

import java.util.ArrayList;
import java.util.List;

import model.Inventario;

public class InventarioDao {
	List<Inventario> inventari;
	
	public InventarioDao() {
		inventari = new ArrayList<Inventario>();
	}
	
	public void aggiungiInventario(Inventario i) {
		inventari.add(i);
	}
	
	public void eliminaInventario(Inventario i) {
		inventari.remove(i);
	}
}
