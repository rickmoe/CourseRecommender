import java.util.LinkedList;

public abstract class Node {

    protected LinkedList<Node> children;
    protected LinkedList<Node> parents;
    protected boolean satisfied;

    public Node() {
        children = new LinkedList<>();
        parents = new LinkedList<>();
        satisfied = false;
    }

    public void addChild(Node node) {
        children.add(node);
        node.parents.add(this);
        updateSatisfied();
    }

    public abstract void updateSatisfied();

    @Override
    public abstract String toString();

    public String toStringWithChildren() {
        return toStringWithChildren(0);
    }

    public String toStringWithChildren(int indentLevel) {
        if (!satisfied) {
            String output = " : ".repeat(indentLevel) + this.toString() + "\n";
            for(Node child : children) output += child.toStringWithChildren(indentLevel + 1);
            return output;
        }
        return "";
    }
}