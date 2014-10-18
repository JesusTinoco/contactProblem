package problems.contact;

import com.google.common.base.Predicate;

public class PredicateLenthEqual implements Predicate<String>{
	
	private Integer lenth;
	
	public PredicateLenthEqual(Integer lenth){
		this.lenth = lenth;
	}

	@Override
	public boolean apply(String a) {
		return a.length() == lenth;
	}
	

}
