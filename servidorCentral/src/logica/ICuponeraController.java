package logica;

import java.util.Set;

import datatypes.DtCuponera;
import datatypes.DtFecha;
import excepciones.ActividadDeportivaException;
import excepciones.CuponeraRepetidaException;
import excepciones.FechaInvalidaException;
import excepciones.InstitucionException;
import excepciones.NoExisteCuponeraException;

public interface ICuponeraController {
	
	public int ingresarCuponera(String nombre, String descripcion, DtFecha inicio, DtFecha fin, 
			int descuento, DtFecha alta) throws CuponeraRepetidaException, FechaInvalidaException;
	
	public Set<String> getNombreCuponeras();
	
	public void agregarActividadCuponera(String nombre, String instituto,String actividadDeportiva, int cantidadClases) 
			throws InstitucionException, ActividadDeportivaException;
	
	public DtCuponera seleccionarCuponera(String n) throws NoExisteCuponeraException;
}
