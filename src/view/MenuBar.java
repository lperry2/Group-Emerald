package src.view;

import src.model.User;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Menu Bar used for the OptionFrame.
 *
 * @author Owen Orlic
 */
public class MenuBar extends JMenuBar {

    /** The budget menu. */
    private JMenu myBudgetMenu;

    /** The files menu. */
    private JMenu myFilesMenu;

    /** The journal menu. */
    private JMenu myJournalMenu;

    /** The project name. */
    private String myProjectName;

    /** The current user. */
    private User myCurrentUser;

    /**
     * The public constructor for the MenuBar class.
     * @param theProjectName the name of the project
     * @param theUser the name of the user
     */
    public MenuBar(String theProjectName, User theUser) {
        myBudgetMenu = new JMenu("Budget");
        myFilesMenu = new JMenu("Files");
        myJournalMenu = new JMenu("Journal");

        myProjectName = theProjectName;
        myCurrentUser = theUser;

        setup();
    }

    /**
     * Sets up the menu bar.
     */
    private void setup() {
        setupBudgetMenu();
        add(myBudgetMenu);
        setupFilesMenu();
        add(myFilesMenu);
        setupJournalMenu();
        add(myJournalMenu);
    }

    /**
     * Sets up the budget menu.
     */
    private void setupBudgetMenu() {
        JMenuItem budgetPage = setupPageChangeItem(myBudgetMenu.getText());
        myBudgetMenu.add(budgetPage);

    }

    /**
     * Sets up the files menu.
     */
    private void setupFilesMenu() {
        JMenuItem filesPage = setupPageChangeItem(myFilesMenu.getText());
        myFilesMenu.add(filesPage);

    }

    /**
     * Sets up the journal menu.
     */
    private void setupJournalMenu() {
        JMenuItem journalPage = setupPageChangeItem(myJournalMenu.getText());
        myJournalMenu.add(journalPage);

    }

    /**
     * Sets up the page change system.
     * @param theType the type of page
     * @return pageChangeItem the page change item
     */
    public JMenuItem setupPageChangeItem(String theType) {
        JMenuItem pageChangeItem = new JMenuItem(theType + "...");
        pageChangeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (theType.equals("Budget")) {
                    new BudgetPage(myCurrentUser, myProjectName, theType);
                } else if(theType.equals("Files")) {
                    new FilePage(myCurrentUser, myProjectName, theType);
                } else if (theType.equals("Journal")) {
                    new JournalPage(myCurrentUser, myProjectName, theType);
                }

            }
        });
        return pageChangeItem;
    }

}
