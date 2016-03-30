import java.util.HashSet;

public class Vertex {
    String s;
    HashSet<Vertex> children;
    Vertex parent;
    static Vertex obj = null;
    boolean visited;

    public Vertex (String s) {
        this.s = s;
        children = new HashSet<Vertex>();
        parent = obj;
        if (obj != null) obj.addChild(this);
        visited = false;
    }

    public void addChild (Vertex v) {
        children.add(v);
    }

    public void setParent (Vertex p) {
        parent.children.remove(this);
        parent = p;
    }

    @Override
    public String toString() {
        return s;
    }
}