import java.util.HashMap;

public class NodeManager {

    public static final String ROOT_NAME = "root";
    private final HashMap<String, Node> nodes;

    public NodeManager() {
        nodes = new HashMap<>();
        nodes.put(ROOT_NAME, new RequirementNode(ROOT_NAME));
    }

    public boolean add(Node parent, Node newNode) {
        if (parent == null || newNode == null || !connectedToTree(parent)) return false;
        if (newNode instanceof RequirementNode && !connectedToTree(newNode)) nodes.put(newNode.toString(), newNode);
        parent.addChild(newNode);
        return true;
    }

    public boolean add(String parentName, Node newNode) {
        if (!inTree(parentName) || newNode == null) return false;
        if (newNode instanceof RequirementNode) nodes.put(newNode.toString(), newNode);
        nodes.get(parentName).addChild(newNode);
        return true;
    }

    public boolean add(String parentName, String nodeName) {
        if (!inTree(parentName) || !inTree(nodeName)) return false;
        nodes.get(parentName).addChild(nodes.get(nodeName));
        return true;
    }

    public boolean connectedToTree(Node node) {
        if (node.equals(nodes.get(ROOT_NAME))) return true;
        for (Node parent : node.parents) {
            if (connectedToTree(parent)) return true;
        }
        return false;
    }

    public boolean inTree(String nodeName) {
        return nodes.containsKey(nodeName);
    }

    public Node get(String nodeName) {
        return nodes.get(nodeName);
    }

}
