package src.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
        boolean alreadyAName = false;
        for (int i = 0; i < myExpenses.size(); i++) {
            if (theName.equals(myExpenses.get(i).getName())) {
                alreadyAName = true;
                break;
            }
        }
        if (alreadyAName) {
            JOptionPane.showMessageDialog(null, "There is already an expense with this name. Please choose a new name.",
                    "Error with Name", JOptionPane.INFORMATION_MESSAGE, makePeteSmall());
        } else if (theExpense <= 0.00){
            JOptionPane.showMessageDialog(null, "Expenses must cost more than $0.00. Please choose a new expense.",
                    "Error with Expense", JOptionPane.INFORMATION_MESSAGE, makePeteSmall());
        } else {
            myTotalExpenses += theExpense;
            myExpenses.add(expense);
        }

    }

    /**
     * Edits an ExpenseItem in the budget by search through myExpenses to
     * find an expense item with the same name, subtracting that expense cost from
     * the total expenses, adding the new expense cost to total expenses, and then
     * calling setExpense to edit its myExpense field.
     *
     * @author Owen Orlic
     * @param theName the name of the ExpenseItem
     * @param theNewExpense the new cost of the expense
     */
    public void editExpense(String theName, double theNewExpense) {
        for (int i = 0; i < myExpenses.size(); i++) {
            ExpenseItem curr = myExpenses.get(i);
            if (curr.getName().equals(theName)) {
                myTotalExpenses -= curr.getExpense(); //subtract the old expense
                myTotalExpenses += theNewExpense;     // and add the new one
                curr.setExpense(theNewExpense);
                break;
            }
        }
    }

    /**
     * Deletes an ExpenseItem by searching through myExpenses to find one
     * with the same name, subtracting its expense cost from the total expenses,
     * and then calling remove on myExpenses to remove the expense item from the list.
     *
     * @author Owen Orlic
     * @param theName the name of the expense item to be deleted
     */
    public void deleteExpense(String theName) {
        for (int i = 0; i < myExpenses.size(); i++) {
            ExpenseItem curr = myExpenses.get(i);
            if (curr.getName().equals(theName)) {
                myTotalExpenses -= curr.getExpense();
                myExpenses.remove(curr);
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

}
