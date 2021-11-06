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
import java.util.Map.Entry;
import java.util.Set;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;

import excepciones.NoExisteCuponeraException;
import excepciones.ClaseException;

import datatypes.DtFecha;
import datatypes.DtPremio;
import datatypes.DtSocio;
import datatypes.DtSocioExt;
import datatypes.TEstado;
import datatypes.TReg;

public class Socio extends Usuario {
	
	private List<ReciboCuponera> reciboCuponeras;
	private List<ReciboClase> reciboClases;
	private List<ActividadDeportiva> favoritos;
	
	private Map<String, Calificacion> calificaciones;
	private Map<String, Premio> premios;
	
	public Socio(DtSocio datos) {
		super(datos.getNickname(),   datos.getNombre(),   datos.getApellido(),   datos.getEmail(),   datos.getContrasenia(),   datos.getFechaNacimiento(),   datos.getImagen());
		reciboCuponeras = new LinkedList<>();
		reciboClases = new LinkedList<>();
		favoritos = new LinkedList<>();
		premios = new HashMap<>();
		calificaciones = new HashMap<>();
	}
	
	public void addReciboCuponera(ReciboCuponera rCup) {
		reciboCuponeras.add(rCup);
	}
	
	public void addReciboClase(ReciboClase rCl) {
		reciboClases.add(rCl);
	}
	
	public DtSocio getDt() {
		DtSocio datos = new DtSocio(this.getNickname(),   this.getNombre(),   this.getApellido(),   this.getCorreo(),   this.getContrasenia(),   this.getFecha(),   this.getImagen());
		return datos;
	}

	public DtSocioExt getDtExt() {
    	Map<String,  Set<String>> clasesDeActividadesAceptadas = new HashMap<>();
    	for (ReciboClase reciboClase: reciboClases) {
    		String nombreAD = reciboClase.getClase().getAD().getNombre();
    		if (!clasesDeActividadesAceptadas.containsKey(nombreAD)) {
    			Set<String> nombreClases = new HashSet<>();
    			clasesDeActividadesAceptadas.put(nombreAD,  nombreClases);
    			for (ReciboClase rc2: reciboClases) {
    				if (rc2.getClase().getAD().getNombre().equals(nombreAD) && rc2.getClase().getAD().getEstado().equals(TEstado.aceptada))
    					nombreClases.add(rc2.getClase().getNombre());
    			}
    		}
    	}
    	Map<String,  Set<String>> clasesDeActividadesFinalizadas = new HashMap<>();
    	for (ReciboClase reciboClase: reciboClases) {
    		String nombreAD = reciboClase.getClase().getAD().getNombre();
    		if (!clasesDeActividadesAceptadas.containsKey(nombreAD)) {
    			Set<String> nombreClases = new HashSet<>();
    			clasesDeActividadesAceptadas.put(nombreAD,  nombreClases);
    			for (ReciboClase rc2: reciboClases) {
    				if (rc2.getClase().getAD().getNombre().equals(nombreAD) && rc2.getClase().getAD().getEstado().equals(TEstado.finalizada))
    					nombreClases.add(rc2.getClase().getNombre());
    			}
    		}
    	}
    	Set<String> cupis = new HashSet<>();
    	for (ReciboCuponera recibo : reciboCuponeras) {
    		cupis.add(recibo.getCuponera().getNombre());
    	}
    	Set<String> favs = new HashSet<>();
    	for (ActividadDeportiva x : favoritos) {
    		favs.add(x.getNombre());
    	}
    	Map<String, DtPremio> prem = new HashMap<>();
    	for (Entry<String, Premio> x : premios.entrySet()) {
    		List<String> winwin = new LinkedList<>();
    		Set<Socio> winwinwin = new HashSet<>();
    		for (Socio y: winwinwin) {
    			winwin.add(y.getNickname());
    		}
    		prem.put(x.getKey(), new DtPremio(x.getValue().getDescription(), x.getValue().getCantidad(), winwin,x.getValue().getFechaSorteo()));
    	}
    	DtSocioExt datosExt = new DtSocioExt(this.getNickname(),   this.getNombre(),   this.getApellido(),   this.getCorreo(),
    			this.getContrasenia(),   this.getFecha(),   clasesDeActividadesAceptadas,   this.getImagen(),  this.getSeguidos().keySet(),
    			this.getSeguidores().keySet(), cupis, favs, prem,clasesDeActividadesFinalizadas);
    	return datosExt;
    }
	
	public List<ReciboCuponera> getReciboCuponera() {
		return reciboCuponeras;
	}
	
	public List<ReciboClase> getReciboClase() {
		return reciboClases;
	}
	
	public void inscribirSocio(ActividadDeportiva actDep,   Clase clase,   TReg tipoCuponera,   DtFecha reg,  Cuponera cupi) throws NoExisteCuponeraException,   
			ClaseException {
		boolean noEstaInsc = true;
		for (ReciboClase res: reciboClases) {
			if (res.getNombreClase() == clase.getNombre()) {
				noEstaInsc = false;
			}
		} 
		if (noEstaInsc) {
			if (tipoCuponera.equals(TReg.general)) {
				ReciboClase nuevoRecibo = new ReciboClase(reg,   TReg.general,   actDep.getCosto(),   clase,   this,   null);
				reciboClases.add(nuevoRecibo);
				clase.addRecibo(nuevoRecibo);	
			} else {
				int iteradorMagico=0;
				for (ReciboClase reciboCl: reciboClases)
					if (reciboCl.esTipoCuponera() && reciboCl.getCuponera()==cupi)
						iteradorMagico++;
				if (iteradorMagico>=cupi.cantidadClases(actDep))
					throw new NoExisteCuponeraException("La cuponera seleccionada no es válida.");
				ReciboClase nuevoRecibo = new ReciboClase(reg,  TReg.cuponera,  actDep.getCosto(),  clase,  this,  cupi);
				reciboClases.add(nuevoRecibo);
				clase.addRecibo(nuevoRecibo);
			}
		} else {
			throw new ClaseException("Este Usuario ya esta inscripto a esta Clase.");
		}
	}

	public Map<String, Calificacion> getCalificaciones() {
		return calificaciones;
	}

	public Map<String, Premio> getPremios() {
		return premios;
	}
	public void addPremio(Premio premiopremio) {
		premios.put(premiopremio.getClass().getName(), premiopremio);
	}
	public List<ActividadDeportiva> getFavoritos() {
		return favoritos;
	}

	public void changeFavoritos(ActividadDeportiva favfav) {
		if (favoritos.contains(favfav))
			favoritos.remove(favfav);
		else
			favoritos.add(favfav);
	}
	
	public void valorarProfesor(Clase clasee, int valor) {
		Calificacion calif = new Calificacion(clasee, this, valor);
		calificaciones.put(clasee.getProfesor().getNickname(), calif);
		clasee.addCalifiacion(getNickname(), calif);
	}
}



/* Constructor deprecado
public Socio(String nickname,   String nombre,   String apellido,   String correo,   DtFecha fecha) {
	super(nickname,   nombre,   apellido,   correo,   fecha);
	reciboCuponeras = new LinkedList<>();
	reciboClases = new LinkedList<>();
} */
