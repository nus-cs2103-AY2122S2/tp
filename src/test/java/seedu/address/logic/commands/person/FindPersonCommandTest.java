package seedu.address.logic.commands.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.UserPrefs;
import seedu.address.model.name.PersonNameContainsKeywordsPredicate;
import seedu.address.model.tag.PersonTagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindPersonCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());

    @Test
    public void equals() {

        PersonNameContainsKeywordsPredicate firstPredicate =
                new PersonNameContainsKeywordsPredicate(Collections.singletonList("first"));
        PersonNameContainsKeywordsPredicate secondPredicate =
                new PersonNameContainsKeywordsPredicate(Collections.singletonList("second"));
        PersonTagContainsKeywordsPredicate thirdPredicate =
                new PersonTagContainsKeywordsPredicate(Collections.singletonList("third"));
        PersonTagContainsKeywordsPredicate fourthPredicate =
                new PersonTagContainsKeywordsPredicate(Collections.singletonList("fourth"));

        FindPersonCommand findFirstCommand = new FindPersonCommand(firstPredicate, thirdPredicate);
        FindPersonCommand findSecondCommand = new FindPersonCommand(secondPredicate, fourthPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPersonCommand findFirstCommandCopy = new FindPersonCommand(firstPredicate, thirdPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonAndTagFound() {
        List<String> nameList = Arrays.asList();
        List<String> tagList = Arrays.asList();
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonNameContainsKeywordsPredicate namePredicate = prepareNamePredicate(nameList);
        PersonTagContainsKeywordsPredicate tagPredicate = prepareTagPredicate(tagList);
        FindPersonCommand command = new FindPersonCommand(namePredicate, tagPredicate);
        expectedModel.updateFilteredPersonList(namePredicate.or(tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsAndTagsFound() {
        List<String> nameList = Arrays.asList("Alice", "Bob");
        List<String> tagList = Arrays.asList("friends", "neighbours");
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonNameContainsKeywordsPredicate namePredicate = prepareNamePredicate(nameList);
        PersonTagContainsKeywordsPredicate tagPredicate = prepareTagPredicate(tagList);
        FindPersonCommand command = new FindPersonCommand(namePredicate, tagPredicate);
        expectedModel.updateFilteredPersonList(namePredicate.or(tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    /**
     * Creates a {@code PersonNameContainsKeywordsPredicate} using {@code nameList}.
     */
    private PersonNameContainsKeywordsPredicate prepareNamePredicate(List<String> nameList) {
        return new PersonNameContainsKeywordsPredicate(nameList);
    }
    /**
     * Creates a {@code PersonTagContainsKeywordsPredicate} using {@code tagSet}.
     */
    private PersonTagContainsKeywordsPredicate prepareTagPredicate(List<String> tagList) {
        return new PersonTagContainsKeywordsPredicate(tagList);
    }
}
