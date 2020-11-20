package net.questions.solutions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

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
}
