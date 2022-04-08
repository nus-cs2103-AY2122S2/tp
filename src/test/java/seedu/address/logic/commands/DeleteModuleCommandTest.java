package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteModuleCommand}.
 */
public class DeleteModuleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validModuleCodeFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteModuleCommand deleteCommand = new DeleteModuleCommand(
                prepareModuleCodePredicate(personToDelete.getModuleCode().toString()));

        String expectedMessage = String.format(DeleteModuleCommand.MESSAGE_DELETE_MULTIPLE_PERSONS_SUCCESS, 1);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validModuleCodeUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteModuleCommand deleteCommand = new DeleteModuleCommand(
                prepareModuleCodePredicate(personToDelete.getModuleCode().toString()));

        String expectedMessage = String.format(DeleteModuleCommand.MESSAGE_DELETE_MULTIPLE_PERSONS_SUCCESS, 1);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidModuleCode_displaysErrorMessage() {
        ModuleCode nonExistentModuleCode = new ModuleCode("9999");

        DeleteModuleCommand deleteCommand = new DeleteModuleCommand(
                prepareModuleCodePredicate(nonExistentModuleCode.toString()));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        String expectedMessage = DeleteModuleCommand.MESSAGE_NONEXISTENT_MODULE_CODE;

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteModuleCommand deleteFirstCommand = new DeleteModuleCommand(
                prepareModuleCodePredicate("1111"));
        DeleteModuleCommand deleteSecondCommand = new DeleteModuleCommand(
                prepareModuleCodePredicate("2222"));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteModuleCommand deleteFirstCommandCopy = new DeleteModuleCommand(
                prepareModuleCodePredicate("1111"));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

        // different command -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }

    /**
     * Parses {@code userInput} into a {@code ModuleCodeContainsKeywordsPredicate}.
     */
    private ModuleCodeContainsKeywordsPredicate prepareModuleCodePredicate(String userInput) {
        return new ModuleCodeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
