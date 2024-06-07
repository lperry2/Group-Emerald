package src.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import src.model.*;
import src.view.BudgetPage;
import src.view.MainGUI;

import javax.swing.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the UI and components.
 *
 * @author Alex Ewing, Owen Orlic, Daniel Alberto Sanchez Aguilar
 * @version v0.00
 */
public class TestUI {

    /*
    "Aiming to test private variables is the first sign that something wrong with the current design.
    Private variables are part of the implementation, tests should focus on behavior rather of implementation details."
        -Someone on Stack Overflow
     */

    /**
     * The main GUI object for testing
     */
    private MainGUI testGUI;
    /**
     * An empty project to test
     */
    private Project emptyProject;

    /**
     * An empty private project to test
     */
    private Project emptyPrivateProject;
    /**
     * An empty budget to test
     */
    private Budget testBudget;
    /**
     * An empty file group to test
     */
    private FileGroup testFiles;

    /**
     * An empty journal to test
     */
    private Journal testJournal;

    /**
     * Set up the test fixture.
     */
    @BeforeEach
    public void setUp() {
        testGUI = new MainGUI();
        testBudget = new Budget(123);
        testFiles = new FileGroup(new ArrayList<SingleFile>());
        testJournal = new Journal(new ArrayList<JournalEntry>());
        emptyProject = new Project("test", testBudget, testFiles, testJournal);
        emptyPrivateProject = new Project("test", "123", testBudget);
    }

    /**
     * Clean up after each test.
     */
    @AfterEach
    public void tearDown() {
        // Clean up or reset the UI state after each test
        testGUI.dispose();
    }

    /**
     * Test if the correct project name is stored in empty project
     */
    @Test
    public void testEmptyProjectName() {
        assertEquals("test", emptyProject.getProjectName());
    }

    /**
     * checks whether we store the correct pin in private empty project
     */
    @Test
    public void testEmptyPrivateProjectPin() {
        assertEquals("123", emptyPrivateProject.getPin());
    }
    /**
     * Test if it stores the correct budget in empty project
     */
    @Test
    public void testEmptyProjectBudget() {
        assertEquals(testBudget, emptyProject.getBudget());
    }

    /**
     * Test if it stores the correct budget value in empty project
     */
    @Test
    public void testEmptyProjectBudgetValue() {
        assertEquals(testBudget.getTotal(), emptyProject.getBudget().getTotal());
    }

    /**
     * Test if we have 0 expenses in our empty project
     */
    @Test
    public void testNumBudgetExpensesEmptyProject() {
        assertEquals(0, emptyProject.getBudget().getExpenses().size());
        assertEquals(0, emptyProject.getBudget().getTotalExpenses());
    }

    /**
     * Test if the storage of a Expense item is correct within empty project
     */
    @Test
    public void TestNewExpenseItemEmptyProject() {
        testBudget.addExpense("tester1", 33.33);
        assertEquals(1, emptyProject.getBudget().getExpenses().size());
        assertEquals(33.33, emptyProject.getBudget().getTotalExpenses());
    }

    /**
     * Tests if we can edit an expense within empty project
     */
    @Test
    public void TestEditExpenseItemEmptyProject() {
        testBudget.addExpense("tester1", 33.33);
        testBudget.editExpense("tester1", 12.99);
        assertEquals(12.99, emptyProject.getBudget().getTotalExpenses());
    }

    /**
     * Tests if we can delete an expense within empty project
     */
    @Test
    public void TestDeleteExpenseItemEmptyProject() {
        testBudget.addExpense("tester1", 33.33);
        testBudget.deleteExpense("tester1");
        assertEquals(0, emptyProject.getBudget().getExpenses().size());
        assertEquals(0, emptyProject.getBudget().getTotalExpenses());
    }

    /**
     * Test if we have the correct budget of the empty project
     */
    @Test
    public void testTotalEmptyProject() {
        assertEquals(123, emptyProject.getBudget().getTotal());
    }

    /**
     * Test to see if no journal entries in empty project
     */
    @Test
    public void testJournalEmptyProject() {
        assertEquals(0, emptyProject.getJournal().getEntries().size());
    }
    /**
     * Test if we correctly add a new journal entry in empty project
     */
    @Test
    public void testAddJournalEntryEmptyProject() {
        testJournal.addEntry("Test1", "TestContent");
        assertEquals(1, emptyProject.getJournal().getEntries().size());
    }

    /**
     * Test if we can edit a journal entry within empty project
     */
    @Test
    public void testEditJournalEntryEmptyProject() {
        testJournal.addEntry("Test1", "TestContent");
        testJournal.editEntry("Test1", "Tester");
        assertEquals("Test1", emptyProject.getJournal().getEntries().get(0).getTitle());
        assertEquals("Tester", emptyProject.getJournal().getEntries().get(0).getContent());
    }

    /**
     * Test if we can remove a journal entry within empty project
     */
    @Test
    public void testRemoveJournalEntryEmptyProject() {
        testJournal.addEntry("Test", "Content");
        testJournal.deleteEntry("Test");
        assertEquals(0, emptyProject.getJournal().getEntries().size());
    }
}