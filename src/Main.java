import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Take in input of majors and classes taken
        Scanner scnr = new Scanner(System.in);
        String userInput = "";
        LinkedList<LinkedList<String>> majorsList = CSVReader.readCSV("res/majors.csv");
        LinkedList<String> headers = majorsList.get(0);
        LinkedList<String> majorData = null;
        LinkedList<String> classesTaken = new LinkedList<>();

        System.out.println("Please enter your major: ");
        while (majorData == null) {
            userInput = scnr.nextLine();
            if (userInput.equals("test")) userInput = "computer sciences, b.s.";
            for (LinkedList<String> data : majorsList) {
                if (data.equals(headers)) continue;
                if (userInput.toLowerCase().equals(data.get(headers.indexOf("major")).toLowerCase()))
                    majorData = data;
            }
            if (majorData == null) System.out.println("Please enter a valid major in the database: ");
        }

        while (!userInput.equals("esc")) {
            System.out.println("Please enter the classes you've taken. Enter \"esc\" to end the list.");
            userInput = scnr.nextLine();
            if (!userInput.equals("esc")) classesTaken.add(userInput.toUpperCase());
        }
        System.out.println();

        // Construct graph based on majors and classes taken
        NodeManager tree = new NodeManager();
        String majorName = majorData.get(headers.indexOf("major"));
        tree.addBelow("root", majorName);
        for (String category : majorData) {
            if (majorData.indexOf(category) < headers.indexOf("requirements**")) continue;
            String[] categoryList = category.split(";");
            RequirementNode categoryNode = new RequirementNode(categoryList[0]);
            tree.addBelow(majorName, categoryNode);
            LinkedList<Node> nodeStack = new LinkedList<>();
            nodeStack.add(categoryNode);
            LinkedList<String[]> stringListStack = new LinkedList<>();
            stringListStack.push(new String[] {categoryList[1]});
            while (!nodeStack.isEmpty()) {
                Node parent = nodeStack.pop();
                String[] prerequisites = stringListStack.pop();
                for (String string : prerequisites) {
                    if (string.length() > 2 && string.charAt(0) == '#') {
                        int numRequired = Integer.parseInt(string.substring(1, string.indexOf('[')));
                        AtLeastNode atLeastNode = new AtLeastNode(numRequired);
                        tree.addBelow(parent, atLeastNode);
                        nodeStack.add(atLeastNode);
                        String[] atLeastConditions = string.substring(string.indexOf('[') + 1, string.indexOf(']')).split(",");
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
                    } else {
                        tree.addBelow(parent, string);
                    }
                }
            }
        }

        // Remove courses that must be taken and can be directly taken, iterate until none found

        // Determine pre-requisite combinations to satisfy remaining requirements and add them
        // to a list

        // Evaluate credit count for all pre-requisite combinations
        // Keep track of the minimum then remove any too high above the min

        // Sort remaining combos based on credit count

        // Present results to user
        for (Node majorNode : tree.getRoot().getPrerequisites()) System.out.println(majorNode.toStringWithChildren());
    }
}