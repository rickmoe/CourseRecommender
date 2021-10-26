import java.util.HashMap;

public class NodeManager {

    private final HashMap<String, Node> nodes;

    public NodeManager() {
        nodes = new HashMap<>();
        nodes.put("root", new RequirementNode("Root"));
    }

    public boolean addBelow(String parentName, RequirementNode toAdd) {
        if (!nodes.containsKey(parentName.toLowerCase()) || toAdd == null) return false;
        nodes.put(toAdd.toString().toLowerCase(), toAdd);
        nodes.get(parentName.toLowerCase()).addPrerequisite(toAdd);
        return true;
    }

    public boolean addBelow(String parentName, String toAdd) {
        return addBelow(parentName, new RequirementNode(toAdd));
    }

    public boolean addBelow(String parentName, LogicNode toAdd, Node[] children) {
        if (!nodes.containsKey(parentName.toLowerCase()) || toAdd == null || children == null || children.length < 1) return false;
        nodes.get(parentName.toLowerCase()).addPrerequisite(toAdd);
        for (Node child : children) {
            nodes.put(child.toString().toLowerCase(), child);
            toAdd.addPrerequisite(child);
        }
        return true;
    }

    public boolean addBelow(LogicNode toAdd, String parentName, String[] children) {
        Node[] childNodes = new Node[children.length];
        for (int i = 0; i < children.length; i++) childNodes[i] = new RequirementNode(children[i]);
        return addBelow(parentName, toAdd, childNodes);
    }

}
