package net.questions.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	static final int MAX_CHAR_COUNT = 256;	// extended ASCII char count

	/**
	 * @category: Cracking The Coding lnterview
	 * @apiNote:  Given a smaller strings and a bigger string b, design an algorithm to find all permutations of the shorter string within the longer one.
	 * Print the location of each permutation
	 * @implNote time complexity; n:= length of b, m:= length of s => O(256 * n) = O(n)
	 */
	public String[] permutationsInLongString2(String s, String b){
		int M = s.length();
		int N = b.length();
		List<String> result = new ArrayList<>();

		// countP[]:  Store count of all characters of s
		// countTW[]: Store count of current window of b
		char[] sMap = new char[MAX_CHAR_COUNT];
		char[] bMap = new char[MAX_CHAR_COUNT];
		for (int i = 0; i < M; i++){
			(sMap[s.charAt(i)])++;
			(bMap[b.charAt(i)])++;
		}

		// Traverse through remaining characters of pattern
		for (int i = M; i < N; i++){
			// Compare counts of current window of text with counts of pattern[]
			if (compare(sMap, bMap))
				result.add(b.substring(i-M,i-M+s.length()));

			// Add current character to current window
			(bMap[b.charAt(i)])++;

			// Remove the first character of previous window
			bMap[b.charAt(i-M)]--;
		}

		// Check for the last window in text
		if (compare(sMap, bMap))
			result.add(b.substring(N-M,N-M+s.length()));
		return result.toArray(new String[result.size()]);
	}

	// This function returns true if contents of arr1[] and arr2[] are same, otherwise false.
	private boolean compare(char arr1[], char arr2[]){
		for (int i = 0; i < MAX_CHAR_COUNT; i++)	// fixed-size iteration
			if (arr1[i] != arr2[i])
				return false;
		return true;
	}

	/**
	 * @category: Cracking The Coding lnterview
	 * @apiNote:  Given a smaller strings and a bigger string b, design an algorithm to find all permutations of the shorter string within the longer one.
	 * Print the location of each permutation
	 * @implNote time complexity; n:= length of b, m:= length of s => O(n m log(m))
	 */
	public String[] permutationsInLongString(String s, String b) {
		char[] sArray = s.toCharArray();
		Arrays.sort(sArray);
		String orderedS = new String(sArray);
		List<String> result = new ArrayList<>();

		for (int i = 0;i<b.length()-s.length()+1;i++){
			String bPartial = b.substring(i,i+s.length());
			char[] bPartialArray = bPartial.toCharArray();
			Arrays.sort(bPartialArray);
			String orderedbPartialArray = new String(bPartialArray);
			if (orderedS.equals(orderedbPartialArray)){
				System.out.println(bPartial);
				result.add(bPartial);
			}
		}
		return result.toArray(new String[result.size()]);
	}

	/**
	 * @category: LeetCode
	 * @apiNote: Given two strings A and B of lowercase letters, return true if and
	 *           only if we can swap two letters in A so that the result equals B.
	 */
	public boolean buddyStrings(String A, String B) {
		if (A.length() != B.length())
			return false;

		int[] differentIndexes = new int[2];
		char[] aArray = A.toCharArray();
		char[] bArray = B.toCharArray();
		int diffCounter = 0;

		for (int i = 0; i < aArray.length; i++) {
			if (aArray[i] != bArray[i]) {
				if (diffCounter < 2)
					differentIndexes[diffCounter] = i;
				diffCounter++;
			}
		}

		if (diffCounter == 0) {
			String repatedTemplate = "";
			for (int i = 0; i < aArray.length; i++) {
				repatedTemplate += aArray[i];
				if (!A.equals(repatedTemplate) && A.split(repatedTemplate).length == 0)
					return true;
			}
			return false;
		}

		if (diffCounter > 2)
			return false;

		return aArray[differentIndexes[0]] == bArray[differentIndexes[1]]
				&& aArray[differentIndexes[1]] == bArray[differentIndexes[0]];
	}

	/**
	 * @category: LeetCode
	 * @apiNote: Given n non-negative integers a1, a2, ..., an , where each
	 *           represents a point at coordinate (i, ai). n vertical lines are
	 *           drawn such that the two endpoints of line i is at (i, ai) and (i,
	 *           0). Find two lines, which together with x-axis forms a container,
	 *           such that the container contains the most water.
	 */
	public int maxArea(int[] height) {
		int result = 0;
		for (int i = 0; i < height.length; i++) {
			if (height[i] == 0)
				continue;
			for (int j = i + 1; j < height.length && height[i] - height[i > 0 ? i - 1 : i] >= 0; j++) {
				result = Math.max(result, calculateArea(height[j], height[i], i + 1, j + 1));
			}
		}
		return result;
	}

	private int calculateArea(int a, int b, int i, int j) {
		return Math.min(a, b) * Math.abs(i - j);
	}

	/**
	 * @category: LeetCode
	 * @apiNote: Given a non-empty array of integers, return the third maximum
	 *           number in this array. If it does not exist, return the maximum
	 *           number. The time complexity must be in O(n).
	 */
	public int thirdMax(int[] nums) {
		long m1 = Long.MIN_VALUE, m2 = Long.MIN_VALUE, m3 = Long.MIN_VALUE;
		long temp = 0, temp2 = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > m1) {
				temp = m1;
				temp2 = m2;
				m1 = nums[i];
				m2 = m2 < temp ? temp : m2;
				m3 = m3 < temp2 ? temp2 : m3;
			} else if (nums[i] > m2) {
				temp = m2;
				m2 = nums[i] < m1 ? nums[i] : m2;
				m3 = temp < m2 ? temp : m3;
			} else if (nums[i] > m3) {
				m3 = nums[i] < m2 ? nums[i] : m3;
			}
		}
		m3 = m3 == Long.MIN_VALUE ? m1 : m3;
		return (int) m3;
	}

	/**
	 * @category: LeetCode
	 * @apiNote: Given a string s, find the longest palindromic substring in s. You
	 *           may assume that the maximum length of s is 1000.
	 */
	public String longestPalindrome(String s) {
		int length = s.length();
		if (isPalindrome(s))
			return s;

		if (length == 2) {
			if (isPalindrome(s))
				return s;
			else {
				return s.substring(0, 1);
			}
		}

		int startIndex = 0;
		String tempResult = "";
		String result = "";
		int subStartIndex = 0;

		while (startIndex <= length - 3) {
			tempResult = s.substring(startIndex, startIndex + 3);

			subStartIndex = startIndex;
			if (!isPalindrome(tempResult)) {
				if (isPalindrome(tempResult.substring(0, 2))) {
					tempResult = tempResult.substring(0, 2);
				} else if (isPalindrome(tempResult.substring(1, 3))) {
					tempResult = tempResult.substring(1, 3);
					subStartIndex = startIndex + 1;
				} else {
					tempResult = "";
				}
				// tempResult = isPalindrome(tempResult.substring(0, 2)) ?
				// tempResult.substring(0, 2) : isPalindrome(tempResult.substring(1, 3))
				// ?tempResult.substring(1, 3):"";
			}

			if (tempResult.length() > 0) {
				int subEndIndex = subStartIndex + tempResult.length() - 1;

				while (--subStartIndex >= 0 && ++subEndIndex < length) {
					if (s.charAt(subStartIndex) == s.charAt(subEndIndex)) {
						tempResult = s.substring(subStartIndex, subEndIndex + 1);
					} else {
						if (s.charAt(subStartIndex + 1) == s.charAt(subEndIndex)
								&& isPalindrome(s.substring(startIndex + 1, subEndIndex + 1))) {
							tempResult = s.substring(startIndex + 1, subEndIndex + 1);
							subStartIndex++;
						} else if (s.charAt(subStartIndex) == s.charAt(subEndIndex - 1)
								&& isPalindrome(s.substring(subStartIndex, subEndIndex))) {
							tempResult = s.substring(subStartIndex, subEndIndex);
							subEndIndex--;
						} else {
							break;
						}
					}
				}

				if (tempResult.length() > result.length())
					result = tempResult;
			}
			startIndex++;
		}

		if (result.length() == 0)
			result = s.substring(0, 1);

		return result;
	}

	public static boolean isPalindrome(String str) {
		int i = 0, j = str.length() - 1;
		while (i < j) {
			if (str.charAt(i) != str.charAt(j))
				return false;
			i++;
			j--;
		}
		return true;
	}

	/**
	 * @category: LeetCode
	 * @apiNote: Given a string, find the length of the longest substring without repeating characters.
	 */
	public int lengthOfLongestSubstring(String s) {
		int longestLength = 0;
		String partialS = "";
		char[] sArray = s.toCharArray();
		for (char c : sArray) {
			String temp = String.valueOf(c);
			if (partialS.contains(temp)) {
				if (partialS.endsWith(temp)) {
					partialS = temp;
				} else {
					partialS = partialS.substring(partialS.indexOf(temp) + 1);
					partialS += temp;
				}
			} else {
				partialS += temp;
				if (partialS.length() > longestLength) {
					longestLength++;
				}
			}
		}

		return longestLength;
	}
}
