package net.questions.solutions;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

	/**@implNote :Rotate List
	 * @category: Leetcode
	 * */
	public ListNode rotateRight(ListNode head, int k) {
		if(head == null || head.next == null) return head;
		ListNode temp = head;
		ListNode runner = head;

		int len = 1;
		while(runner.next != null){
			temp = temp.next;
			len++;
			if(runner.next!= null){
				runner = runner.next;
			}

			if(runner != null && runner.next != null){
				runner = runner.next;
				len++;
			}
		}

		int kModLen = k % len;
		if(kModLen == 0)
			return head;

		int mid = len % 2 == 0 ? len/2 : len/2 +1  ;
		if(kModLen < mid){
			while(--mid > kModLen){
				temp = temp.next;
			}
		}else{
			temp = head;
			while(++kModLen < len){
				temp = temp.next;
			}
		}

		runner.next = head;
		head = temp.next;
		temp.next = null;

		return head;
	}

	/**@implNote :Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.
	 * Input: target = 7, nums = [2,3,1,2,4,3], Output: 2
	 * @category: Leetcode
	 * */
	public int minSubArrayLen(int target, int[] nums) {
		int sumUpTo = 0;
		int minLength = Integer.MAX_VALUE;
		int beginner = 0;

		for(int i=0;i<nums.length;i++){
			sumUpTo += nums[i];
			while(sumUpTo>=target){
				sumUpTo -=nums[beginner];
				if(i - beginner < minLength)
					minLength = i - beginner +1;
				beginner++;
			}

		}

		return minLength == Integer.MAX_VALUE?0:minLength;
	}

	/**@implNote :return the longest word in the string. If there are two or more words that are the same length, return the first word from the string with that length. Ignore punctuation and assume sen will not be empty. Words may also contain numbers, for example "Hello world123 567"
	 * Input: "fun&!! time" Output: time, Input: "I love dogs" Output: love
	 * @category: coderbyte
	 * */
	public static String longestWord(String sen) {
		int beginIndex = 0;
		int endIndex = 0;
		int resultBeginIndex = 0;
		int resultEndIndex = 0;
		for (int i = 0; i < sen.length(); i++) {
			if(sen.charAt(i) == ' '){
				if(endIndex - beginIndex>resultEndIndex - resultBeginIndex){
					resultBeginIndex = beginIndex;
					resultEndIndex = endIndex;
				}
				beginIndex = i+1;
				endIndex = i+1;
			}else {
				if ((sen.charAt(i) >= 'a' && sen.charAt(i) <= 'z')
						|| (sen.charAt(i) >= 'A' && sen.charAt(i) <= 'Z')) {
					endIndex++;
				}
			}
		}
		return sen.substring(resultBeginIndex,resultEndIndex);
	}

	/**@implNote :strArr which will contain 2 elements: the first element will represent a list of comma-separated numbers sorted in ascending order, the second element will represent a second list of comma-separated numbers (also sorted). Your goal is to return a comma-separated string containing the numbers that occur in elements of strArr in sorted order. If there is no intersection, return the string false
	 * Input: new String[] {"1, 3, 4, 7, 13", "1, 2, 4, 13, 15"}, Output: 1,4,13
	 * Input: new String[] {"1, 3, 9, 10, 17, 18", "1, 4, 9, 10"},Output: 1,9,10
	 * @category: coderbyte
	 * */
	public String findIntersection(String[] strArr) {
		String[] numsStr1 = strArr[0].replace(" ","").split(",");
		String[] numsStr2 = strArr[1].replace(" ","").split(",");
		int[] nums1 = new int[numsStr1.length];
		int[] nums2 = new int[numsStr2.length];
		for(int i = 0;i<numsStr1.length;i++) nums1[i] = Integer.parseInt(numsStr1[i]);
		for(int i = 0;i<numsStr2.length;i++) nums2[i] = Integer.parseInt(numsStr2[i]);

		StringBuilder sb = new StringBuilder();
		int j = 0;
		for(int i = 0;i<nums1.length;i++){
			for(;j<nums2.length && nums2[j]<=nums1[i];j++){
				if(nums1[i]== nums2[j]){
					sb.append(nums2[j]);
					sb.append(",");
					break;
				}
			}
		}
		if(sb.length()>0)
			sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

/*
    * The King of FarFarAway, is considering building some castles on the border. The border is divided into N segments. The King knows the height of the terrain in each segment of the border.
    * The height of each segment of terrain is stored in array A, with A[P] denoting the height of the P-th segment of the border.
    * The King has decided to build a castle on top of every hill and in the bottom of every valley.
Let [P..Q] denote a group of consecutive segments from P to Q inclusive such that (0 ≤ P ≤ Q ≤ N−1). Segments [P..Q] form a hill or a valley if all the following conditions are satisfied:

The terrain height of each segment from P to Q is the same (A[P] = A[P+1] = ... = A[Q]);
If P > 0 then A[P−1] < A[P] (for a hill) or A[P−1] > A[P] (for a valley);
If Q < N−1 then A[Q+1] < A[Q] (for a hill) or A[Q+1] > A[Q] (for a valley);

That is, a hill is higher than its surroundings and a valley is lower than its surroundings. Note that if the surroundings on either side of the hill or valley don't exist (i.e. at the edges of the area under consideration, where P = 0 or Q = N−1), then the condition is considered satisfied for that side of the hill/valley.
The king is wondering how many castles is he going to build. Can you help him?
For example, consider the following array A = [2, 2, 3, 4, 3, 3, 2, 2, 1, 1, 2, 5].
There are two hills: [3..3] and [11..11]. There are also two valleys: [0..1] and [8..9]. There are no other suitable places for castles.

Write a function:
that, given an array A consisting of N integers, as explained above, returns the total number of hills and valleys.
For example, given array A as described above, the function should return 4.
Given array A = [−3, −3] describing segments with a terrain height below 0, segment [0..1] forms both a hill and a valley, and only one castle can be built, so the function should return 1.
Write an efficient algorithm for the following assumptions:
N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
    * */
    public int findCastlesCount(int[] A) {
        // write your code in Java SE 8
        int castles = 1;
        int N = A.length;

        for(int i = 0; i< N-1; i++){
            if(A[i] == A[i+1]) continue;
            int index = i;
            if(A[i]<A[i + 1]){
                while (index<N-1 && A[index]<=A[index + 1]){
                    index++;
                }
            }else {
                while (index<N-1 && A[index]>=A[index + 1]){
                    index++;
                }
            }

            if(i != index){
                castles++;
                i = index-1;
            }
        }
        return castles;
    }

	/*
    Question Description

Given a stock price of one company for a day, create an method that would return the maximum profit for the day given the following conditions:

- You can only buy and sell once.
- The difference between buy and sell time must be at least five seconds long.
Assumptions:

- The difference in time between each stock price is 1 second long.
- Stock prices are already in order by time.
- If there is no profit for the day, return 0.
    * */

    public static long getMaxProfit(List<Long> prices){
        long maxProfit = 0;
        long minPrice = Long.MAX_VALUE;
        for (int i = 0; i < prices.size()-5; i++) {
            minPrice = Math.min(prices.get(i),minPrice);
            maxProfit = Math.max(maxProfit,prices.get(i+5)-minPrice);
        }
        return maxProfit>0?maxProfit:0;
    }

	public static int sherlockAndAnagrams(String s) {
		// Write your code here
		Map<String,List<String>> anagramsList = new HashMap<>();
		for (int i = 0; i < s.length() ; i++) {
			for (int j = 0;  j+i < s.length(); j++) {
				String key = s.substring(j, j+i + 1);
				if (anagramsList.containsKey(key)) {
					List<String> angr = new ArrayList<>(anagramsList.get(key));
					angr.add(key);
					anagramsList.put(key,angr);
				} else {
					char[] subStr = key.toCharArray();
					Arrays.sort(subStr);
					String mapKey = new String(subStr);
					if(anagramsList.containsKey(mapKey)){
						List<String> angr = new ArrayList<>(anagramsList.get(mapKey));
						angr.add(key);
						anagramsList.put(mapKey,angr);
					}else {
						anagramsList.put(mapKey,Arrays.asList(key));
					}
				}
			}
		}
		return anagramsList.entrySet().stream().filter(f->f.getValue().size()>1)
				.map(i->{
					int size = i.getValue().size();
					return size * (size-1)/2;
				})
				.reduce(0,(a,b)->a+b);
	}

	public static int maxSubArray(int[] nums) {
		int len = nums.length;
		int sum = Arrays.stream(nums).sum();
		int result = Integer.MIN_VALUE;
		int leftSum = 0;
		int rightSum = 0;
		for(int i = 0; i<len; i++){
			leftSum+=nums[i];
			rightSum+=nums[len-1-i];
			if(sum - leftSum>result)
				result = sum - leftSum;
			if(sum - rightSum>result)
				result = sum - rightSum;

		}
		return result;
	}

	/**@implNote: given a string S, find how many character should be deleted to ensure that every character has unique count
	 * e.g. S:"abcd" return 3, S:"aaaabbbb" return 1
	 * */
	public int findHowManyCharShouldBeDeleted(String S) {
		// write your code in Java SE 8
		int len = S.length();
		int[] ocurences = new int[26];
		for(int i =0;i<len;i++){
			ocurences[S.charAt(i)-'a']++;
		}

		int deleteCount = 0;
		List<Integer> uniqueNumbers = new ArrayList<>();

		ocurences = IntStream.of(ocurences)
				.boxed()
				.sorted(Comparator.reverseOrder())
				.mapToInt(i -> i)
				.toArray();

		uniqueNumbers.add(ocurences[0]);
		for(int i =1;i<26;i++){
			if(ocurences[i] == 0) continue;

			if(uniqueNumbers.contains(ocurences[i])){
				int last = uniqueNumbers.get(uniqueNumbers.size()-1);
				if(ocurences[i] > last){
					int currentDeletion = Math.abs(last - ocurences[i] -1);
					deleteCount +=currentDeletion;
					if(ocurences[i] - currentDeletion>0)
						uniqueNumbers.add(ocurences[i] - currentDeletion);

				}else {
					deleteCount++;
					if(ocurences[i]-1>0)
						uniqueNumbers.add(ocurences[i]-1);
				}
			}else{
				uniqueNumbers.add(ocurences[i]);
			}
		}

		return deleteCount;
	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		Set<List<Integer>> set = new HashSet<>();
		combinationSum2Dfs(0,candidates,target,new ArrayList<>(),set,0);
		List<List<Integer>> combinations = new ArrayList<>();
		set.forEach(c->combinations.add(new ArrayList<>(c)));
		return combinations;
	}

	public void combinationSum2Dfs(int index,int[] candidates, int target,List<Integer> combinationCandidate,Set<List<Integer>> combinations,int sum) {
		if (sum==target) {
			combinations.add(new ArrayList<>(combinationCandidate));
			return;
		}

		for (int i = index; i < candidates.length; i++) {
			if(candidates[i] + sum <= target) {
				combinationCandidate.add(candidates[i]);
				combinationSum2Dfs(i+1 , candidates,target,combinationCandidate,combinations,candidates[i] + sum);
				combinationCandidate.remove(combinationCandidate.size()-1);
			}
		}
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<List<Integer>> combinations = new ArrayList<>();
		combinationSumDfs(0,candidates,target,new ArrayList<>(),combinations,0);
		return combinations;
	}

	public void combinationSumDfs(int index,int[] candidates, int target,List<Integer> combinationCandidate,List<List<Integer>> combinations,int sum) {
		if (sum==target) {
			combinations.add(new ArrayList<>(combinationCandidate));
			return;
		}


		for (int i = index; i < candidates.length; i++) {
			if(candidates[i] + sum <= target) {
				combinationCandidate.add(candidates[i]);
				combinationSumDfs(i , candidates,target,combinationCandidate,combinations,candidates[i] + sum);
				combinationCandidate.remove(combinationCandidate.size()-1);
			}
		}
	}

	public int divide(int dividend, int divisor) {
		if(dividend == Integer.MIN_VALUE){
			if(divisor == -1) return Integer.MAX_VALUE;
		}

		long absDividend = dividend < 0 ? -((long)dividend):(long)dividend;
		long absDivisor = divisor < 0 ? -((long)divisor):(long)divisor ;
		boolean negative = dividend < 0 ^ divisor < 0;

		if(absDividend < absDivisor) return 0;
		if(absDivisor == 1) return (int)(negative ? -absDividend :absDividend);

		long quotient = 1;

		while ((quotient+quotient) * absDivisor <= absDividend)
			quotient = quotient<<1;

		long midQuotient = quotient;

		while (midQuotient != 1){
			midQuotient =midQuotient>>1;
			if ((quotient +midQuotient)* absDivisor > absDividend)
				continue;
			quotient += midQuotient;
		}
		return (int)(negative ? -quotient :quotient);
	}

	public int divide2(int dividend, int divisor) {
		if(dividend == Integer.MIN_VALUE){if(divisor == -1)     return   Integer.MAX_VALUE; }

		//if(Math.abs(divisor)>Math.abs(dividend)) return 0;

		int remainder = 0;
		int quotient = 0;
		boolean isNegativeResult = false;

		if((divisor<0 && dividend<0) || (divisor>0 && dividend>0) ){
			remainder = divisor - divisor - divisor;
		}else{
			remainder = divisor;
			isNegativeResult = true;
		}

		if(divisor == 1 || divisor == -1){
			return isNegativeResult ? dividend<0? dividend:-1*dividend
					: dividend<0? -1*dividend:dividend;
		}

		while(isInInterval(dividend,divisor)){
			dividend += remainder;
			quotient++;
		}

		if(isNegativeResult ){
			quotient = quotient - quotient - quotient;
		}
		return quotient;
	}

	private boolean isInInterval(int dividend,int divisor){
		long lDividend = dividend;
		long lDivisor = divisor;
		return Math.abs(lDividend)>=Math.abs(lDivisor);
	}

	public int strStr(String haystack, String needle) {
		int nLength = needle.length();
		if(nLength==0) return 0;
		int hLength=haystack.length();

		if(nLength>hLength) return -1;
		int result =-1;
		for (int i=0;i<hLength;i++){

			int j = 0;
			while(j<=nLength/2
					&& i+j<hLength
					&& i+nLength <= hLength
					&& haystack.charAt(i+j) == needle.charAt(j)
					&& haystack.charAt(i+nLength-j-1)==needle.charAt(nLength-j-1)){
				j++;

			}
			j *= 2;
			j -= nLength % 2==1 ?1:2;

			if(j==nLength){
				result=i;
				break;
			}
		}
		return result;
	}

	// x+2y=t
	public List<Integer> choosePossibleSolutions(List<Integer> list){
		List<Integer> result = new ArrayList<>();
		for (Integer w: list) {
			if(w%2 == 0){
				result.add((w/2)+1);
			}else{
				result.add(0);
			}
		}
		return result;
	}

	public int treasureIsland(String[] island) {
		if (island == null || island.length == 0) return 0;

		int steps = 0;
		Queue<Integer[]> queue = new LinkedList<>();
		queue.add(new Integer[]{0,0});
		boolean[][] visited = new boolean[island.length][island[0].length()];
		visited[0][0] = true;
		//directions; forward, backward, top, down
		int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

		// bfs
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Integer[] coordinate = queue.poll();
				int x = coordinate[0];
				int y = coordinate[1];
				if (island[x].charAt(y) == 'X') return steps;

				for (int[] dir : dirs) {
					int newX = x + dir[0];
					int newY = y + dir[1];

					if (newX >= 0
							&& newX < island.length
							&& newY >= 0
							&& newY < island[0].length()
							&& island[x].charAt(y) != 'D'
							&& !visited[newX][newY]) {
						queue.add(new Integer[]{newX,newY});
						visited[newX][newY] = true;
					}
				}
			}
			steps++;
		}
		return 0;
	}

	public List<String> generateParenthesis(int n) {
		int start = ((int)Math.pow(2,n)-1);
		int end = ((int)Math.pow(2,2*n) - 1)/3;

		List<String> result = new ArrayList<>();
		for (int i = start; i <= end; i+=2) {
			int sum = 0;
			StringBuilder wellFormed = new StringBuilder();
			for (int j = 2*n - 1; j >= 0; j--) {
				int iBit = (i >> j) & 1;
				if (iBit == 0){
					sum++;
					wellFormed.append("(");
				}else {
					sum--;
					wellFormed.append(")");
				}
				if (sum > n+1 || sum<0) break;
			}
			if (sum==0)
				result.add(wellFormed.toString());
		}
		
		return result;
	}

	public String longestCommonPrefix(String[] strs) {
		StringBuilder sb = new StringBuilder("");
		int position = 0;
		boolean isSame=false;
		while(position >= 0){
			for (int i = 0; i < strs.length-1; i++) {
				if (position >= strs[i].length())
					break;
				isSame = strs[i].charAt(position) == strs[i+1].charAt(position);
				if (!isSame)
					break;
			}
			position++;
			if (isSame)
				sb.append(strs[0].charAt(position));
			else break;
		}
		return sb.toString();
	}

	/**
	 * @category: Leetcode
	 * @apiNote: Given a roman numeral, convert it to an integer.
	 * @implNote time complexity; O(s.length())
	 *
	 */
	public int romanToInt(String s) {
		Map<Character,Integer> map = new HashMap<>();
		map.put('I',1);
		map.put('V',5);
		map.put('X',10);
		map.put('L',50);
		map.put('C',100);
		map.put('D',500);
		map.put('M',1000);

		int i = s.length()-1;
		int result = 0;
		while(i>=0){
			int num = map.get(s.charAt(i));
			result+= num;
			i--;
			while(i>=0 && map.get(s.charAt(i))< num){
				result -=map.get(s.charAt(i));
				i--;
			}

		}

		return result;
	}


	/**
	 * @category: Hackerrank
	 * @apiNote: Reach the End in Time: A 2-D grid consisting of some blocked (represented as '#') and some unblocked (represented as '.') cells is given. The starting position of a pointer is in the top-left corner of the grid. It is guaranteed that the starting position is in an unblocked cell. It is also guaranteed that the bottom-right cell is unblocked. Each cell of the grid is connected with its right, left, top, and bottom cells (if those cells exist). It takes 1 second for a pointer to move from a cell to its adjacent cell. If the pointer can reach the bottom-right corner of the grid within k seconds, return the string 'Yes'. Otherwise, return the string 'No'.
	 * @implNote time complexity;
	 */
	public String reachTheEnd(String[] grid,int maxTime){
		/*
		give a number for all the cells in the grid starting from 0 for (0,0)
		when you wanna reach any cell by number implement division algorithm wrt colCount as divisor
		e.g. 11 = 2 (4)+ 3 means (2,3) for 4 columns grid. there is no need to use an additional class for pairs.
		so the target cell that must be reached is (cRow-1)(cCol-1)
		*/
		int nodeNumber = 0;
		Map<Integer,List<Integer>> graph = new HashMap<>();
		int cRow = grid.length;
		int cCol = grid[0].length();

		//construct the connected cells (contains '.'), a graph with adjacency list
		for (int i = 0; i < cRow; i++) {
			for (int j = 0; j < cCol; j++) {
				if (grid[i].charAt(j)=='.'){
					List<Integer> siblings = new ArrayList<>();
					if(j<cCol-1 && grid[i].charAt(j+1)=='.')
						siblings.add(nodeNumber+1);
					if(i<cRow-1 && grid[i+1].charAt(j)=='.')
						siblings.add(nodeNumber+cCol);
					graph.put(nodeNumber,siblings);
				}
				nodeNumber++;
			}
		}

		int reachTime = 0;
		int targetCellNo = cCol*cRow-1;

		//implement the dfs algorithm
		Stack<Integer> stack = new Stack<>();
		stack.add(0);
		boolean [][]visited = new boolean[cRow][cCol];

		while (!stack.isEmpty()){
			Integer currentNode = stack.pop();
			System.out.println("currentNode: " + currentNode);
			if(currentNode == targetCellNo && reachTime <= maxTime){
				return "Yes";
			}

			reachTime++;
			int rowCurrent = currentNode/cCol;
			int colCurrent = currentNode%cCol;
			if (!visited[rowCurrent][colCurrent]){
				visited[currentNode/cCol][currentNode%cCol]=true;
				List<Integer> subNodes = graph.get(currentNode);
				for (Integer i: subNodes) {
					if(!visited[i/cCol][i%cCol])
						stack.push(i);
				}
			}
		}

		return "No";
	}

	/**
	 * @category: Hackerrank
	 * @apiNote: Parking Dilemma: There are many cars parked in a parking lot. The parking lot is a straight line with a parking spot for every meter. There are n cars currently parked and a roofer wants to cover them with a roof. The requirement is that at least k cars currently in the lot are covered by the roof. Determine the minimum length of the roof to cover k cars.
	 * @implNote time complexity; O(nlogn); n:cars.length
	 *
	 */
	public long carParkingRoof(List<Long> cars, int k) {
		// Write your code here
		Collections.sort(cars);
		long result = Long.MAX_VALUE;
		for (int i = 0; i <= cars.size() - k; i++) {
			result = Math.min(result,cars.get(i+k-1) - cars.get(i)+1);
		}
		return result;
	}

	/**
	 * @category: Hackerrank
	 * @apiNote: Competitive Gaming: A group of friends are playing a video game together. During the game, each player earns a number of points. At the end of a round, players who achieve at least a certain rank get to "level up" their characters to gain increased abilities. Given the scores of the players at the end of a round, how many players will be able to level up?
	 * Note: Players with equal scores will have equal ranks, but the player with the next lower score will be ranked based on the position within the list of all players' scores. For example, if there are four players, and three players tie for first place, their ranks are 1, 1, 1, and 4.
	 * Note:  No player with a score of 0 can level up, regardless of rank.
	 * @implNote time complexity; O(nlogn); n:scores.length
	 *
	 */
	public int numPlayers(int k, List<Integer> scores) {
		int result = 0;
		List<Integer> ranks = new ArrayList<>();
		Collections.sort(scores,Collections.reverseOrder());
		int rankOrder = 1;
		ranks.add(rankOrder);
		for (int i = 1; i < scores.size(); i++) {
			rankOrder++;
			if(scores.get(i)==scores.get(i-1)){
				ranks.add(ranks.get(i-1));;
			}
			else{
				ranks.add(rankOrder);
			}
		}

		for (int i = 0; i < ranks.size(); i++) {
			if(ranks.get(i)<=k)
				result++;
		}

		return result;
	}


	/**
	 * @category: Leetcode
	 * @apiNote: Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.
	 * @implNote time complexity; O(inp.length)
	 *
	 */
	public List<String> commonChars(String[] A) {
		int[][] occurences = new int[A.length][26];
		for (int i = 0; i < A.length; i++) {
			char[] toChars = A[i].toCharArray();
			for (int j = 0; j < toChars.length; j++) {
				occurences[i][toChars[j]-'a']++;
			}
		}

		List<String> commonChars = new ArrayList<>();
		int counter = 0;
		int commonCharCounter = Integer.MAX_VALUE;
		for (int i = 0; i < 26; i++) {
			final int colNo = i;
			int[] col = IntStream.range(0, occurences.length)
					.map(j -> occurences[j][colNo]).toArray();
			for (int j = 0; j < col.length; j++) {
				if (col[j]>0){
					counter++;
				}

				if (col[j]<commonCharCounter)
					commonCharCounter = col[j];
			}
			if (counter>= A.length){
				for (int j = 0; j < commonCharCounter; j++) {
					commonChars.add(String.valueOf((char)('a'+colNo)));
				}
			}
			counter = 0;
			commonCharCounter = Integer.MAX_VALUE;
		}

		return commonChars;
	}

	/**
	 * @category: Leetcode
	 * @apiNote: You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
	 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1
	 * @implNote time complexity; O(coins.length * amount)
	 * space complexity: O(amount)
	 */
	public int coinChange(int[] coins, int amount) {
		if(amount == 0)
			return 0;

		int INF = Integer.MAX_VALUE - 1 ;
		long[] minCoins = new long[amount+1];
		Arrays.fill(minCoins,INF);
		minCoins[0] = 0;
		for (int j = 0; j < coins.length; j++) {
			for(int i = 0; i <= amount; i++) {
				if(i >= coins[j])
					minCoins[i] = Math.min(minCoins[i], minCoins[i - coins[j]] + 1);
			}
		}

		return (int) (minCoins[amount] != INF ? minCoins[amount] : -1);
	}

	/**
	 * @category: Leetcode
	 * @apiNote: Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
	 * Return the maximum product you can get.
	 * @implNote time complexity; O(n/3) = O(n)
	 */
	public int integerBreak(int n) {
		if(n==2)
			return 1;
		if(n== 3)
			return 2;

		int base = IntStream.range(1,n/3).reduce(1, (product, i) -> product * 3);

		if(n % 3 == 0){	//n is splitted as 3+3k, so the multiplication 3 * 3^k
			return 3*base;
		}else if(n % 3 == 1){	//n is splitted as 4+3k, so the multiplication 4 * 3^k
			return 4*base;
		}else {	//n is splitted as 2+3+3k, so the multiplication 2* 3 * 3^k
			return 2*3*base;
		}
	}

	/**
	 * @category: Unknown
	 * @apiNote: There are N blocks, numbered from 0 to N-1, arranged in a row. A couple of frogs were sitting together on one block when they had a terrible quarrel. Now they want to jump away from one another so that the distance between them will be as large as possible. The distance between blocks numbered J and K, where J ≤ K, is computed as K − J + 1. The frogs can only jump up, meaning that they can move from one block to another only if the two blocks are adjacent and the second block is of the same or greater height as the first. What is the longest distance that they can possibly create between each other, if they also chose to sit on the optimal starting block initially?
	 * @implNote time complexity;
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
	 * @implNote time complexity;
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
	 * @implNote time complexity; O(s.length * numRows)
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
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
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

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1 == null) return l2;
		if(l2 == null) return l1;

		ListNode head = null;
		ListNode temp = null;

		while(l1!=null && l2!=null){
			if(l1.val==l2.val){
				if(head == null){
					head = new ListNode(l1.val);
					temp = head;
				}else{
					temp.next = new ListNode(l1.val);
					temp = temp.next;
				}
				temp.next = new ListNode(l1.val);
				temp = temp.next;
				addNode(head,temp,l1.val);
				addNode(head,temp,l1.val);

				l1 = l1.next;
				l2 = l2.next;
			}else if(l1.val < l2.val){
				if(head == null){
					head = new ListNode(l1.val);
					temp = head;
				}else{
					temp.next = new ListNode(l1.val);
					temp = temp.next;
				}
				l1 = l1.next;
			}else{
				if(head == null){
					head = new ListNode(l2.val);
					temp = head;
				}else{
					temp.next = new ListNode(l2.val);
					temp = temp.next;
				}
				l2 = l2.next;
			}

		}

		while(l1!=null){
			temp.next = new ListNode(l1.val);
			temp = temp.next;
			l1 = l1.next;
		}

		while(l2!=null){
			temp.next = new ListNode(l2.val);
			temp = temp.next;
			l2 = l2.next;
		}

		return head;
	}

	private void addNode(ListNode head, ListNode current, int val){
		if(head == null){
			head = new ListNode(val,current);
			current = head;
			return;
		}
		current.next = new ListNode(val);
		current = current.next;
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
		int topMostIndex = 0;
		int bottomMostIndex = matrix.length-1;
		int leftMostIndex = 0;
		int rightMostIndex = matrix[0].length-1;
		int direction = 0;	// 0: right, 1:down, 2:left, 3:up
		List<Integer> result = new ArrayList<>();
		while (topMostIndex<=bottomMostIndex && leftMostIndex<=rightMostIndex){
			if(direction ==0){
				for (int i = leftMostIndex; i <= rightMostIndex; i++) {
					result.add(matrix[topMostIndex][i]);
				}
				topMostIndex++;
				direction=1;
			}else if(direction==1){
				for (int i = topMostIndex; i <= bottomMostIndex; i++) {
					result.add(matrix[i][rightMostIndex]);
				}
				direction=2;
				rightMostIndex--;
			}else if (direction==2){
				for (int i = rightMostIndex; i >= leftMostIndex; i--) {
					result.add(matrix[bottomMostIndex][i]);
				}
				direction=3;
				bottomMostIndex--;
			}else {
				for (int i = bottomMostIndex; i >= topMostIndex; i--) {
					result.add(matrix[i][leftMostIndex]);
				}
				direction=0;
				leftMostIndex++;
			}
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

	public boolean isBalancedWithStack(String s) {
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			char x = s.charAt(i);

			if (x == '(' || x == '[' || x == '{'){
				stack.push(x);
				continue;
			}

			if (stack.isEmpty())
				return false;
			char check;
			switch (x) {
				case ')':
					check = stack.pop();
					if (check == '{' || check == '[')
						return false;
					break;

				case '}':
					check = stack.pop();
					if (check == '(' || check == '[')
						return false;
					break;

				case ']':
					check = stack.pop();
					if (check == '(' || check == '{')
						return false;
					break;
			}
		}

		return stack.isEmpty();
	}

	/**
	 * @category: Cracking The Coding Interview
	 * @apiNote:  Numbers are randomly generated and stored into an (expanding) array. keep track of the median
	 * @implNote time complexity;
	 */
