package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXPENSES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExpenses.ANNUAL_NETFLIX_FEES;
import static seedu.address.testutil.TypicalExpenses.CAR_WASH;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.expense.DescriptionContainsKeywordsPredicate;
import seedu.address.testutil.ExpenseExpertBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ExpenseExpert(), new ExpenseExpert(modelManager.getExpenseExpert()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setExpenseExpertFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setExpenseExpertFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setExpenseExpertFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setExpenseExpertFilePath(null));
    }

    @Test
    public void setExpenseExpertFilePath_validPath_setsExpenseExpertFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setExpenseExpertFilePath(path);
        assertEquals(path, modelManager.getExpenseExpertFilePath());
    }

    @Test
    public void hasExpense_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasExpense(null));
    }

    @Test
    public void hasExpense_expenseNotInExpenseExpert_returnsFalse() {
        assertFalse(modelManager.hasExpense(ANNUAL_NETFLIX_FEES));
    }

    @Test
    public void hasExpense_expenseInExpenseExpert_returnsTrue() {
        modelManager.addExpense(ANNUAL_NETFLIX_FEES);
        assertTrue(modelManager.hasExpense(ANNUAL_NETFLIX_FEES));
    }

    @Test
    public void getFilteredExpenseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredExpenseList().remove(0));
    }

    @Test
    public void equals() {
        ExpenseExpert expenseExpert = new ExpenseExpertBuilder()
                .withExpense(ANNUAL_NETFLIX_FEES).withExpense(CAR_WASH).build();
        ExpenseExpert differentExpenseExpert = new ExpenseExpert();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(expenseExpert, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(expenseExpert, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));


        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different expenseExpert -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentExpenseExpert, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ANNUAL_NETFLIX_FEES.getDescription().description.split("\\s+");
        modelManager.updateFilteredExpenseList(new DescriptionContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(expenseExpert, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setExpenseExpertFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(expenseExpert, differentUserPrefs)));
    }
}
