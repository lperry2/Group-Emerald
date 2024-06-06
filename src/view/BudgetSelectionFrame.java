package src.view;

import src.model.Budget;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Implementation of an AbstractSelectionFrame that is used for editing and deleting
 * ExpenseItems in the Budget.
 *
 * @author Owen Orlic
 */
public class BudgetSelectionFrame extends AbstractSelectionFrame {

    /** Fires to listeners if an expense needs edited or deleted. */
    private final PropertyChangeSupport myPcs = new PropertyChangeSupport(this);

    /** The budget being changed. */
    private Budget myBudget;

    /**
     * Sends title and option to the AbstractSelectionFrame. theTitle will become the title
     * of the JFrame and theOption will decide whether the selection is for editing for deleting.
     * Uses theBudget to get all ExpenseItems in that budget.
     *
     * @param theBudget the budget whose items are being edited or deleted
     * @param theTitle the title of the JFrame
     * @param theOption choice between editing or deleting the selection
     */
    public BudgetSelectionFrame(Budget theBudget, String theTitle, int theOption) {
        super(theTitle, theOption);
        myBudget = theBudget;

        setupBtnActions();
    }

    /**
     * Based on myOption, either sets up the Button Actions to allow for
     * editing or deletion.
     */
    private void setupBtnActions() {
        if (myOption == 0) {
            setupEditBtns();
        } else if (myOption == 1) {
            setupDeleteBtns();
        } else {
            System.out.println("Error with myOption selection. BudgetSelectionFrame.java");
        }
    }

    /**
     * Makes it so the selected expense can be edited.
     */
    private void setupEditBtns() {
        for (int i = 0; i < myBudget.getExpenses().size(); i++) {
            JButton btn = new JButton(myBudget.getExpenses().get(i).getName());
            int finalI = i;
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newExpense = (String) JOptionPane.showInputDialog(null,"Please Enter New Cost of Expense: ",
                                                                    "Changing Expense", JOptionPane.INFORMATION_MESSAGE, makePeteSmall(),
                                                                     null, null);
                    double expense = Double.parseDouble(newExpense);
                    //improper use of firePropertyChange(), just go with it
                    myPcs.firePropertyChange("repaintPageBudgetEdit", btn.getText(), expense);
                    BudgetSelectionFrame.this.dispose(); //close frame
                }
            });
            myPanel.add(btn);
        }
    }

    /**
     * Makes it so the selected expense will be deleted. Prompts twice before deleting.
     */
    private void setupDeleteBtns() {
        for (int i = 0; i < myBudget.getExpenses().size(); i++) {
            JButton btn = new JButton(myBudget.getExpenses().get(i).getName());
            int finalI = i;
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int firstChoice; //first verification
                    int secondChoice; //second verification
                    firstChoice = JOptionPane.showConfirmDialog(null, "Would Like To Delete This Expense?",
                            "Just Confirming", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, makePeteSmall());
                    if (firstChoice == 0) { //chose yes
                        secondChoice = JOptionPane.showConfirmDialog(null, "Are You Sure You Would Like To Delete This Expense?",
                                "Double Checking", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, makePeteSmall());
                        if (secondChoice == 0) { //chose yes
                            //improper use of firePropertyChange(), just go with it
                            myPcs.firePropertyChange("repaintPageBudgetDelete", btn.getText(), "neverGetsUsed");
                        }
                    }
                    //close frame when button is pressed
                    BudgetSelectionFrame.this.dispose();
                }
            });
            myPanel.add(btn);
        }
    }

    /**
     * Method that returns a small pete for JOptionPanes.
     *
     * @author Daniel
     * @return little version of pete
     */
    private static ImageIcon makePeteSmall() {
        //code to make pete normal-sized
        ImageIcon icon = new ImageIcon("src/images/projectpete.png");
        Image img = icon.getImage();
        // Resize the image to 50x50 pixels
        Image resizedImg = img.getScaledInstance(100, 75, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        return resizedIcon;
    }

    /**
     * Adds a listener for property change events from this class.
     *
     * @param theListener a PropertyChangeListener to add.
     */
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    /**
     * Removes a listener for property change events from this class.
     *
     * @param theListener a PropertyChangeListener to remove.
     */
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);

    }

}

