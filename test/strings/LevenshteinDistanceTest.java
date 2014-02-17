package strings;
import static org.junit.Assert.*;
import static strings.LevenshteinDistance.*;

import org.junit.Test;
public class LevenshteinDistanceTest {

	@Test
	public void testOneEmptyString() {
		assertEquals(2, getDistance("", "ab"));
	}
	@Test
	public void testSameLength() {
		assertEquals(2, getDistance("bc", "ab"));
	}
	
	@Test
	public void testDifferingLength() {
		assertEquals(1, getDistance("abc", "ab"));
	}
	@Test(expected=IllegalArgumentException.class)
	public void testOnlyAllowTranspositionsError() {
		getDistance("a", "ab", true);
	}
	
	@Test
	public void testOnlyAllowTranspositions() {
		assertEquals(3,getDistance("abc", "def", true));
	}
}
