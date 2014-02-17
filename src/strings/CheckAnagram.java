package strings;

import java.util.Arrays;

/**
 * Check if one string is anagram of another
 * @author puneet
 *
 */
public class CheckAnagram {
	/**
	 * Naive implementation which needs nlogn time where n is the length of string(s)
	 * @param s1
	 * @param s2
	 */
	public static boolean areAnagrams(String s1, String s2) {
		if(s1.length() != s2.length()) { //if length is not equal they cant be anagrams
			return false;
		}
		char[] arr1 = s1.toCharArray();
		char[] arr2 = s2.toCharArray();
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		for(int i=0;i<arr1.length;i++) {
			if(arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}
}
