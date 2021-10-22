/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

package logica;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;

import excepciones.NoExisteCuponeraException;
import excepciones.ClaseException;

import datatypes.DtFecha;
import datatypes.DtSocio;
import datatypes.DtSocioExt;
import datatypes.TReg;

public class Socio extends Usuario {
	
	private List<ReciboCuponera> reciboCuponeras;
	
	private List<ReciboClase> reciboClases;
	
	/* Constructor no usado
	public Socio(String nickname, String nombre, String apellido, String correo, DtFecha fecha) {
		super(nickname, nombre, apellido, correo, fecha);
		reciboCuponeras = new LinkedList<>();
		reciboClases = new LinkedList<>();
	} */
	
	public Socio(DtSocio datos) {
		super(datos.getNickname(), datos.getNombre(), datos.getApellido(), datos.getEmail(), datos.getContrasenia(), datos.getFechaNacimiento(), datos.getImagen());
		reciboCuponeras = new LinkedList<>();
		reciboClases = new LinkedList<>();
	}
	
	public void addReciboCuponera(ReciboCuponera rCup) {
		reciboCuponeras.add(rCup);
	}
	
	public void addReciboClase(ReciboClase rCl) {
		reciboClases.add(rCl);
	}
	
	public DtSocio getDt() {
		DtSocio datos = new DtSocio(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(), this.getContrasenia(), this.getFecha(), this.getImagen());
		return datos;
	}

	public DtSocioExt getDtExt() {
    	Map<String,Set<String>> clasesDeActividades = new HashMap<>();
    	for(ReciboClase reciboClase: reciboClases) {
    		String nombreClase = reciboClase.getClase().getAD().getNombre();
    		if(!clasesDeActividades.containsKey(nombreClase)) {
    			Set<String> nombreClases = new HashSet<>();
    			clasesDeActividades.put(nombreClase,nombreClases);
    			for(ReciboClase rc2: reciboClases) {
    				if(rc2.getClase().getAD().getNombre().equals(nombreClase))
    					nombreClases.add(rc2.getClase().getNombre());
    			}
    		}
    	}
    	Set<String> cupis = new HashSet<>();
    	for(ReciboCuponera recibo : reciboCuponeras) {
    		cupis.add(recibo.getCuponera().getNombre());
    	}
    	DtSocioExt datosExt = new DtSocioExt(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(), this.getContrasenia(), this.getFecha(), clasesDeActividades, this.getImagen(),this.getSeguidos().keySet(),this.getSeguidores().keySet(),cupis);
    	return datosExt;
    }
	
	public List<ReciboCuponera> getReciboCuponera() {
		return reciboCuponeras;
	}
	
	public List<ReciboClase> getReciboClase() {
		return reciboClases;
	}
	
	public void inscribirSocio(ActividadDeportiva actDep, Clase clase, TReg tipoCuponera, DtFecha reg,Cuponera cupi) throws NoExisteCuponeraException, 
			ClaseException {
		boolean noEstaInsc = true;
		for (ReciboClase res: reciboClases) {
			if (res.getNombreClase() == clase.getNombre()) {
				noEstaInsc = false;
			}
		} 
		if (noEstaInsc) {
			if(tipoCuponera.equals(TReg.general)) {
				ReciboClase nuevoRecibo = new ReciboClase(reg, TReg.general, actDep.getCosto(), clase, this, null);
				reciboClases.add(nuevoRecibo);
				clase.addRecibo(nuevoRecibo);	
			} else {
				int iteradorMagico=0;
				for (ReciboClase reciboCl: reciboClases)
					if(reciboCl.esTipoCuponera() && reciboCl.getCuponera()==cupi)
						iteradorMagico++;
				if(iteradorMagico>=cupi.cantidadClases(actDep))
					throw new NoExisteCuponeraException("La cuponera seleccionada no es válida.");
				ReciboClase nuevoRecibo = new ReciboClase(reg,TReg.cuponera,actDep.getCosto(),clase,this,cupi);
				reciboClases.add(nuevoRecibo);
				clase.addRecibo(nuevoRecibo);
			}
		} else {
			throw new ClaseException("Este Usuario ya esta inscripto a esta Clase.");
		}
	}
}




