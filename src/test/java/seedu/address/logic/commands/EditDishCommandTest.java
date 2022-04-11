package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FRIES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISH_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISH_NAME_FRIES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISH_PRICE_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISH_PRICE_FRIES;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDishAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.dish.Dish;
import seedu.address.testutil.DishBuilder;
import seedu.address.testutil.EditDishDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditDishCommand.
 */
public class EditDishCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Dish editedDish = new DishBuilder().build();
        EditDishCommand.EditDishDescriptor descriptor =
                new EditDishDescriptorBuilder(editedDish).build();
        EditDishCommand editDishCommand = new EditDishCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditDishCommand.MESSAGE_EDIT_DISH_SUCCESS, editedDish);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDish(model.getFilteredDishList().get(0), editedDish);

        assertCommandSuccess(editDishCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastDish = Index.fromOneBased(model.getFilteredDishList().size());
        Dish lastDish = model.getFilteredDishList().get(indexLastDish.getZeroBased());

        DishBuilder dishInList = new DishBuilder(lastDish);
        Dish editedDish = dishInList.withPrice(VALID_DISH_PRICE_DONUT).build();

        EditDishCommand.EditDishDescriptor descriptor =
                new EditDishDescriptorBuilder()
                .withPrice(VALID_DISH_PRICE_DONUT).build();
        EditDishCommand editDishCommand = new EditDishCommand(indexLastDish, descriptor);

        String expectedMessage = String.format(EditDishCommand.MESSAGE_EDIT_DISH_SUCCESS, editedDish);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDish(lastDish, editedDish);

        assertCommandSuccess(editDishCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditDishCommand editDishCommand = new EditDishCommand(INDEX_FIRST,
                new EditDishCommand.EditDishDescriptor());
        Dish editedDish = model.getFilteredDishList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditDishCommand.MESSAGE_NOT_EDITED, editedDish);

        assertCommandFailure(editDishCommand, model, expectedMessage);
    }

    @Test
    public void execute_filteredList_success() {
        showDishAtIndex(model, INDEX_FIRST);

        Dish dishInFilteredList = model.getFilteredDishList().get(INDEX_FIRST.getZeroBased());
        Dish editedDish = new DishBuilder(dishInFilteredList).withName(VALID_DISH_NAME_FRIES)
                .withPrice(VALID_DISH_PRICE_FRIES).build();
        EditDishCommand editDishCommand = new EditDishCommand(INDEX_FIRST,
                new EditDishDescriptorBuilder().withName(VALID_DISH_NAME_FRIES)
                        .withPrice(VALID_DISH_PRICE_FRIES).build());

        String expectedMessage = String.format(EditDishCommand.MESSAGE_EDIT_DISH_SUCCESS, editedDish);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDish(model.getFilteredDishList().get(0), editedDish);

        assertCommandSuccess(editDishCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateDishUnfilteredList_failure() {
        Dish firstDish = model.getFilteredDishList().get(INDEX_FIRST.getZeroBased());
        EditDishCommand.EditDishDescriptor descriptor =
                new EditDishDescriptorBuilder(firstDish).build();
        EditDishCommand editDishCommand = new EditDishCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editDishCommand, model, EditDishCommand.MESSAGE_DUPLICATE_DISH);
    }

    @Test
    public void execute_duplicateDishFilteredList_failure() {
        showDishAtIndex(model, INDEX_FIRST);

        // edit Dish in filtered list into a duplicate in address book
        Dish dishInList = model.getAddressBook().getDishList().get(INDEX_SECOND.getZeroBased());
        EditDishCommand editDishCommand = new EditDishCommand(INDEX_FIRST,
                new EditDishDescriptorBuilder(dishInList).build());

        assertCommandFailure(editDishCommand, model, EditDishCommand.MESSAGE_DUPLICATE_DISH);
    }

    @Test
    public void execute_invalidDishIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDishList().size() + 1);
        EditDishCommand.EditDishDescriptor descriptor =
                new EditDishDescriptorBuilder().withName(VALID_DISH_NAME_DONUT).build();
        EditDishCommand editDishCommand = new EditDishCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editDishCommand, model, Messages.MESSAGE_INVALID_DISH_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidDishIndexFilteredList_failure() {
        showDishAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getDishList().size());

        EditDishCommand editDishCommand = new EditDishCommand(outOfBoundIndex,
                new EditDishDescriptorBuilder().withName(VALID_DISH_NAME_DONUT).build());

        assertCommandFailure(editDishCommand, model, Messages.MESSAGE_INVALID_DISH_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditDishCommand standardCommand = new EditDishCommand(INDEX_FIRST, DESC_FRIES);

        // same values -> returns true
        EditDishCommand.EditDishDescriptor copyDescriptor =
                new EditDishCommand.EditDishDescriptor(DESC_FRIES);
        EditDishCommand commandWithSameValues = new EditDishCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditDishCommand(INDEX_SECOND, DESC_FRIES)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditDishCommand(INDEX_FIRST, DESC_DONUT)));
    }

}
