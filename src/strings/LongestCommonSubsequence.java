package strings;

/**
 * http://en.wikipedia.org/wiki/Longest_common_subsequence_problem 2 items
 * solution. Finds the longest common subsequence not just the length. 
 * 
 * @author puneet
 * 
 */
public class LongestCommonSubsequence {
	/**
	 * Recursive solution
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String getLongestCommonSubsequence(String s1, String s2) {
		return longestCommonSubsequence(s1, s2).toString();
	}
	/**
	 * This method transacts in stringbuilder(s) to prevent unnecessary string allocations
	 * @param s1
	 * @param s2
	 * @return
	 */
	private static StringBuilder longestCommonSubsequence(String s1, String s2) {
		StringBuilder sb = new StringBuilder("");
		if (s1.length() != 0 && s2.length() != 0) {

			char lastChar1 = s1.charAt(s1.length() - 1);
			char lastChar2 = s2.charAt(s2.length() - 1);
			if (lastChar1 == lastChar2) { //Same character at the end, LCS = LCS(s1[0:-1], s2[0:-1]) + lastChar
				sb.append(
						longestCommonSubsequence(
								s1.substring(0, s1.length() - 1),
								s2.substring(0, s2.length() - 1))).append(
						lastChar1);
			} else { //Last char is different. LCS = longer of assume lastChar1 is the last char and lastChar2 is the lastChar
				
				//Assume lastChar2 is the lastChar. so lastChar 1 cant be the last thus calculate LCS with s1[0:-1],s2
				StringBuilder sb1 = longestCommonSubsequence(
						s1.substring(0, s1.length() - 1), s2); 
				//Assume lastChar1 is the lastChar. so lastChar 2 cant be the last thus calculate LCS with s1,s2[0:-1]
				StringBuilder sb2 = longestCommonSubsequence(s1,
						s2.substring(0, s2.length() - 1));
				return sb1.length() > sb2.length() ? sb1 : sb2; //Pick the longer
			}
		}
		return sb;
	}
	
	public static int getLongestCommonSubsequenceLength(String s1, String s2) {
		int[][] matrix = new int[s1.length() + 1][s2.length() + 1];
		for(int i=0;i<s1.length()+1;i++) {
			matrix[i][0] = 0;
		}
		for(int i=0;i<s2.length()+1;i++) {
			matrix[0][i] = 0;
		}
		for(int i=1;i<s1.length()+1;i++) {
			char c1 = s1.charAt(i-1);
			for(int j=1;j<s2.length()+1;j++) {
				char c2 = s2.charAt(j-1);
				if(c1 == c2) {
					matrix[i][j] = matrix[i-1][j-1] + 1;
				} else {
					matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
				}
			}
		}
		return matrix[s1.length()][s2.length()];
	}
}
