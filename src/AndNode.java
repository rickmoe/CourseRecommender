import java.util.ArrayList;

public class AndNode extends LogicNode {

    ArrayList<Node> invertedPrerequisites = new ArrayList<>();

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
        if (invertedPrerequisites.contains(node)) prerequisites.add(new RequirementNode("{ null }"));
        for (Node n : prerequisites) if (!invertedPrerequisites.contains(n)) break;
        setLogicallySatisfied();
    }

}