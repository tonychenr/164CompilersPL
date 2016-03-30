import java.util.LinkedList;

public class Vertex {
    String s;
    LinkedList<Vertex> children;
    Vertex parent;

    public Vertex (String s) {
        this.s = s;
        children = new LinkedList<Vertex>();
        parent = null;
    }

    public void addChild (Vertex v) {
        children.add(v);
    }

    public void setParent (Vertex p) {
        parent = p;
    }

    public boolean hasParent () {
        return parent != null;
    }

    @Override
    public String toString() {
        return s;
    }
}