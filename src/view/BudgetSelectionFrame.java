package src.view;

import src.model.Budget;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BudgetSelectionFrame extends AbstractSelectionFrame {

//    /**
//     * A factor for scaling the size of the GUI relative to
//     * the current screen size.
//     */
//    private static final int SCALE = 3;
//
//    /** A ToolKit. */
//    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
//
//    /** The Dimension of the screen. */
//    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
//
//    /** The width of the screen. */
//    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;
//
//    /** The height of the screen. */
//    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;
//
//    public static final int EDIT_OPTION = 0;
//
//    public static final int DELETE_OPTION = 1;

    private final PropertyChangeSupport myPcs = new PropertyChangeSupport(this);
    // private User myUser;

    private Budget myBudget;

//    /** Panel that will hold all the buttons with the ExpenseItem's names on them. */
//    private JPanel myPanel;
//
//    /**
//     * This denotes whether this is an edit frame or a delete frame.
//     * Edit frames are chosen with 0 and delete frames are chosen with 1.
//     * If neither 0 or 1 is used, 0 is automatically selected.
//     */
//    private int myOption;

    public BudgetSelectionFrame(Budget theBudget, String theTitle, int theOption) {
        super(theTitle, theOption);
        //myUser = theUser;
        myBudget = theBudget;
//        myPanel = new JPanel(new GridLayout(0, 1, 0, 4));
//        if (theOption == 0 || theOption == 1) {
//            myOption = theOption;
//        } else {
//            myOption = 0;
//        }
        setup();
    }

    /**
     * Sets the location and size of the search frame, makes the
     * search list, and makes the frame visible.
     *
     * @author Owen Orlic
     */
    private void setup() {
//        this.setLocation(SCREEN_WIDTH / SCALE,
//                SCREEN_HEIGHT / SCALE);
//        this.setSize(SCREEN_WIDTH/ SCALE, SCREEN_HEIGHT / SCALE);

        //the panel that will hold all the buttons with ExpenseItem's names on them
        //JPanel expensePanel = new JPanel(new GridLayout(0, 1, 0, 4));

//        for (int i = 0; i < myBudget.getExpenses().size(); i++) {
//            JButton btn = new JButton(myBudget.getExpenses().get(i).getName());
//            int finalI = i;
//            btn.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    String newExpense = JOptionPane.showInputDialog("Please Enter New Cost of Expense: ");
//                    double expense = Double.parseDouble(newExpense);
//                    //myBudget.editExpense(btn.getText(), expense);
//                    //improper use of firePropertyChange(), just go with it
//                    myPcs.firePropertyChange("repaintPage", btn.getText(), expense);
//                    EditSelectionFrame.this.dispose();
//                }
//            });
//            myPanel.add(btn);
//        }
        makeBtnActions();
//        this.add(myPanel, BorderLayout.CENTER);
        //this.setVisible(true);
    }

    private void makeBtnActions() {
        if (myOption == 0) {
            setupEditBtns();
        } else if (myOption == 1) {
            setupDeleteBtns();
        } else {
            System.out.println("Error with myOption selection. EditSelectionFrame.java");
        }
    }

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
                    BudgetSelectionFrame.this.dispose();
                }
            });
            myPanel.add(btn);
        }
    }

    private void setupDeleteBtns() {
        for (int i = 0; i < myBudget.getExpenses().size(); i++) {
            JButton btn = new JButton(myBudget.getExpenses().get(i).getName());
            int finalI = i;
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int firstChoice;
                    int secondChoice;
                    firstChoice = JOptionPane.showConfirmDialog(null, "Would Like To Delete This Expense?",
                            "Just Confirming", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, makePeteSmall());
                    if (firstChoice == 0) {
                        secondChoice = JOptionPane.showConfirmDialog(null, "Are You Sure You Would Like To Delete This Expense?",
                                "Double Checking", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, makePeteSmall());
                        if (secondChoice == 0) {

                        }
                    }

                    //improper use of firePropertyChange(), just go with it
                    myPcs.firePropertyChange("repaintPageBudgetDelete", btn.getText(), "neverGetsUsed");
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



//    public Budget getBudget() {
//        return myBudget;
//    }

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

