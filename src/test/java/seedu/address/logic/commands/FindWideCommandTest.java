package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandUnExecuteSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameFacultyRoleContainsAnyKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindWideCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameFacultyRoleContainsAnyKeywordsPredicate firstPredicate =
                new NameFacultyRoleContainsAnyKeywordsPredicate(Collections.singletonList("first"));
        NameFacultyRoleContainsAnyKeywordsPredicate secondPredicate =
                new NameFacultyRoleContainsAnyKeywordsPredicate(Collections.singletonList("second"));

        FindWideCommand findFirstCommand = new FindWideCommand(firstPredicate);
        FindWideCommand findSecondCommand = new FindWideCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindWideCommand findFirstCommandCopy = new FindWideCommand(firstPredicate);
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
        NameFacultyRoleContainsAnyKeywordsPredicate predicate = preparePredicate(" ");
        FindWideCommand command = new FindWideCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        NameFacultyRoleContainsAnyKeywordsPredicate predicate = preparePredicate("TA Kurz");
        FindWideCommand command = new FindWideCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL, DANIEL, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void unExecute_find_successful() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        NameFacultyRoleContainsAnyKeywordsPredicate predicate = preparePredicate("TA Kurz");
        FindWideCommand command = new FindWideCommand(predicate);

        assertCommandUnExecuteSuccess(command, model, expectedModel);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsAnyPredicate}.
     */
    private NameFacultyRoleContainsAnyKeywordsPredicate preparePredicate(String userInput) {
        return new NameFacultyRoleContainsAnyKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
