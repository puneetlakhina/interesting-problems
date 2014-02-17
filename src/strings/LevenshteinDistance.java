package strings;

/**
 * http://en.wikipedia.org/wiki/Levenshtein_distance
 * 
 * @author puneet
 * 
 */
public class LevenshteinDistance {
	/**
	 * Recursive implementation of levenshtein distance
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int getDistance(String s1, String s2) {
		return getDistance(s1, s2, false);
	}

	/**
	 * Levenshtein distance with the option to not permit deletes. if
	 * onlyAllowTranspositions is true and and strings are of differing length
	 * IllegalArgumentException is thrown
	 * 
	 * @param s1
	 * @param s2
	 * @param allowDeletes
	 * @return
	 */
	public static int getDistance(String s1, String s2,
			boolean onlyAllowTranspositions) {
		if (onlyAllowTranspositions && s1.length() != s2.length()) {
			throw new IllegalArgumentException(
					"onlyAllowTranspositions is true but strings are of differing length");
		}
		if (s1.length() == 0) {
			return s2.length();
		}

		if (s2.length() == 0) {
			return s1.length();
		}
		int lastCharCost = s1.charAt(s1.length() - 1) == s2
				.charAt(s2.length() - 1) ? 0 : 1;
		return min(
				onlyAllowTranspositions ? Integer.MAX_VALUE : getDistance(
						s1.substring(0, s1.length() - 1), s2) + 1,
				onlyAllowTranspositions ? Integer.MAX_VALUE : getDistance(s1,
						s2.substring(0, s2.length() - 1)) + 1,
				getDistance(s1.substring(0, s1.length() - 1),
						s2.substring(0, s2.length() - 1))
						+ lastCharCost);
	}

	private static int min(int... vals) {
		if (vals.length == 0) {
			throw new IllegalArgumentException("empty array");
		}
		int minValue = Integer.MAX_VALUE;
		for (int val : vals) {
			if (val < minValue) {
				minValue = val;
			}
		}
		return minValue;
	}
}
