/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

package logica;

import java.util.Set;

import datatypes.DtClase;
import datatypes.DtClaseExt;
import datatypes.DtFecha;
import datatypes.TReg;
import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.FechaInvalidaException;
import excepciones.InstitucionException;
import excepciones.NoExisteCuponeraException;
import excepciones.UsuarioNoExisteException;

public interface IDictadoClaseController {
	
	public Set<String> obtenerUsuarios();
	
	public Set<String> obtenerInstituciones();
	
	public Set<String> obtenerActividades(String ins) throws InstitucionException ;
	public Set<String> obtenerActividadesAprobadas(String ins) throws InstitucionException;
	
	public Set<String> obtenerProfesores(String ins) throws InstitucionException ;
	
	public Set<String> obtenerClases(String ins, String actDep) throws InstitucionException ;
	
	public DtClaseExt seleccionarClase(String  ins, String actDep, String clase) throws InstitucionException, ClaseException,
			ActividadDeportivaException;
	
	public DtClaseExt buscarClase(String nombreClase) throws ClaseException;
	
	public int ingresarDatosClase(String ins, String actDep, DtClase datos) throws  InstitucionException, FechaInvalidaException,
			ClaseException, UsuarioNoExisteException, ActividadDeportivaException;
	
	public void inscribirSocio(String ins, String actDep, String clase, String socio, TReg tipoRegistro, DtFecha fechaReg,String cuponera) 
			throws  ClaseException, FechaInvalidaException, NoExisteCuponeraException, InstitucionException, 
			UsuarioNoExisteException, ActividadDeportivaException;

	public Set<String> obtenerSocios();

	public Set<String> getCuponerasSocioClase(String nombreSocio,String nombreInst,String nombreAd,String nombreClase);
	
	public Set<String> getCuponerasDisponibles(String nombreSocio, String nombreInst, String nombreAd) 
			throws UsuarioNoExisteException, InstitucionException, ActividadDeportivaException;
	
	//public void modificarDatosClase(String ins,String actDep,DtClase datos);
}