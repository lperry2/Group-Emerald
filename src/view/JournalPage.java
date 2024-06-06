package src.view;

import src.model.Journal;
import src.model.JournalEntry;
import src.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class JournalPage extends AbstractPage implements PropertyChangeListener {

    /** The current journal. */
    private Journal myCurrentJournal;

    /** The project name. */
    private String myProjectName;

    /**
     * Sends parameters to the super class constructor and calls setup().
     * Sets myCurrentJournal to the journal from the project denoted by the
     * project name passed in.
     *
     * @author Owen Orlic
     * @param theUser user using the application
     * @param theProjectName just the name of the project, not the pathname
     * @param theType type of txt file we need
     */
    public JournalPage(User theUser, String theProjectName, String theType) {
        super(theUser, theProjectName, theType);
        myCurrentJournal = myCurrentProject.getJournal();
        myProjectName = theProjectName;
        if (myProjectName.charAt(0) == '~') {
            myProjectName = myProjectName.substring(1);
        }
        setup();
    }

    /**
     * Calls the other individual setup methods and makes the JFrame visible.
     *
     * @author Owen Orlic
     */
    private void setup() {
        setupHeader();
        setupEntries();
        setupButtons();
    }
    /**
     * Creates a header that shows the journal in the form:
     * Journal for projectName.
     */
    private void setupHeader() {

        myTitleLabel = new JLabel("Journal for " + myProjectName);
        myTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        myTitlePanel = new JPanel();
        myTitlePanel.add(myTitleLabel);
        this.add(myTitlePanel, BorderLayout.NORTH);
    }

    /**
     * Sets up and displays all the entries.
     */
    private void setupEntries() {
        //will show if there are no entries yet
        String allEntries = "There are currently no journal entries for this project!";

        //if there are entries, list them
        if (myCurrentJournal.getEntries().size() != 0.0) {
            allEntries = "<html>";
            ArrayList<JournalEntry> entries = myCurrentJournal.getEntries();
            for (int i = 0; i < entries.size(); i++) {
                allEntries += entries.get(i).toString() + "<br/>"; //<br/> is line break for JLabel
            }
            allEntries += "</html>";
        }
        myContentLabel = new JLabel(allEntries);
        myContentPanel = new JPanel();

        myContentPanel.add(myContentLabel);
        this.add(myContentPanel, BorderLayout.CENTER);
    }

    /**
     * Sets up the add item and save buttons.
     *
     * @author Owen Orlic
     */
    private void setupButtons() {
        myButtonPanel = new JPanel();
        JButton addBtn = new JButton("Add Entry");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entryTitle = JOptionPane.showInputDialog("Please Enter Name of the Entry: ");
                String entryContent = JOptionPane.showInputDialog("Please Enter Content of Entry: ");
                myCurrentJournal.addEntry(entryTitle, entryContent);
                writeEntries();
            }
        });
        myButtonPanel.add(addBtn, new FlowLayout());

        JButton editBtn = new JButton("Edit Item");
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new EditSelectionFrame(myCurrentBudget);
//                writeExpenses();
                //openEditSelection(myCurrentBudget);
                String title = "Please Select an Expense to Edit.";
                JournalSelectionFrame frame = new JournalSelectionFrame(myCurrentJournal, title, BudgetSelectionFrame.EDIT_OPTION);
                frame.addPropertyChangeListener(JournalPage.this);
                writeEntries();

            }
        });
        myButtonPanel.add(editBtn, new FlowLayout());

        JButton deleteBtn = new JButton("Delete Item");
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String title = "Please Select an Expense to Delete.";
                JournalSelectionFrame frame = new JournalSelectionFrame(myCurrentJournal, title, BudgetSelectionFrame.DELETE_OPTION);
                frame.addPropertyChangeListener(JournalPage.this);
                writeEntries();

            }
        });
        myButtonPanel.add(deleteBtn, new FlowLayout());

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myCurrentUser.save();
            }
        });
        myButtonPanel.add(saveBtn, new FlowLayout());

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JournalPage.this.dispose();
            }
        });
        myButtonPanel.add(backBtn, new FlowLayout());

        this.add(myButtonPanel, BorderLayout.SOUTH);
    }

    /**
     * Used for updating the page when budget info has changed.
     *
     * @author Owen Orlic
     */
    private void writeEntries() {
        String allEntries = "<html>";
        ArrayList<JournalEntry> entries = myCurrentJournal.getEntries();

        //remove labels
        myTitlePanel.removeAll();
        myContentPanel.removeAll();

        //get label's info again
        for (int i = 0; i < entries.size(); i++) {
            allEntries += entries.get(i).toString() + "<br/>";
        }
        allEntries += "</html>";

        myContentLabel = new JLabel(allEntries);
        myTitleLabel = new JLabel("Journal for " + myProjectName);
        myTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        //add everything back
        myTitlePanel.add(myTitleLabel);
        myContentPanel.add(myContentLabel);
        this.add(myContentPanel, BorderLayout.CENTER);
        this.add(myTitlePanel, BorderLayout.NORTH);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        System.out.println(theEvent);
        if (theEvent.getPropertyName().equals("repaintPageJournalEdit")) {
            myCurrentJournal.editEntry((String) theEvent.getOldValue(), (String) theEvent.getNewValue());
            writeEntries();
        } else if (theEvent.getPropertyName().equals("repaintPageJournalDelete")) {
            myCurrentJournal.deleteEntry((String) theEvent.getOldValue());
            writeEntries();
        }
    }
}
