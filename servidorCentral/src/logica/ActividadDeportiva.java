package logica;

import datatypes.DtClaseExt;
import datatypes.DtClase;
import datatypes.DtActividadDeportiva;
import datatypes.DtFecha;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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
		
		nombre = nom;
		descripcion = desc;
		duracionMinutos = dur;
		this.costo = costo;
		fechaRegistro = new DtFecha(fec);
		
		this.clases = new HashMap<>();
		
	}
	public ActividadDeportiva(DtActividadDeportiva x) {
		nombre=x.getNombre();
		descripcion=x.getDescripcion();
		duracionMinutos=x.getDuracionMinutos();
		costo=x.getCosto();
		fechaRegistro = new DtFecha(x.getFechaRegistro());
	}
	
	public String getNombre() {
		return nombre;
	}

	//public void setNombre(String nombre) {
	//	this.nombre = nombre;
	//}

	public int addClase(DtClase cl, Profesor p) {

		int res = 1;
		if (!clases.containsKey(cl.getNombre())) {
			//Lo dejo comentado para que reflexiones: Clase nueva.Clase(cl, p /*,this??????*/);
			Clase x = new Clase(cl,p,this);
			clases.put(cl.getNombre(), x);
			res = 0;	
		}
		return res;
			
	}
	
	public DtActividadDeportiva getDt(){
		DtActividadDeportiva x = new DtActividadDeportiva(nombre, descripcion, duracionMinutos, costo, fechaRegistro);
		return x;
	}
	
	public DtClaseExt getClaseDatos(String c) {
		
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
	
	public void modificarDatos(DtActividadDeportiva datosAD) {
		
		nombre = datosAD.getNombre();
		descripcion = datosAD.getDescripcion();
		duracionMinutos = datosAD.getDuracionMinutos();
		costo = datosAD.getCosto();
		fechaRegistro = datosAD.getFechaRegistro();
	}
	
	public Clase findClase(String c) {
		
		return clases.get(c);
	}
	
	public List<String> getNombreActDep(String nombreCup){
		//Guille: Despues vemos como se hace bien.
		//Cuponera cup;//FALTA TERMINAR
		//return cup.getNombresActDep();
		List<String> x = new ArrayList<>();
		return x;
	}

	public String getDescripcion() {
		return descripcion;
	}

	//public void setDescripcion(String descripcion) {
	//	this.descripcion = descripcion;
	//}

	public int getDuracionMinutos() {
		return duracionMinutos;
	}

	//public void setDuracionMinutos(int duracionMinutos) {
	//	this.duracionMinutos = duracionMinutos;
	//}

	public float getCosto() {
		return costo;
	}

	//public void setCosto(float costo) {
	//	this.costo = costo;
	//}

	public DtFecha getFechaRegistro() {
		return fechaRegistro;
	}

	//public void setFechaRegistro(DtFecha fechaRegistro) {
	//	this.fechaRegistro = fechaRegistro;
	//}
}
