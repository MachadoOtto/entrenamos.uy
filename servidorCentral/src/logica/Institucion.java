/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

package logica;

import datatypes.DtActividadDeportiva;
import datatypes.DtClase;
import datatypes.DtClaseExt;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

public class Institucion {
    private String nombre;
    private String URL;
    private String descripcion;
    private Map<String, ActividadDeportiva> actsDeps;
    private Set<Profesor> profesores;

    // Guille: Te inventaste un datatype: Institucion(DtInstitucion dataIns) {
    public Institucion(String n, String u, String d) {
        this.nombre = n;
        this.URL = u;
        this.descripcion = d;
        this.actsDeps = new HashMap<>();
        this.profesores = new HashSet<>();
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getURL() {
        return this.URL;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Map<String, ActividadDeportiva> getActsDeps() {
        return this.actsDeps;
    }
    
    public Set<Profesor> getProfesores() {
    	return profesores;
    }
    
    public ActividadDeportiva findActividad(String actDepNombre) {
    	return actsDeps.get(actDepNombre);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setDescripcion(String desc) {
        this.descripcion = desc;
    }

    public void setActsDeps(Map<String, ActividadDeportiva> actsDeps) {
        this.actsDeps = actsDeps;
    }
    
    public void addProfesor(Profesor profe) {
    	profesores.add(profe);
    }
    
    public void nuevaActDep(DtActividadDeportiva datosAD) {
        ActividadDeportiva actDep = new ActividadDeportiva(datosAD);
        actsDeps.put(datosAD.getNombre(),actDep);
    }

    public void addActividadDeportiva(DtActividadDeportiva datosAD) {
        ActividadDeportiva actDep = new ActividadDeportiva(datosAD);
        if (!actsDeps.containsKey(datosAD.getNombre())) {
			actsDeps.put(datosAD.getNombre(), actDep);
		}
    }

    public Boolean existeActDep(String nombreActDep) {
        return actsDeps.containsKey(nombreActDep);
    }

    public Set<String> obtenerActDep() {
		return actsDeps.keySet();
    }

    public Set<String> obtenerNombreClasesActDep(String actDep) {
        ActividadDeportiva actDept = actsDeps.get(actDep);
        return actDept.getNombreClases();
    }

    public DtClaseExt obtenerDtClase(String actDep,String clase) {
        ActividadDeportiva actDept = actsDeps.get(actDep);
        return actDept.getClaseDatos(clase);
    }

    public Set<DtClase> obtenerDtClases(String actDep) {
        ActividadDeportiva actDept = actsDeps.get(actDep);
        return actDept.getDatosClases();
    }

    public ActividadDeportiva getActDep(String nombreActDep) {
        return actsDeps.get(nombreActDep);
    }

    public void addClase(String actDep,DtClase datosClase,Profesor pp){
        //ActividadDeportiva actDept = actsDeps.get(actDep);
        //int cod = actDept.addClase(datosClase,pp);
    }

    public Clase findClase(String actDep, String clase) {
        ActividadDeportiva actDept = actsDeps.get(actDep);
        return actDept.findClase(clase);
    }

    public Set<String> getMiTrabajo(Profesor profe) {
        Set<String> nombreActsDepsProfe = new HashSet<>();
        Collection<ActividadDeportiva> actDepCollection = actsDeps.values();
		Iterator<ActividadDeportiva> itActsDeps = actDepCollection.iterator();
        while (itActsDeps.hasNext()) {
            //ActividadDeportiva actDepAux = itActsDeps.next();
			//Set<DtClase> dtClasesActDep = actDepAux.getDatosClases();
            //Guille: wtf es esto Collection<DtClase> dtClaseCollection = dtClasesActDep.values();
            //Iterator<DtClase> itDtClase = dtClaseCollection.iterator();
            //while (itDtClase.hasNext()) {
            //    DtClase dtClaseAux = itDtClase.next();
            //    if (dtClaseAux.getNombreProfesor() == profe.getNombre()) {
            //        nombreActsDepsProfe.add(actDepAux.getNombre());
            //    }
           // }
        }
        return nombreActsDepsProfe;
    }
}
