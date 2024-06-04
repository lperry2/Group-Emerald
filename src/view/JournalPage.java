package src.view;

import src.model.User;

public class JournalPage extends AbstractPage {



    /**
     * Sends parameters to the super class constructor and calls setup().
     *
     * @author Owen Orlic
     * @param theUser user using the application
     * @param theProjectName just the name of the project, not the pathname
     * @param theType type of txt file we need
     */
    public JournalPage(User theUser, String theProjectName, String theType) {
        super(theUser, theProjectName, theType);
        //myCurrentBudget = myCurrentProject.getBudget();
        System.out.println("Project Name: " + theProjectName);

        //setup();

    }
}
