package src.model;

import javax.swing.*;

public class JournalEntry {

    private String myTitle;

    private String myContent;

    public JournalEntry(String theTitle, String theContent) {
        myTitle = theTitle;
        myContent = theContent;
    }

    public String getTitle() {
        return myTitle;
    }

    public void setTitle(String theTitle) {
        myTitle = theTitle;
    }

    public String getContent() {
        return myContent;
    }

    public void setContent(String theContent) {
        myContent = theContent;
    }



    @Override
    public String toString() {
        return myTitle + ": " + myContent;
    }
}
