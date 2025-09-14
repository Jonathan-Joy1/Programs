import java.io.IOException;

import java.io.*;
import java.util.*;
public class Processor {
    // Main method to run the SurveyApp
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        File csvFile = new File("questions.csv");

        System.out.println("\nWould you like to view existing questions? (yes/no)");      

        if (scanner.nextLine().trim().toLowerCase().equals("yes")) {
            System.out.println("\nDisplaying existing questions...");
            if (!csvFile.exists()) {
                System.out.println("No questions found.");
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(csvFile));
                String line;
                boolean isHeader = true;
                while ((line = reader.readLine()) != null) {
                    if (isHeader) { isHeader = false; continue; } // skip header
                    String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // split on commas not inside quotes
                    String question = parts[0].replaceAll("^\"|\"$", "");
                    String type = parts[1].replaceAll("^\"|\"$", "");
                    System.out.println("Question: " + question);
                    System.out.println("Type: " + (type.equals("M") ? "Multiple Choice" : "Open-ended"));
                    if (type.equals("M")) {
                        for (int i = 2; i < parts.length; i++) {
                            String ans = parts[i].replaceAll("^\"|\"$", "");
                            if (!ans.isEmpty()) {
                                System.out.println("  Choice " + (i-1) + ": " + ans);
                            }
                        }
                    } else if (type.equals("O")) {
                        System.out.println("  Free response");
                    }
                    System.out.println();
                }
                reader.close();
            }
            System.out.println("\n");
        }
        scanner.close();
    }
}