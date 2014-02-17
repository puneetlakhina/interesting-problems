package strings;
import static org.junit.Assert.*;
import static strings.LongestCommonSubstring.*;

import org.junit.Test;
public class LongestCommonSubstringTest {

	@Test
	public void testPrefixLongest() {
		assertEquals("abc", findLongestCommonSubstring("abc", "abcbc"));
	}
	
	@Test
	public void testSuffixLongest() {
		assertEquals("xyz", findLongestCommonSubstring("abcxyz", "cbxyz"));
	}
	
	@Test
	public void testMiddle() {
		assertEquals("XYZ", findLongestCommonSubstring("xXYZab", "abXYZx"));
	}
	
	@Test
	public void testPickLongestStartingAtSameCharacter() {
		assertEquals("bcxyz", findLongestCommonSubstring("bcxyz", "abcDDDDDbcxyz"));
	}
}
