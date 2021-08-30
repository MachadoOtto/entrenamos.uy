package logica;

import datatypes.DtClaseExt;
import datatypes.DtClase;
import datatypes.DtActividadDeportiva;
import datatypes.DtActividadDeportivaExt;
import datatypes.DtFecha;
import excepciones.ClaseException;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActividadDeportiva {
	
	private Map <String, Clase> clases;  // Nombre de clase y clase
	private Map <String, ClasesCuponera> clCuponera;
	private String nombre;
	private String descripcion;
	private int duracionMinutos;
	private float costo;
	private DtFecha fechaRegistro;
	private Logger log;
	
	public ActividadDeportiva(String nom, String desc, int dur, float costo, DtFecha fec){
		nombre = nom;
		descripcion = desc;
		duracionMinutos = dur;
		this.costo = costo;
		fechaRegistro = new DtFecha(fec);
		crearHandler();
	}
	public ActividadDeportiva(DtActividadDeportiva x) {
		nombre=x.getNombre();
		descripcion=x.getDescripcion();
		duracionMinutos=x.getDuracionMinutos();
		costo=x.getCosto();
		fechaRegistro = new DtFecha(x.getFechaRegistro());
		crearHandler();
	}
	private void crearHandler() {
		clases = new HashMap<>();
		clCuponera= new HashMap<>();
		log = Logger.getLogger(HandlerInstitucion.class.getName());
		log.setLevel(Level.INFO);
		Handler handler = new ConsoleHandler();
		log.addHandler(handler);
	}
	
	public String getNombre() {
		return nombre;
	}
	public int addClasesCup(ClasesCuponera cp) {
		if(clCuponera.containsKey(cp.getNombreCuponera()))
			return 1;
		clCuponera.put(cp.getNombreCuponera(), cp);
		log.info("ActDep "+nombre+" event: "+" new cup added "+cp.getNombreCuponera());
		return 0;
	}
	
	public int addClase(DtClase cl, Profesor p) {
		if(clases.containsKey(cl.getNombre()))
			return 1;
		Clase x = new Clase(cl,p,this);
		clases.put(cl.getNombre(), x);
		p.addClase(x);
		log.info("ActDep "+nombre+" event: "+" new clase "+cl.getNombre());
		return 0;
	}

	public DtActividadDeportiva getDt(){
		DtActividadDeportiva x = new DtActividadDeportiva(nombre, descripcion, duracionMinutos, costo, fechaRegistro);
		return x;
	}
	
	public DtClaseExt getClaseDatos(String c) {
		return clases.get(c).getDt();
	}
	
	public Set<String> getNombreClases(){
		return clases.keySet();		
	}
	
	public Set<DtClase> getDatosClases() {
		Set<DtClase> resultado = new HashSet<>();
		for(Map.Entry<String, Clase> x: clases.entrySet())
			resultado.add(x.getValue().getDt());
		return resultado;
	}
	
	public void modificarDatos(DtActividadDeportiva datosAD) {	
		nombre = datosAD.getNombre();
		descripcion = datosAD.getDescripcion();
		duracionMinutos = datosAD.getDuracionMinutos();
		costo = datosAD.getCosto();
		fechaRegistro = datosAD.getFechaRegistro();
	}
	
	public Clase findClase(String c) throws ClaseException {	
		Clase res = clases.get(c);
		if (res == null) {
			throw new ClaseException("La Clase seleccionada no pertenece a esta Actividad Deportiva.");
		}
		return res;
	}

	public String getDescripcion() {return descripcion;}
	public int getDuracionMinutos() {return duracionMinutos;}
	public float getCosto() {return costo;}
	public DtFecha getFechaRegistro() {return fechaRegistro;}
	
	public boolean participaProfesor(Profesor profe) {
		for(Map.Entry<String, Clase> x: clases.entrySet())
			if(x.getValue().getProfesor()==profe)
				return true;
        return false;
	}
	public DtActividadDeportivaExt getDtExt() {
		Set<String> q = new HashSet<>(clases.keySet());
		Set<String> r = new HashSet<>(clCuponera.keySet());
		DtActividadDeportivaExt x = new DtActividadDeportivaExt(nombre, descripcion, duracionMinutos, costo, fechaRegistro,q,r);
		return x;
	}

}