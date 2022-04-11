package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.getExpectedListCommandResult;
import static seedu.address.logic.commands.CommandTestUtil.showCompanyAtIndex;
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
public class ListCompanyCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandResult expectedCommandResult;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        model.showCompanyList(Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
        expectedModel.showCompanyList(Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        expectedCommandResult = getExpectedListCommandResult(SearchType.UNARCHIVED_ONLY, ListType.COMPANY);
        assertCommandSuccess(
                new ListCompanyCommand(SearchType.UNARCHIVED_ONLY), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsUnarchived() {
        showCompanyAtIndex(model, INDEX_FIRST_ENTRY);
        expectedCommandResult = getExpectedListCommandResult(SearchType.UNARCHIVED_ONLY, ListType.COMPANY);
        assertCommandSuccess(
                new ListCompanyCommand(SearchType.UNARCHIVED_ONLY), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsArchived() {
        expectedCommandResult = getExpectedListCommandResult(SearchType.ARCHIVED_ONLY, ListType.COMPANY);
        expectedModel.showCompanyList(Model.PREDICATE_SHOW_ARCHIVED_ONLY);
        assertCommandSuccess(
                new ListCompanyCommand(SearchType.ARCHIVED_ONLY), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsAll() {
        expectedCommandResult = getExpectedListCommandResult(SearchType.ALL, ListType.COMPANY);
        expectedModel.showCompanyList(Model.PREDICATE_SHOW_ALL);
        assertCommandSuccess(
                new ListCompanyCommand(SearchType.ALL), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        ListCompanyCommand firstCommand = new ListCompanyCommand(SearchType.UNARCHIVED_ONLY);
        ListCompanyCommand secondCommand = new ListCompanyCommand(SearchType.ARCHIVED_ONLY);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        ListCompanyCommand firstCommandCopy = new ListCompanyCommand(SearchType.UNARCHIVED_ONLY);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
