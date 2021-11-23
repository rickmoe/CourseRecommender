import java.util.HashMap;
import java.util.Locale;

public class NodeManager {

    public static final String ROOT_NAME = "root";
    private final HashMap<String, RequirementNode> nodes;

    public NodeManager() {
        nodes = new HashMap<>();
        nodes.put(ROOT_NAME.toLowerCase(), new RequirementNode(ROOT_NAME));
    }

    public boolean add(Node parent, Node newNode) {
        if (parent == null || newNode == null || !connectedToTree(parent)) {
            return false;
        }
        if (newNode instanceof RequirementNode && !connectedToTree(newNode)) {
            nodes.put(newNode.toString().toLowerCase(), (RequirementNode)newNode);
        }
        parent.addChild(newNode);
        return true;
    }

    public boolean add(String parentName, Node newNode) {
        if (!inTree(parentName) || newNode == null) {
            return false;
        }
        if (newNode instanceof RequirementNode) {
            nodes.put(newNode.toString().toLowerCase(), (RequirementNode)newNode);
        }
        nodes.get(parentName.toLowerCase()).addChild(newNode);
        return true;
    }

    public boolean add(String parentName, String nodeName) {
        if (!inTree(parentName) || !inTree(nodeName)) {
            return false;
        }
        nodes.get(parentName.toLowerCase()).addChild(nodes.get(nodeName.toLowerCase()));
        return true;
    }

    public boolean connectedToTree(Node node) {
        if (node.equals(nodes.get(ROOT_NAME.toLowerCase()))) {
            return true;
        }
        for (Node parent : node.parents) {
            if (connectedToTree(parent)) {
                return true;
            }
        }
        return false;
    }

    public boolean inTree(String nodeName) {
        return nodes.containsKey(nodeName.toLowerCase());
    }

    public Node get(String nodeName) {
        return nodes.get(nodeName.toLowerCase());
    }

    public void setSatisfied(String nodeName) {
        if (inTree(nodeName) && nodes.get(nodeName.toLowerCase()) instanceof CourseNode) {
            ((CourseNode)nodes.get(nodeName.toLowerCase())).setSatisfied();
        }
    }

}
