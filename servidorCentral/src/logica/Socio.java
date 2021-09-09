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

	//Guille: ...
	public boolean esSocio() {
		return true;
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
    	Map<String,Set<String>> x = new HashMap<>();
    	for(ReciboClase rc: reciboClases) {
    		String z = rc.getClase().getAD().getNombre();
    		if(!x.containsKey(z)) {
    			Set<String> y = new HashSet<>();
    			x.put(z,y);
    			for(ReciboClase rc2: reciboClases) {
    				if(rc2.getClase().getAD().getNombre().equals(z))
    					y.add(rc2.getClase().getNombre());
    			}
    		}
    	}
    	DtSocioExt datosExt = new DtSocioExt(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(), this.getContrasenia(), this.getFecha(), x, this.getImagen());
    	return datosExt;
    }
	
	public List<ReciboCuponera> getReciboCuponera() {
		return reciboCuponeras;
	}
	
	public List<ReciboClase> getReciboClase() {
		return reciboClases;
	}
	
	public void inscribirSocio(ActividadDeportiva actDep, Clase cl, TReg t, DtFecha reg) throws NoExisteCuponeraException, 
			ClaseException {
		boolean noEstaInsc = true;
		for (ReciboClase res: reciboClases) {
			if (res.getNombreClase() == cl.getNombre()) {
				noEstaInsc = false;
			}
		} 
		if (noEstaInsc) {
			if(t.equals(TReg.general)) {
				ReciboClase nuevoRecibo = new ReciboClase(reg, TReg.general, actDep.getCosto(), cl, this, null);
				reciboClases.add(nuevoRecibo);
				cl.addRecibo(nuevoRecibo);	
			} else {
				for (ReciboCuponera y: reciboCuponeras) {
					Cuponera cupActual = y.getCuponera();
					if (cupActual.tieneActividadDeportiva(actDep)) {
						int cantidadClases = 0;
						for (ReciboClase x: reciboClases) {
							if ((x.getCuponera() == cupActual) && (x.tieneActividadDeportiva(actDep))) {
								cantidadClases++;
							}
						}
						if (cantidadClases < cupActual.cantidadClases(actDep)) {
							ReciboClase nuevoRecibo = new ReciboClase(reg,TReg.cuponera,actDep.getCosto(),cl,this,cupActual);
							reciboClases.add(nuevoRecibo);
							cl.addRecibo(nuevoRecibo);
						}
					}
				}
				throw new NoExisteCuponeraException("Este Usuario no presenta Cuponeras validas para esta Clase.");
			}
		} else {
			throw new ClaseException("Este Usuario ya esta inscripto a esta Clase.");
		}
	}
}