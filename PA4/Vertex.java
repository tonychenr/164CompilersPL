import java.util.HashSet;

public class Vertex {
    class_c myClass;
    HashSet<Vertex> children;
    Vertex parent;
    boolean visited;

    public Vertex (class_c myClass) {
        this.myClass = myClass;
        children = new HashSet<Vertex>();
        parent = null;
        visited = false;
    }

    public void addChild (Vertex v) {
        children.add(v);
    }

    public void setParent (Vertex p) {
        if (parent != null) {
            parent.children.remove(this);
        }
        parent = p;
    }

    @Override
    public String toString() {
        return myClass.getName().toString();
    }
}