package models;

import java.util.Set;

import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.InstitucionException;
import datatypes.DtActividadDeportiva;
import datatypes.DtClaseExt;
import datatypes.DtActividadDeportivaExt;
import datatypes.DtInstitucion;

public interface IActividadDeportivaController {
	
	public Set<String> obtenerInstituciones(); 
	
	public Boolean ingresarDatosActividadDep(String nombreInsti,  DtActividadDeportiva datosAD) throws InstitucionException, 
			ActividadDeportivaException;
	
	public Set<String> obtenerActividades(String ins) throws InstitucionException ;
	
	public Set<String> obtenerDeltaInstituciones(String nombreCup,  String ins) throws InstitucionException ;
	
	// Migue: Esta funcion va a cambiar de controller,  me lo llevo a DictadoClaseController,  si no la usan en otro lado favor borrar
	public DtClaseExt seleccionarClase(String  ins,  String actDep,  String clase) throws InstitucionException, 
			ActividadDeportivaException,  ClaseException;
	
	public Set<String> obtenerSocios();
	
	public DtActividadDeportivaExt getActDepExt(String ins,  String actDep) throws InstitucionException,  
			ActividadDeportivaException;	
	
	// WEB SERVICE DISABLED OPERATION
	//public int altaInstitucion(String nombre,  String descripcion,  String URL);
	
	public DtInstitucion obtenerDatosInstitucion(String inst) throws InstitucionException;
	
	// WEB SERVICE DISABLED OPERATION
	// public void aprobarActividad(String actividadDeportiva,  TEstado estado);
	
	public void finalizarActividad(String actividadDeportiva);
	
	public Set<String> obtenerActDepIngresadas();
	
	//// WEB SERVICE DISABLED OPERATION
	//public void ingresarCatergoria(DtCategoria datos) throws CategoriaException;

	public Set<String> obtenerCategorias();

	public DtActividadDeportivaExt buscarActDep(String nombreActDep) throws ActividadDeportivaException;
}