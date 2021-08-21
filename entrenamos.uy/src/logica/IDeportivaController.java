package logica;

import java.util.Set;

public interface IDeportivaController {
	public int ingresarCuponera(String nombre, String descripcion,
			DtFecha inicio, DtFecha fin, int descuento, DtFecha alta);
	
	public Set<String> getNombreCuponeras();
	
	public void agregarActividadCuponera(String nombre, String instituto,
			String actividadDeportiva, int cantidadClases);
	
	public boolean altaInstitucion(String nombre, String descripcion, String URL);
	
}
