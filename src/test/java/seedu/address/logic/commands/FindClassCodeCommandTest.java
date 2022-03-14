package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ClassCodeContainsKeywordsPredicate;

public class FindClassCodeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ClassCodeContainsKeywordsPredicate firstPredicate =
                new ClassCodeContainsKeywordsPredicate(Collections.singletonList("4A"));
        ClassCodeContainsKeywordsPredicate secondPredicate =
                new ClassCodeContainsKeywordsPredicate(Collections.singletonList("4B"));

        FindClassCodeCommand findFirstCommand = new FindClassCodeCommand(firstPredicate);
        FindClassCodeCommand findSecondCommand = new FindClassCodeCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindClassCodeCommand findFirstCommandCopy = new FindClassCodeCommand(firstPredicate);
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
        ClassCodeContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindClassCodeCommand command = new FindClassCodeCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code ClassCodeContainsKeywordsPredicate}.
     */
    private ClassCodeContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ClassCodeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
