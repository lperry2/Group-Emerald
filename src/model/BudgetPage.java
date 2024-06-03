package src.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BudgetPage extends AbstractPage {


    //private String myTitle;

    //private File myFile;

    //private JLabel myTitleLabel;

    private Budget myCurrentBudget;

    private JLabel myTitleLabel;

    private JPanel myTitlePanel;

    private JLabel myExpenseLabel;

    private JPanel myExpensePanel;

    private JPanel myButtonPanel;



    public BudgetPage(User theUser, String theProjectName, String theType) {
        super(theUser, theProjectName, theType);
        myCurrentBudget = myCurrentProject.getBudget();
        System.out.println("myCurrentBudget: " + myCurrentBudget.getTotal());

        setup();

    }

    private void setup() {

        setupHeader();
        setupExpenses();
        setupButtons();
        this.setVisible(true);

    }

    private void setupHeader() {
        double expenses = myCurrentBudget.getTotalExpenses();
        double total = myCurrentBudget.getTotal();

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
            allExpenses = "";
            ArrayList<ExpenseItem> expenseItems = myCurrentBudget.getExpenses();
            for (int i = 0; i < expenseItems.size(); i++) {
                allExpenses += expenseItems.get(i).toString() + "\n";
            }
        }
        myExpenseLabel = new JLabel(allExpenses);
        myExpensePanel = new JPanel();

        myExpensePanel.add(myExpenseLabel);
        this.add(myExpensePanel, BorderLayout.CENTER);
    }

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
            }
        });
        myButtonPanel.add(addBtn, BorderLayout.WEST);
        this.add(myButtonPanel, BorderLayout.SOUTH);
    }

    private void writeExpenses() {
        String allExpenses = "";
        ArrayList<ExpenseItem> expenseItems = myCurrentBudget.getExpenses();
        for (int i = 0; i < expenseItems.size(); i++) {
            allExpenses += expenseItems.get(i).toString() + "\n";
        }

        double expenses = myCurrentBudget.getTotalExpenses();
        double total = myCurrentBudget.getTotal();
        myExpenseLabel = new JLabel(allExpenses);
        //myExpenseLabel.setText(allExpenses);
        myTitleLabel = new JLabel("Budget: $" + expenses + "/" + total);
        //myTitleLabel.setText("Budget: $" + expenses + "/" + total);
        //myExpensePanel.repaint();
        //this.repaint();
        this.add(myExpensePanel, BorderLayout.CENTER);
        this.add(myTitlePanel, BorderLayout.NORTH);
        this.repaint();
    }


}
