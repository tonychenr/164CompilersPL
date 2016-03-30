import java.util.HashMap;

public class Graph {
    HashMap<String,Vertex> s2v;

    public Graph () {
        s2v = new HashMap<String,Vertex>();
    }

    /* returns false if s is already in the graph */
    public boolean addVertex (String s) {
        if (s2v.containsKey(s)) return false;
        Vertex v = new Vertex(s);
        s2v.put(s,v);
        return true;
    }

    /* returns false if parent or child are not in the graph,
       or if child already has a parent */
    public boolean addEdge (String parent, String child) {
        if (!s2v.containsKey(parent) || !s2v.containsKey(child)) return false;
        Vertex childVertex = s2v.get(child);
        if (childVertex.hasParent()) return false;
        Vertex parentVertex = s2v.get(parent);
        childVertex.setParent(parentVertex);
        parentVertex.addChild(childVertex);
        return true;
    }
}
