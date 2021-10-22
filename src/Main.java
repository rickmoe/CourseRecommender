import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        LinkedList<LinkedList<String>> fileLists = CSVReader.readCSV("res/majors.csv");
        for (LinkedList<String> lineList : fileLists) {
            System.out.println();
            for (String value : lineList) {
                LinkedList<String> splitVals = CSVReader.parseCSVLine(value, ";");
                System.out.print("- " + splitVals.get(0));
                if (splitVals.size() > 1) System.out.print(" : \t" + splitVals.get(1));
                System.out.println();
            }
        }

        // Take in input of majors and classes taken

        // Construct graph based on majors and classes taken

        // Remove courses that must be taken and can be directly taken, iterate until none found

        // Determine pre-requisite combinations to satisfy remaining requirements and add them
        // to a list

        // Evaluate credit count for all pre-requisite combinations
        // Keep track of the minimum then remove any too high above the min

        // Sort remaining combos based on credit count

        // Present results to user

    }
}