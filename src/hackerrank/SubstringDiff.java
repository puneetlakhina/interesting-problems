package hackerrank;
/**
 * https://www.hackerrank.com/challenges/substring-diff
 * @author puneet
 *
 */
public class SubstringDiff {
	static final int[] NULL = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
	/**
	 * Find longest common substring with the allowed number of diffs
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int findLongestCommonSubstringWithAllowedDifferences(String s1, String s2, int allowedDiffs) {
		int[][][] matrix = new int[s1.length()][s2.length()][2];
		int max=Integer.MIN_VALUE;
		int maxi = -1;
		int maxj=-1;
		for(int i=0;i<s1.length();i++) {
			char c1 = s1.charAt(i);
			for(int j=0;j<s2.length();j++) {
				char c2 = s2.charAt(j);
				int[] left = i-1 < 0 ? NULL:matrix[i-1][j];
				int[] right = j-1 < 0 ? NULL:matrix[i][j-1];
				int[] diagonal = i-1 < 0 || j- 1 < 0 ? NULL:matrix[i-1][j-1];
				matrix[i][j] = getBest(left, right, diagonal, allowedDiffs, c1 == c2 ? 0:1);
				if(matrix[i][j][0] > max) {
					max = matrix[i][j][0];
					maxi=i;
					maxj=j;
				}
			}
		}
		System.out.println("Maxi:" + maxi + " maxj:" + maxj);
		return max;
	}
	
	private static int[] getBest(int[] a1, int[]a2, int[] a3, int maxDiffAllowed, int additionalDiff) {
		int[] best = new int[]{1, additionalDiff};
		int bestLength = best[0];
		int bestLengthDistance = best[1];
		if((a1[0] + 1) > bestLength && (a1[1] + additionalDiff) <= maxDiffAllowed) {
			bestLength = a1[0] + 1;
			bestLengthDistance = a1[1] + additionalDiff;
		}
		
		if((a2[0] + 1) > bestLength && (a2[1] + additionalDiff) <= maxDiffAllowed) {
			bestLength = a2[0] + 1;
			bestLengthDistance = a2[1] + additionalDiff;
		}
		
		if((a3[0] + 1) > bestLength && (a3[1] + additionalDiff) <= maxDiffAllowed) {
			bestLength = a3[0] + 1;
			bestLengthDistance = a3[1] + additionalDiff;
		}
		
		best[0] = bestLength;
		best[1] = bestLengthDistance;
		return best;
	}
}
