package logica;

import datatypes.DtClase;
import datatypes.DtActividadDeportiva;
import datatypes.DtActividadDeportivaExt;
import datatypes.DtFecha;
import datatypes.TEstado;
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
	
	private Map<String,  Clase> clases;  // Nombre de clase y clase
	private Map<String,  ClasesCuponera> clCuponera;
	private Map<String,  Categoria> cats;
	private String nombre;
	private String descripcion;
	private int duracionMinutos;
	private float costo;
	private DtFecha fechaRegistro;
	private Logger log;
	private TEstado estado;
	private Profesor creador;
	private String imgName;
	
	public ActividadDeportiva(DtActividadDeportiva datosActDep,  Map<String,  Categoria> cat,  Profesor profe) {
		nombre=datosActDep.getNombre();
		descripcion=datosActDep.getDescripcion();
		duracionMinutos=datosActDep.getDuracionMinutos();
		costo=datosActDep.getCosto();
		fechaRegistro = new DtFecha(datosActDep.getFechaRegistro());
		cats = cat;
		creador = profe;
		imgName = datosActDep.getImgName();
		estado = datosActDep.getEstado(); 
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
	public int addClasesCup(ClasesCuponera claseCup) {
		if (clCuponera.containsKey(claseCup.getNombreCuponera()))
			return 1;
		clCuponera.put(claseCup.getNombreCuponera(),  claseCup);
		log.info("ActDep "+nombre+" event: "+" new cup added "+claseCup.getNombreCuponera());
		return 0;
	}
	
	public int addClase(DtClase clase,  Profesor profe) {
		if (clases.containsKey(clase.getNombre()))
			return 1;
		Clase aula = new Clase(clase,  profe,  this);
		clases.put(clase.getNombre(),  aula);
		profe.addClase(aula);
		log.info("ActDep "+nombre+" event: "+" new clase "+clase.getNombre());
		return 0;
	}

	public Set<String> getNombreClases(){
		return clases.keySet();		
	}
		
	public Clase findClase(String clase) throws ClaseException {
		Clase res = clases.get(clase);
		if (res == null) {
			throw new ClaseException("La Clase seleccionada no pertenece a esta Actividad Deportiva.");
		}
		return res;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public int getDuracionMinutos() {
		return duracionMinutos;
	}
	public float getCosto() {
		return costo;
	}
	public DtFecha getFechaRegistro() {
		return fechaRegistro;
	}
	
	public boolean participaProfesor(Profesor profe) {
		for (Map.Entry<String,  Clase> x: clases.entrySet())
			if (x.getValue().getProfesor()==profe)
				return true;
        return false;
	}
	public DtActividadDeportivaExt getDtExt() {
		Set<String> nombresClases = new HashSet<>(clases.keySet());
		Set<String> nombresClasesCuponeras = new HashSet<>(clCuponera.keySet());
		DtActividadDeportivaExt actDep = new DtActividadDeportivaExt(getNombre(),  getDescripcion(),  getDuracionMinutos(),  
				getCosto(),  getFechaRegistro() ,  cats.keySet(),  nombresClases,  nombresClasesCuponeras,  estado,  creador.getNickname(),  imgName);
		return actDep;
	}
	public TEstado getEstado() {
		return estado;
	}
	public boolean setEstado(TEstado nuevoEstado) {
		if (estado==TEstado.ingresada && (nuevoEstado==TEstado.aceptada || nuevoEstado==TEstado.rechazada)) {
			estado = nuevoEstado;
			return true;
		}
		else return false;
	}
	public Set<Categoria> getCategorias() {
		Set<Categoria> res = new HashSet<>();
		res.addAll(cats.values());
		return res;
	}
	public Map<String,  ClasesCuponera> getClaseCuponeras() {
		return clCuponera;
	}
}


/* Migue Watafa: Esto no se usa nunca!!
public void modificarDatos(DtActividadDeportiva datosAD) {	
	nombre = datosAD.getNombre();
	descripcion = datosAD.getDescripcion();
	duracionMinutos = datosAD.getDuracionMinutos();
	costo = datosAD.getCosto();
	fechaRegistro = datosAD.getFechaRegistro();
} */

/* FUNCIONES LEGACY (NO SE UTILIZAN)
public DtActividadDeportiva getDt(){
	Set<String> c = new HashSet<String>(cats.keySet());
	DtActividadDeportiva x = new DtActividadDeportiva(nombre,  descripcion,  duracionMinutos,  costo,  fechaRegistro, c, estado, creador.getNickname());
	return x;
}

public DtClaseExt getClaseDatos(String c) {
	return clases.get(c).getDt();
}
public Profesor getCreador() {
	return creador;
}

public Set<DtClase> getDatosClases() {
	Set<DtClase> resultado = new HashSet<>();
	for (Map.Entry<String,  Clase> x: clases.entrySet())
		resultado.add(x.getValue().getDt());
	return resultado;
}
*/