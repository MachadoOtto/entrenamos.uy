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
import java.util.Set;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Iterator;

public class Socio extends Usuario {
private List<ReciboCuponera> reciboCuponeras;
	
	private List<ReciboClase> reciboClases;
	
	public Socio() {
		super();
		reciboCuponeras = new LinkedList<>();
		reciboClases = new LinkedList<>();
	}
	
	public Socio(String nickname, String nombre, String apellido, String correo, DtFecha fecha) {
		super(nickname, nombre, apellido, correo, fecha);
		reciboCuponeras = new LinkedList<>();
		reciboClases = new LinkedList<>();
	}
	
	public Socio(DtSocio datos) {
		super(datos.getNickname(), datos.getNombre(), datos.getApellido(), datos.getCorreo(), datos.getFecha());
		reciboCuponeras = new LinkedList<>();
		reciboClases = new LinkedList<>();
	}
	
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
		DtSocio datos = new DtSocio(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(), 
				this.getFecha());
		return datos;
	}
	
    public DtSocioExt getDtExt() {
    	DtProfesor datos = this.getDt();
    	Set<String> clasesInscripto = new HashSet<>();
    	Iterator<ReciboClase> itRClases = reciboClases.iterator();
		while (itRClases.hasNext()) {
			clasesInscripto.add(itRClases.next().getNombreClase());
		}
    	DtSocioExt datosExt = new DtSocioExt(datos, clasesInscripto);
    	return datosExt;
    }
	
	public List<ReciboCuponera> getReciboCuponera() {
		return reciboCuponeras;
	}
	
	public List<ReciboClase> getReciboClase() {
		return reciboClases;
	}
	
	public int inscribirSocio(ActividadDeportiva actDep, Clase cl, TReg.General) {
		ReciboClase nuevoRecibo = new ReciboClase(TReg.General, actDep.getCosto());
		nuevoRecibo.setClase(cl);
		reciboClases.add(nuevoRecibo);
		return 1;
	}
	
	public int inscribirSocio(ActividadDeportiva actDep, Clase cl, TReg.Cuponera) {
		int numClasesCuponera = 0;
		Iterator<ReciboCuponera> itRCuponeras = reciboCuponeras.iterator();
		while (itRCuponeras.hasNext()) {
			numClasesCuponera += itRCuponeras.next().cantidadClases(actDep);
		}
		int numInscripto = 0;
		Iterator<ReciboClase> itRClases = reciboClases.iterator();
		while (itRClases.hasNext()) {
			ReciboClase rCl = itRClases.next();
			if (rCl.esTipoCuponera()) {
				numInscripto += rCl.tieneActividadDeportiva(actDep);
			}
		}
		if (numClasesCuponera > numInscripto) {
			ReciboClase nuevoRecibo = new ReciboClase(TReg.Cuponera, actDep.getCosto());
			nuevoRecibo.setClase(cl);
			reciboClases.add(nuevoRecibo);
			return 0;
		} else {
			return 1;
		}
	}
}