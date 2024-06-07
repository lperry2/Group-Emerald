package src.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import static java.lang.Character.isDigit;

/**
 * A class representing a user.
 *
 * @author Owen Orlic
 * @author Alex Ewing
 */
public class User implements PropertyChangeListener {

    /** The user's name. */
    private String myName;

    /** The user's email. */
    private String myEmail;

    /** The user's projects. */
    private ArrayList<Project> myProjects;

    /**
     * The public constructor for the User class.
     * @param theName the user's name
     * @param theEmail the user's email
     * @param theProjects the user's projects
     */
    public User(String theName, String theEmail, ArrayList<Project> theProjects) {
        myName = theName;
        myEmail = theEmail;
        myProjects = theProjects;
    }

    /**
     * Gets the user's name.
     * @return myName the user's name
     */
    public String getName() {
        return myName;
    }


    @Override
    public void propertyChange(PropertyChangeEvent theEvent) {

    }

    /**
     * Gets the user's projects.
     * @return myProjects the user's projects
     */
    public ArrayList<Project> getProjects() {
        return myProjects;
    }

    /**
     * Gets the names of the user's projects.
     * @return names The names of the user's projects
     */
    public String[] getProjectNames() {
        String[] names = new String[myProjects.size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = myProjects.get(i).getProjectName();
        }
        return names;
    }

    /**
     * Gets a specified project from the user's projects.
     * @param theProjectName the name of the desired project
     * @return myProjects.get(projectIndex) The desired project
     */
    public Project getProject(String theProjectName) {
        String correct = "src/" + myName + "/" + theProjectName;
        int projectIndex = 0;
        for (int i = 0; i < myProjects.size(); i++) {
            String projPath = "src/" + myName + "/" + myProjects.get(i).getProjectName();
            //System.out.println(correct + " = " + projPath);
            if (correct.equals(projPath)) {
                projectIndex = i;
                break;
            }
        }
        return myProjects.get(projectIndex);
    }

    /**
     * Adds a project to the user's projects.
     * @param theProject the project to be added.
     */
    public void addProject(Project theProject) {
        myProjects.add(theProject);
    }

    /**
     * Saves the most recent changes to the project.
     */
    public void save() {
        for (int i = 0; i < myProjects.size(); i++) {
            String projName = myProjects.get(i).getProjectName();
            char end = projName.charAt(projName.length() - 1);
            //check if project name has pin at end
            if (isDigit(end)) {
                projName = projName.substring(0, projName.length() - 4);
            }
            String path = "src/" + myName + "/" + projName;
            //System.out.println("myProjects.get(i).getProjectName(): " + myProjects.get(i).getProjectName());
            //System.out.println("myProjects.get(i): " + myProjects.get(i));
            saveBudget(path, myProjects.get(i));
            saveFiles(path, myProjects.get(i));
            saveJournal(path, myProjects.get(i));

        }
    }

    /**
     * Saves the most recent changes to the budget.
     * @param thePath the path to the budget folder
     * @param theProj the project
     */
    private void saveBudget(String thePath, Project theProj) {
        Budget budget = theProj.getBudget();
        ArrayList<ExpenseItem> expenses = budget.getExpenses();
        File projFolder = new File(thePath);
        File oldBudget = new File(thePath + "/Budget.txt");
        oldBudget.delete();
        File newBudget = new File(projFolder,"/Budget.txt");
        try (PrintStream out = new PrintStream(newBudget)) {
            out.println("+");
            out.println("Budget | " + budget.getTotal());
            for (int i = 0; i < expenses.size(); i++) {
                ExpenseItem ex = expenses.get(i);
                out.println("\n----");            //----
                out.println(ex.getName());        //Expense Name
                out.print(ex.getExpense());       //Expense Cost
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * Saves the most recent changes to the Files.
     * @param thePath the path to the Files folder
     * @param theProj the project
     */
    private void saveFiles(String thePath, Project theProj) {
        FileGroup files = theProj.getFiles();
        ArrayList<SingleFile> singleFiles = files.getFiles();
        System.out.println(singleFiles.size());
        File projFolder = new File(thePath);
        File oldFiles = new File(projFolder, "/Files.txt");
        oldFiles.delete();
        File newFiles = new File(projFolder, "/Files.txt");
        try (PrintStream out = new PrintStream(newFiles)) {
            out.println("+");
            out.println("Files | placeholderInUser.javaSaveFiles");
            for (int i = 0; i < singleFiles.size(); i++) {
                SingleFile f = singleFiles.get(i);
                out.println("\n----");                   //----
                out.println(f.getName());                //Entry Title
                out.print(f.getFile().toString());       //Entry Content
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * Saves the most recent changes to the journal.
     * @param thePath the path to the Journal folder
     * @param theProj the project
     */
    private void saveJournal(String thePath, Project theProj) {
        Journal journal = theProj.getJournal();
        ArrayList<JournalEntry> entries = journal.getEntries();
        File projFolder = new File(thePath);
        File oldJournal = new File(thePath + "/Journal.txt");
        oldJournal.delete();
        File newJournal = new File(projFolder,"/Journal.txt");
        try (PrintStream out = new PrintStream(newJournal)) {
            out.println("+");
            out.println("Journal | placeholderInUser.javaSaveJournal");
            for (int i = 0; i < entries.size(); i++) {
                JournalEntry en = entries.get(i);
                out.println("\n----");            //----
                out.println(en.getTitle());       //Entry Title
                out.print(en.getContent());       //Entry Content
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * A string representation of a User.
     *
     * @return a string representation of a user
     */
    @Override
    public String toString() {
        String result = "Name: " + myName + "\nEmail: " + myEmail + "\n";
        for (int i = 0; i < myProjects.size(); i++) {
            result += myProjects.get(i).toString() + "\n";
        }
        return result;
    }
}
