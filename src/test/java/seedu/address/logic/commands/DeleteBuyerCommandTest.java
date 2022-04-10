package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.BuyerCommandTestUtil.showBuyerAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.Buyer;
import seedu.address.testutil.TypicalBuyers;

public class DeleteBuyerCommandTest {

    private Model model = new ModelManager(new UserPrefs(),
            new SellerAddressBook(), TypicalBuyers.getTypicalBuyerAddressBook());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Buyer buyerToDelete = model.getFilteredBuyerList().get(INDEX_FIRST_CLIENT.getZeroBased());
        DeleteBuyerCommand deleteCommand = new DeleteBuyerCommand(INDEX_FIRST_CLIENT);

        String expectedMessage = String.format(DeleteBuyerCommand.MESSAGE_DELETE_CLIENT_SUCCESS, buyerToDelete);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), new SellerAddressBook(),
                TypicalBuyers.getTypicalBuyerAddressBook());
        expectedModel.deleteBuyer(buyerToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBuyerList().size() + 1);
        DeleteBuyerCommand deleteCommand = new DeleteBuyerCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showBuyerAtIndex(model, INDEX_FIRST_CLIENT);

        Buyer buyerToDelete = model.getFilteredBuyerList().get(INDEX_FIRST_CLIENT.getZeroBased());
        DeleteBuyerCommand deleteCommand = new DeleteBuyerCommand(INDEX_FIRST_CLIENT);

        String expectedMessage = String.format(DeleteBuyerCommand.MESSAGE_DELETE_CLIENT_SUCCESS, buyerToDelete);

        Model expectedModel = new ModelManager(new UserPrefs(), new SellerAddressBook(),
                TypicalBuyers.getTypicalBuyerAddressBook());
        expectedModel.deleteBuyer(buyerToDelete);
        showNoBuyer(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBuyerAtIndex(model, INDEX_FIRST_CLIENT);

        Index outOfBoundIndex = INDEX_SECOND_CLIENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBuyerAddressBook().getBuyerList().size());

        DeleteBuyerCommand deleteCommand = new DeleteBuyerCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteBuyerCommand deleteFirstCommand = new DeleteBuyerCommand(INDEX_FIRST_CLIENT);
        DeleteBuyerCommand deleteSecondCommand = new DeleteBuyerCommand(INDEX_SECOND_CLIENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteBuyerCommand deleteFirstCommandCopy = new DeleteBuyerCommand(INDEX_FIRST_CLIENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different buyer -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoBuyer(Model model) {
        model.updateFilteredBuyerList(p -> false);

        assertTrue(model.getFilteredBuyerList().isEmpty());
    }
}
