package problems.contact;

import java.util.List;
import java.util.Map;

import com.google.common.base.Splitter;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

public class ContactProblem implements IContactProblem {

	private Integer minLength;
	private Integer maxLength;
	private String message;
	private Map<String, Integer> map;
	private boolean executed = false;
	
	public ContactProblem(){
	}
	
	@Override
	public void setData(int minLength, int maxLength, String message) {
		if(minLength <= 0 || maxLength <= 0)
			throw new IllegalArgumentException();
		if(minLength > maxLength)
			throw new IllegalArgumentException();
		if(message == null)
			throw new IllegalArgumentException();
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.message = message;
		this.map = Maps.newHashMap();
	}

	@Override
	public void run() {
		List<String> it;
		String aux;
		for(int i = minLength; i <= maxLength; i++){
			aux = this.message;
			
			for(int j = 0; j < 2; j++){
				it = Lists.newArrayList(Splitter.fixedLength(i).split(aux.subSequence(j, aux.length())));
				addToMap(Collections2.filter(it, new PredicateLenthEqual(i)));
				if(i == aux.length())
					break;
			}
			
			
		}
		executed = true;
	}
	
	public void addToMap(Iterable<String> it){
		List<String> l = Lists.newArrayList(it);
		do{
			List<String> lPatron = Lists.newArrayList(it);
			lPatron = Lists.newArrayList(Collections2.filter(lPatron, new PredicatePatronEqual(l.get(0))));
			String key = l.get(0);
			if(map.containsKey(key)){
				map.put(key, map.get(key) + lPatron.size());
			}else{
				map.put(key, lPatron.size());
			}
			l.removeAll(lPatron);
		}while(l.size() != 0);
		
	}
	

	@Override
	public Map<String, Integer> getResult(int topRank) {
		
		if(!executed)
			return null;
		
		Map<String, Integer> map = Maps.newHashMap(this.map);
		Map<String, Integer> res = Maps.newHashMap();
		List<Integer> l = Lists.newArrayList(Ordering.natural().reverse().sortedCopy(map.values())); 
		Integer i = 1;
		do{
			Integer value = l.get(0);
			for(String s: map.keySet()){
				if(map.get(s) == value){
					res.put(s, value);
					l.remove(value);
					map.remove(s);
					break;
				}
			}
			i++;
		}while(i <= topRank);
		
		return res;
	}

}
