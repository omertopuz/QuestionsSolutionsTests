package net.questions.solutions;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

	/**
	 * @category: Unknown
	 * @apiNote: There are N blocks, numbered from 0 to N-1, arranged in a row. A couple of frogs were sitting together on one block when they had a terrible quarrel. Now they want to jump away from one another so that the distance between them will be as large as possible. The distance between blocks numbered J and K, where J ≤ K, is computed as K − J + 1. The frogs can only jump up, meaning that they can move from one block to another only if the two blocks are adjacent and the second block is of the same or greater height as the first. What is the longest distance that they can possibly create between each other, if they also chose to sit on the optimal starting block initially?
	 * @implNote time complexity; O(n)
	 */
	public static int jumpingFrogs(int[] blocks) {
		int result = Integer.MIN_VALUE;
		for (int i = 0; i < blocks.length-1; i++) {
			if (blocks[i] < blocks[i+1]){

				if (i == 0){
					int targetIndex = i;
					while (targetIndex < blocks.length -1 &&
							blocks[targetIndex] <= blocks[targetIndex+1])
						targetIndex++;
					if (targetIndex + 1 >result)
						result = targetIndex+1;
				}else {
					int forwardIndex = i;
					int backwardIndex = i;
					while (forwardIndex < blocks.length-1 &&
							blocks[forwardIndex] <= blocks[forwardIndex+1])
						forwardIndex++;
					while (backwardIndex>0 &&
							blocks[backwardIndex] <= blocks[backwardIndex-1])
						backwardIndex--;
					if (forwardIndex - backwardIndex + 1 >result)
						result = forwardIndex - backwardIndex + 1 ;
				}
			}

		}

		if(result == Integer.MIN_VALUE)
			result = 2;

		return result;
	}

	/**
	 * @category: Unknown
	 * @apiNote: given an array A consisting of N integers, returns the maximum sum of two numbers whose digits add up to an equal sum. If there are no two numbers whose digits have an equal sum, the function should return -1
	 * @implNote time complexity; O(n)
	 */
	public int maxSumTuples(int[] A) {

		long maxSum = Long.MIN_VALUE;
		int[] digitSums = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			digitSums[i] = getSum(A[i]);
		}

		for (int i = 0; i < A.length; i++) {
			for (int j = i+1; j < A.length; j++) {
				if(digitSums[i] == digitSums[j] && A[i] + A[j] > maxSum)
					maxSum = A[i] + A[j];
			}
		}

		if (maxSum == Long.MIN_VALUE)
			return -1;
		else
			return (int)maxSum;
	}

	private int getSum(int n){
		int sum = 0;
		while (n != 0) {
			sum = sum + n % 10;
			n = n / 10;
		}
		return sum;
	}

	/**
	 * @category: Unknown
	 * @apiNote: replace all of the question marks with letters (one letter per question mark) in such a way that no letter appears next to another letter of the same kind.
	 * @implNote time complexity; O(n)
	 */
	public String replaceQuestionMarksRandom(String riddle) {
		char[] rChars = riddle.toCharArray();
		for (int i = 0; i < rChars.length; i++) {
			if (rChars[i] == '?'){
				if (i == 0)
					rChars[i] = 'a';
				else {
					if(i<riddle.length()-1){
						rChars[i] = getRandomChar(rChars[i-1],rChars[i+1]);
					}else {
						rChars[i] = getRandomChar(rChars[i-1],'z');
					}
				}
			}
		}

		return new String(rChars);
	}

	private char getRandomChar(char c1, char c2){
		Random random = new Random();
		int max = 'z';
		int min = 'a';
		int rand = 0;
		do {
			rand = random.nextInt(max - min + 1) + min;
		}while (c1 == rand || c2 == rand);
		return (char) rand;
	}

	/**
	 * @category: LeetCode
	 * @apiNote: The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
	 * @implNote time complexity; O(n)
	 */
	public String convert(String s, int numRows) {
		if (numRows == 1) return s;
		int sLength=0;
		String[] rows = new String[numRows];
		for (int i = 0; i < rows.length; i++) {
			rows[i] = "";
		}

		while (sLength<s.length()){
			for (int i = 0; sLength<s.length() && i < numRows; i++) {
				rows[i] += s.charAt(sLength++);
			}
			for (int i = numRows-2; sLength<s.length() && i > 0; i--) {
				rows[i] +=s.charAt(sLength++);
			}
		}

		return Arrays.stream(rows).reduce((a,b)->a+b).get();
	}

	/**
	 * @category: LeetCode
	 * @apiNote: Merge two sorted linked lists and return it as a sorted list. The list should be made by splicing together the nodes of the first two lists.
	 * @implNote time complexity; O(n)
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode temp1 = l1;
		ListNode temp2 = l2;
		ListNode temp = null;
		ListNode head = null;

		while (temp1 != null && temp2 != null){
			while (temp1 != null && temp2 != null && temp1.val <= temp2.val){
				ListNode new_node = new ListNode(temp1.val);
				if (head == null){
					head = new_node;
					temp = new_node;
				}else {
					temp.next = new_node;
					temp = temp.next;
				}
				temp1 = temp1.next;
			}

			while (temp2 != null && temp1!= null && temp2.val <= temp1.val){
				ListNode new_node = new ListNode(temp2.val);
				if (head == null){
					head = new_node;
					temp = new_node;
				}else {
					temp.next = new_node;
					temp = temp.next;
				}
				temp2 = temp2.next;
			}
		}

		while (temp1 != null){
			ListNode new_node = new ListNode(temp1.val);
			if (head == null){
				head = new_node;
				temp = new_node;
			}else {
				temp.next = new_node;
				temp = temp.next;
			}
			temp1 = temp1.next;
		}
		while (temp2 != null){
			ListNode new_node = new ListNode(temp2.val);
			if (head == null){
				head = new_node;
				temp = new_node;
			}else {
				temp.next = new_node;
				temp = temp.next;
			}
			temp2 = temp2.next;
		}

		return head;
	}

	/**
	 * @category: unknown
	 * @apiNote: reverse a linked list.
	 * @implNote time complexity; O(n)
	 */
	public ListNode reverseLinkedList2(ListNode head){
		if (head == null || head.next == null)
			return head;

		ListNode next = null;
		ListNode previous = null;
		ListNode current = head;

		while (current != null){
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}

		return previous;
	}

	public ListNode reverseLinkedList (ListNode head){
		if (head == null || head.next == null)
			return head;

		ListNode response = reverseLinkedList(head.next);
		head.next.next = head;
		head.next = null;

		return response;
	}

	/**
	 * @category: unknown
	 * @apiNote: Given a non-negative number represented as an array of digits, add 1 to the number ( increment the number represented by the digits ). The digits are stored such that the most significant digit is first element of array.
	 * @implNote time complexity; O(n)
	 */
	public int[] incrementNumber(int[] arr){

		boolean increaseCapacity = false;
		int carry = 0;
		for(int i = arr.length - 1; i>=0; i--){
			if(arr[i] + carry +1 > 9){
				carry = 1;
				arr[i] = 0;
				if(i ==  0)
					increaseCapacity = true;
			}else{
				arr[i]=carry+1;
				break;
			}
		}

		if(increaseCapacity){
			int returnArrSize = arr.length+1;
			int[] result = new int[returnArrSize];
			result[0] = 1;

			for(int i = 1; i<arr.length; i++){
				result[i] = arr[i];
			}
			return result;
		}else
			return arr;
	}

	/**
	 * @category: hackerrank
	 * @apiNote: count all of the substrings that make up a valid command. Each of the valid commands will be in the following format:
	 * The first letter is a lowercase English letter.
	 * Next, it contains a sequence of zero or more of the following characters: lowercase English letters, digits, and colons.
	 * Next, it contains a forward slash '/'.
	 * Next, it contains a sequence of one or more of the following characters: lowercase English letters and digits.
	 * Next, it contains a backward slash '\'.
	 * Next, it contains a sequence of one or more lowercase English letters.
	 * @implNote time complexity; O(commands.size() * max(commands.length))
	 */
	public List<Integer> commandCount(List<String> commands) {
		List<Integer> result = new ArrayList<>();

		String pattern = "^([a-z])([a-z0-9+:]*)/([a-z0-9]*)\\\\([a-z]*)";

		for (String cmd: commands) {
			if (cmd.matches(pattern+".*")){
				int counter = 0;
				String[] backSlashParts = cmd.split("\\\\");
				for (int i = 0; i < backSlashParts.length; i++) {
					String partialCmd = backSlashParts[i]+"\\";
					if(i < backSlashParts.length-1){
						if (backSlashParts[i+1].contains("/"))
							partialCmd+=backSlashParts[i+1].substring(0, partialCmd.indexOf('/')+1);
						else
							partialCmd+=backSlashParts[i+1];
					}
					if(partialCmd.matches(pattern+".*")){
						counter +=(int)partialCmd.substring(0,partialCmd.indexOf('/')+1)
								.chars().filter(c->c>='a' && c<='z').count() *
								(int)partialCmd.substring(partialCmd.indexOf('\\')+1,partialCmd.length())
								.chars().filter(c->c>='a' && c<='z').count();
					}
				}
				result.add(counter);
			}
			else {
				result.add(0);
			}
		}

		return result;
	}

	/**
	 * @category: hackerrank
	 * @apiNote: Start with an infinite two dimensional grid filled with zeros, indexed from (1,1) at bottom left corner with coordinates increasing towards the top and right. Given a series of coordinates (r,c) where r is the ending row and c is the ending column, add 1 to each element in the range from (1,1) to (r,c) inclusive. Once all coordinates are processed, determine how many cells contain the maximal value in the grid.
	 * @implNote time complexity;
	 */
	public long countMax(List<String> upRight) {
		int minRow = Integer.MAX_VALUE;
		int minCol = Integer.MAX_VALUE;
		for (String s: upRight) {
			String[] coordinates = s.split(" ");
			int row = Integer.parseInt(coordinates[0]);
			int col = Integer.parseInt(coordinates[1]);
			if (row < minRow)
				minRow = row;
			if (col < minCol)
				minCol = col;
		}
		return minRow * minCol;
	}


	/**
	 * @category: Hackerrank
	 * @apiNote: Given a graph of friends who have different interests, determine which
	 * groups of friends have the most interests in common. Then use a little
	 * math to determine a value to return.
	 * The graph will be represented as a series of nodes numbered
	 * consecutively from 1 to friends_nodes. Friendships have evolved based
	 * on interests which will be represented as weights in the graph. Any
	 * members who share the same interest are said to be connected by that
	 * interest. Once the node pairs with the maximum number of shared
	 * interests are determined, multiply the friends_nodes of the resulting
	 * node pairs and return the maximal product.
	 * @implNote time complexity: ?;
	 */
	public int maxShared(int friendsNodes, List<Integer> friendsFrom, List<Integer> friendsTo, List<Integer> friendsWeight) {
		Map<Integer, Set<Integer>> weightNodeMap = new HashMap<>();
		for (int i = 0; i < friendsWeight.size(); i++) {
			int p = friendsWeight.get(i);
			if(weightNodeMap.containsKey(p)){
				Set<Integer> temp = weightNodeMap.get(p);
				temp.add(friendsFrom.get(i));
				temp.add(friendsTo.get(i));
				weightNodeMap.put(p,temp);
			}else {
				weightNodeMap.put(p, Stream.of(friendsFrom.get(i),friendsTo.get(i)).collect(Collectors.toSet()));
			}
		}

		//build up all pairs
		Map<List<Integer>, Integer> pairCounts = new HashMap<>();
		weightNodeMap.values().forEach(v->{
			List<List<Integer>> combinations = calculateCombinatorialNR(v.size(),2);
			List<Integer> vList = v.stream().collect(Collectors.toList());
			combinations.forEach(c->{
				List<Integer> p = Arrays.asList(vList.get(c.get(0)),vList.get(c.get(1)));
				if (pairCounts.containsKey(p)){
					Integer i = pairCounts.get(p);
					pairCounts.put(p,++i);
				}else {
					pairCounts.put(p,1);
				}
			});
		});


		int maxValueInPairCounts=(Collections.max(pairCounts.values()));
		List<Integer> rates = pairCounts.entrySet().stream()
				.filter(e->e.getValue() == maxValueInPairCounts)
				.map(p->p.getKey().get(0) * p.getKey().get(1))
				.sorted(Collections.reverseOrder()).collect(Collectors.toList());

		return rates.get(0);
	}


	/**
	 * @category: Unknown
	 * @apiNote: Generate all possible subset of size r of the given array with distinct elements.
	 * @implNote time complexity; O(c(n,r))
	 */
	public List<List<Integer>> calculateCombinatorialNR(int n, int r) {
		List<List<Integer>> combinations = new ArrayList<>();
		combinatorialHelper(combinations, new int[r], 0, n-1, 0);
		return combinations;
	}

	private void combinatorialHelper(List<List<Integer>> combinations, int data[], int start, int end, int index) {
		if (index == data.length) {
			combinations.add(Arrays.stream(data).boxed().collect(Collectors.toList()));
		} else if (start <= end) {
			data[index] = start;
			combinatorialHelper(combinations, data, start + 1, end, index + 1);
			combinatorialHelper(combinations, data, start + 1, end, index);
		}
	}

	/**
	 * @category: hackerrank
	 * @apiNote: Find the number of strings of a given length that can be formed under the following rules:
	 * Each letter is a vowel, that is, it is in the set {a, e, i, o, u}.
	 * The letter a may only be followed by the letter e.
	 * An e may only be followed by an a or an i.
	 * An i may not be next to another i.
	 * The letter o may only be followed by an i or a u.
	 * The letter u may only be followed by an a.
	 * @implNote time complexity;
	 */
	static int modulo = 1000000007;
	public int countPerms(int n){
		char[] vowels = new char[]{'a','e','i','o','u'};
		int count = buildAllNLengthString(vowels, "", n,0);
		return count;
	}

	private int buildAllNLengthString(char[] set, String prefix, int k,int counter){
		if (k == 0){
			if(rulesValid(prefix.toCharArray())){
				counter++;
				if (counter>modulo)
					counter= counter % modulo;
			}
			return counter;
		}

		for (int i = 0; i < set.length; ++i){
			String newPrefix = prefix + set[i];
			counter = buildAllNLengthString(set, newPrefix,k - 1,counter);
		}
		return counter;
	}

	/*
	* Each letter is a vowel, that is, it is in the set {a, e, i, o, u}.
The letter a may only be followed by the letter e.
An e may only be followed by an a or an i.
An i may not be next to another i.
The letter o may only be followed by an i or a u.
The letter u may only be followed by an a.
	* */
	private static boolean rulesValid(char[] sCharArray) {
		for (int i = 0; i < sCharArray.length-1; i++) {
			if (!((sCharArray[i] == 'a' && sCharArray[i+1] == 'e')
			|| (sCharArray[i] == 'e' && (sCharArray[i+1] == 'a' || sCharArray[i+1] == 'i'))
			|| (sCharArray[i] == 'i' && sCharArray[i+1] != 'i')
			|| (sCharArray[i] == 'o' && (sCharArray[i+1] == 'i' || sCharArray[i+1] == 'u'))
			|| (sCharArray[i] == 'u' && sCharArray[i+1] == 'a')))
				return false;
		}
		return true;
	}


	/**
	 * @category: LeetCode
	 * @apiNote: Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
	 * @implNote time complexity; O(n 2^(n-1))
	 */
