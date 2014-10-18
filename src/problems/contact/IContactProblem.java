package problems.contact;

import java.util.Map;

public interface IContactProblem {
	/*
	 * This method sets the input data for the problem.
	 * @param minLength the minimal length of a pattern.
	 * @param maxLength the maximal length of a pattern.
	 * @param topRank the number of expected results in the top rank.
	 * @param message the message under analysis. 
	 * @throws IllegalArgumentException if any of the integer input parameters are zero or negative, 
	 * the maxLength is less than the minLength or the message is null.
	 */
	public abstract void setData(int minLength, int maxLength, String message);
		
	/*
	 * Executes the problem if the data has been correctly set
	 */
	public abstract void run();
	
	/*
	 * @return the top rank of patterns and their frequencies. 
	 * It return null if the problem has not been solved yet.
	 * @throws IllegalArgumentException if the topRank parameter is less than zero
	 */
	public abstract Map<String,Integer> getResult(int topRank);
}