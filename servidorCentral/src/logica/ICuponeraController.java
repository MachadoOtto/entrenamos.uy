package logica;

import java.util.Set;

import datatypes.DtCuponera;
import datatypes.DtFecha;
import excepciones.InstitucionException;

public interface ICuponeraController {
	
	public int ingresarCuponera(String nombre, String descripcion, DtFecha inicio, DtFecha fin, int descuento, DtFecha alta);
	
	public Set<String> getNombreCuponeras();
	
	public void agregarActividadCuponera(String nombre, String instituto,String actividadDeportiva, int cantidadClases) throws InstitucionException;
	
	public DtCuponera seleccionarCuponera(String n);
}
