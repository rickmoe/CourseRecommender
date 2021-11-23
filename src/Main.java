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

        System.out.print("Please enter the classes you've taken. Enter \"esc\" to end the list.");
        while (!userInput.equals("esc")) {
            userInput = scnr.nextLine();
            if (!userInput.equals("esc")) {
                classesTaken.add(userInput.toUpperCase());
                System.out.println("Class added");
            }
        }
        System.out.println();

        // Construct graph based on majors and classes taken
        NodeManager tree = new NodeManager();
        String majorName = majorData.get(headers.indexOf("major"));
        tree.add(NodeManager.ROOT_NAME, new RequirementNode(majorName));
        for (String category : majorData) {
            if (majorData.indexOf(category) < headers.indexOf("requirements**")) continue;
            String[] categoryList = category.split(";");
            RequirementNode categoryNode = new RequirementNode(categoryList[0]);
            tree.add(majorName, categoryNode);
            BuildSubtreeHelper.buildSubtree(tree, categoryNode, categoryList[1]);
        }

        for (String course : classesTaken) {
            tree.setSatisfied(course);
        }

        // Remove courses that must be taken and can be directly taken, iterate until none found

        // Determine pre-requisite combinations to satisfy remaining requirements and add them
        // to a list

        // Evaluate credit count for all pre-requisite combinations
        // Keep track of the minimum then remove any too high above the min

        // Sort remaining combos based on credit count

        // Present results to user
        for (Node majorNode : tree.get(NodeManager.ROOT_NAME).children) {
            System.out.println(majorNode.toStringWithChildren());
        }
    }
}