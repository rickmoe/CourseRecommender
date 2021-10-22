import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class CSVReader {

    public static LinkedList<LinkedList<String>> readCSV(String filePath) {
        LinkedList<LinkedList<String>> output = new LinkedList<>();
        if (filePath == null) return output;
        File file;
        Scanner scnr;
        try {
            file = new File(filePath);
            scnr = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return output;
        }

        while (scnr.hasNextLine()) {
            String toParse = scnr.nextLine();
            output.add(parseCSVLine(toParse));
        }

        return output;
    }

    public static LinkedList<String> parseCSVLine(String toParse) {
        return parseCSVLine(toParse, ",");
    }

    public static LinkedList<String> parseCSVLine(String toParse, String delimiter) {
        LinkedList<String> output = new LinkedList<>();
        String[] splitList = toParse.split(delimiter);
        for (int i = 0; i < splitList.length; i++) {
            boolean evenQuotes = true;
            for (int j = 0; j < splitList[i].length(); j++) if (splitList[i].charAt(j) == '"') evenQuotes = !evenQuotes;
            if (!evenQuotes) {
                splitList[i] += "," + splitList[i + 1];
                String[] tempSplitList = new String[splitList.length - 1];
                for (int j = 0; j < tempSplitList.length; j++) tempSplitList[j] = (j <= i) ? splitList[j] : splitList[j + 1];
                splitList = tempSplitList;
                i--;
            }
        }
        for (String s : splitList) {
            if (s == null) continue;
            if (s.length() >= 2 && s.charAt(0) == '"' && s.charAt(s.length() - 1) == '"')
              s = s.substring(1, s.length() - 1);
            output.add(s.replaceAll("\"\"", "\""));
        }
        return output;
    }

}