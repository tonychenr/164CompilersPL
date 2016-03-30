import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
    HashMap<String,Vertex> s2v;

    public Graph () {
        s2v = new HashMap<String,Vertex>();
    }

    /* returns false if s is already in the graph */
    public boolean addVertex (class_c myClass) {
        if (s2v.containsKey(myClass.getName().toString())) return false;
        Vertex v = new Vertex(myClass);
        s2v.put(myClass.getName().toString(),v);
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
     * or the name of a class in a cycle 
     * Vertex of class Object must be added before using this method */
    public class_c hasCycle () {
        LinkedList<Vertex> fringe = new LinkedList<Vertex>();
        fringe.add(s2v.get("Object"));
        while (!fringe.isEmpty()) {
            Vertex v = fringe.pop();
            v.visited = true;
            fringe.addAll(v.children);
        }
        for (Vertex v : s2v.values()) {
            if (!v.visited) return v.myClass;
        }

        return null;
    }
}
