import java.util.LinkedList;

public abstract class Node {

    protected LinkedList<Node> prerequisites;
    protected LinkedList<Node> prerequisitesFor;

    public Node() {
        prerequisites = new LinkedList<>();
        prerequisitesFor = new LinkedList<>();
    }

    public void addPrerequisite(Node node) {
        prerequisites.add(node);
        node.getPrerequisitesFor().add(this);
    }

    public void removePrerequisite(Node node) {
        prerequisites.remove(node);
        node.getPrerequisitesFor().remove(this);
    }

    public LinkedList<Node> getPrerequisites() {
        return prerequisites;
    }

    public void addPrerequisiteFor(Node node) {
        prerequisitesFor.add(node);
        node.getPrerequisites().add(this);
    }

    public void removePrerequisiteFor(Node node) {
        prerequisitesFor.remove(node);
        node.getPrerequisites().remove(this);
    }

    public LinkedList<Node> getPrerequisitesFor() {
        return prerequisitesFor;
    }

    public boolean canBeRemoved() {
        return prerequisites.size() > 0;
    }

    public boolean remove() {
        if (!canBeRemoved()) return false;
        for (Node node : prerequisitesFor) node.removePrerequisite(this);
        for (Node node : prerequisites) node.removePrerequisiteFor(this);
        return true;
    }

    public String toStringWithChildren() {
        return toStringWithChildren(0);
    }

    public String toStringWithChildren(int indentLevel) {
        String output = " : ".repeat(indentLevel) + this.toString() + "\n";
        for(Node prerequisite : prerequisites) output += prerequisite.toStringWithChildren(indentLevel + 1);
        return output;
    }

    public String toStringWithChildren(int indentLevel, boolean inverted) {
        String output = " : ".repeat(indentLevel) + "Not " + this.toString() + "\n";
        for(Node prerequisite : prerequisites) output += prerequisite.toStringWithChildren(indentLevel + 1);
        return output;
    }
}