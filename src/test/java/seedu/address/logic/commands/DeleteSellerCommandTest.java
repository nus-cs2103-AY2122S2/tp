package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.SellerCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SellerCommandTestUtil.assertSellerCommandFailure;
import static seedu.address.logic.commands.SellerCommandTestUtil.showSellerAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SELLER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SELLER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.BuyerAddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.seller.Seller;
import seedu.address.testutil.TypicalSellers;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteSellerCommandTest {

    private Model model = new ModelManager(new UserPrefs(),
            TypicalSellers.getTypicalSellerAddressBook(), new BuyerAddressBook());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Seller sellerToDelete = model.getFilteredSellerList().get(INDEX_FIRST_SELLER.getZeroBased());
        DeleteSellerCommand deleteSellerCommand = new DeleteSellerCommand(INDEX_FIRST_SELLER);

        String expectedMessage = String.format(DeleteSellerCommand.MESSAGE_DELETE_SELLER_SUCCESS, sellerToDelete);

        ModelManager expectedModel = new ModelManager(new UserPrefs(),
                TypicalSellers.getTypicalSellerAddressBook(), new BuyerAddressBook());
        expectedModel.deleteSeller(sellerToDelete);

        assertCommandSuccess(deleteSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredSellerList().size() + 1);
        DeleteSellerCommand deleteSellerCommand = new DeleteSellerCommand(outOfBoundIndex);
        assertSellerCommandFailure(deleteSellerCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showSellerAtIndex(model, INDEX_FIRST_SELLER);

        Seller sellerToDelete = model.getFilteredSellerList().get(INDEX_FIRST_SELLER.getZeroBased());
        DeleteSellerCommand deleteSellerCommand = new DeleteSellerCommand(INDEX_FIRST_SELLER);

        String expectedMessage = String.format(DeleteSellerCommand.MESSAGE_DELETE_SELLER_SUCCESS, sellerToDelete);

        Model expectedModel = new ModelManager(new UserPrefs(),
                TypicalSellers.getTypicalSellerAddressBook(), new BuyerAddressBook());
        expectedModel.deleteSeller(sellerToDelete);
        showNoSeller(expectedModel);

        assertCommandSuccess(deleteSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showSellerAtIndex(model, INDEX_FIRST_SELLER);

        Index outOfBoundIndex = INDEX_SECOND_SELLER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSellerAddressBook().getSellerList().size());

        DeleteSellerCommand deleteSellerCommand = new DeleteSellerCommand(outOfBoundIndex);

        assertSellerCommandFailure(deleteSellerCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteSellerCommand deleteFirstSellerCommand = new DeleteSellerCommand(INDEX_FIRST_SELLER);
        DeleteSellerCommand deleteSecondSellerCommand = new DeleteSellerCommand(INDEX_SECOND_SELLER);

        // same object -> returns true
        assertTrue(deleteFirstSellerCommand.equals(deleteFirstSellerCommand));

        // same values -> returns true
        DeleteSellerCommand deleteFirstSellerCommandCopy = new DeleteSellerCommand(INDEX_FIRST_SELLER);
        assertTrue(deleteFirstSellerCommand.equals(deleteFirstSellerCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstSellerCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstSellerCommand.equals(null));

        // different client -> returns false
        assertFalse(deleteFirstSellerCommand.equals(deleteSecondSellerCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoSeller(Model model) {
        model.updateFilteredSellerList(p -> false);

        assertTrue(model.getFilteredSellerList().isEmpty());
    }
}
