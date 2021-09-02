package net.questions.solutions;

import java.util.*;

public class Graph2 {
    public static void main(String[] args) {
        Graph2 g = new Graph2();
        g.adjVertices = new HashMap<>();
        g.adjVertices.put(0, Arrays.asList(1,2,5));
        g.adjVertices.put(1, Arrays.asList(0,3));
        g.adjVertices.put(2, Arrays.asList(0,3,4));
        g.adjVertices.put(3, Arrays.asList(1,2));
        g.adjVertices.put(4, Arrays.asList(2));
        g.adjVertices.put(5, Arrays.asList(0));
        g.dfsWithoutRecursion(0);
    }

    private Map<Integer, List<Integer>> adjVertices;

    public Graph2() {
        this.adjVertices = new HashMap<Integer, List<Integer>>();
    }

    public void addVertex(int vertex) {
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int src, int dest) {
        adjVertices.get(src).add(dest);
    }

    public void dfsWithoutRecursion(int start) {
        Stack<Integer> stack = new Stack<Integer>();
        boolean[] isVisited = new boolean[adjVertices.size()];
        stack.push(start);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!isVisited[current]) {
                isVisited[current] = true;
                visit(current);
                for (int dest : adjVertices.get(current)) {
                    if (!isVisited[dest])
                        stack.push(dest);
                }
            }

        }
        System.out.println("");
    }

    public void dfs(int start) {
        boolean[] isVisited = new boolean[adjVertices.size()];
        dfsRecursive(start, isVisited);
    }

    private void dfsRecursive(int current, boolean[] isVisited) {
        isVisited[current] = true;
        visit(current);
        for (int dest : adjVertices.get(current)) {
            if (!isVisited[dest])
                dfsRecursive(dest, isVisited);
        }
    }

    public List<Integer> topologicalSort(int start) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        boolean[] isVisited = new boolean[adjVertices.size()];
        topologicalSortRecursive(start, isVisited, result);
        return result;
    }

    private void topologicalSortRecursive(int current, boolean[] isVisited, LinkedList<Integer> result) {
        isVisited[current] = true;
        for (int dest : adjVertices.get(current)) {
            if (!isVisited[dest])
                topologicalSortRecursive(dest, isVisited, result);
        }
        result.addFirst(current);
    }

    private void visit(int value) {
        System.out.print(" " + value);
    }
}
