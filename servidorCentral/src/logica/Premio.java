package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Premio {
	private String description;
	private int cantidad;
	private Clase classroom;
	private boolean realizado = false;
	private Set<Socio> winners = null;
	
	Premio(Clase  classss ,  String  desc,  int  cant){
		description = desc;
		cantidad = cant;
		classroom = classss;
	}

	public int getCantidad() {
		return cantidad;
	}

	public String getDescription() {
		return description;
	}

	public Clase getClase() {
		return classroom;
	}

	public boolean isRealizado() {
		return realizado;
	}
	
	public Set<Socio> realizarSorteo(Set<Socio> inscriptos) {
		if (realizado)
			return winners;
		List<Socio> lista = new ArrayList<>();
		Set<Socio> winners = new HashSet<>();
		lista.addAll(inscriptos);
		Random rand = new Random();
		for (int i=0; i<cantidad && lista.size()>0; i++) {
			int pick = rand.nextInt(lista.size());
			winners.add(lista.get(pick));
			lista.remove(pick);
		}
		realizado = true;
		return winners;
	}

	public Set<Socio> getWinners() {
		return winners;
	}
}
