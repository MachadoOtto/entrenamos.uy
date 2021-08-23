package logica;

import java.util.Set;

import datatypes.DtCuponera;
import datatypes.DtFecha;

public class DeportivaController implements IDeportivaController {

	private static DeportivaController instance = null;

	private DeportivaController() {}
	
	public static DeportivaController getInstance(){
		if ( instance == null )
			instance = new DeportivaController();
		return instance;
	}
	
	public int ingresarCuponera(String nombre, String descripcion,
			DtFecha inicio, DtFecha fin, int descuento, DtFecha alta)
	{
		HandlerCuponera hu = HandlerCuponera.getInstance();
		int res = hu.addCuponera(nombre, descripcion, inicio, fin, descuento, 
				alta);
		return res;
	}
	
	public Set<String> getNombreCuponeras(){
		HandlerCuponera hu = HandlerCuponera.getInstance();
		Set<String> res = hu.getNombreCuponeras();
		return res;
	}
		
	public void agregarActividadCuponera(String nombreCuponera, 
			String institucion, String actividadDeportiva, int cantidadClases)
	{
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion i = hi.findInstitucion(institucion);
		HandlerCuponera hc = HandlerCuponera.getInstance();
		Cuponera c = hc.getCup(nombreCuponera);
		ActividadDeportiva ad = i.getActDep(actividadDeportiva);
		c.addActDep(ad,cantidadClases);
	}
		
	public int altaInstitucion(String nombre, String descripcion, String URL) {
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		if(!hi.existeInstitucion(nombre)){
			Institucion i = new Institucion(nombre,descripcion,URL);
			hi.addInstitucion(i);
			return 0;
		}
		return 1;
	}
	public DtCuponera seleccionarCuponera(String n) {
		HandlerCuponera hu = HandlerCuponera.getInstance();
		Cuponera c = hu.getCup(n);
		return c.getDt();
	}
	
}
