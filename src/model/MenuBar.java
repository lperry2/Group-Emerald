package src.model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class MenuBar extends JMenuBar {

    private JMenu myBudgetMenu;

    private JMenu myFilesMenu;

    private JMenu myJournalMenu;

    private String myProjectName;

    private User myCurrentUser;

    public MenuBar(String theProjectName, User theUser) {
        myBudgetMenu = new JMenu("Budget");
        myFilesMenu = new JMenu("Files");
        myJournalMenu = new JMenu("Journal");

        myProjectName = theProjectName;
        myCurrentUser = theUser;

        setup();
    }

    private void setup() {
        setupBudgetMenu();
        add(myBudgetMenu);
        setupFilesMenu();
        add(myFilesMenu);
        setupJournalMenu();
        add(myJournalMenu);
    }

    private void setupBudgetMenu() {
        JMenuItem budgetPage = setupPageChangeItem(myBudgetMenu.getText());
        myBudgetMenu.add(budgetPage);

    }

    private void setupFilesMenu() {
        JMenuItem filesPage = setupPageChangeItem(myFilesMenu.getText());
        myFilesMenu.add(filesPage);

    }

    private void setupJournalMenu() {
        JMenuItem journalPage = setupPageChangeItem(myJournalMenu.getText());
        myJournalMenu.add(journalPage);

    }

    public JMenuItem setupPageChangeItem(String theType) {
        JMenuItem pageChangeItem = new JMenuItem(theType + "...");
        pageChangeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (theType.equals("Budget")) {
                    new BudgetPage(myCurrentUser, myProjectName, theType);
                }
//                try {
//                    new MenuReaderPopulation(f, myCurrentUser, myProjectName, theType);
//                } catch (FileNotFoundException ex) {
//                    throw new RuntimeException(ex);
//                }

            }
        });
        return pageChangeItem;
    }

}
