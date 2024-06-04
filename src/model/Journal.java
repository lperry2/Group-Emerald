package src.model;

import java.util.ArrayList;

public class Journal {

    private ArrayList<JournalEntry> myEntries;

    public Journal(ArrayList<JournalEntry> theEntries) {
        myEntries = theEntries;
    }

//    public ArrayList<JournalEntry> getEntries() {
//
//    }

    private void addEntry(String theTitle, String theContent) {
        JournalEntry entry = new JournalEntry(theTitle, theContent);
        myEntries.add(entry);
    }
}
