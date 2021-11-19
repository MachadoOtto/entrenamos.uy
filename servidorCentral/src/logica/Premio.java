package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import datatypes.DtFecha;
import datatypes.DtPremio;

public class Premio {
	private String description;
	private int cantidad;
	private Clase classroom;
	private boolean realizado = false;
	private Set<Socio> winners = new HashSet<>();
	private DtFecha fechaSorteo = new DtFecha(0,0,0,0,0,0);
	
	Premio(Clase  classss ,  String  desc,  int  cant){
		description = desc;
		cantidad = cant;
		classroom = classss;
	}
	
	Premio(Clase  classss ,  String  desc,  int  cant, boolean real, DtFecha fechaSorteo2){
		description = desc;
		cantidad = cant;
		classroom = classss;
		realizado = real;
		fechaSorteo = fechaSorteo2;
		
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
		lista.addAll(inscriptos);
		Random rand = new Random();
		for (int i=0; i<cantidad && lista.size()>0; i++) {
			int pick = rand.nextInt(lista.size());
			winners.add(lista.get(pick));
			lista.remove(pick);
		}
		fechaSorteo = new DtFecha();
		realizado = true;
		return winners;
	}
	
	public Set<Socio> getWinners() {
		return winners;
	}
	
	public DtPremio getDt() {
		List<String> nomwin = new ArrayList<>();
		for(Socio x: winners)
			nomwin.add(x.getNickname());
		return new DtPremio(description,cantidad,nomwin,fechaSorteo);
	}

	public DtFecha getFechaSorteo() {
		return fechaSorteo;
	}

	public void setFechaSorteo(DtFecha fechaSorteo) {
		this.fechaSorteo = fechaSorteo;
	}
	
	public void addWiner(Socio nombre) {
		winners.add(nombre);
	}
}
