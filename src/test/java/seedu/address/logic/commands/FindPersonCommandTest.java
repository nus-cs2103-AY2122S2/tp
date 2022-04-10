package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntries.CARL;
import static seedu.address.testutil.TypicalEntries.ELLE;
import static seedu.address.testutil.TypicalEntries.FIONA;
import static seedu.address.testutil.TypicalEntries.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.SearchTypeUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entry.predicate.PersonContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPersonCommand}.
 */
public class FindPersonCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {

        PersonContainsKeywordsPredicate firstPredicate = new PersonContainsKeywordsPredicate(
                List.<String>of("first"), List.<String>of(""), List.<String>of(""),
                SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.UNARCHIVED_ONLY));
        PersonContainsKeywordsPredicate secondPredicate = new PersonContainsKeywordsPredicate(
                List.<String>of("second"), List.<String>of(""), List.<String>of(""),
                SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.UNARCHIVED_ONLY));


        FindPersonCommand findFirstCommand = new FindPersonCommand(firstPredicate);
        FindPersonCommand findSecondCommand = new FindPersonCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPersonCommand findFirstCommandCopy = new FindPersonCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    // There is no way for FindPersonCommand to receive zeroKeywords as it will be checked by Parser
    //@Test
    //public void execute_zeroKeywords_noPersonFound() {
    //    String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
    //    NameContainsKeywordsPredicate predicate = preparePredicate(" ");
    //    FindPersonCommand command = new FindPersonCommand(predicate);
    //    expectedModel.updateFilteredPersonList(predicate);
    //    CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false, true, false, false);
    //    assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    //    assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    //}

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate(List.<String>of("Kurz", "Elle", "Kunz"), List.<String>of(""),
                        List.<String>of(""), SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.UNARCHIVED_ONLY));

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false, true, false, false);
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.showPersonList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    ///**
    // * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
    // */
    //private NameContainsKeywordsPredicate preparePredicate(String userInput) {
    //    return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    //}
}
