import java.util.HashMap;
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

//        System.out.println();
//        System.out.println("You've taken...");
//        for (String value : classesTaken) System.out.println("- " + value);
//        System.out.println();
//        System.out.println("You need to take...");
//        for (String value : majorData) {
//            LinkedList<String> splitVals = CSVReader.parseCSVLine(value, ";");
//            if (splitVals.size() < 2) continue;
//            System.out.println("- " + splitVals.get(0) + " : \t" + splitVals.get(1));
//        }

        // Construct graph based on majors and classes taken
        HashMap<String, Node> nodes = new HashMap<>();
        RequirementNode root = new RequirementNode("Graduation");
        nodes.put("root", root);
        nodes.put(majorData.get(headers.indexOf("major")), new RequirementNode(majorData.get(headers.indexOf("major"))));
        root.addPrerequisite(nodes.get(majorData.get(headers.indexOf("major"))));
        for (String category : majorData) {
            if (majorData.indexOf(category) < headers.indexOf("requirements**")) continue;
            String[] categoryList = category.split(";");
            nodes.put(categoryList[0], new RequirementNode(categoryList[0]));
            nodes.get(majorData.get(headers.indexOf("major"))).addPrerequisite(nodes.get(categoryList[0]));
            System.out.println(categoryList[1]);

            int atLeastIndex = categoryList[1].indexOf("#");
            if (atLeastIndex != -1) {
                int numRequired = Integer.parseInt(categoryList[1].substring(atLeastIndex + 1, categoryList[1].indexOf("[")));
                nodes.put(categoryList[0] + " at least", new AtLeastNode(numRequired));
                nodes.get(categoryList[0]).addPrerequisite(nodes.get(categoryList[0] + " at least"));

                String coursesString = categoryList[1].substring(categoryList[1].indexOf("[") + 1, categoryList[1].lastIndexOf("]"));
                String[] atLeastConditions = coursesString.split(",");
                for (String condition : atLeastConditions) {
                    if (condition.contains("+")) {
                        nodes.put(categoryList[0] + " or " + condition, new OrNode());      // TODO: FIX ME..................
                        nodes.get(categoryList[0] + " at least").addPrerequisite(nodes.get(categoryList[0] + " or " + condition));

                        String[] orList = condition.split("\\+");
                        for (String list: orList) {
                            if (list.contains("*")) {
                                nodes.put(categoryList[0] + " and " + list, new AndNode());
                                nodes.get(categoryList[0] + " or " + condition).addPrerequisite(nodes.get(categoryList[0] + " and " + list));

                                String[] andList = condition.split("\\*");
                                for (String course : andList) {
                                    nodes.put(course, new RequirementNode(course));
                                    nodes.get(categoryList[0] + " and " + list).addPrerequisite(nodes.get(course));
                                }
                            } else {
                                nodes.put(list, new RequirementNode(list));
                                nodes.get(categoryList[0] + " or " + condition).addPrerequisite(nodes.get(list));
                            }
                        }
                    } else if (condition.contains("*")) {
                        nodes.put(categoryList[0] + " and " + condition, new AndNode());
                        nodes.get(categoryList[0] + " at least").addPrerequisite(nodes.get(categoryList[0] + " and " + condition));

                        String[] andList = condition.split("\\*");
                        for (String course : andList) {
                            nodes.put(course, new RequirementNode(course));
                            nodes.get(categoryList[0] + " and " + condition).addPrerequisite(nodes.get(course));
                        }
                    } else {
                        nodes.put(condition, new RequirementNode(condition));
                        nodes.get(categoryList[0] + " at least").addPrerequisite(nodes.get(condition));
                    }
                }
            } else if (categoryList[1].contains("+")) {
                nodes.put(categoryList[0] + " or", new OrNode());
                nodes.get(categoryList[0]).addPrerequisite(nodes.get(categoryList[0] + " or"));

                String[] orList = categoryList[1].split("\\+");
                for (String list : orList) {
                    if (list.contains("*")) {
                        nodes.put(categoryList[0] + " and " + list, new AndNode());
                        nodes.get(categoryList[0] + " or").addPrerequisite(nodes.get(categoryList[0] + " and " + list));

                        String[] andList = list.split("\\*");
                        for (String course : andList) {
                            nodes.put(course, new RequirementNode(course));
                            nodes.get(categoryList[0] + " and " + list).addPrerequisite(nodes.get(course));
                        }
                    } else {
                        nodes.put(list, new RequirementNode(list));
                        nodes.get(categoryList[0] + " or").addPrerequisite(nodes.get(list));
                    }
                }
            } else if (categoryList[1].contains("*")) {
                nodes.put(categoryList[0] + " and", new AndNode());
                nodes.get(categoryList[0]).addPrerequisite(nodes.get(categoryList[0] + " and"));

                String[] andList = categoryList[1].split("\\*");
                for (String course : andList) {
                    nodes.put(course, new RequirementNode(course));
                    nodes.get(categoryList[0] + " and").addPrerequisite(nodes.get(course));
                }
            } else {
                nodes.put(categoryList[1], new RequirementNode(categoryList[1]));
                nodes.get(categoryList[0]).addPrerequisite(nodes.get(categoryList[1]));
            }
        }

        // Remove courses that must be taken and can be directly taken, iterate until none found

        // Determine pre-requisite combinations to satisfy remaining requirements and add them
        // to a list

        // Evaluate credit count for all pre-requisite combinations
        // Keep track of the minimum then remove any too high above the min

        // Sort remaining combos based on credit count

        // Present results to user
        System.out.println(root.toStringWithChildren());
    }
}