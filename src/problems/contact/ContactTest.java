package problems.contact;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ContactTest {
	IContactProblem contactProblem;
	int minLength, maxLength, topRank;
	String message;
	Map<String,Integer> expectedResult;
	
	
	public ContactTest (int minLength, int maxLength, int topRank, String message, Map<String,Integer> expectedResult) {
			this.minLength = minLength;
			this.maxLength = maxLength;
			this.topRank = topRank;
			this.message = message;
			this.expectedResult = expectedResult;
	}
	
	 // creates the test data
	@Parameters
	public static Collection<Object[]> data() {
		Map<String,Integer> mapResult1 = new HashMap<String,Integer>() {{put("00",23);}};
		Map<String,Integer> mapResult2 = new HashMap<String,Integer>() {{put("01",2); put("10",1);put("010",1);put("101",1);put("0101",1);}};
	    Object[][] data = new Object[][] { {2,4,1,"01010010010001000111101100001010011001111000010010011110010000000",mapResult1},
	    								   {2,4,5,"0101",mapResult2}};

	    return Arrays.asList(data);
	  }

	
	@Test
	public void testContactProblem() {
		contactProblem = new ContactProblem();
		contactProblem.setData(minLength,maxLength,message);
		contactProblem.run();
		Map<String,Integer> result = contactProblem.getResult(topRank);
		// checks if the mappings are equal, but the frequency is not checked with equals.
		Assert.assertEquals("length: ["+minLength+","+maxLength+"], top "+ topRank + ": "
							, expectedResult, result );
		// now we check that the values are correct for every key
		for (Entry<String,Integer> entry: result.entrySet()) {
			Integer frequency = expectedResult.get(entry.getKey());
			Assert.assertNotNull("length: ["+minLength+","+maxLength+"], top "+ topRank + ": a frequency is null in the result", frequency);
			Assert.assertEquals(entry.getValue(), frequency);
		}
	}
}