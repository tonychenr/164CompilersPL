import java.util.HashMap;

public class Graph {
    HashMap<String,Vertex> s2v;
    Vertex obj;

    public Graph () {
        s2v = new HashMap<String,Vertex>();
        obj = new Vertex("Object");
        Vertex.obj = obj;
        s2v.put("Object", obj);
        s2v.put("IO", new Vertex("IO"));
    }

    /* returns false if s is already in the graph */
    public boolean addVertex (String s) {
        if (s2v.containsKey(s)) return false;
        Vertex v = new Vertex(s);
        s2v.put(s,v);
        return true;
    }

    /* returns false if parent class isn't declared */
    public boolean addEdge (String parent, String child) {
        if (!s2v.containsKey(parent)) return false;
        Vertex childVertex = s2v.get(child);
        Vertex parentVertex = s2v.get(parent);
        childVertex.setParent(parentVertex);
        parentVertex.addChild(childVertex);
        return true;
    }

    /* returns null if no cycle
     * or the last class name in a cycle */
    public String hasCycle () {
        return null;
    }
}
