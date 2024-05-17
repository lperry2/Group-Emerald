package src.model;

import java.util.ArrayList;

public class Budget {

    /** The total budget for the project. */
    private double myTotal;

    /** The total expenses on the project so far. */
    private double myTotalExpenses;

    private ArrayList<ExpenseItem> myExpenses;

    public Budget(double theTotal) {
        myTotal = theTotal;
        myTotalExpenses = 0; //set expenses to zero at project creation
        myExpenses = new ArrayList<>();
    }


}
