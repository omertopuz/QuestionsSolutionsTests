package net.questions.solutions;

import org.junit.jupiter.api.*;

import java.util.Arrays;

class TriesTest {
    private Tries tries;
    private String[] words;
    @BeforeEach
    void initEach(TestInfo testInfo, TestReporter testReporter) {
        testReporter.publishEntry(" Current Test Method : " + testInfo.getDisplayName());
        tries = new Tries();
        words = new String[]{"abc","abcd","abce","abgl","ertyu",
                "ertfyu","abced","abcde"};
        tries.insertWordFromArray(words);

    }

    @Test
    void trieStartsWithTest(){
        String prefix = "ab";
        String[] startsWith = tries.startsWith(prefix);
        Assertions.assertEquals(startsWith.length,
                Arrays.stream(words).filter(w->w.startsWith(prefix)).count());
    }

    @Test
    void trieContainsWordTest(){
        String w = words[(words.length)/2];
        Assertions.assertEquals(tries.containsWord(w),
                true);
    }
}