//	public List<String> generateParenthesis(int n) {
//		int iterationCount = 2;
//		List<String> result = new ArrayList<>();
//		for (int i = 1; i <iterationCount ; i++) {
//			String bitString = Integer.toBinaryString(i);
//			bitString = bitString.substring(bitString.length() - 2*n);
//			if (i <= n)
//				iterationCount *=2;
//			if (isWellFormedParenthesis(bitString))
//				result.add(bitString);
//		}
//		return result;
//	}
//
//	private boolean isWellFormedParenthesis(String bitString) {
//		return false;
//	}

	/**
	 * @category: LeetCode
	 * @apiNote: Given the head of a linked list, remove the nth node from the end of the list, in one pass and return its head.
	 * @implNote time complexity; O(n), n: size of linkedlist
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode slow = head;
		ListNode fast = head;
		int c_nodes = 1;

		while (slow != null){

			if (fast!=null){
				fast = fast.next;
				if (fast!=null){
					c_nodes++;
					slow = slow.next;
					fast = fast.next;
					if (fast!=null)
						c_nodes++;
				}

			}else {
				if(c_nodes - n == 0)
					return head.next;

				int middlePoint = (c_nodes / 2)+1;
				if (c_nodes - n > middlePoint){
					while (middlePoint != c_nodes - n){
						middlePoint++;
						slow = slow.next;
					}
				}else {
					slow = head;
					for (int i = 1; i < c_nodes - n; i++) {
						slow = slow.next;
					}
				}

				if (slow.next != null)
					slow.next = slow.next.next;

				break;
			}

		}

		return head;
	}

	/**
	 * @category: LeetCode
	 * @apiNote: Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
	 * @implNote time complexity; O(4^n) : a digit can map with 4 letter (for loop)
	 */
	public List<String> letterCombinations(String digits) {
		List<String> result = new ArrayList<>();
		if (digits == null || digits.length()==0)
			return result;

		Queue<String> queue = new LinkedList<>();
		String[] digitLetterMatches = new String[]{" ", "+","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

		queue.add("");
		int[] digitArr = digits.chars().map(p-> p - '0').toArray();

		while(!queue.isEmpty()){
			String s = queue.remove();

			if(s.length() == digitArr.length){
				result.add(s);
			}else {
				digitLetterMatches[digitArr[s.length()]]
						.chars()
						.forEach(l->queue.add(s+Character.toString(l)));
			}
		}
		return result;
	}

	/**
	 * @category: LeetCode
	 * @apiNote: Add Two Numbers. You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
	 *
	 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
	 * @implNote time complexity; O(n)
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result = new ListNode((l1.val + l2.val)%10);
		int carry = l1.val + l2.val >= 10 ? 1:0;
		ListNode temp = result;

	  	while (l1.next != null && l2.next!= null){
			l1 = l1.next;
			l2 = l2.next;
	  		temp.next = new ListNode((l1.val + l2.val + carry)%10);
			carry = l1.val + l2.val + carry >= 10 ? 1:0;
			temp = temp.next;
		}

		while (l1.next != null){
			l1 = l1.next;
			temp.next = new ListNode((l1.val + carry)%10);
			carry = l1.val + carry >= 10 ? 1:0;
			temp = temp.next;
		}

		while (l2.next != null){
			l2 = l2.next;
			temp.next = new ListNode((l2.val + carry)%10);
			carry = l2.val + carry >= 10 ? 1:0;
			temp = temp.next;
		}

		if(carry>0)
			temp.next = new ListNode(carry);

	  	return result;
	}

	/**
	 * @category: LeetCode
	 * @apiNote: Given an m x n matrix, return all elements of the matrix in spiral order.
	 * @implNote time complexity; O(n)
	 */
	public List<Integer> spiralOrderWithRecursion(int[][] matrix) {
		return spiralOrderWithRecursion(new ArrayList<>(),matrix,0);
	}

	private List<Integer> spiralOrderWithRecursion(List<Integer> result,int[][] matrix, int loopCounter ){
		int cRow = matrix.length;
		int cCol = matrix[0].length;
		int minOfRowAndCol = Math.min(cRow,cCol);
		int exitCondition = (minOfRowAndCol%2 == 0) ? minOfRowAndCol/2 : (minOfRowAndCol/2)+1;
		if(loopCounter == exitCondition)
			return result;

		//step forward
		for (int i = loopCounter; i <cCol-loopCounter; i++){
			result.add(matrix[loopCounter][i]);
		}

		//step down
		for (int i = loopCounter+1; i <cRow-loopCounter; i++){
			result.add(matrix[i][cCol - loopCounter - 1]);
		}

		//step backward
		for (int i = cCol-loopCounter-2; i >=loopCounter && loopCounter+1<cRow-loopCounter; i--){
			result.add(matrix[cRow - loopCounter - 1][i]);
		}

		//step up
		for (int i = cRow - loopCounter- 2; i > loopCounter && cCol-loopCounter-2>=loopCounter; i--){
			result.add(matrix[i][loopCounter]);
		}

		loopCounter++;
		return spiralOrderWithRecursion(result, matrix, loopCounter);
	}

	/**
	 * @category: LeetCode
	 * @apiNote: Given an m x n matrix, return all elements of the matrix in spiral order.
	 * @implNote time complexity; O(n)
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		int cRow = matrix.length;
		int cCol = matrix[0].length;
		List<Integer> result = new ArrayList<>();
		int cMin = 0,visitedCellCount = 0;
		while (visitedCellCount < cRow * cCol){
			for (int i = cMin; i <cCol - cMin; i++){
				visitedCellCount++;
				result.add(matrix[cMin][i]);
			}

			for (int i = cMin+1; i <cRow - cMin; i++){
				visitedCellCount++;
				result.add(matrix[i][cCol-(cMin+1)]);
			}

			if(visitedCellCount == cRow * cCol)	// 1 x n and n x 1 matrices
				break;

			for (int i = cCol - (cMin+2); i >=cMin; i--){
				visitedCellCount++;
				result.add(matrix[cRow-(cMin+1)][i]);
			}
			if(visitedCellCount == cRow * cCol)
				break;
			for (int i = cRow - (cMin+2); i > cMin; i--){
				visitedCellCount++;
				result.add(matrix[i][cMin]);
			}
			cMin++;
		}

		return result;
	}

	/**
	 * @category: LeetCode
	 * @apiNote:  Complete the function isBalanced in the editor below. It must return a string: YES if the sequence is balanced or NO if it is not.
	 * @implNote time complexity; O(n) under assumption that HashMap.containsKey method has complexity of O(1)
	 * @see 'Javascript' implementation below
	 * function twoSum(inputArray, target) {
	 *     var tempArray = [];
	 *
	 *     for (i = 0; i < inputArray.length; i++) {
	 *         if (tempArray[inputArray[i] + ""] == undefined) {
	 *             tempArray[inputArray[i] + ""] = [];
	 *             tempArray[inputArray[i] + ""][0] = 0;
	 *             tempArray[inputArray[i] + ""][1] = 0;
	 *         }
	 *
	 *         tempArray[inputArray[i] + ""][0]++;
	 *         tempArray[inputArray[i] + ""][1] = i;
	 *
	 *         if (tempArray[(target - inputArray[i]) + ""] == undefined) {
	 *             tempArray[(target - inputArray[i]) + ""] = [];
	 *             tempArray[(target - inputArray[i]) + ""][0] = 0;
	 *             tempArray[(target - inputArray[i]) + ""][1] = 0;
	 *         }
	 *         tempArray[(target - inputArray[i]) + ""][0]++;
	 *     }
	 *
	 *     var result = [];
	 *     var index = 0;
	 *     for (i = 0; i < inputArray.length; i++) {
	 *         if (tempArray[inputArray[i] + ""][0] > 1) {
	 *             result[index++] = [tempArray[inputArray[i] + ""][1], tempArray[(target - inputArray[i]) + ""][1]];
	 *         }
	 *     }
	 *
	 *     return result;
	 * }
	 */
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> ranks = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if (ranks.containsKey(complement)) {
				return new int[] { ranks.get(complement), i };
			}
			ranks.put(nums[i], i);
		}

		return null;
	}

	/**
	 * @category: Hackerrank
	 * @apiNote:  Complete the function isBalanced in the editor below. It must return a string: YES if the sequence is balanced or NO if it is not.
	 * @implNote time complexity; O(n)
	 */
	public String isBalanced(String s) {
		if (s.length()%2 == 1)
			return "NO";

		char[] unMatchedBrackets = new char[s.length()];

		int unMatchedBracketCount = 0;

		for (int i = 0;i<s.length();i++){

			switch (s.charAt(i)){
				case '{':
				case '[':
				case '(':
					unMatchedBrackets[unMatchedBracketCount++] = s.charAt(i);
					break;
				case '}':
					if (unMatchedBracketCount<=0
							|| unMatchedBrackets[--unMatchedBracketCount] !='{')
						return "NO";
					break;
				case ']':
					if (unMatchedBracketCount<=0
							|| unMatchedBrackets[--unMatchedBracketCount] !='[')
						return "NO";
					break;
				case ')':
					if (unMatchedBracketCount<=0
							|| unMatchedBrackets[--unMatchedBracketCount] !='(')
						return "NO";
					break;
				default:return "NO";
			}

		}

		return unMatchedBracketCount == 0 ? "YES":"NO";
	}

	/**
	 * @category: Cracking The Coding Interview
	 * @apiNote:  Numbers are randomly generated and stored into an (expanding) array. keep track of the median
	 * @implNote time complexity;
	 */
	public IntHeap minHeap = new IntHeap(IntHeap.MinMaxType.MIN_HEAP);
	public IntHeap maxHeap = new IntHeap(IntHeap.MinMaxType.MAX_HEAP);
