package tools;

import java.util.Comparator;
import datatypes.DtActividadDeportiva;
import datatypes.DtClase;
import datatypes.DtCuponera;
import datatypes.DtFecha;

public class FechaComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1,  Object o2) {
    	if ((o1 instanceof DtActividadDeportiva) && (o2 instanceof DtActividadDeportiva)) {
    		DtFecha fecha1 = ((DtActividadDeportiva)o1).getFechaRegistro();
    		DtFecha fecha2 = ((DtActividadDeportiva)o2).getFechaRegistro();
    		int min1 = fecha1.getMinutos() + (fecha1.getHoras() + (fecha1.getDia() + (fecha1.getMes() + 
    				(fecha1.getAnio()) * 12) * 31) * 24) * 60;
    		int min2 = fecha2.getMinutos() + (fecha2.getHoras() + (fecha2.getDia() + (fecha2.getMes() + 
    				(fecha2.getAnio()) * 12) * 31) * 24) * 60;
    		return min1 - min2;
    	}
    	if ((o1 instanceof DtClase) && (o2 instanceof DtClase)) {
    		DtFecha fecha1 = ((DtClase)o1).getFechaRegistro();
    		DtFecha fecha2 = ((DtClase)o2).getFechaRegistro();
    		int min1 = fecha1.getMinutos() + (fecha1.getHoras() + (fecha1.getDia() + (fecha1.getMes() + 
    				(fecha1.getAnio()) * 12) * 31) * 24) * 60;
    		int min2 = fecha2.getMinutos() + (fecha2.getHoras() + (fecha2.getDia() + (fecha2.getMes() + 
    				(fecha2.getAnio()) * 12) * 31) * 24) * 60;
    		return min1 - min2;
    	}
    	if ((o1 instanceof DtCuponera) && (o2 instanceof DtCuponera)) {
    		DtFecha fecha1 = ((DtCuponera)o1).getFechaAlta();
    		DtFecha fecha2 = ((DtCuponera)o2).getFechaAlta();
    		int min1 = fecha1.getMinutos() + (fecha1.getHoras() + (fecha1.getDia() + (fecha1.getMes() + 
    				(fecha1.getAnio()) * 12) * 31) * 24) * 60;
    		int min2 = fecha2.getMinutos() + (fecha2.getHoras() + (fecha2.getDia() + (fecha2.getMes() + 
    				(fecha2.getAnio()) * 12) * 31) * 24) * 60;
    		return min1 - min2;
    	}
        return o1.hashCode() - o2.hashCode();
    }
}