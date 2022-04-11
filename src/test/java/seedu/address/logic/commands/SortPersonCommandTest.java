package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.getExpectedSortCommandResult;
import static seedu.address.testutil.TypicalEntries.ALICE;
import static seedu.address.testutil.TypicalEntries.BENSON;
import static seedu.address.testutil.TypicalEntries.CARL;
import static seedu.address.testutil.TypicalEntries.DANIEL;
import static seedu.address.testutil.TypicalEntries.ELLE;
import static seedu.address.testutil.TypicalEntries.FIONA;
import static seedu.address.testutil.TypicalEntries.GEORGE;
import static seedu.address.testutil.TypicalEntries.JANE;
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
public class SortPersonCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandResult expectedCommandResult;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        model.showPersonList(Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
        expectedModel.showPersonList(Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
    }

    @Test
    public void execute_sortListAscending_personListOnlyUnarchived() {
        expectedModel.sortPersonListByName(Ordering.ASCENDING, Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
        expectedCommandResult = getExpectedSortCommandResult(SearchType.UNARCHIVED_ONLY,
                ListType.PERSON, Ordering.ASCENDING);
        assertCommandSuccess(new SortPersonCommand(SearchType.UNARCHIVED_ONLY, Ordering.ASCENDING), model,
                expectedCommandResult, expectedModel);
        assertEquals(List.of(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, JANE),
                model.getAddressBook().getPersonList());
    }

    @Test
    public void execute_sortListDescending_personListOnlyUnarchived() {
        expectedModel.sortPersonListByName(Ordering.DESCENDING, Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
        expectedCommandResult = getExpectedSortCommandResult(SearchType.UNARCHIVED_ONLY,
                ListType.PERSON, Ordering.DESCENDING);
        assertCommandSuccess(new SortPersonCommand(SearchType.UNARCHIVED_ONLY, Ordering.DESCENDING), model,
                expectedCommandResult, expectedModel);
        assertEquals(List.of(JANE, GEORGE, FIONA, ELLE, DANIEL, CARL, BENSON, ALICE),
                model.getAddressBook().getPersonList());
    }

    @Test
    public void execute_sortListAscending_personListWithArchived() {
        expectedModel.sortPersonListByName(Ordering.ASCENDING, Model.PREDICATE_SHOW_ALL);
        expectedCommandResult = getExpectedSortCommandResult(SearchType.ALL, ListType.PERSON,
                Ordering.ASCENDING);
        assertCommandSuccess(new SortPersonCommand(SearchType.ALL, Ordering.ASCENDING), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_sortListDescending_personListWithArchived() {
        expectedModel.sortPersonListByName(Ordering.DESCENDING, Model.PREDICATE_SHOW_ALL);
        expectedCommandResult = getExpectedSortCommandResult(SearchType.ALL, ListType.PERSON,
                Ordering.DESCENDING);
        assertCommandSuccess(new SortPersonCommand(SearchType.ALL, Ordering.DESCENDING), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        SortPersonCommand firstCommand = new SortPersonCommand(SearchType.UNARCHIVED_ONLY,
                Ordering.ASCENDING);
        SortPersonCommand secondCommand = new SortPersonCommand(SearchType.UNARCHIVED_ONLY,
                Ordering.DESCENDING);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        SortPersonCommand firstCommandCopy = new SortPersonCommand(SearchType.UNARCHIVED_ONLY,
                Ordering.ASCENDING);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
