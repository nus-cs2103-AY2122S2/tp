package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.StudentIdContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(new Index[]{INDEX_FIRST_PERSON});

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultipleIndexUnfilteredList_success() {
        Person personToDelete1 = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personToDelete2 = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(
                new Index[]{INDEX_FIRST_PERSON, INDEX_SECOND_PERSON});

        String expectedMessage = String.format(
                DeleteCommand.MESSAGE_DELETE_MULTIPLE_PERSONS_SUCCESS, 2);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete1);
        expectedModel.deletePerson(personToDelete2);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(new Index[]{outOfBoundIndex});

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidMultipleIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Index outOfBoundIndex2 = Index.fromOneBased(model.getFilteredPersonList().size() + 2);
        DeleteCommand deleteCommand = new DeleteCommand(new Index[]{outOfBoundIndex, outOfBoundIndex2});

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validAndInvalidIndexUnfilteredList_throwsCommandException() {
        Index validIndex = Index.fromOneBased(model.getFilteredPersonList().size() - 1);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(new Index[]{validIndex, outOfBoundIndex});

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(new Index[]{INDEX_FIRST_PERSON});

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(new Index[]{outOfBoundIndex});

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validStudentIdFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(personToDelete.getStudentId());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validStudentIdUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(personToDelete.getStudentId());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIdUnfilteredList_throwsCommandException() {
        StudentId nonExistentId = new StudentId("A234Z");

        DeleteCommand deleteCommand = new DeleteCommand(nonExistentId);

        String expectedMessage = Messages.MESSAGE_NONEXISTENT_STUDENTID;

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void equals_index() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(new Index[]{INDEX_FIRST_PERSON});
        DeleteCommand deleteSecondCommand = new DeleteCommand(new Index[]{INDEX_SECOND_PERSON});
        DeleteCommand deleteMultipleCommand = new DeleteCommand(
                new Index[]{INDEX_FIRST_PERSON, INDEX_SECOND_PERSON});

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(new Index[]{INDEX_FIRST_PERSON});
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // same object -> returns true
        assertTrue(deleteMultipleCommand.equals(deleteMultipleCommand));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

        // different command -> returns false
        assertFalse(deleteFirstCommand.equals(deleteMultipleCommand));
    }

    @Test
    public void equals_studentId() {
        DeleteCommand deleteFirstId = new DeleteCommand(new StudentId(VALID_ID_AMY));
        DeleteCommand deleteSecondId = new DeleteCommand(new StudentId(VALID_ID_BOB));

        // same object -> returns true
        assertTrue(deleteFirstId.equals(deleteFirstId));

        // same object -> returns true
        assertTrue(deleteSecondId.equals(deleteSecondId));

        // different types -> returns false
        assertFalse(deleteFirstId.equals(1));

        // null -> returns false
        assertFalse(deleteFirstId.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstId.equals(deleteSecondId));

        // different person -> returns false
        assertFalse(deleteFirstId.equals(deleteSecondId));
    }

    public void equals_indexAndId() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(new Index[]{INDEX_FIRST_PERSON});
        DeleteCommand deleteSecondCommand = new DeleteCommand(new Index[]{INDEX_SECOND_PERSON});
        DeleteCommand deleteMultipleCommand = new DeleteCommand(
                new Index[]{INDEX_FIRST_PERSON, INDEX_SECOND_PERSON});
        DeleteCommand deleteFirstId = new DeleteCommand(new StudentId(VALID_ID_AMY));
        DeleteCommand deleteSecondId = new DeleteCommand(new StudentId(VALID_ID_BOB));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(new Index[]{INDEX_FIRST_PERSON});
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // same object -> returns true
        assertTrue(deleteMultipleCommand.equals(deleteMultipleCommand));

        // same object -> returns true
        assertTrue(deleteFirstId.equals(deleteFirstId));

        // same object -> returns true
        assertTrue(deleteSecondId.equals(deleteSecondId));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

        // different command -> returns false
        assertFalse(deleteFirstCommand.equals(deleteMultipleCommand));

        // different person -> returns false
        assertFalse(deleteFirstId.equals(deleteSecondId));
    }

    /**
     * For testing between Delete and Find commands since both use predicates
     */
    public void equals_differentCommand() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(new Index[]{INDEX_FIRST_PERSON});
        DeleteCommand deleteMultipleCommand = new DeleteCommand(
                new Index[]{INDEX_FIRST_PERSON, INDEX_SECOND_PERSON});
        DeleteCommand deleteFirstId = new DeleteCommand(new StudentId(VALID_ID_AMY));

        // name
        FindCommand findFirstCommand = new FindCommand(prepareNamePredicate(VALID_NAME_AMY));

        // mod code
        FindCommand findSecondCommand = new FindCommand(prepareModuleCodePredicate(VALID_MODULE_CODE_AMY));

        // student id
        FindCommand findThirdCommand = new FindCommand(prepareStudentIdPredicate(VALID_ID_AMY));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(findFirstCommand));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(findSecondCommand));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(findThirdCommand));

        // different command -> returns false
        assertFalse(deleteFirstId.equals(findFirstCommand));

        // different command -> returns false
        assertFalse(deleteFirstId.equals(findSecondCommand));

        // different command -> returns false
        assertFalse(deleteFirstId.equals(findThirdCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code StudentIdContainsKeywordsPredicate}.
     */
    private StudentIdContainsKeywordsPredicate prepareStudentIdPredicate(String userInput) {
        return new StudentIdContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code ModuleCodeContainsKeywordsPredicate}.
     */
    private ModuleCodeContainsKeywordsPredicate prepareModuleCodePredicate(String userInput) {
        return new ModuleCodeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
