package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HandlerCuponera {
	
	//atributos
	
	private static HandlerCuponera instance = null;
	private Map<String,Cuponera> cuponeras;
	
	//operaciones esenciales
	
	private HandlerCuponera()
	{
		cuponeras = new HashMap<>();
	}
	
	public static HandlerCuponera getInstance()
	{
		if(instance == null)
		{
			instance = new HandlerCuponera();
		}
		return instance;
	}
	
	public boolean exists(String nombreCuponera)
	{
		boolean res = cuponeras.containsKey(nombreCuponera);
		return res;
	}
	
	public void add(String nombreCuponera, Cuponera cuponera)
	{
		cuponeras.put(nombreCuponera, cuponera);
	}
	
	//Operaciones diagramas
	
	public int addCuponera(String nombreCuponera, String descripcion, 
			DtFecha ini, DtFecha fin, int descuento, DtFecha fechaAlta)
	{
		int res = 0;
		if( cuponeras.containsKey( nombreCuponera ) )
		{
			res = 1;
		}
		else
		{
			Cuponera nuevaCuponera = new Cuponera( nombreCuponera, descripcion, 
					ini, fin, descuento, fechaAlta );
			cuponeras.put( nombreCuponera, nuevaCuponera );
		}
		return res;
	}
	
	public Cuponera getCup(String nombreCuponera)
	{
		Cuponera res = cuponeras.get(nombreCuponera);
		return res;
	}
	
	public Set<String> getNombreCuponeras()
	{
		Set<String> res = cuponeras.keySet();
		/*Set<String> res = new HashSet<>();
		Iterator<Map.Entry<String,Cuponera>> it = 
				cuponeras.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry<String,Cuponera> par = it.next();
			Cuponera cup = par.getValue();
			res.add(cup.getNombre());
		}*/
		return res;
	}
	
	public DtCuponera getDtCuponera(String nombreCuponera)
	{
		DtCuponera res = this.getCup(nombreCuponera).getDt();
		return res;
	}
}
