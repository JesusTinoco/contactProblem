package problems.contact;

import com.google.common.base.Predicate;

public class PredicatePatronEqual implements Predicate<String> {
	
	private String patron;
	
	public PredicatePatronEqual(String patron){
		this.patron = patron;
	}
	
	@Override
	public boolean apply(String patron) {
		return this.patron.equals(patron);
	}

	
	
}
