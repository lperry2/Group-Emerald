package src.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Journal {

    private ArrayList<JournalEntry> myEntries;

    public Journal(ArrayList<JournalEntry> theEntries) {
        myEntries = theEntries;
    }

    public ArrayList<JournalEntry> getEntries() {
        return myEntries;
    }



    public void addEntry(String theTitle, String theContent) {
        JournalEntry entry = new JournalEntry(theTitle, theContent);
        myEntries.add(entry);
    }
}
