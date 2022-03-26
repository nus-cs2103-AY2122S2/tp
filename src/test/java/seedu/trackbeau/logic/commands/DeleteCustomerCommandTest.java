package seedu.trackbeau.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.trackbeau.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackbeau.logic.commands.CommandTestUtil.showCustomerAtIndex;
import static seedu.trackbeau.testutil.TypicalCustomers.getTypicalTrackBeau;
import static seedu.trackbeau.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static seedu.trackbeau.testutil.TypicalIndexes.INDEX_SECOND_CUSTOMER;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.commons.core.Messages;
import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.logic.commands.customer.DeleteCustomerCommand;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.ModelManager;
import seedu.trackbeau.model.UserPrefs;
import seedu.trackbeau.model.customer.Customer;



/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCustomerCommand}.
 */
public class DeleteCustomerCommandTest {

    private Model model = new ModelManager(getTypicalTrackBeau(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Customer customerToDelete = model.getFilteredCustomerList().get(INDEX_FIRST_CUSTOMER.getZeroBased());
        ArrayList<Index> firstCustomer = new ArrayList<>() {
            {
                add(INDEX_FIRST_CUSTOMER);
            }
        };
        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(firstCustomer);

        StringBuilder sb = new StringBuilder();
        sb.append(customerToDelete).append("\n");
        String expectedMessage = String.format(DeleteCustomerCommand.MESSAGE_DELETE_CUSTOMER_SUCCESS, sb);

        ModelManager expectedModel = new ModelManager(model.getTrackBeau(), new UserPrefs());
        expectedModel.deleteCustomer(customerToDelete);

        assertCommandSuccess(deleteCustomerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        ArrayList<Index> outOfBoundIndex = new ArrayList<>() {
            {
                add(Index.fromOneBased(model.getFilteredCustomerList().size() + 1));
            }
        };

        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(outOfBoundIndex);

        assertCommandFailure(deleteCustomerCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validMultipleIndexesUnfilteredList_success() {
        Customer customerToDelete1 = model.getFilteredCustomerList().get(INDEX_FIRST_CUSTOMER.getZeroBased());
        Customer customerToDelete2 = model.getFilteredCustomerList().get(INDEX_SECOND_CUSTOMER.getZeroBased());

        ArrayList<Index> customers = new ArrayList<>() {
            {
                add(INDEX_FIRST_CUSTOMER);
                add(INDEX_SECOND_CUSTOMER);
            }
        };
        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(customers);

        StringBuilder sb = new StringBuilder();
        sb.append(customerToDelete1).append("\n");
        sb.append(customerToDelete2).append("\n");
        String expectedMessage = String.format(DeleteCustomerCommand.MESSAGE_DELETE_CUSTOMER_SUCCESS, sb);

        ModelManager expectedModel = new ModelManager(model.getTrackBeau(), new UserPrefs());
        expectedModel.deleteCustomer(customerToDelete1);
        expectedModel.deleteCustomer(customerToDelete2);

        assertCommandSuccess(deleteCustomerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidMultipleIndexesUnfilteredList_throwsCommandException() {
        ArrayList<Index> customers = new ArrayList<>() {
            {
                add(INDEX_FIRST_CUSTOMER);
                add(Index.fromOneBased(model.getFilteredCustomerList().size() + 1));
            }
        };
        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(customers);

        assertCommandFailure(deleteCustomerCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);

        Customer customerToDelete = model.getFilteredCustomerList().get(INDEX_FIRST_CUSTOMER.getZeroBased());
        ArrayList<Index> firstCustomer = new ArrayList<>() {
            {
                add(INDEX_FIRST_CUSTOMER);
            }
        };
        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(firstCustomer);

        StringBuilder sb = new StringBuilder();
        sb.append(customerToDelete).append("\n");
        String expectedMessage = String.format(DeleteCustomerCommand.MESSAGE_DELETE_CUSTOMER_SUCCESS, sb);

        Model expectedModel = new ModelManager(model.getTrackBeau(), new UserPrefs());
        expectedModel.deleteCustomer(customerToDelete);
        showNoCustomer(expectedModel);

        assertCommandSuccess(deleteCustomerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);

        ArrayList<Index> outOfBoundIndex = new ArrayList<>() {
            {
                add(INDEX_SECOND_CUSTOMER);
            }
        };
        // ensures that outOfBoundIndex is still in bounds of trackBeau list
        assertTrue(outOfBoundIndex.get(0).getZeroBased() < model.getTrackBeau().getCustomerList().size());

        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(outOfBoundIndex);

        assertCommandFailure(deleteCustomerCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ArrayList<Index> firstCustomer = new ArrayList<>() {
            {
                add(INDEX_FIRST_CUSTOMER);
            }
        };
        ArrayList<Index> secondCustomer = new ArrayList<>() {
            {
                add(INDEX_SECOND_CUSTOMER);
            }
        };
        DeleteCustomerCommand deleteFirstCommand = new DeleteCustomerCommand(firstCustomer);
        DeleteCustomerCommand deleteSecondCommand = new DeleteCustomerCommand(secondCustomer);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCustomerCommand deleteFirstCommandCopy = new DeleteCustomerCommand(firstCustomer);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different customer -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoCustomer(Model model) {
        model.updateFilteredCustomerList(p -> false);

        assertTrue(model.getFilteredCustomerList().isEmpty());
    }
}
