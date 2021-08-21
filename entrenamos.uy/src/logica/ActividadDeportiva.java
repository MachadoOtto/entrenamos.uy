package logica;

import datatypes.DtClase;
import datatypes.DtActividadDeportiva;
import datatypes.DtFecha;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ActividadDeportiva {
	
	private Map <String, Clase> clases;  // Nombre de clase y clase
	//private reciboCuponera 
	
	private String nombre;
	private String descripcion;
	private int duracionMinutos;
	private float costo;
	private DtFecha fechaRegistro;

	public ActividadDeportiva(String nom, String desc, int dur, float costo, DtFecha fec){
		
		this.nombre = nom;
		this.descripcion = desc;
		this.duracionMinutos = dur;
		this.costo = costo;
		this.fechaRegistro = fec;
		
		this.clases = new HashMap<>();
		
	}
	
	public int addClase(DtClase cl, Profesor p) {

		int res = 1;
		if (!clases.containsKey(cl.getNombre())) {
			Clase nueva.Clase(cl, p /*,this??????*/);
			res = 0;	
		}
		return res;
			
	}
	
	public DtClase getClaseDatos(String c) {
		
		Clase res = clases.get(c);
		return res.getDt();
	}
	
	public Set<String> getNombreClases(){
		
		return clases.keySet();		
	}
	
	public Set<DtClase> getDatosClases() {
		
		Set<DtClase> resultado = new HashSet<>();
		Collection<Clase> clascoll = clases.values();
		Iterator<Clase> c = clascoll.iterator();
		while (c.hasNext()) {
			Clase aux = c.next();
			resultado.add(aux.getDt());
		}
		return resultado;
	}
	
	public void modificarDatos(DtActividadDeportiva DatosAD) {
		
		this.nombre = DatosAD.getNombre();
		this.descripcion = DatosAD.getDescripcion();
		this.duracionMinutos = DatosAD.getDuracionMinutos();
		this.costo = DatosAD.getCosto();
		this.fechaRegistro = DatosAD.getFechaRegistro();
	}
	
	public Clase findClase(String c) {
		
		return clases.get(c);
	}
	
	public Set<String> getNombreActDep(String nombreCup){
		
		Cuponera cup;//FALTA TERMINAR
		return cup.getNombreActividadesDeportivas();
	}
}
