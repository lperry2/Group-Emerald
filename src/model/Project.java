package src.model;

import java.util.ArrayList;

/** Represents a project */
public class Project {
    /** The name of the project. */
    private String myProjectName;
    /** The description of the project. */
    //private String myProjectDescription;
    /** The project's budget. */
    private Budget myBudget;

    private Journal myJournal;

    private boolean isPrivate;


    /**
     * The constructor for the Project class.
     *
     * @author Alex Ewing
     * @param theProjectName the name of the project
     * @param theBudget the budget of the project
     */
    public Project(String theProjectName, Budget theBudget){
        myProjectName = theProjectName;
        myBudget = theBudget;
        myJournal = new Journal(new ArrayList<JournalEntry>());
    }

    /**
     * The constructor for the Project class, with a Journal too.
     *
     * @author Alex Ewing
     * @param theProjectName the name of the project
     * @param theBudget the budget of the project
     * @param theJournal the journal for the project
     */
    public Project(String theProjectName, Budget theBudget, Journal theJournal){
        myProjectName = theProjectName;
        myBudget = theBudget;
        myJournal = theJournal;
    }

    /**
     * Gets the project name.
     *
     * @author Alex Ewing
     * @return myProjectName the name of the project
     */
    public String getProjectName() {
        return myProjectName;
    }

    /**
     * Sets the project name.
     *
     * @author Alex Ewing
     * @param  theProjectName the new name of the project
     */
    public void setProjectName(String theProjectName) {
        this.myProjectName = theProjectName;
    }

    public Budget getBudget() {
        return myBudget;
    }

    public Journal getJournal() {
        return myJournal;
    }

    /**
     * Gets the project description.
     * @return myProjectDescription the description of the project
     * @author Alex Ewing
     */
//    public String getProjectDescription() {
//        return myProjectDescription;
//    }

    /**
     * Sets the project description.
     * @param theProjectDescription the new description of the project
     * @author Alex Ewing
     */
//    public void setProjectDescription(String theProjectDescription) {
//        this.myProjectDescription = theProjectDescription;
//    }

    @Override
    public String toString() {
        String result = "Project " + myProjectName;
        result += myBudget.toString();
        return result;
    }
}

