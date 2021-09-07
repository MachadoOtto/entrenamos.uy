/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

package logica;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;

import excepciones.CategoriaException;

public class HandlerCategoria {
	
	private static HandlerCategoria instancia = null;
	private Map<String, Categoria> categorias;
	
	private HandlerCategoria() {
		categorias = new HashMap<>();
	}
	
	public static HandlerCategoria getInstance() {
		if (instancia == null) {
			instancia = new HandlerCategoria(); 
		}
		return instancia;
	}
	
	public void addCategoria(Categoria cat) throws CategoriaException {
		if (categorias.containsKey(cat.getNombre())) {
			throw new CategoriaException("Ya existe una Categoria en el Sistema con ese nombre.");
		} else {
			categorias.put(cat.getNombre(), cat);
		}
	}
	
	public Categoria findCategoria(String catNombre) throws CategoriaException {
		if (categorias.containsKey(catNombre)) {
			return categorias.get(catNombre);
		} else {
			throw new CategoriaException("No existe una Categoria en el Sistema con ese nombre.");
		}
	}
	
	public boolean existeCategoria(String catNombre) {
		return categorias.containsKey(catNombre);
	}
	
	public Set<String> getNombreCategorias() {
		return categorias.keySet();
	}
}