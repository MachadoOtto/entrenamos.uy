package adapters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SetArrayAdapter<T> {
	private ArrayList<T> items;

	public SetArrayAdapter(Set<T> set) {
		items = new ArrayList<T>();
		for (T x : set)
			items.add(x);
	}
	public ArrayList<T> getItems() {
		return items;
	}
	public Set<T> rebuild(){
		Set<T> fenix = new HashSet<T>();
		for (T x: items)
			fenix.add(x);
		return fenix;
	}
}
