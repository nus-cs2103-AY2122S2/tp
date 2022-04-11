package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_DRIVER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_DRIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
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
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.DriverStatus;
import seedu.address.testutil.DriverBuilder;
import seedu.address.testutil.EditDriverDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditDriverCommand.
 */
public class EditDriverCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Driver editedDriver = new DriverBuilder().build();
        EditDriverCommand.EditDriverDescriptor descriptor =
                new EditDriverDescriptorBuilder(editedDriver).build();
        EditDriverCommand editDriverCommand = new EditDriverCommand(INDEX_SECOND, descriptor);

        String expectedMessage = String.format(EditDriverCommand.MESSAGE_EDIT_DRIVER_SUCCESS, editedDriver);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDriver(model.getFilteredDriverList().get(1), editedDriver);

        assertCommandSuccess(editDriverCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editStatusToBusy_failure() {
        Driver editedDriver = new DriverBuilder().build();
        EditDriverCommand.EditDriverDescriptor descriptor =
                new EditDriverDescriptorBuilder(editedDriver).withStatus(DriverStatus.BUSY).build();
        EditDriverCommand editDriverCommand = new EditDriverCommand(INDEX_SECOND, descriptor);

        String expectedMessage = String.format(EditDriverCommand.MESSAGE_UPDATE_TO_BUSY_FAIL,
                model.getFilteredDriverList().get(1));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDriver(model.getFilteredDriverList().get(1), editedDriver);

        assertCommandFailure(editDriverCommand, model, expectedMessage);
    }
    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastDriver = Index.fromOneBased(model.getFilteredDriverList().size());
        Driver lastDriver = model.getFilteredDriverList().get(indexLastDriver.getZeroBased());

        DriverBuilder driverInList = new DriverBuilder(lastDriver);
        Driver editedDriver = driverInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();

        EditDriverCommand.EditDriverDescriptor descriptor =
                new EditDriverDescriptorBuilder().withName(VALID_NAME_BOB)
                        .withPhone(VALID_PHONE_BOB).build();
        EditDriverCommand editDriverCommand = new EditDriverCommand(indexLastDriver, descriptor);

        String expectedMessage = String.format(EditDriverCommand.MESSAGE_EDIT_DRIVER_SUCCESS, editedDriver);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDriver(lastDriver, editedDriver);

        assertCommandSuccess(editDriverCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditDriverCommand editDriverCommand = new EditDriverCommand(INDEX_SECOND,
                new EditDriverCommand.EditDriverDescriptor());
        Driver editedDriver = model.getFilteredDriverList().get(INDEX_SECOND.getZeroBased());

        String expectedMessage = String.format(EditDriverCommand.MESSAGE_EDIT_DRIVER_SUCCESS, editedDriver);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editDriverCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateDriverUnfilteredList_failure() {
        Driver firstDriver = model.getFilteredDriverList().get(INDEX_FIRST.getZeroBased());
        EditDriverCommand.EditDriverDescriptor descriptor =
                new EditDriverDescriptorBuilder(firstDriver).build();
        EditDriverCommand editDriverCommand = new EditDriverCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editDriverCommand, model, EditDriverCommand.MESSAGE_DUPLICATE_DRIVER);
    }

    @Test
    public void execute_invalidDriverIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDriverList().size() + 1);
        EditDriverCommand.EditDriverDescriptor descriptor =
                new EditDriverDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditDriverCommand editDriverCommand = new EditDriverCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editDriverCommand, model, Messages.MESSAGE_INVALID_DRIVER_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        final EditDriverCommand standardCommand = new EditDriverCommand(INDEX_FIRST, DESC_AMY_DRIVER);
        // same values -> returns true
        EditDriverCommand.EditDriverDescriptor copyDescriptor =
                new EditDriverCommand.EditDriverDescriptor(DESC_AMY_DRIVER);
        EditDriverCommand commandWithSameValues = new EditDriverCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditDriverCommand(INDEX_SECOND, DESC_AMY_DRIVER)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditDriverCommand(INDEX_FIRST, DESC_BOB_DRIVER)));
    }

}
