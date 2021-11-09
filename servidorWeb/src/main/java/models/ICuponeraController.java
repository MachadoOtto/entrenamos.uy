package models;

import java.util.Set;

import datatypes.DtCuponera;
import datatypes.DtFecha;
import excepciones.ActividadDeportivaException;
import excepciones.CuponeraInmutableException;
import excepciones.CuponeraRepetidaException;
import excepciones.FechaInvalidaException;
import excepciones.InstitucionException;
import excepciones.NoExisteCuponeraException;

public interface ICuponeraController {
	
	// WEB SERVICE DISABLED OPERATION
	//public int ingresarCuponera(String nombre,  String descripcion,  DtFecha inicio,  DtFecha fin,  
			//int descuento,  DtFecha alta, String imagen) throws CuponeraRepetidaException,  FechaInvalidaException;
	
	public Set<String> getNombreCuponeras();
	
	// WEB SERVICE DISABLED OPERATION
	//public void agregarActividadCuponera(String nombre,  String instituto,  String actividadDeportiva,  int cantidadClases) 
			//throws InstitucionException,  ActividadDeportivaException,  CuponeraInmutableException;
	
	public DtCuponera seleccionarCuponera(String nombre) throws NoExisteCuponeraException;
	
	public Set<String> getNombreCuponerasSinRecibos();
}
