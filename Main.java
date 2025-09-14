import java.util.*;
import java.io.*;

class SurveyApp {

    public void RunApp() throws IOException {
    
        // Print introductory message and options for the user
        System.out.println("\nWelcome to the Survey Application, where you can create and manage your survey questions!");
        System.out.println("\nPlease select an option:\n1. Create a new question\n2. Exit");

        try (Scanner scanner = new Scanner(System.in)) {
            int choice = scanner.nextInt();

            if (choice < 1 || choice > 3) {
                System.out.println("\nInvalid choice. Please try again.");
                return;
            }

            else if (choice == 2) {
                System.out.println("\nExiting the application. Goodbye!");
                return;
            }

            else {
                System.out.println("\nEnter the question text:");
                scanner.nextLine();
                String questionText = scanner.nextLine();

                //Store question in file
                System.out.println("\nEnter the type of question (Multiple choice [M] or Open-ended [O]):");
                String questionType = scanner.nextLine();


                File csvFile = new File("questions.csv");
                boolean fileExists = csvFile.exists();
                FileWriter writer = new FileWriter(csvFile, true);
                // Write header if file is new
                if (!fileExists) {
                    writer.write("question,type,answer1,answer2,answer3,answer4,answer5,answer6\n");
                }

                if (questionType.equals("M")) {
                    System.out.println("\nHow many answers do you want?");
                    int answers = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    String[] answerChoices = new String[answers];
                    System.out.println("\nEnter the answer choices: ");
                    for (int i = 0; i < answers; i++) {
                        answerChoices[i] = scanner.nextLine();
                    }
                    // Write only as many answers as entered
                    writer.write("\"" + questionText.replace("\"", "''") + "\"," + questionType);
                    for (int i = 0; i < answers; i++) {
                        writer.write("," + '"' + (answerChoices[i] != null ? answerChoices[i].replace("\"", "''") : "") + '"');
                    }
                    writer.write("\n");
                    System.out.println("Question created: " + questionText + " (" + questionType + ")");
                    writer.close();
                } 
                else if (questionType.equals("O")) {
                    writer.write("\"" + questionText.replace("\"", "''") + "\"," + questionType + ",\"Free response\"\n");
                    System.out.println("Free-Response created");
                    writer.close();
                }

                System.out.println("\nThank you for using the Survey Application. Goodbye!");

                //Close the scanners to display content
                scanner.close();
            }               
        }
    }
}

public class Main {
    // Main method to run the SurveyApp
    public static void main(String[] args) throws IOException {
        SurveyApp app = new SurveyApp();
        app.RunApp();
    }
}