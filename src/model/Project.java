package src.model;

public class Project {
    /** The name of the project. */
    private String myProjectName;
    /** The description of the project. */
    private String myProjectDescription;

    private boolean isPrivate;

    /**
     * The constructor for the Project class.
     *
     * @param theProjectName the name of the project
     * @param theProjectDescription the description of the project
     */
    public Project(String theProjectName, String theProjectDescription){
        myProjectName = theProjectName;
        myProjectDescription = theProjectDescription;
    }

    /**
     * Gets the project name.
     * @return myProjectName the name of the project
     */
    public String getProjectName() {
        return myProjectName;
    }

    /**
     * Sets the project name.
     * @param  theProjectName the new name of the project
     */
    public void setProjectName(String theProjectName) {
        this.myProjectName = theProjectName;
    }

    /**
     * Gets the project description.
     * @return myProjectDescription the description of the project
     */
    public String getProjectDescription() {
        return myProjectDescription;
    }

    /**
     * Sets the project description.
     * @param theProjectDescription the new description of the project
     */
    public void setProjectDescription(String theProjectDescription) {
        this.myProjectDescription = theProjectDescription;
    }
}

