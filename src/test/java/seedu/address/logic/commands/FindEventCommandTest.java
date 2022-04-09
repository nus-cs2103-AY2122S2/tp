package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntries.INTERVIEW_A;
import static seedu.address.testutil.TypicalEntries.INTERVIEW_B;
import static seedu.address.testutil.TypicalEntries.ONLINE_ASSESSMENT;
import static seedu.address.testutil.TypicalEntries.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.SearchTypeUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entry.predicate.EventContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindEventCommand}.
 */
public class FindEventCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.showEventList(Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
        expectedModel.showEventList(Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
    }
    @Test
    public void equals() {
        EventContainsKeywordsPredicate firstPredicate = new EventContainsKeywordsPredicate(
                List.<String>of("first"), List.<String>of(""), null, null,
                List.<String>of(""), List.<String>of(""), List.<String>of(""),
                SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.UNARCHIVED_ONLY));
        EventContainsKeywordsPredicate secondPredicate = new EventContainsKeywordsPredicate(
                List.<String>of("second"), List.<String>of(""), null, null,
                List.<String>of(""), List.<String>of(""), List.<String>of(""),
                SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.UNARCHIVED_ONLY));


        FindEventCommand findFirstCommand = new FindEventCommand(firstPredicate);
        FindEventCommand findSecondCommand = new FindEventCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindEventCommand findFirstCommandCopy = new FindEventCommand(firstPredicate);
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
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        EventContainsKeywordsPredicate predicate =
                new EventContainsKeywordsPredicate(List.<String>of("dbsss", "bb", "online"), List.<String>of(""),
                        null, null, List.<String>of(""), List.<String>of(""), List.<String>of(""),
                        SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.UNARCHIVED_ONLY));

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false, false, false, true);
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.showEventList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(INTERVIEW_A, INTERVIEW_B, ONLINE_ASSESSMENT),
                model.getFilteredEventList());
    }
}
