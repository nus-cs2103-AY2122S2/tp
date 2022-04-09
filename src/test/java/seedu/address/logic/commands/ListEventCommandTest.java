package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.getExpectedListCommandResult;
import static seedu.address.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.address.testutil.TypicalEntries.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.ListType;
import seedu.address.commons.core.SearchTypeUtil.SearchType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListPersonCommand.
 */
public class ListEventCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandResult expectedCommandResult;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        model.showEventList(Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
        expectedModel.showEventList(Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        expectedCommandResult = getExpectedListCommandResult(SearchType.UNARCHIVED_ONLY, ListType.EVENT);
        assertCommandSuccess(
                new ListEventCommand(SearchType.UNARCHIVED_ONLY), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsUnarchived() {
        showEventAtIndex(model, INDEX_FIRST_ENTRY);
        expectedCommandResult = getExpectedListCommandResult(SearchType.UNARCHIVED_ONLY, ListType.EVENT);
        assertCommandSuccess(
                new ListEventCommand(SearchType.UNARCHIVED_ONLY), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsArchived() {
        expectedCommandResult = getExpectedListCommandResult(SearchType.ARCHIVED_ONLY, ListType.EVENT);
        expectedModel.showEventList(Model.PREDICATE_SHOW_ARCHIVED_ONLY);
        assertCommandSuccess(
                new ListEventCommand(SearchType.ARCHIVED_ONLY), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsAll() {
        expectedCommandResult = getExpectedListCommandResult(SearchType.ALL, ListType.EVENT);
        expectedModel.showEventList(Model.PREDICATE_SHOW_ALL);
        assertCommandSuccess(
                new ListEventCommand(SearchType.ALL), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        ListEventCommand firstCommand = new ListEventCommand(SearchType.UNARCHIVED_ONLY);
        ListEventCommand secondCommand = new ListEventCommand(SearchType.ARCHIVED_ONLY);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        ListEventCommand firstCommandCopy = new ListEventCommand(SearchType.UNARCHIVED_ONLY);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
