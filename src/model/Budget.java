package src.model;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintStream;

/**
 * Class to hold individual expenses of a project's budget.
 * ExpenseItems make up the total expense of a project.
 *
 * @author Owen Orlic
 */
public class Budget {

    /** The total budget for the project. */
    private double myTotal;

    /** The total expenses on the project so far. */
    private double myTotalExpenses;

    /** List of all the individual expenses. */
    private ArrayList<ExpenseItem> myExpenses;

    /** File that will hold all of the project's budget and expense info. */
    private PrintStream budgetFile;

    public Budget(double theTotal) {
        myTotal = theTotal;
        myTotalExpenses = 0; //set expenses to zero at project creation
        myExpenses = new ArrayList<>();
        try {
            //creates a file to store all of this project's budget info
            budgetFile = new PrintStream(new File("src/ProjectBudget.txt"));
            budgetFile.println("++++");
            budgetFile.println("Project Budget | " + myTotal); //adds the project's total budget to file on instantiation
        } catch (IOException e) {
            throw new RuntimeException("No file found: " + e);
        }
    }

    /**
     * Sets the budget total budget to a new number.
     *
     * @param theNewTotal the project's new budget total
     * @author Owen Orlic
     */
    public void setTotal(double theNewTotal) {
        myTotal = theNewTotal;
    }

    /**
     * Gets the project's budget total.
     *
     * @return project's budget total
     * @author Owen Orlic
     */
    public double getTotal() {
        return myTotal;
    }

    /**
     * Gets the project's expense total.
     *
     * @return project's expense total
     * @author Owen Orlic
     */
    public double getTotalExpenses() {
        return myTotalExpenses;
    }

    private void addExpense(String theName, double theExpense) {
        ExpenseItem expense = new ExpenseItem(theName, theExpense);
        myExpenses.add(expense);
        budgetFile.println("----");
        budgetFile.println(theName + " | " + theExpense);
    }

}