//	PriorityQueue<Integer> minHeap = new PriorityQueue<>(10); // for saving bigger elements
//	PriorityQueue<Integer> maxHeap = new PriorityQueue<>(10,Collections.reverseOrder()); // for saving smaller elements
	public int medianOfExpandingArray(int val) {
		int median = 0;

		if (val<minHeap.peek()){
			maxHeap.add(val);
		}else {
			minHeap.add(val);
		}

		if (maxHeap.size()> minHeap.size()+1){
			minHeap.add(maxHeap.poll());
		}else if (minHeap.size()> maxHeap.size()+1){
			maxHeap.add(minHeap.poll());
		}

		if (minHeap.size()==maxHeap.size())
			median = (minHeap.peek()+ maxHeap.peek())/2;
		else if (minHeap.size()> maxHeap.size())
			median = minHeap.peek();
		else if (maxHeap.size()>minHeap.size())
			median = maxHeap.peek();

		return median;
	}

	static final int MAX_CHAR_COUNT = 256;	// extended ASCII char count

	public String[] permute(String s) {
		List<String> result = new ArrayList<String>();
		permute(s.toCharArray(), 0,result);
		return result.toArray(new String[result.size()]);
	}

	private void permute(char[] sCharArray,int  l,List<String> result) {
		if (l == sCharArray.length - 1) {
//			System.out.println(sCharArray);
			result.add(new String(sCharArray));
		}

		for(int i=l;i<sCharArray.length;i++) {
			swap(sCharArray,l,i);
			permute(sCharArray, l+1,result);
			swap(sCharArray,l,i);
		}
	}

	private void swap(char[] c,int i, int l) {
		char temp = c[i];
		c[i] = c[l];
		c[l] = temp;
	}

	/**
	 * @category: Cracking The Coding Interview
	 * @apiNote:  Given a smaller strings and a bigger string b, design an algorithm to find all permutations of the shorter string within the longer one.
	 * Print the location of each permutation
	 * @implNote time complexity; n:= length of b, m:= length of s => O(256 * n) = O(n)
	 */
	public String[] permutationsInLongString2(String s, String b){
		int M = s.length();
		int N = b.length();
		List<String> result = new ArrayList<String>();

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
	 * @category: Cracking The Coding Interview
	 * @apiNote:  Given a smaller strings and a bigger string b, design an algorithm to find all permutations of the shorter string within the longer one.
	 * Print the location of each permutation
	 * @implNote time complexity; n:= length of b, m:= length of s => O(n m log(m))
	 */
	public String[] permutationsInLongString(String s, String b) {
		char[] sArray = s.toCharArray();
		Arrays.sort(sArray);
		String orderedS = new String(sArray);
		List<String> result = new ArrayList<String>();

		for (int i = 0;i<b.length()-s.length()+1;i++){
			String bPartial = b.substring(i,i+s.length());
			char[] bPartialArray = bPartial.toCharArray();
			Arrays.sort(bPartialArray);
			String orderedbPartialArray = new String(bPartialArray);
			if (orderedS.equals(orderedbPartialArray)){
//				System.out.println(bPartial);
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
			String repeatedTemplate = "";
			for (int i = 0; i < aArray.length; i++) {
				repeatedTemplate += aArray[i];
				if (!A.equals(repeatedTemplate) && A.split(repeatedTemplate).length == 0)
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