//	public IntHeap minHeap = new IntHeap(IntHeap.MinMaxType.MIN_HEAP);
//	public IntHeap maxHeap = new IntHeap(IntHeap.MinMaxType.MAX_HEAP);
	PriorityQueue<Integer> minHeap = new PriorityQueue<>(10); // for saving bigger elements
	PriorityQueue<Integer> maxHeap = new PriorityQueue<>(10,Collections.reverseOrder()); // for saving smaller elements
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
		int len = height.length-1;
		int leftIndex = 0;
		int rightIndex = len;
		int mostWater = 0;
		while(leftIndex<rightIndex){

			if(height[leftIndex] < height[rightIndex]){
				mostWater = Math.max(mostWater,height[leftIndex] * (rightIndex-leftIndex));
				leftIndex++;
			}else{
				mostWater = Math.max(mostWater,height[rightIndex] * (rightIndex-leftIndex));
				rightIndex--;
			}

		}

		return mostWater;
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
	public String longestPalindrome2(String s) {
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

	public String longestPalindrome(String s) {
//		String result = "";
		int end = s.length();
		int longestStart = 0;
		int longestEnd = 0;
		while (end>longestEnd-longestStart){
			for (int i = 0; end-i>longestEnd-longestStart; i++) {
				if (isPalindrome(s.substring(i,end))){
					longestStart = i;
					longestEnd = end;
				}
			}
			end--;
		}
		return s.substring(longestStart,longestEnd);
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

	public int[][] rotation(int[][] matrix){
		int start = 0, end = matrix.length - 1;
		int temp = 0;
		//save left vertical line to temp and rotate 90 degree line by line
		while( start < end ){
			for( int i = 0; i < end - start; i++ )
			{
				temp = matrix[ start ][ start + i ];
				matrix[ start ][ start + i ] = matrix[ end - i ][ start ];
				matrix[ end - i ][ start ] = matrix[ end ][ end - i ];
				matrix[ end ][ end - i ] = matrix[ start + i ][ end ];
				matrix[ start + i ][ end ] = temp;
			}
			start++;
			end--;
		}
		return matrix;
	}

	public List<List<Integer>> permute(int[] arr) {
		List<List<Integer>> list = new ArrayList<>();
		permuteHelper(list, new ArrayList<>(), arr);
		return list;
	}

	private void permuteHelper(List<List<Integer>> list, List<Integer> resultList, int [] arr){
		if(resultList.size() == arr.length){
			list.add(new ArrayList<>(resultList));
			return;
		}
		for(int i = 0; i < arr.length; i++){
			if(resultList.contains(arr[i]))  continue;
			resultList.add(arr[i]);
			permuteHelper(list, resultList, arr);
			resultList.remove(resultList.size() - 1);
		}
	}

	public void permutation(String str) {
		permutation(str, "");

	}

	public void permutation(String str, String prefix) {
		if (str.length() == 0) {
			System.out.println(prefix);
		} else {
			for (int i= 0; i < str.length(); i++) {
				String rem = str.substring(0, i) + str.substring(i + 1);
				permutation(rem, prefix + str.charAt(i));
			}
		}
	}
	public int[][] rotateMatrix(int[][] matrix){
		int rowIndex= matrix.length-1;
		int colIndex=matrix[0].length-1;
		int temp=0;
		for (int i = 0; i < colIndex-i; i++) {
			temp = matrix[i][i];
			matrix[i][i] = matrix[i+rowIndex][i];
			matrix[i+rowIndex][i] = matrix[i+rowIndex][i+colIndex];
			matrix[i+rowIndex][i+colIndex] = matrix[i][i+colIndex];
			matrix[i][i+colIndex]=temp;
		}

		return matrix;
	}

}
