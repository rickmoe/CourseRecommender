import java.util.ArrayList;

public class AndNode extends LogicNode {

    private final ArrayList<Node> invertedPrerequisites;
    private ArrayList<Node> removedInverteds;

    public AndNode() {
        super();
        invertedPrerequisites = new ArrayList<>();
        removedInverteds = new ArrayList<>();
    }

    @Override
    public void addPrerequisite(Node node) {
        super.addPrerequisite(node);
    }

    public void addPrerequisite(Node node, boolean invert) {
        super.addPrerequisite(node);
        if (invert) invertedPrerequisites.add(node);
    }

    @Override
    public void removePrerequisite(Node node) {
        super.removePrerequisite(node);
        if (invertedPrerequisites.contains(node)) removedInverteds.add(node);
        if (prerequisites.isEmpty() && removedInverteds.isEmpty()) setLogicallySatisfied();
    }

    @Override
    public String toString() {
        return "All Of:";
    }

    @Override
    public String toStringWithChildren(int indentLevel) {
        String output = " : ".repeat(indentLevel) + this.toString() + "\n";
        for(Node prerequisite : prerequisites) {
            if (invertedPrerequisites.contains(prerequisite)) output += prerequisite.toStringWithChildren(indentLevel + 1, true);
            else output += prerequisite.toStringWithChildren(indentLevel + 1);
        }
        return output;
    }
}