package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListTaskCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalTaskList());
        expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), model.getTaskList());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListTaskCommand(""), model,
                ListTaskCommand.MESSAGE_SUCCESS_NOT_COMPLETED, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        assertCommandSuccess(new ListTaskCommand("all"), model,
                ListTaskCommand.MESSAGE_SUCCESS_ALL, expectedModel);
    }

    @Test
    public void equals() {
        ListTaskCommand listAllTaskCommand = new ListTaskCommand("all");
        ListTaskCommand listCompleteTaskCommand = new ListTaskCommand("c");
        ListTaskCommand listIncompleteTaskCommand = new ListTaskCommand("nc");

        // same object -> returns true
        assertTrue(listAllTaskCommand.equals(listAllTaskCommand));

        // same values -> returns true
        ListTaskCommand listAllTaskCommandCopy = new ListTaskCommand("all");
        assertTrue(listAllTaskCommand.equals(listAllTaskCommandCopy));

        // different prefix -> returns false
        assertFalse(listCompleteTaskCommand.equals(listIncompleteTaskCommand));
    }
}
