package net.questions.solutions;

import org.junit.jupiter.api.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

	private Solution solution;

	@BeforeEach
	void initEach(TestInfo testInfo, TestReporter testReporter) {
		solution = new Solution();
		testReporter.publishEntry(" Current Test Method : " + testInfo.getDisplayName());
	}

	@Test
	@DisplayName("testBuddyStrings : Buddy Strings ")
	@Tag("LeetCode")
	void testBuddyStrings() {
		assertAll(
				() -> assertTrue(solution.buddyStrings("ab", "ba")),
				() -> assertFalse(solution.buddyStrings("ab", "ab")),
				() -> assertTrue(solution.buddyStrings("aa", "aa")),
				() -> assertTrue(solution.buddyStrings("aaaaaaabc", "aaaaaaacb")),
				() -> assertTrue(solution.buddyStrings("abab", "abab")),
				() -> assertFalse(solution.buddyStrings("abcd", "abcd")),
				() -> assertFalse(solution.buddyStrings("ab", "ca"))
				);
	}

	@Test
	@DisplayName("Container With Most Water")
	@Tag("LeetCode")
	void testMaxArea() {
		assertAll(
				() -> assertEquals(solution.maxArea(new int[] {1,8,6,2,5,4,8,3,7}),49),
				() -> assertEquals(solution.maxArea(new int[] {1,1}),1)
				);
	}

	@Test
	@DisplayName("Third Maximum Number")
	@Tag("LeetCode")
	void testThirdMax() {
		assertAll(
				() -> assertEquals(solution.thirdMax(new int[] {Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,1,1,1}),1),
				() -> assertEquals(solution.thirdMax(new int[] {Integer.MIN_VALUE,1,1}),1),
				() -> assertEquals(solution.thirdMax(new int[] {5,2,2}),5),
				() -> assertEquals(solution.thirdMax(new int[] {1,2,Integer.MIN_VALUE}),Integer.MIN_VALUE),
				() -> assertEquals(solution.thirdMax(new int[] {1,2,2}),2),
				() -> assertEquals(solution.thirdMax(new int[] {1,2}),2),
				() -> assertEquals(solution.thirdMax(new int[] {3,2,1}),1),
				() -> assertEquals(solution.thirdMax(new int[] {2,2,3,1}),1)
				);
	}

	@Test
	@DisplayName("Longest Palindromic Substring")
	@Tag("LeetCode")
	void longestPalindrome() {
		assertAll(
				() -> assertEquals(solution.longestPalindrome("babad"),"bab"),
				() -> assertEquals(solution.longestPalindrome("cbbd"),"bb"),
				() -> assertEquals(solution.longestPalindrome("euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew"),"aqkqa"),
				() -> assertEquals(solution.longestPalindrome("cwziydanrqvsdtvnnqgjnbrvvwxwqojeqgxhwxdoktjktulemwpbeqscbbtbfvkxsrjetfdrovcrdwzfmnnihtgxybuairswfewvpuscocqifuwylhssldpjrawqdrbvkykpaggspbfrulcktpbofchzikhzxhpocgvdbwpewpywsgqbczmamprklaoovcfecwchhmsaqkhvuvvzjblmgvqpqtnlipgqsanvovylpmxlmxvymppdykphhaamtxjnnlsqfwjwhyywgurteaummwhvavxbcpgrfffxrowluqmqjaugryxdmwvyokdcfcvcytxpixbvwrdgzctejdoaavgtezexmvxgrkpnayvfarkyoruofqmpnsqdzojxqrjsnfwsbzjmaoigytygukqlrcqaxazvmytgfghdczvzphfdbnxtklaiqqsotavdmhiaermluafheowcobjqmrkmlzyas"),"gytyg"),
				() -> assertEquals(solution.longestPalindrome("azwdzwmwcqzgcobeeiphemqbjtxzwkhiqpbrprocbppbxrnsxnwgikiaqutwpftbiinlnpyqstkiqzbggcsdzzjbrkfmhgtnbujzszxsycmvipjtktpebaafycngqasbbhxaeawwmkjcziybxowkaibqnndcjbsoehtamhspnidjylyisiaewmypfyiqtwlmejkpzlieolfdjnxntonnzfgcqlcfpoxcwqctalwrgwhvqvtrpwemxhirpgizjffqgntsmvzldpjfijdncexbwtxnmbnoykxshkqbounzrewkpqjxocvaufnhunsmsazgibxedtopnccriwcfzeomsrrangufkjfzipkmwfbmkarnyyrgdsooosgqlkzvorrrsaveuoxjeajvbdpgxlcrtqomliphnlehgrzgwujogxteyulphhuhwyoyvcxqatfkboahfqhjgujcaapoyqtsdqfwnijlkknuralezqmcryvkankszmzpgqutojoyzsnyfwsyeqqzrlhzbc"),"sooos"),
				() -> assertEquals(solution.longestPalindrome("222020221"),"2202022"),
				() -> assertEquals(solution.longestPalindrome("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg"),"ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"),
				() -> assertEquals(solution.longestPalindrome("aaabaaaa"),"aaabaaa"),
				() -> assertEquals(solution.longestPalindrome("jrjnbctoqgzimtoklkxcknwmhiztomaofwwzjnhrijwkgmwwuazcowskjhitejnvtblqyepxispasrgvgzqlvrmvhxusiqqzzibcyhpnruhrgbzsmlsuacwptmzxuewnjzmwxbdzqyvsjzxiecsnkdibudtvthzlizralpaowsbakzconeuwwpsqynaxqmgngzpovauxsqgypinywwtmekzhhlzaeatbzryreuttgwfqmmpeywtvpssznkwhzuqewuqtfuflttjcxrhwexvtxjihunpywerkktbvlsyomkxuwrqqmbmzjbfytdddnkasmdyukawrzrnhdmaefzltddipcrhuchvdcoegamlfifzistnplqabtazunlelslicrkuuhosoyduhootlwsbtxautewkvnvlbtixkmxhngidxecehslqjpcdrtlqswmyghmwlttjecvbueswsixoxmymcepbmuwtzanmvujmalyghzkvtoxynyusbpzpolaplsgrunpfgdbbtvtkahqmmlbxzcfznvhxsiytlsxmmtqiudyjlnbkzvtbqdsknsrknsykqzucevgmmcoanilsyyklpbxqosoquolvytefhvozwtwcrmbnyijbammlzrgalrymyfpysbqpjwzirsfknnyseiujadovngogvptphuyzkrwgjqwdhtvgxnmxuheofplizpxijfytfabx"),"qosoq"),
				() -> assertEquals(solution.longestPalindrome("abcda"),"a"),
				() -> assertEquals(solution.longestPalindrome("aaaa"),"aaaa"),
				() -> assertEquals(solution.longestPalindrome("ac"),"a"),
				() -> assertEquals(solution.longestPalindrome("a"),"a")
		);
	}
	
	@Test
	@DisplayName("Longest Palindromic Substring")
	@Tag("LeetCode")
	void lengthOfLongestSubstring() {
		assertAll(
				() -> assertEquals(solution.lengthOfLongestSubstring("abcabcbb"),3),
				() -> assertEquals(solution.lengthOfLongestSubstring("bbbbb"),1),
				() -> assertEquals(solution.lengthOfLongestSubstring("pwwkew"),3),
				() -> assertEquals(solution.lengthOfLongestSubstring("ckilbkd"),5),
				() -> assertEquals(solution.lengthOfLongestSubstring("dvdf"),3)
		);
	}

	@Test
	@DisplayName("find all permutations of the shorter string within the longer one")
	@Tag("Cracking_The_Coding_lnterview")
	void testpermutationsInLongString() {
		assertArrayEquals(solution.permutationsInLongString("abbc","cbabadcbbabbcbabaabccbabc"),
				new String[]{"cbab","cbba","abbc","bcba","cbab","cbab","babc"});
	}

	@Test
	@DisplayName("find all permutations of the shorter string within the longer one")
	@Tag("Cracking_The_Coding_lnterview")
	void testpermutationsInLongString2() {
		assertArrayEquals(solution.permutationsInLongString2("abbc","cbabadcbbabbcbabaabccbabc"),
				new String[]{"cbab","cbba","abbc","bcba","cbab","cbab","babc"});
	}

	@Test
	@DisplayName("find all permutations")
	@Tag("Cracking_The_Coding_lnterview")
	void testpermutations() {
		assertArrayEquals(solution.permute("abc"),
				new String[]{"abc","acb","bac","bca","cba","cab"});
	}

	@Test
	@DisplayName("keep track of the median")
	@Tag("Cracking_The_Coding_lnterview")
	void test_medianOfExpandingArray() {
		//22,49,44,38,37,1,6,13,5,30,21,44,4,32,14
		assertAll(
				() -> assertEquals(solution.medianOfExpandingArray(22),22),
				() -> assertEquals(solution.medianOfExpandingArray(49),35),
				() -> assertEquals(solution.medianOfExpandingArray(44),44),
				() -> assertEquals(solution.medianOfExpandingArray(38),41),
				() -> assertEquals(solution.medianOfExpandingArray(37),38),
				() -> assertEquals(solution.medianOfExpandingArray(1),37),
				() -> assertEquals(solution.medianOfExpandingArray(6),37),
				() -> assertEquals(solution.medianOfExpandingArray(13),29),
				() -> assertEquals(solution.medianOfExpandingArray(5),22),
				() -> assertEquals(solution.medianOfExpandingArray(30),26),
				() -> assertEquals(solution.medianOfExpandingArray(21),22),
				() -> assertEquals(solution.medianOfExpandingArray(44),26),
				() -> assertEquals(solution.medianOfExpandingArray(4),22),
				() -> assertEquals(solution.medianOfExpandingArray(32),26),
				() -> assertEquals(solution.medianOfExpandingArray(14),22)
		);
	}


	@Test
	@DisplayName("Balanced Brackets")
	@Tag("Hackerrank")
	void test_isBalanced() {
		assertAll(
				() -> assertEquals(solution.isBalanced("{[()]}"),"YES"),
				() -> assertEquals(solution.isBalanced("{[(])}"),"NO"),
				() -> assertEquals(solution.isBalanced("{{[[(())]]}}"),"YES"),
				() -> assertEquals(solution.isBalanced("{(([])[])[]}"),"YES"),
				() -> assertEquals(solution.isBalanced("{(([])[])[]]}"),"NO"),
				() -> assertEquals(solution.isBalanced("{(([])[])[]}[]"),"YES"),

				() -> assertEquals(solution.isBalanced("}][}}(}][))]"),"NO"),
				() -> assertEquals(solution.isBalanced("[](){()}"),"YES"),
				() -> assertEquals(solution.isBalanced("()"),"YES"),
				() -> assertEquals(solution.isBalanced("({}([][]))[]()"),"YES"),
				() -> assertEquals(solution.isBalanced("{)[](}]}]}))}(())("),"NO"),
				() -> assertEquals(solution.isBalanced("([[)"),"NO")
		);
	}

	@Test
	@DisplayName("Two Sum")
	@Tag("LeetCode")
	void testTwoSum() {
		assertAll(
				() -> assertArrayEquals(solution.twoSum(new int[] {0,3,12,31,9,-3,0,4,15},9),new int[]{0,4})
				,() -> assertArrayEquals(solution.twoSum(new int[] {2,7,11,15},9),new int[]{0,1})
				,() -> assertArrayEquals(solution.twoSum(new int[] {3,2,4},6),new int[]{1,2})
				,() -> assertArrayEquals(solution.twoSum(new int[] {15,23,56},1),null)
		);
	}

	@Test
	@DisplayName("Spiral Matrix")
	@Tag("LeetCode")
	void testGetMatrixInSpiralOrder() {
		int[][] inp1 = new int[][]{
				 new int[]{1,2,3,4,5}
				,new int[]{16,17,18,19,6}
				,new int[]{15,24,25,20,7}
				,new int[]{14,23,22,21,8}
				,new int[]{13,12,11,10,9}
		};
		List<Integer> out1 = IntStream.range(1,26).boxed().collect(Collectors.toList());

		int[][] inp2 = new int[][]{
				new int[]{1,2,3},new int[]{4,5,6},new int[]{7,8,9}
		};
		List<Integer> out2 = Stream.of(1,2,3,6,9,8,7,4,5).collect(Collectors.toList());

		int[][] inp3 = new int[][]{new int[]{1,2,3,4,5}};
		List<Integer> out3 = IntStream.range(1,6).boxed().collect(Collectors.toList());

		int[][] inp4 = new int[][]{new int[]{1},new int[]{2},new int[]{3},new int[]{4},new int[]{5}};
		List<Integer> out4 = IntStream.range(1,6).boxed().collect(Collectors.toList());

		int[][] inp5 = new int[][]{new int[]{1,2,3}
		,new int[]{12,13,4}
		,new int[]{11,14,5}
		,new int[]{10,15,6}
		,new int[]{9,8,7}
		};
		List<Integer> out5 = IntStream.range(1,16).boxed().collect(Collectors.toList());

		int[][] inp6 = new int[][]{new int[]{1}};
		List<Integer> out6 = IntStream.range(1,2).boxed().collect(Collectors.toList());
		int[][] inp7 = new int[][]{new int[]{}};
		List<Integer> out7 = IntStream.range(1,1).boxed().collect(Collectors.toList());

		int[][] inp8 = new int[][]{new int[]{1,2,3,4,5,6,7}
				,new int[]{14,13,12,11,10,9,8}
		};
		List<Integer> out8 = IntStream.range(1,15).boxed().collect(Collectors.toList());
		assertAll(
				() -> assertIterableEquals(solution.spiralOrder(inp1), out1)
				,() -> assertIterableEquals(solution.spiralOrder(inp2), out2)
				,() -> assertIterableEquals(solution.spiralOrder(inp3), out3)
				,() -> assertIterableEquals(solution.spiralOrder(inp4), out4)
				,() -> assertIterableEquals(solution.spiralOrder(inp5), out5)
				,() -> assertIterableEquals(solution.spiralOrder(inp6), out6)
				,() -> assertIterableEquals(solution.spiralOrder(inp7), out7)
				,() -> assertIterableEquals(solution.spiralOrder(inp8), out8)
		);
	}

	@Test
	@DisplayName("Spiral Matrix by recursion")
	@Tag("LeetCode")
	void testGetMatrixInSpiralOrderRecursion() {
		int[][] inp1 = new int[][]{
				new int[]{1,2,3,4,5}
				,new int[]{16,17,18,19,6}
				,new int[]{15,24,25,20,7}
				,new int[]{14,23,22,21,8}
				,new int[]{13,12,11,10,9}
		};
		List<Integer> out1 = IntStream.range(1,26).boxed().collect(Collectors.toList());

		int[][] inp2 = new int[][]{
				new int[]{1,2,3},new int[]{4,5,6},new int[]{7,8,9}
		};
		List<Integer> out2 = Stream.of(1,2,3,6,9,8,7,4,5).collect(Collectors.toList());

		int[][] inp3 = new int[][]{new int[]{1,2,3,4,5}};
		List<Integer> out3 = IntStream.range(1,6).boxed().collect(Collectors.toList());

		int[][] inp4 = new int[][]{new int[]{1},new int[]{2},new int[]{3},new int[]{4},new int[]{5}};
		List<Integer> out4 = IntStream.range(1,6).boxed().collect(Collectors.toList());

		int[][] inp5 = new int[][]{new int[]{1,2,3}
				,new int[]{12,13,4}
				,new int[]{11,14,5}
				,new int[]{10,15,6}
				,new int[]{9,8,7}
		};
		List<Integer> out5 = IntStream.range(1,16).boxed().collect(Collectors.toList());

		int[][] inp6 = new int[][]{new int[]{1}};
		List<Integer> out6 = IntStream.range(1,2).boxed().collect(Collectors.toList());
		int[][] inp7 = new int[][]{new int[]{}};
		List<Integer> out7 = IntStream.range(1,1).boxed().collect(Collectors.toList());


		int[][] inp8 = new int[][]{new int[]{1,2,3,4,5,6,7}
				,new int[]{14,13,12,11,10,9,8}
		};
		List<Integer> out8 = IntStream.range(1,15).boxed().collect(Collectors.toList());

		assertAll(
				() -> assertIterableEquals(solution.spiralOrderWithRecursion(inp1), out1)
				,() -> assertIterableEquals(solution.spiralOrderWithRecursion(inp2), out2)
				,() -> assertIterableEquals(solution.spiralOrderWithRecursion(inp3), out3)
				,() -> assertIterableEquals(solution.spiralOrderWithRecursion(inp4), out4)
				,() -> assertIterableEquals(solution.spiralOrderWithRecursion(inp5), out5)
				,() -> assertIterableEquals(solution.spiralOrderWithRecursion(inp6), out6)
				,() -> assertIterableEquals(solution.spiralOrderWithRecursion(inp7), out7)
				,() -> assertIterableEquals(solution.spiralOrderWithRecursion(inp8), out8)
		);
	}

	@Test
	@DisplayName("Add Two Numbers")
	@Tag("LeetCode")
	void testAddTwoNumbers() {
		ListNode l10 = new ListNode(2);
		ListNode l11 = new ListNode(4);l10.next = l11;
		ListNode l12 = new ListNode(3);l11.next = l12;

		ListNode l20 = new ListNode(5);
		ListNode l21 = new ListNode(6);l20.next = l21;
		ListNode l22 = new ListNode(4);l21.next = l22;

		ListNode l30 = new ListNode(9);
		ListNode l31 = new ListNode(9);l30.next = l31;
		ListNode l32 = new ListNode(9);l31.next = l32;
		ListNode l33 = new ListNode(9);l32.next = l33;

		ListNode l40 = new ListNode(9);
		ListNode l41 = new ListNode(9);l40.next = l41;

		ListNode l50 = new ListNode(9);
		ListNode l51 = new ListNode(8);l50.next = l51;

		ListNode l60 = new ListNode(1);

		assertAll(
				() -> assertEquals(solution.addTwoNumbers(l10,l20).toString(),"708")
				,() -> assertEquals(solution.addTwoNumbers(l11,l20).toString(),"994")
				,() -> assertEquals(solution.addTwoNumbers(l12,l20).toString(),"864")
				,() -> assertEquals(solution.addTwoNumbers(l12,l20).toString(),"864")
				,() -> assertEquals(solution.addTwoNumbers(l12,l22).toString(),"7")
				,() -> assertEquals(solution.addTwoNumbers(l30,l40).toString(),"89001")
				,() -> assertEquals(solution.addTwoNumbers(l32,l40).toString(),"891")
		,() -> assertEquals(solution.addTwoNumbers(l50,l60).toString(),"09")
		);
	}

	@Test
	@DisplayName("Two Sum")
	@Tag("LeetCode")
	void testLetterCombinations() {
		List<String>  _483 = Stream.of("gtd","gte","gtf","gud","gue","guf","gvd","gve","gvf"
				,"htd","hte","htf","hud","hue","huf","hvd","hve","hvf"
				,"itd","ite","itf","iud","iue","iuf","ivd","ive","ivf").collect(Collectors.toList());

		List<String>  _488 = Stream.of("gtt","gtu","gtv","gut","guu","guv","gvt","gvu","gvv"
				,"htt","htu","htv","hut","huu","huv","hvt","hvu","hvv"
				,"itt","itu","itv","iut","iuu","iuv","ivt","ivu","ivv").collect(Collectors.toList());

		assertAll(
//				() -> assertIterableEquals(solution.letterCombinations("483"),_483)
//				,() -> assertIterableEquals(solution.letterCombinations("488"),_488)
//				,
				() -> assertIterableEquals(solution.letterCombinations(""),new ArrayList<>())
		);
	}
}
