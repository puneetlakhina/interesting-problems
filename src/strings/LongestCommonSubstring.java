package strings;

/**
 * Find the longest common substring of 2 strings
 * 
 * @author puneet
 * 
 */
public class LongestCommonSubstring {

	/**
	 * Find longest common substring
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String findLongestCommonSubstring(String s1, String s2) {
		if (s1.length() == 0 || s2.length() == 0) {
			return "";
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
				int s1index = i;
				char c = s1.charAt(s1index);
				//Position s2index at the next match
				while (s2index < s2.length() && c != s2.charAt(s2index)) {
					s2index++;
				}
				while (s2index < s2.length() && s1index < s1.length()
						&& s1.charAt(s1index) == s2.charAt(s2index)) {
					currentSubstring.append(s1.charAt(s1index));
					s1index++;
					s2index++;
				}
				if (currentSubstring.length() > maxSubstring.length()) {
					maxSubstring = currentSubstring.toString();
				}
				currentSubstring.setLength(0);

			}
		}
		return maxSubstring;
	}
}
