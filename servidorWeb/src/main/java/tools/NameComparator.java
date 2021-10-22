package tools;

import java.util.Comparator;
import datatypes.DtActividadDeportiva;
import datatypes.DtClase;
import datatypes.DtCuponera;
import datatypes.DtUsuario;

public class NameComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1,  Object o2) {
    	if ((o1 instanceof DtActividadDeportiva) && (o2 instanceof DtActividadDeportiva)) {
    		return ((DtActividadDeportiva)o1).getNombre().compareTo(((DtActividadDeportiva)o2).getNombre());
    	}
    	if ((o1 instanceof DtClase) && (o2 instanceof DtClase)) {
    		return ((DtClase)o1).getNombre().compareTo(((DtClase)o2).getNombre());
    	}
    	if ((o1 instanceof DtCuponera) && (o2 instanceof DtCuponera)) {
    		return ((DtCuponera)o1).getNombre().compareTo(((DtCuponera)o2).getNombre());
    	}
    	if ((o1 instanceof DtUsuario) && (o2 instanceof DtUsuario)) {
    		return ((DtUsuario)o1).getNickname().compareTo(((DtUsuario)o2).getNickname());
    	}
        return o1.hashCode() - o2.hashCode();
    }
}