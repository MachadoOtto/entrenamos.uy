package logica;

import java.util.Set;

import datatypes.DtCuponera;
import datatypes.DtFecha;

public interface IDeportivaController {
	public int ingresarCuponera(String nombre, String descripcion, DtFecha inicio, DtFecha fin, int descuento, DtFecha alta);
	public Set<String> getNombreCuponeras();
	public void agregarActividadCuponera(String nombre, String instituto,String actividadDeportiva, int cantidadClases);
	public int altaInstitucion(String nombre, String descripcion, String URL);
	public DtCuponera seleccionarCuponera(String n);
}
