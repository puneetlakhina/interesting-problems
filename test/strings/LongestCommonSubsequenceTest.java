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
}
