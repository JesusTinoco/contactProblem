package problems.contact;

import java.util.Comparator;
import java.util.Map;

public class ComparatorValues implements Comparator<String> {

	private Map<String, Integer> map;
	
	public ComparatorValues(Map<String, Integer> map){
		this.map = map;
	}
	
	@Override
	public int compare(String a, String b) {
		if(map.get(a) <= map.get(b))
			return 1;
		else
			return -1;
	}

}
