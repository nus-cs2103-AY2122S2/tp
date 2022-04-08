package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_COMPANIES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntries.BIG_BANK;
import static seedu.address.testutil.TypicalEntries.DBSSS;
import static seedu.address.testutil.TypicalEntries.JANICE_STREET;
import static seedu.address.testutil.TypicalEntries.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.SearchTypeUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entry.predicate.CompanyContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCompanyCommand}.
 */
public class FindCompanyCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.showCompanyList(Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
        expectedModel.showCompanyList(Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
    }
    @Test
    public void equals() {
        CompanyContainsKeywordsPredicate firstPredicate = new CompanyContainsKeywordsPredicate(
                List.<String>of("first"), List.<String>of(""),
                SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.UNARCHIVED_ONLY));
        CompanyContainsKeywordsPredicate secondPredicate = new CompanyContainsKeywordsPredicate(
                List.<String>of("second"), List.<String>of(""),
                SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.UNARCHIVED_ONLY));


        FindCompanyCommand findFirstCommand = new FindCompanyCommand(firstPredicate);
        FindCompanyCommand findSecondCommand = new FindCompanyCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCompanyCommand findFirstCommandCopy = new FindCompanyCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_multipleKeywords_multipleCompaniesFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 3);
        CompanyContainsKeywordsPredicate predicate =
                new CompanyContainsKeywordsPredicate(List.<String>of("dbsss", "big", "janice"),
                        List.<String>of(""), SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.UNARCHIVED_ONLY));

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false, false, true, false);
        FindCompanyCommand command = new FindCompanyCommand(predicate);
        expectedModel.showCompanyList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(DBSSS, BIG_BANK, JANICE_STREET), model.getFilteredCompanyList());
    }
}
