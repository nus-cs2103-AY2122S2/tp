package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.getExpectedSortCommandResult;
import static seedu.address.testutil.TypicalEntries.BIG_BANK;
import static seedu.address.testutil.TypicalEntries.DBSSS;
import static seedu.address.testutil.TypicalEntries.JANICE_STREET;
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
public class SortCompanyCommandTest {

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
    public void execute_sortListAscending_companyListOnlyUnarchived() {
        expectedModel.sortCompanyListByName(Ordering.ASCENDING, Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
        expectedCommandResult = getExpectedSortCommandResult(SearchType.UNARCHIVED_ONLY,
                ListType.COMPANY, Ordering.ASCENDING);
        assertCommandSuccess(new SortCompanyCommand(SearchType.UNARCHIVED_ONLY, Ordering.ASCENDING),
                model, expectedCommandResult, expectedModel);
        assertEquals(List.of(BIG_BANK, DBSSS, JANICE_STREET), model.getAddressBook().getCompanyList());
    }

    @Test
    public void execute_sortListDescending_companyListOnlyUnarchived() {
        expectedModel.sortCompanyListByName(Ordering.DESCENDING, Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
        expectedCommandResult = getExpectedSortCommandResult(SearchType.UNARCHIVED_ONLY,
                ListType.COMPANY, Ordering.DESCENDING);
        assertCommandSuccess(new SortCompanyCommand(SearchType.UNARCHIVED_ONLY, Ordering.DESCENDING),
                model, expectedCommandResult, expectedModel);
        assertEquals(List.of(JANICE_STREET, DBSSS, BIG_BANK), model.getAddressBook().getCompanyList());
    }

    @Test
    public void execute_sortListAscending_companyListWithArchived() {
        expectedModel.sortCompanyListByName(Ordering.ASCENDING, Model.PREDICATE_SHOW_ALL);
        expectedCommandResult = getExpectedSortCommandResult(SearchType.ALL, ListType.COMPANY, Ordering.ASCENDING);
        assertCommandSuccess(new SortCompanyCommand(SearchType.ALL, Ordering.ASCENDING), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_sortListDescending_companyListOnlyArchived() {
        expectedModel.sortCompanyListByName(Ordering.DESCENDING, Model.PREDICATE_SHOW_ARCHIVED_ONLY);
        expectedCommandResult = getExpectedSortCommandResult(SearchType.ARCHIVED_ONLY,
            ListType.COMPANY, Ordering.DESCENDING);
        assertCommandSuccess(new SortCompanyCommand(SearchType.ARCHIVED_ONLY, Ordering.DESCENDING), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        SortCompanyCommand firstCommand = new SortCompanyCommand(SearchType.UNARCHIVED_ONLY,
                Ordering.ASCENDING);
        SortCompanyCommand secondCommand = new SortCompanyCommand(SearchType.UNARCHIVED_ONLY,
                Ordering.DESCENDING);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        SortCompanyCommand firstCommandCopy = new SortCompanyCommand(SearchType.UNARCHIVED_ONLY,
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
