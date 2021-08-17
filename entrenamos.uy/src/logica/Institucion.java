/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

 package logica

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

    Institucion(DtInstitucion dataIns) {
        this.nombre = dataIns.nombre;
        this.URL = dataIns.URL;
        this.descripcion = dataIns.descripcion;
        this.actsDeps = new HashMap<>();
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
    //Esto no creo que vaya en Institucion...
    public ActividadDeportiva nuevaActDep(DtActividadDeportiva datosAD) {
        return ActividadDeportiva(datosAD);        
    }

    public void addActividadDeportiva(DtActividadDeportiva datosAD) {
        ActividadDeportiva actDep = new ActividadDeportiva(datosAD);
        if (!existeActDep(datosAD.getNombre()) {
			actsDeps.put(datosAD.getNombre(), actDep);
		}
    }

    public bool existeActDep(String nombreActDep) {
        return actsDeps.containsKey(nombreActDep);
    }

    public Set<String> obtenerActDep() {
        Set<String actsDeps> nombreActsDeps = new HashSet<>();
		Collection<ActividadDeportiva> actDepCollection = actsDeps.values();
		Iterator<ActividadDeportiva> itActsDeps = actDepCollection.iterator();
		while (itActsDeps.hasNext()) {
			ActividadDeportiva actDepAux = itActsDeps.next();
			nombreActsDeps.add(actDepAux.getNombre());
		}
		return nombreActsDeps;
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
        return actDept.getDtClases();
    }

    public ActividadDeportiva getActDep(String nombreActDep) {
        return actsDeps.get(nombreActDep);
    }

    public void addClase(String actDep,DtClase datosClase,Profesor pp){
        ActividadDeportiva actDept = actsDeps.get(actDep);
        int cod = actDept.addClase(datosClase,pp);
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
            ActividadDeportiva actDepAux = itActsDeps.next();
			Set<DtClase> dtClasesActDep = actDepAux.getDatosClases();
            Collection<DtClase> dtClaseCollection = dtClasesActDep.values();
            Iterator<DtClase> itDtClase = dtClaseCollection.iterator();
            while (itDtClase.hasNext()) {
                DtClase dtClaseAux = itDtClase.next();
                if (dtClaseAux.nombreProfesor == profe.getNombre()) {
                    nombreActDepsProfe.add(actDepAux.getNombre());
                }
            }
        }
        return nombreActDepsProfe;
    }
}
