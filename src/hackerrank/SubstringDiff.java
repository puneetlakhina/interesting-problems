package hackerrank;
/**
 * https://www.hackerrank.com/challenges/substring-diff
 * @author puneet
 *
 */
public class SubstringDiff {
	/**
	 * Find longest common substring
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int findLongestCommonSubstringWithAllowedDifferences(String s1, String s2, int allowedDiffs) {
		if (s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		String maxSubstring = "";
		StringBuilder currentSubstring = new StringBuilder("");

		for (int i = 0; i < s1.length(); i++) {
			int maxPossibleLengthAti = s1.length()-i;
			//Terminate early if its not possible to get a larger substring
			if(maxPossibleLengthAti <= maxSubstring.length()) {
				break;
			}
			int s2index = 0;
			while (s2index < s2.length()) {
				int current_diffs = 0;
				int s1index = i;
				int s2matchindex = s2index++;
				while (s2matchindex < s2.length() && s1index < s1.length()
						&& (s1.charAt(s1index++) == s2.charAt(s2matchindex++) || ++current_diffs <= allowedDiffs)) {
					currentSubstring.append(s1.charAt(s1index-1));
				}
				if (currentSubstring.length() > maxSubstring.length()) {
					maxSubstring = currentSubstring.toString();
				}
				currentSubstring.setLength(0);

			}
		}
		return maxSubstring.length();
	}
}
