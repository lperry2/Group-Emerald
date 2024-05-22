package src.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProjectDataReader {

    public static void main(String[] args) {
        readProjectData();
    }

    public static void readProjectData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("project_data.txt"))) {
            String line;
            StringBuilder projectBlock = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    // End of a project block
                    if (projectBlock.length() > 0) {
                        displayProjectBlock(projectBlock.toString());
                        projectBlock.setLength(0);  // Clear the StringBuilder for the next block
                    }
                } else {
                    projectBlock.append(line).append(System.lineSeparator());
                }
            }

            // Handle the last block if the file doesn't end with a blank line
            if (projectBlock.length() > 0) {
                displayProjectBlock(projectBlock.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayProjectBlock(String block) {
        System.out.println("Project Block:");
        System.out.println(block);
    }
}
