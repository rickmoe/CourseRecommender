import java.util.LinkedList;

public class BuildSubtreeHelper {

    public static void buildSubtree(NodeManager tree, Node root, String rootString) {
        LinkedList<Node> nodeStack = new LinkedList<>();
        nodeStack.add(root);
        LinkedList<String[]> stringListStack = new LinkedList<>();
        stringListStack.push(new String[] {rootString});
        while (!nodeStack.isEmpty()) {
            Node parent = nodeStack.pop();
            String[] prerequisites = stringListStack.pop();
            for (String string : prerequisites) {
                if (string.length() > 2 && string.charAt(0) == '#') {
                    int numRequired = Integer.parseInt(string.substring(1, string.indexOf('[')));
                    AtLeastNode atLeastNode = new AtLeastNode(numRequired);
                    tree.addBelow(parent, atLeastNode);
                    nodeStack.add(atLeastNode);
                    String[] atLeastConditions = string.substring(string.indexOf('[') + 1, string.lastIndexOf(']')).split(",");
                    stringListStack.add(atLeastConditions);
                } else if (string.contains("+")) {
                    OrNode orNode = new OrNode();
                    tree.addBelow(parent, orNode);
                    nodeStack.add(orNode);
                    String[] orConditions = string.split("\\+");
                    stringListStack.add(orConditions);
                } else if (string.contains("*")) {
                    AndNode andNode = new AndNode();
                    tree.addBelow(parent, andNode);
                    nodeStack.add(andNode);
                    String[] andConditions = string.split("\\*");
                    stringListStack.add(andConditions);
                } else if (parent instanceof AndNode && string.length() > 0 && string.charAt(0) == '~') {
                    tree.addBelow((AndNode)parent, string.substring(1), true);
                } else {
                    tree.addBelow(parent, string);
                }
            }
        }
    }

}
