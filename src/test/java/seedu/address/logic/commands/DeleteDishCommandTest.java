package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDishAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.dish.Dish;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteDishCommand}.
 */
public class DeleteDishCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Dish dishToDelete = model.getFilteredDishList().get(INDEX_FIRST.getZeroBased());
        DeleteDishCommand deleteDishCommand = new DeleteDishCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteDishCommand.MESSAGE_DELETE_DISH_SUCCESS, dishToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteDish(dishToDelete);

        assertCommandSuccess(deleteDishCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDishList().size() + 1);
        DeleteDishCommand deleteDishCommand = new DeleteDishCommand(outOfBoundIndex);

        assertCommandFailure(deleteDishCommand, model, Messages.MESSAGE_INVALID_DISH_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showDishAtIndex(model, INDEX_FIRST);

        Dish dishToDelete = model.getFilteredDishList().get(INDEX_FIRST.getZeroBased());
        DeleteDishCommand deleteDishCommand = new DeleteDishCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteDishCommand.MESSAGE_DELETE_DISH_SUCCESS, dishToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteDish(dishToDelete);
        showNoDish(expectedModel);

        assertCommandSuccess(deleteDishCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDishAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getDishList().size());

        DeleteDishCommand deleteDishCommand = new DeleteDishCommand(outOfBoundIndex);

        assertCommandFailure(deleteDishCommand, model, Messages.MESSAGE_INVALID_DISH_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteDishCommand deleteFirstCommand = new DeleteDishCommand(INDEX_FIRST);
        DeleteDishCommand deleteSecondCommand = new DeleteDishCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteDishCommand deleteFirstCommandCopy = new DeleteDishCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different Dish -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoDish(Model model) {
        model.updateFilteredDishList(p -> false);

        assertTrue(model.getFilteredDishList().isEmpty());
    }
}

