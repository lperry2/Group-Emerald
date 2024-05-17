package src.model;

public class ExpenseItem {

    /** Name of the expense item. */
    private String myName;

    /** Cost of the expense. */
    private double myExpense;

    public ExpenseItem(String theName, double theExpense) {
        myName = theName;
        myExpense = theExpense;
    }
}
