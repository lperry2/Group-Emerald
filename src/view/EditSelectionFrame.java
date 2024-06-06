package src.view;

import src.model.Budget;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EditSelectionFrame extends JFrame {

    /**
     * A factor for scaling the size of the GUI relative to
     * the current screen size.
     */
    private static final int SCALE = 3;

    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;

    private final PropertyChangeSupport myPcs = new PropertyChangeSupport(this);
   // private User myUser;

    private Budget myBudget;

    public EditSelectionFrame(Budget theBudget) {
        super("Choose an Expense to Edit");
        //myUser = theUser;
        myBudget = theBudget;
        setup();
    }

    /**
     * Sets the location and size of the search frame, makes the
     * search list, and makes the frame visible.
     *
     * @author Owen Orlic
     */
    private void setup() {
        this.setLocation(SCREEN_WIDTH / SCALE,
                SCREEN_HEIGHT / SCALE);
        this.setSize(SCREEN_WIDTH/ SCALE, SCREEN_HEIGHT / SCALE);

        //the panel that will hold all the buttons with ExpenseItem's names on them
        JPanel expensePanel = new JPanel(new GridLayout(0, 1, 0, 4));

        for (int i = 0; i < myBudget.getExpenses().size(); i++) {
            JButton btn = new JButton(myBudget.getExpenses().get(i).getName());
            int finalI = i;
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newExpense = JOptionPane.showInputDialog("Please Enter New Cost of Expense: ");
                    double expense = Double.parseDouble(newExpense);
                    //myBudget.editExpense(btn.getText(), expense);
                    //improper use of firePropertyChange(), just go with it
                    myPcs.firePropertyChange("repaint page", newExpense, expense);
                    EditSelectionFrame.this.dispose();
                }
            });
            expensePanel.add(btn);
        }
        this.add(expensePanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public Budget getBudget() {
        return myBudget;
    }

    /**
     * Adds a listener for property change events from this class.
     *
     * @param theListener a PropertyChangeListener to add.
     */
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }
}
