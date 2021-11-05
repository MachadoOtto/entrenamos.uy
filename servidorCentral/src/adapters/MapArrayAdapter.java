package adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapArrayAdapter<K, V> {
	private ArrayList<K> keys;
	private ArrayList<V> values;
	
	public MapArrayAdapter(Map<K, V> map) {
		keys = new ArrayList<K>();
		values = new ArrayList<V>();
		for (Entry<K, V> x : map.entrySet()) {
			keys.add(x.getKey());
			values.add(x.getValue());
		}
	}
	
	public Map<K, V> rebuild(){
		Map<K, V> fenix = new HashMap<K, V>();
		for (int i=0; i<keys.size(); i++) {
			fenix.put(keys.get(i), values.get(i));
		}
		return fenix;
	}
}
