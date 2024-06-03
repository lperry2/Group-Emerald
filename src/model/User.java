package src.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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

    public Project getProject(String theProjectName) {
        String correct = "src/" + myName + "/" + theProjectName;
        int projectIndex = -1;
        for (int i = 0; i < myProjects.size(); i++) {
            String projPath = "src/" + myName + "/" + myProjects.get(i).getProjectName();
            System.out.println(correct + " = " + projPath);
            if (correct.equals(projPath)) {
                projectIndex = i;
                break;
            }
        }
        return myProjects.get(projectIndex);
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
