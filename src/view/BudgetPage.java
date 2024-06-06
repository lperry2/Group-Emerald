package src.view;

import src.model.Budget;
import src.model.ExpenseItem;
import src.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class BudgetPage extends AbstractPage implements PropertyChangeListener {

    /** For watching if there are shapes currently drawn. */
    //private final PropertyChangeSupport myPcs = new PropertyChangeSupport(this);

    private Budget myCurrentBudget;


    /**
     * Sends parameters to the super class constructor and calls setup().
     * Also instantiates myCurrentBudget.
     *
     * @author Owen Orlic
     * @param theUser user using the application
     * @param theProjectName just the name of the project, not the pathname
     * @param theType type of txt file we need
     */
    public BudgetPage(User theUser, String theProjectName, String theType) {
        super(theUser, theProjectName, theType);
        myCurrentBudget = myCurrentProject.getBudget();
        //System.out.println("Project Name: " + theProjectName);

        setup();

    }

    /**
     * Calls the other individual setup methods and makes the JFrame visible.
     *
     * @author Owen Orlic
     */
    private void setup() {

        setupHeader();
        setupExpenses();
        setupButtons();
        this.setVisible(true);

    }

    /**
     * Creates a header that shows the budget in the form:
     * totalExpense / projectBudget.
     */
    private void setupHeader() {
        //double expenses = myCurrentBudget.getTotalExpenses();
        //double total = myCurrentBudget.getTotal();
        String expenses = String.format("%.2f", myCurrentBudget.getTotalExpenses());
        String total = String.format("%.2f", myCurrentBudget.getTotal());
        myTitleLabel = new JLabel("Budget: $" + expenses + "/" + total);
        myTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        myTitlePanel = new JPanel();
        myTitlePanel.add(myTitleLabel);
        this.add(myTitlePanel, BorderLayout.NORTH);
    }

    private void setupExpenses() {
        //will show if there are no expenses yet
        String allExpenses = "There are currently no expenses for this project!";

        //if there are expenses, list them
        if (myCurrentBudget.getTotalExpenses() != 0.0) {
            allExpenses = "<html>";
            ArrayList<ExpenseItem> expenseItems = myCurrentBudget.getExpenses();
            for (int i = 0; i < expenseItems.size(); i++) {
                allExpenses += expenseItems.get(i).toString() + "<br/>"; //<br/> is line break for JLabel
            }
            allExpenses += "</html>";
        }
        myContentLabel = new JLabel(allExpenses);
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
        JButton addBtn = new JButton("Add Item");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String expenseName = JOptionPane.showInputDialog("Please Enter Name of the Expense: ");
                String expenseCost = JOptionPane.showInputDialog("Please Enter Cost of Expense: ");
                double expense = Double.parseDouble(expenseCost);
                myCurrentBudget.addExpense(expenseName, expense);
                writeExpenses();
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
                BudgetSelectionFrame frame = new BudgetSelectionFrame(myCurrentBudget, title, BudgetSelectionFrame.EDIT_OPTION);
                frame.addPropertyChangeListener(BudgetPage.this);
                writeExpenses();

            }
        });
        myButtonPanel.add(editBtn, new FlowLayout());

        JButton deleteBtn = new JButton("Delete Item");
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String title = "Please Select an Expense to Delete.";
                BudgetSelectionFrame frame = new BudgetSelectionFrame(myCurrentBudget, title, BudgetSelectionFrame.DELETE_OPTION);
                frame.addPropertyChangeListener(BudgetPage.this);
                writeExpenses();

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

        this.add(myButtonPanel, BorderLayout.SOUTH);
    }

    private void openEditSelection(Budget theBudget) {
        //EditSelectionFrame f = new EditSelectionFrame(theBudget, 0, this);
        //f.addPropertyChangeListener(this);
        writeExpenses();
    }

    /**
     * Used for updating the page when budget info has changed.
     *
     * @author Owen Orlic
     */
    private void writeExpenses() {
        String allExpenses = "<html>";
        ArrayList<ExpenseItem> expenseItems = myCurrentBudget.getExpenses();

        //remove labels
        myTitlePanel.removeAll();
        myContentPanel.removeAll();

        //get label's info again
        for (int i = 0; i < expenseItems.size(); i++) {
            allExpenses += expenseItems.get(i).toString() + "<br/>";
        }
        allExpenses += "</html>";
        String expenses = String.format("%.2f", myCurrentBudget.getTotalExpenses());
        String total = String.format("%.2f", myCurrentBudget.getTotal());
        myContentLabel = new JLabel(allExpenses);
        myTitleLabel = new JLabel("Budget: $" + expenses + "/" + total);
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
        if (theEvent.getPropertyName().equals("repaintPageBudgetEdit")) {
            myCurrentBudget.editExpense((String) theEvent.getOldValue(), (double) theEvent.getNewValue());
            writeExpenses();
        } else if (theEvent.getPropertyName().equals("repaintPageBudgetDelete")) {
            myCurrentBudget.deleteExpense((String) theEvent.getOldValue());
            writeExpenses();
        }
    }
}
