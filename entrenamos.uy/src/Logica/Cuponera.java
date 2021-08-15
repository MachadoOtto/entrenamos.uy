package Logica;

import java.util.HashSet;
import java.util.Set;

public class Cuponera {
	
	//Atributos
	
	String nombre;
	String descripcion;
	DtFecha fechaInicio;
	DtFecha fechaFin;
	int descuento;
	
	List<ClasesCuponera> clasesCuponeras;
	List<ReciboCuponera> reciboCuponeras;
	
	//Operaciones esenciales
	
	public Cuponera(String nombre, String descripcion, DtFecha fechaInicio, 
			DtFecha fechaFin, int descuento)
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descuento = descuento;
		
		this.clasesCuponeras = new ArrayList<>;
		this.reciboCuponeras = new ArrayList<>;
	}
	
	public Cuponera(DtCuponera dtc)
	{
		Cuponera( dtc.getNombre(), dtc.getDescripcion(), dtc.getFechaInicio,
				dtc.getFechaFin(), dtc.getDescuento() );
	}
	
	public String getNombre() 
	{
		String res = nombre;
		return res;
	}
	
	public String getDescripcion() 
	{
		String res = descripcion;
		return res;
	}
	
	public DtFecha getFechaInicio() 
	{
		DtFecha res = fechaInicio;
		return res;
	}
	
	public DtFecha getFechaFin()
	{
		DtFecha res = fechaFin;
		return res;
	}
	
	public int getDescuento()
	{
		int res = descuento;
		return res;
	}
	
	//Operaciones Casos de Uso
	
	//TERMINAR
	public Set<String> getNombresActDep()
	{
		Set<String> res = new HashSet<>();
		return res;
	}
	
	//TERMINAR
	public int addActDep(ActividadDeportiva ad, int num)
	{
		int res = 0;
		return res;
	}
	
	//TERMINAR
	public int cantidadClases(String nombreActividadDeportiva)
	{
		int res = 0;
		return res;
	}
	
	//TERMINAR
	public boolean tieneActividadDeportiva(String nombre)
	{
		int res = 0;
		return res;
	}
	
	//TERMINAR
	public DtCuponera getDt()
	{
		DtCuponera res = new DtCuponera();
		return res;
	}
}
