package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.DeleteCommand.MESSAGE_MULTIPLE_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalNames.FULL_NAME_FIRST_PERSON;
import static seedu.address.testutil.TypicalNames.NAME_INVALID_PERSON;
import static seedu.address.testutil.TypicalNames.NAME_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getDuplicatesHustleBook;
import static seedu.address.testutil.TypicalPersons.getTypicalHustleBook;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalHustleBook(), new UserPrefs());

    @Test
    public void execute_validNameUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(FULL_NAME_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getHustleBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameUnfilteredList_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(NAME_INVALID_PERSON);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_NAME);
    }

    @Test
    public void execute_validNameFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(FULL_NAME_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getHustleBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_success() {
        model.setHustleBook(getDuplicatesHustleBook());

        DeleteCommand deleteCommand = new DeleteCommand(NAME_SECOND_PERSON);

        Model expectedModel = new ModelManager(getDuplicatesHustleBook(), new UserPrefs());
        Predicate<Person> predicate = new NameContainsKeywordsPredicate(Arrays.asList(NAME_SECOND_PERSON.fullName));
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(deleteCommand, model, MESSAGE_MULTIPLE_PERSON, expectedModel);
    }

    @Test
    public void execute_duplicatePersonValidIndex_success() throws ParseException {
        model.setHustleBook(getDuplicatesHustleBook());
        Predicate<Person> predicate = new NameContainsKeywordsPredicate(Arrays.asList(NAME_SECOND_PERSON.fullName));

        DeleteCommand deleteCommand = new DeleteCommand(NAME_SECOND_PERSON);

        Model expectedModel = new ModelManager(getDuplicatesHustleBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(deleteCommand, model, MESSAGE_MULTIPLE_PERSON, expectedModel);

        deleteCommand.setIndex(2);
        Person personToDelete = expectedModel.getFilteredPersonList().get(1);
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        DeleteCommand deleteCommand = new DeleteCommand(NAME_SECOND_PERSON);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_NAME);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(FULL_NAME_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(NAME_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(FULL_NAME_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
