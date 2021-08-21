package logica;

import java.util.HashSet;
import java.util.Set;

public class DeportivaController implements IDeportivaController {
	
	//Atributos
	
	private static DeportivaController instance = null;
	
	//Operaciones esenciales
	
	private DeportivaController() 
	{
		
	}
	
	public DeportivaController getInstance()
	{
		if ( instance == null )
		{
			instance = new DeportivaController();
		}
		return instance;
	}
	
	
	//Operaciones Casos de Uso
	
	public int ingresarCuponera(String nombre, String descripcion,
			DtFecha inicio, DtFecha fin, int descuento, DtFecha alta)
	{
		HandlerCuponera hu = HandlerCuponera.getInstance();
		int res = hu.addCuponera(nombre, descripcion, inicio, fin, descuento, 
				alta);
		return res;
	}
	
	public Set<String> getNombreCuponeras()
	{
		HandlerCuponera hu = HandlerCuponera.getInstance();
		Set<String> res = hu.getNombreCuponeras();
		return res;
	}
		
	public void agregarActividadCuponera(String nombreCuponera, 
			String institucion, String actividadDeportiva, int cantidadClases)
	{
		HandlerInstitucion hi = HandlerInstituciones.getInstance();
		Institucion i = hi.findInstitucion(institucion);
		HandlerCuponera hc = HandlerCuponera.getInstance();
		Cuponera c = hc.getCup(nombreCuponera);
		ActividadDeportiva ad = i.getActDep(actividadDeportiva);
		c.addActDep(ad,cantidadClases);
	}
		
	public boolean altaInstitucion(String nombre, String descripcion, 
			String URL)
	{
		boolean res = false;
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		res = hi.existeInstitucion(nombre);
		if( res == true )
		{
			Institucion i = new Institucion(nombre,descripcion,URL);
			hi.addInstitucion(i);
		}
		return res;
	}
		
	
}
