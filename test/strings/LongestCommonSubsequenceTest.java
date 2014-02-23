package strings;
import static org.junit.Assert.*;
import static strings.LongestCommonSubsequence.*;

import org.junit.Test;
public class LongestCommonSubsequenceTest {

	@Test
	public void testSameString() {
		assertEquals("abc", getLongestCommonSubsequence("abc", "abc"));
	}
	
	@Test
	public void testDifferingLength() {
		assertEquals("abc", getLongestCommonSubsequence("abcdef", "abc"));
	}
	
	@Test
	/**
	 * Make sure LCS is not longest common substring
	 */
	public void testSkippedIndices() {
		assertEquals("BG", getLongestCommonSubsequence("aBcdG", "xyztBzzxxkkGy"));
	}
	
	@Test
	public void testLength() {
		assertEquals(3, getLongestCommonSubsequenceLength("abc", "abc"));
		assertEquals(3, getLongestCommonSubsequenceLength("abcdef", "abc"));
		assertEquals("BG".length(), getLongestCommonSubsequenceLength("aBcdG", "xyztBzzxxkkGy"));
		
	}
	
	@Test
	public void testLongString() {
		/**
		 * Build 2 strings of totalLengthOfStringsLength with the same character ever equalCharacterFrequency
		 */
		int totalLengthOfStrings = 15000;
		int equalCharacterFrequency = 2; 
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		for(int i=0;i<totalLengthOfStrings;i++) {
			if(i%equalCharacterFrequency == 0) {
				sb1.append("a");
				sb2.append("a");
			} else {
				sb1.append("x");
				sb2.append("y");
			}
		}
		assertEquals(totalLengthOfStrings/equalCharacterFrequency, getLongestCommonSubsequenceLength(sb1.toString(), sb2.toString()));
		
	}
}
