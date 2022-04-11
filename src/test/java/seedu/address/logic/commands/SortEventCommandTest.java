package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.getExpectedSortCommandResult;
import static seedu.address.testutil.TypicalEntries.INTERVIEW_A;
import static seedu.address.testutil.TypicalEntries.INTERVIEW_B;
import static seedu.address.testutil.TypicalEntries.ONLINE_ASSESSMENT;
import static seedu.address.testutil.TypicalEntries.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.ListType;
import seedu.address.commons.core.OrderingUtil.Ordering;
import seedu.address.commons.core.SearchTypeUtil.SearchType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListPersonCommand.
 */
public class SortEventCommandTest {

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
    public void execute_sortListAscending_eventListOnlyUnarchived() {
        expectedModel.sortEventListByDate(Ordering.ASCENDING, Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
        expectedCommandResult = getExpectedSortCommandResult(SearchType.UNARCHIVED_ONLY,
                ListType.EVENT, Ordering.ASCENDING);
        assertCommandSuccess(new SortEventCommand(SearchType.UNARCHIVED_ONLY,
                Ordering.ASCENDING), model, expectedCommandResult, expectedModel);
        assertEquals(List.of(ONLINE_ASSESSMENT, INTERVIEW_A, INTERVIEW_B),
                model.getAddressBook().getEventList());
    }

    @Test
    public void execute_sortListDescending_eventListOnlyUnarchived() {
        expectedModel.sortEventListByDate(Ordering.DESCENDING, Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
        expectedCommandResult = getExpectedSortCommandResult(SearchType.UNARCHIVED_ONLY,
                ListType.EVENT, Ordering.DESCENDING);
        assertCommandSuccess(new SortEventCommand(SearchType.UNARCHIVED_ONLY,
                Ordering.DESCENDING), model, expectedCommandResult, expectedModel);
        assertEquals(List.of(INTERVIEW_B, INTERVIEW_A, ONLINE_ASSESSMENT),
                model.getAddressBook().getEventList());
    }

    @Test
    public void execute_sortListAscending_eventListWithArchived() {
        expectedModel.sortEventListByDate(Ordering.ASCENDING, Model.PREDICATE_SHOW_ALL);
        expectedCommandResult = getExpectedSortCommandResult(SearchType.ALL,
                ListType.EVENT, Ordering.ASCENDING);
        assertCommandSuccess(new SortEventCommand(SearchType.ALL, Ordering.ASCENDING), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_sortListDescending_eventListWithArchived() {
        expectedModel.sortEventListByDate(Ordering.DESCENDING, Model.PREDICATE_SHOW_ALL);
        expectedCommandResult = getExpectedSortCommandResult(SearchType.ALL,
                ListType.EVENT, Ordering.DESCENDING);
        assertCommandSuccess(new SortEventCommand(SearchType.ALL, Ordering.DESCENDING), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        SortEventCommand firstCommand = new SortEventCommand(SearchType.UNARCHIVED_ONLY, Ordering.ASCENDING);
        SortEventCommand secondCommand = new SortEventCommand(SearchType.UNARCHIVED_ONLY, Ordering.DESCENDING);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        SortEventCommand firstCommandCopy = new SortEventCommand(SearchType.UNARCHIVED_ONLY, Ordering.ASCENDING);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}

