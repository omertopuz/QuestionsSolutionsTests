package net.questions.solutions;

import java.util.*;

public class Graph<T>{

    private Map<T, Map<T,Integer>> adjList = new HashMap<>();  // T : vertex, Map<T,Integer> : T: subvertices, Integer : weight
//	private boolean bidirectional;

    public Graph() {
    }

    public void addAllEdge(Map<T, Map<T,Integer>> l) {
        l.forEach((k,v)->{
            v.forEach((p,q)->addEdge(k,p,q));
        });
    }

    /**@implNote use for only unweigted graphs
    * */
    public void addAll(Map<T, List<T>> l)
    {
        l.forEach((k,v)->{
            v.forEach(p->addEdge(k,p));
        });
    }

    //for unweigted graphs
    public void addEdge(T source, T destination) {
        addEdge(source,destination,0);
    }

    public void addEdge(T source, T destination, int weight) {
        if (!adjList.containsKey(source))
            addVertex(source,weight);

        if (!adjList.containsKey(destination))
            addVertex(destination,weight);

        adjList.get(source).put(destination,weight);
        adjList.get(destination).put(source,weight);    // for directed graphs skip this line
    }

    public void addVertex(T source, int weight) {
        adjList.putIfAbsent(source, new HashMap<>());
    }

    //for unweigted graphs
    public void addVertex(T source) {
        addVertex(source,0);
    }

    public boolean hasEdge(T source, T destination) {
        Map<T,Integer> node = adjList.get(source);
        return node != null ? node.containsKey(destination):false ;
    }

    public Map<T,Integer> getAdjVertices(T vertex) {
        return adjList.get(vertex);
    }

    public List<T> dfsTraversal(T root){
        List<T> visited = new ArrayList<>();
        Stack<T> s = new Stack<>();
        if(root == null)
            s.push(adjList.entrySet().stream().findFirst().get().getKey());
        else
            s.push(root);

        while (!s.isEmpty()){
            T vertex = s.pop();
            if (!visited.contains(vertex)){
                visited.add(vertex);
                Map<T,Integer> subVertices = getAdjVertices(vertex);
                for (Map.Entry<T,Integer> v: subVertices.entrySet()) {
                    s.push(v.getKey());
                }
            }
        }

        return visited;
    }

    public List<T> bfsTraversal(T root){
        Queue<T> s = new LinkedList<>();
        List<T> visited = new ArrayList<>();

        if(root == null)
            s.add(adjList.entrySet().stream().findFirst().get().getKey());
        else
            s.add(root);

        visited.add(s.peek());
        while (!s.isEmpty()){
            T vertex = s.poll();
            Map<T,Integer> subVertices = getAdjVertices(vertex);
            for (Map.Entry<T,Integer> v: subVertices.entrySet()) {
                if (!visited.contains(v.getKey())){
                    visited.add(v.getKey());
                    s.add(v.getKey());
                }
            }
        }

        return visited;
    }

    public int getNodeCount(){
        Set<T> nodes = new HashSet<>();

        this.adjList.forEach((k,v)->{
            nodes.add(k);
            v.forEach((p,q)->{
                nodes.add(p);
            });
        });

        return nodes.size();
    }
}
