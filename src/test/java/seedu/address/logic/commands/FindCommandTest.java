package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.InterviewContainsKeywordsPredicate;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {

        List<String> first = new ArrayList<>();
        first.add("n/first");

        List<String> second = new ArrayList<>();
        second.add("n/second");

        PersonContainsKeywordsPredicate firstPredicate = new PersonContainsKeywordsPredicate(first);
        InterviewContainsKeywordsPredicate secondPredicate = new InterviewContainsKeywordsPredicate(second);

        FindPersonCommand findFirstCommand = new FindPersonCommand(firstPredicate);
        FindInterviewCommand findSecondCommand = new FindInterviewCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindPersonCommand findFirstCommandCopy = new FindPersonCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        FindInterviewCommand findSecondCommandCopy = new FindInterviewCommand(secondPredicate);
        assertEquals(findSecondCommand, findSecondCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);
        assertNotEquals(1, findSecondCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);
        assertNotEquals(null, findSecondCommand);

        // different applicant -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        List<String> keywords = new ArrayList<>();
        keywords.add("n/wrong n/still_wrong");

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(keywords);
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        List<String> keywords = new ArrayList<>();
        keywords.add("n/Kurz");
        keywords.add("n/Elle");
        keywords.add("n/Kunz");

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(keywords);
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }
}
