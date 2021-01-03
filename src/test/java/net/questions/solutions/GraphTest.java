package net.questions.solutions;

import org.junit.jupiter.api.*;

import java.util.*;

class GraphTest {

    private Graph<Integer> graph;

    @BeforeEach
    void initEach(TestInfo testInfo, TestReporter testReporter) {
        testReporter.publishEntry(" Current Test Method : " + testInfo.getDisplayName());
        graph = new Graph<>();
        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>() {{
            put(0,new LinkedList<Integer>(Arrays.asList(1,4)));
            put(1,new LinkedList<Integer>(Arrays.asList(2,3,4)));
            put(2,new LinkedList<Integer>(Arrays.asList(3)));
            put(3,new LinkedList<Integer>(Arrays.asList(4,5,6)));
            put(5,new LinkedList<Integer>(Arrays.asList(7)));
            put(6,new LinkedList<Integer>(Arrays.asList(7)));
        }};
        graph.addAll(adjList);

    }

    @Test
    void testBfsAndDfs(){
        int nodeCount = graph.getNodeCount();
        List<Integer> allNodeValuesByDfs =  graph.dfsTraversal(null);
        List<Integer> allNodeValuesByBfs =  graph.bfsTraversal(0);

        Assertions.assertEquals(nodeCount,allNodeValuesByDfs.size());
        Assertions.assertEquals(nodeCount,allNodeValuesByBfs.size());
    }
}
