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

    @Override
    public void propertyChange(PropertyChangeEvent theEvent) {

    }
}
