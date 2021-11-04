import java.util.HashMap;

public class NodeManager {

    private static final String ROOT_NAME = "root";
    private final HashMap<String, Node> nodes;

    public NodeManager() {
        nodes = new HashMap<>();
        nodes.put(ROOT_NAME, new RequirementNode(ROOT_NAME));
    }

    public boolean addBelow(String parentName, Node toAdd) {
        if (!inTree(parentName.toLowerCase()) || toAdd == null) return false;
        if (toAdd instanceof RequirementNode) nodes.put(toAdd.toString().toLowerCase(), toAdd);
        nodes.get(parentName.toLowerCase()).addPrerequisite(toAdd);
        return true;
    }

    public boolean addBelow(String parentName, String toAdd) {
        if (toAdd == null) return false;
        else if (inTree(toAdd)) return addBelow(parentName, nodes.get(toAdd));
        return addBelow(parentName, new RequirementNode(toAdd));
    }

    public boolean addBelow(Node parent, Node toAdd) {
        if (parent == null || toAdd == null || !connectedToTree(parent)) return false;
        if (toAdd instanceof RequirementNode) nodes.put(toAdd.toString().toLowerCase(), toAdd);
        parent.addPrerequisite(toAdd);
        return true;
    }

    public boolean addBelow(Node parent, String toAdd) {
        if (toAdd == null) return false;
        else if (inTree(toAdd)) return addBelow(parent, nodes.get(toAdd));
        return addBelow(parent, new RequirementNode(toAdd));
    }

    public boolean addBelow(AndNode parent, String toAdd, boolean invert) {
        if (toAdd == null) return false;
        else if (inTree(toAdd)) return addBelow(parent, nodes.get(toAdd));
        return addBelow(parent, new RequirementNode(toAdd), invert);
    }

    public boolean addBelow(AndNode parent, Node toAdd, boolean invert) {
        if (parent == null || toAdd == null || !connectedToTree(parent)) return false;
        if (toAdd instanceof RequirementNode) nodes.put(toAdd.toString().toLowerCase(), toAdd);
        parent.addPrerequisite(toAdd, invert);
        return true;
    }

    public boolean connectedToTree(Node node) {
        if (node.equals(nodes.get(ROOT_NAME))) return true;
        boolean connected = false;
        for (Node parent : node.getPrerequisitesFor()) {
            if (connectedToTree(parent)) connected = true;
        }
        return connected;
    }

    public Node getRoot() {
        return nodes.get(ROOT_NAME);
    }

    public Node get(String nodeName) {
        return nodes.get(nodeName);
    }

    public boolean inTree(String nodeName) {
        return nodes.containsKey(nodeName);
    }

}
