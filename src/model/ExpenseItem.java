package src.model;

/**
 * ExpenseItems are individual expenses that are taken
 * out of the project budget. When looking at the project screen
 * total expense / total budget is shown and total expense is the
 * summation of all ExpenseItems.
 *
 * @author Owen Orlic
 */
public class ExpenseItem {

    /** Name of the expense item. */
    private String myName;

    /** Cost of the expense. */
    private double myExpense;


    /**
     * Creates an ExpenseItem based on a name and a cost.
     *
     * @author Owen Orlic
     * @param theName name of the ExpenseItem
     * @param theExpense cost of the ExpenseItem
     */
    public ExpenseItem(String theName, double theExpense) {
        myName = theName;
        myExpense = theExpense;
    }

    /**
     * Returns the name of the ExpenseItem.
     *
     * @author Owen Orlic
     * @return name of expense
     */
    public String getName() {
        return myName;
    }

    /**
     * Returns the cost of the ExpenseItem.
     *
     * @author Owen Orlic
     * @return cost of expense
     */
    public double getExpense() {
        return myExpense;
    }

    /**
     * Changes the cost of the ExpenseItem. Used for editing budget.
     *
     * @author Owen Orlic
     * @param theExpense the new expense for the time
     */
    public void setExpense(double theExpense) {
        myExpense = theExpense;
    }


    /**
     * Checks if two ExpenseItems have the same name.
     * Cost of expense does not play a factor in equality.
     *
     * @author Owen Orlic
     * @param theOther another ExpenseItem
     * @return if the expenses have the same name
     */
    public boolean equals(ExpenseItem theOther) {
        return this.myName.equals(theOther.getName());
    }

    /**
     * Returns the ExpenseItem in the form "myName: myExpense".
     *
     * @author Owen Orlic
     * @return a string representation of an ExpenseItem
     */
    @Override
    public String toString() {
        String expense = String.format("$%.2f", myExpense);
        return myName + ": " + expense;
    }
}
