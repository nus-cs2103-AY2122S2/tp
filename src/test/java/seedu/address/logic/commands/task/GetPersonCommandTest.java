package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Tabs.CONTACT_TAB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.TASK_FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.TASK_SECOND_INDEX;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.UserPrefs;
import seedu.address.model.name.Name;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code GetPersonCommand}.
 */
public class GetPersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task indicatedTask = model.getFilteredTaskList().get(TASK_FIRST_INDEX.getZeroBased());
        GetPersonCommand getPersonCommand = new GetPersonCommand(TASK_FIRST_INDEX);

        List<String> keywords = new ArrayList<>();
        for (Name name: indicatedTask.getPersons()) {
            keywords.add(name.fullName);
        }
        GetPersonCommand.CustomPersonNameContainsKeywordsPredicate predicate =
                new GetPersonCommand.CustomPersonNameContainsKeywordsPredicate(keywords);
        expectedModel.updateFilteredPersonList(predicate);

        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                expectedModel.getFilteredPersonList().size());
        CommandResult expectedCommandResult =
                new CommandResult(expectedMessage, false, false, CONTACT_TAB, false, null, false);

        assertCommandSuccess(getPersonCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);

        GetPersonCommand getPersonCommand = new GetPersonCommand(outOfBoundIndex);

        assertCommandFailure(getPersonCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, TASK_FIRST_INDEX);

        Task indicatedTask = model.getFilteredTaskList().get(TASK_FIRST_INDEX.getZeroBased());
        GetPersonCommand getPersonCommand = new GetPersonCommand(TASK_FIRST_INDEX);

        List<String> keywords = new ArrayList<>();
        for (Name name: indicatedTask.getPersons()) {
            keywords.add(name.fullName);
        }
        GetPersonCommand.CustomPersonNameContainsKeywordsPredicate predicate =
                new GetPersonCommand.CustomPersonNameContainsKeywordsPredicate(keywords);
        expectedModel.updateFilteredPersonList(predicate);
        showTaskAtIndex(expectedModel, TASK_FIRST_INDEX);

        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                expectedModel.getFilteredPersonList().size());
        CommandResult expectedCommandResult =
                new CommandResult(expectedMessage, false, false, CONTACT_TAB, false, null, false);

        assertCommandSuccess(getPersonCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, TASK_FIRST_INDEX);

        Index outOfBoundIndex = TASK_SECOND_INDEX;
        // ensures that outOfBoundIndex is still in bounds of task book
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskBook().getTaskList().size());

        GetPersonCommand getPersonCommand = new GetPersonCommand(outOfBoundIndex);

        assertCommandFailure(getPersonCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        GetPersonCommand getPersonFirstCommand = new GetPersonCommand(TASK_FIRST_INDEX);
        GetPersonCommand getPersonSecondCommand = new GetPersonCommand(TASK_SECOND_INDEX);

        // same object -> returns true
        assertTrue(getPersonFirstCommand.equals(getPersonFirstCommand));

        // same values -> returns true
        GetPersonCommand getPersonFirstCommandCopy = new GetPersonCommand(TASK_FIRST_INDEX);
        assertTrue(getPersonFirstCommand.equals(getPersonFirstCommandCopy));

        // different types -> returns false
        assertFalse(getPersonFirstCommand.equals(1));

        // null -> returns false
        assertFalse(getPersonFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(getPersonFirstCommand.equals(getPersonSecondCommand));
    }
}
