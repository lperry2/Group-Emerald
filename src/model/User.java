package src.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import static java.lang.Character.isDigit;

public class User implements PropertyChangeListener {

    /** The user's name. */
    private String myName;

    /** The user's email. */
    private String myEmail;

    /** An array list for the user's projects. */
    private ArrayList<Project> myProjects;

    /**
     * The public constructor for the User class.
     * @param theName The user's name
     * @param theEmail The user's email
     * @param theProjects The user's projects
     */
    public User(String theName, String theEmail, ArrayList<Project> theProjects) {
        myName = theName;
        myEmail = theEmail;
        myProjects = theProjects;
    }

    /**
     * Returns the user's name
     * @return myName The user's name
     */
    public String getName() {
        return myName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent theEvent) {

    }

    /**
     * Returns the user's projects.
     * @return myProjects The user's projects
     */
    public ArrayList<Project> getProjects() {
        return myProjects;
    }

    /**
     * Returns the user's project names.
     * @return names The user's project names
     */
    public String[] getProjectNames() {
        String[] names = new String[myProjects.size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = myProjects.get(i).getProjectName();
        }
        return names;
    }

    /**
     * Returns a specified project
     * @param theProjectName The project to be returned
     * @return myProjects.get(projectIndex) The project in the arrayList to be returned
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
     * Adds a project to the user's project list.
     * @param theProject the project to be added
     */
    public void addProject(Project theProject) {
        myProjects.add(theProject);
    }

    /**
     * Saves the most recent changes to the user's projects.
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
            saveJournal(path, myProjects.get(i));

        }
    }

    /**
     * Saves the most recent changes to the user's budget.
     * @param thePath The project folder file path
     * @param theProj The project the budget is located in
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
     * Saves the most recent changes to the user's journal.
     * @param thePath The project folder file path
     * @param theProj The project the journal is located in
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

    @Override
    public String toString() {
        String result = "Name: " + myName + "\nEmail: " + myEmail + "\n";
        for (int i = 0; i < myProjects.size(); i++) {
            result += myProjects.get(i).toString() + "\n";
        }
        return result;
    }
}
