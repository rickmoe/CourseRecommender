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
                    tree.add(parent, atLeastNode);
                    nodeStack.add(atLeastNode);
                    String[] atLeastConditions = string.substring(string.indexOf('[') + 1, string.lastIndexOf(']')).split(",");
                    stringListStack.add(atLeastConditions);
                } else if (string.length() > 2 && string.charAt(0) == '$') {
                    int creditsRequired = Integer.parseInt(string.substring(1, string.indexOf('[')));
                    AtLeastCreditNode atLeastCreditNode = new AtLeastCreditNode(creditsRequired);
                    tree.add(parent, atLeastCreditNode);
                    nodeStack.add(atLeastCreditNode);
                    String[] atLeastCreditConditions = string.substring(string.indexOf('[') + 1, string.lastIndexOf(']')).split(",");
                    stringListStack.add(atLeastCreditConditions);
                } else if (string.length() > 1 && string.charAt(0) == '~') {
                    NotNode notNode = new NotNode();
                    tree.add(parent, notNode);
                    nodeStack.add(notNode);
                    String[] notCondition = new String[]{string.substring(1)};
                    stringListStack.add(notCondition);
                } else if (string.contains("+")) {
                    OrNode orNode = new OrNode();
                    tree.add(parent, orNode);
                    nodeStack.add(orNode);
                    String[] orConditions = string.split("\\+");
                    stringListStack.add(orConditions);
                } else if (string.contains("*")) {
                    AndNode andNode = new AndNode();
                    tree.add(parent, andNode);
                    nodeStack.add(andNode);
                    String[] andConditions = string.split("\\*");
                    stringListStack.add(andConditions);
                } else {
                    // FIXME: ADD CREDIT AMOUNT PARSED FROM COURSES FILE
                    tree.add(parent, new CourseNode(string, 0));
                }
            }
        }
    }

}
