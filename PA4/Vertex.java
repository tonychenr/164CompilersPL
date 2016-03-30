import java.util.LinkedList;

public class Vertex {
    String s;
    LinkedList<Vertex> children;
    Vertex parent;
    static Vertex obj = null;

    public Vertex (String s) {
        this.s = s;
        children = new LinkedList<Vertex>();
        parent = obj;
    }

    public void addChild (Vertex v) {
        children.add(v);
    }

    public void setParent (Vertex p) {
        parent = p;
    }

    @Override
    public String toString() {
        return s;
    }
}