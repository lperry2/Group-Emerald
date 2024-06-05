package src.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class User implements PropertyChangeListener {

    private String myName;

    private String myEmail;

    private ArrayList<Project> myProjects;


    public User(String theName, String theEmail, ArrayList<Project> theProjects) {
        myName = theName;
        myEmail = theEmail;
        myProjects = theProjects;
    }

    public String getName() {
        return myName;
    }


    @Override
    public void propertyChange(PropertyChangeEvent theEvent) {

    }

    public ArrayList<Project> getProjects() {
        return myProjects;
    }

    public String[] getProjectNames() {
        String[] names = new String[myProjects.size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = myProjects.get(i).getProjectName();
        }
        return names;
    }

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

    public void addProject(Project theProject) {
        myProjects.add(theProject);
    }

    public void save() {
        for (int i = 0; i < myProjects.size(); i++) {
            String path = "src/" + myName + "/" + myProjects.get(i).getProjectName();
            saveBudget(path, myProjects.get(i));
            saveJournal(path, myProjects.get(i));

        }
    }

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