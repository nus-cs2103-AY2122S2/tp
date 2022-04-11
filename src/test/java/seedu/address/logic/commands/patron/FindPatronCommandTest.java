package seedu.address.logic.commands.patron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PATRONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatrons.CARL;
import static seedu.address.testutil.TypicalPatrons.ELLE;
import static seedu.address.testutil.TypicalPatrons.FIONA;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patron.NameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalLibTask;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindPatronCommandTest {
    private Model model = new ModelManager(TypicalLibTask.getTypicalLibTask(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalLibTask.getTypicalLibTask(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindPatronCommand findFirstCommand = new FindPatronCommand(firstPredicate);
        FindPatronCommand findSecondCommand = new FindPatronCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPatronCommand findFirstCommandCopy = new FindPatronCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different patron -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPatronFound() {
        String expectedMessage = String.format(MESSAGE_PATRONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindPatronCommand command = new FindPatronCommand(predicate);
        expectedModel.updateFilteredPatronList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPatronList());
    }

    @Test
    public void execute_multipleKeywords_multiplePatronsFound() {
        String expectedMessage = String.format(MESSAGE_PATRONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindPatronCommand command = new FindPatronCommand(predicate);
        expectedModel.updateFilteredPatronList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPatronList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
