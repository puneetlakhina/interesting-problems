package strings;

import org.junit.Test;
import static org.junit.Assert.*;
import static strings.CheckAnagram.*;
public class AnagramsTest {

	@Test
	public void testDifferingLength() {
		assertFalse(areAnagrams("a", "ab"));
	}
	
	@Test
	public void testPositiveCase() {
		assertTrue(areAnagrams("xy", "yx"));
	}
	
	@Test
	public void testNegativeCase() {
		assertFalse(areAnagrams("xy", "yz"));
	}
}
