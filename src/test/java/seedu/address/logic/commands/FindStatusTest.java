package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.StatusContainsKeywordsPredicate;

public class FindStatusTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StatusContainsKeywordsPredicate firstPredicate =
                new StatusContainsKeywordsPredicate(Collections.singletonList("Positive"));
        StatusContainsKeywordsPredicate secondPredicate =
                new StatusContainsKeywordsPredicate(Collections.singletonList("Negative"));

        FindStatusCommand findFirstCommand = new FindStatusCommand(firstPredicate);
        FindStatusCommand findSecondCommand = new FindStatusCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindStatusCommand findFirstCommandCopy = new FindStatusCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        StatusContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindStatusCommand command = new FindStatusCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_negativeStatus_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        StatusContainsKeywordsPredicate predicate = preparePredicate("Negative");
        FindStatusCommand command = new FindStatusCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL, DANIEL, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_positiveStatus_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        StatusContainsKeywordsPredicate predicate = preparePredicate("Positive");
        FindStatusCommand command = new FindStatusCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_closeContactStatus_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        StatusContainsKeywordsPredicate predicate = preparePredicate("Close-Contact");
        FindStatusCommand command = new FindStatusCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, GEORGE), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code StatusContainsKeywordsPredicate}.
     */
    private StatusContainsKeywordsPredicate preparePredicate(String userInput) {
        return new StatusContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
