package src.model;

import java.io.FileOutputStream;
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

    /** File that will hold all the project's budget and expense info. */
    private PrintStream budgetFile;

    /**
     * Creates the new budget for the entire project. Sets expenses to zero and instantiates the
     * myExpense list as an empty ArrayList.
     *
     * @param theTotal project's total budget
     * @author Owen Orlic
     */
    public Budget(double theTotal) {
        myTotal = theTotal;
        myTotalExpenses = 0; //set expenses to zero at project creation
        myExpenses = new ArrayList<>();
    }

    /**
     * Creates the new budget for the entire project. Sets expenses to zero and instantiates the
     * myExpense list as an empty ArrayList. Takes total from a String instead of a double.
     *
     * @param theTotal project's total budget
     * @author Owen Orlic
     */
    public Budget(String theTotal) {
        myTotal = Double.parseDouble(theTotal);
        myTotalExpenses = 0; //set expenses to zero at project creation
        myExpenses = new ArrayList<>();
    }

    /**
     * Creates the budget for the previously existing project.
     *
     * @param theTotal project's total budget
     * @author Owen Orlic
     */
    public Budget(double theTotal, double theTotalExpenses, ArrayList<ExpenseItem> theExpenses) {
        myTotal = theTotal;
        myTotalExpenses = theTotalExpenses;
        myExpenses = theExpenses;
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

    public ArrayList<ExpenseItem> getExpenses() {
        return myExpenses;
    }

    /**
     * Creates a new ExpenseItem, adds it to the myExpenses list, and adds it to the
     * budget file.
     *
     * @param theName name of the expense
     * @param theExpense cost of the expense
     * @author Owen Orlic
     */
    public void addExpense(String theName, double theExpense) {
        ExpenseItem expense = new ExpenseItem(theName, theExpense);
        myTotalExpenses += theExpense;
        myExpenses.add(expense);
        //budgetFile.println("----");
        //budgetFile.println(theName + " | " + theExpense);
    }

    public void editExpense(String theName, double theNewExpense) {
        for (int i = 0; i < myExpenses.size(); i++) {
            ExpenseItem curr = myExpenses.get(i);
            if (curr.getName().equals(theName)) {
                myTotalExpenses -= curr.getExpense(); //subtract the old expense
                myTotalExpenses += theNewExpense;     // and add the new one
                curr.setExpense(theNewExpense);
                //System.out.println("Budget.java editExpense: happened");
                break;
            }
        }
    }

    @Override
    public String toString() {
        String result = "Total: " + myTotal + "\n";
        result += "Expense Total: " + myTotalExpenses + "\n";
        for (int i = 0; i < myExpenses.size(); i++ ) {
            result += myExpenses.get(i).toString() + "\n";
        }
        return result;
    }

}
