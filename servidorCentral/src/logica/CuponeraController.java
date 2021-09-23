package logica;

import java.util.Set;

import datatypes.DtCuponera;
import datatypes.DtFecha;
import excepciones.ActividadDeportivaException;
import excepciones.CuponeraInmutableException;
import excepciones.CuponeraRepetidaException;
import excepciones.FechaInvalidaException;
import excepciones.InstitucionException;
import excepciones.NoExisteCuponeraException;

public class CuponeraController implements ICuponeraController {

	private static CuponeraController instance = null;

	private CuponeraController() {}
	
	public static CuponeraController getInstance(){
		if ( instance == null )
			instance = new CuponeraController();
		return instance;
	}
	
	public int ingresarCuponera(String nombre, String descripcion, DtFecha inicio, DtFecha fin, 
				int descuento, DtFecha alta) throws CuponeraRepetidaException, FechaInvalidaException {
		if (!alta.esMenor(inicio)) {
			throw new FechaInvalidaException("La fecha de alta debe ser anterior a la de inicio.");
		}
		if (!inicio.esMenor(fin)) {
			throw new FechaInvalidaException("La fecha de inicio debe ser anterior a la de finalizacion.");
		}
		return getHC().addCuponera(nombre, descripcion, inicio, fin, descuento, alta);
	}
	
	public Set<String> getNombreCuponeras(){
		return getHC().getNombreCuponeras();
	}
		
	public void agregarActividadCuponera(String nombreCuponera, String institucion, String actividadDeportiva, int cantidadClases) 
			throws InstitucionException, ActividadDeportivaException, CuponeraInmutableException{
		getHC().getCup(nombreCuponera).addActDep(getHI().findInstitucion(institucion).getActDep(actividadDeportiva),cantidadClases);
	}
		
	public DtCuponera seleccionarCuponera(String n) throws NoExisteCuponeraException {
		HandlerCuponera hu = HandlerCuponera.getInstance();
		Cuponera c = hu.getCup(n);
		if (c == null) {
			throw new NoExisteCuponeraException("La cuponera seleccionada no existe en el sistema.");
		}
		return c.getDt();
	}
	
	private HandlerInstitucion getHI() {
		return HandlerInstitucion.getInstance();
	}
	private HandlerCuponera getHC() {
		return HandlerCuponera.getInstance();
	}
	
	public Set<String> getNombreCuponerasSinRecibos(){
		
		 Set<String> ss = getHC().getNombreCuponeras();
			for(String x: ss ) {
				Boolean comprada = (getHC().getCup(x).getRc()) != null;
				if(comprada) {
					ss.remove(x);
				}	
			}
		return ss;
	}
}
