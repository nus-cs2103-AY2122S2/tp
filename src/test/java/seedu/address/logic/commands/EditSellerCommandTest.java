package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.SellerCommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.SellerCommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.SellerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.SellerCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SellerCommandTestUtil.assertSellerCommandFailure;
import static seedu.address.logic.commands.SellerCommandTestUtil.showSellerAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SELLER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SELLER;
import static seedu.address.testutil.TypicalSellers.getTypicalSellerAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditSellerCommand.EditSellerDescriptor;
//import seedu.address.model.AddressBook;
import seedu.address.model.BuyerAddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.seller.Seller;
import seedu.address.testutil.EditSellerDescriptorBuilder;
import seedu.address.testutil.SellerBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditSellerCommandTest {

    private Model model = new ModelManager(new UserPrefs(), getTypicalSellerAddressBook(),
            new BuyerAddressBook());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Seller editedSeller = new SellerBuilder().build();
        EditSellerDescriptor descriptor = new EditSellerDescriptorBuilder(editedSeller).build();
        EditSellerCommand editSellerCommand = new EditSellerCommand(INDEX_FIRST_SELLER, descriptor);

        String expectedMessage = String.format(EditSellerCommand.MESSAGE_EDIT_SELLER_SUCCESS, editedSeller);
        System.out.println(expectedMessage);
        Model expectedModel = new ModelManager(new UserPrefs(),
                new SellerAddressBook(model.getSellerAddressBook()), new BuyerAddressBook());
        expectedModel.setSeller(model.getFilteredSellerList().get(0), editedSeller);
        assertCommandSuccess(editSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastSeller = Index.fromOneBased(model.getFilteredSellerList().size());
        Seller lastSeller = model.getFilteredSellerList().get(indexLastSeller.getZeroBased());

        SellerBuilder sellerInList = new SellerBuilder(lastSeller);
        Seller editedSeller = sellerInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditSellerDescriptor descriptor = new EditSellerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditSellerCommand editCommand = new EditSellerCommand(indexLastSeller, descriptor);

        String expectedMessage = String.format(EditSellerCommand.MESSAGE_EDIT_SELLER_SUCCESS, editedSeller);

        Model expectedModel = new ModelManager(new UserPrefs(),
                new SellerAddressBook(model.getSellerAddressBook()), new BuyerAddressBook());
        expectedModel.setSeller(lastSeller, editedSeller);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditSellerCommand editSellerCommand = new EditSellerCommand(INDEX_FIRST_SELLER, new EditSellerDescriptor());
        Seller editedSeller = model.getFilteredSellerList().get(INDEX_FIRST_SELLER.getZeroBased());

        String expectedMessage = String.format(EditSellerCommand.MESSAGE_EDIT_SELLER_SUCCESS, editedSeller);

        Model expectedModel = new ModelManager(new UserPrefs(),
                new SellerAddressBook(model.getSellerAddressBook()), new BuyerAddressBook());

        assertCommandSuccess(editSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showSellerAtIndex(model, INDEX_FIRST_SELLER);

        Seller sellerInFilteredList = model.getFilteredSellerList().get(INDEX_FIRST_SELLER.getZeroBased());
        Seller editedSeller = new SellerBuilder(sellerInFilteredList).withName(VALID_NAME_BOB).build();
        EditSellerCommand editSellerCommand = new EditSellerCommand(INDEX_FIRST_SELLER,
                new EditSellerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditSellerCommand.MESSAGE_EDIT_SELLER_SUCCESS, editedSeller);

        Model expectedModel = new ModelManager(new UserPrefs(),
                new SellerAddressBook(model.getSellerAddressBook()), new BuyerAddressBook());
        expectedModel.setSeller(model.getFilteredSellerList().get(0), editedSeller);

        assertCommandSuccess(editSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateSellerUnfilteredList_failure() {
        Seller firstSeller = model.getFilteredSellerList().get(INDEX_FIRST_SELLER.getZeroBased());
        EditSellerDescriptor descriptor = new EditSellerDescriptorBuilder(firstSeller).build();
        EditSellerCommand editSellerCommand = new EditSellerCommand(INDEX_SECOND_SELLER, descriptor);

        assertSellerCommandFailure(editSellerCommand, model, EditSellerCommand.MESSAGE_DUPLICATE_SELLER);
    }

    @Test
    public void execute_duplicateSellerFilteredList_failure() {
        showSellerAtIndex(model, INDEX_FIRST_SELLER);

        // edit seller in filtered list into a duplicate in address book
        Seller sellerInList = model.getSellerAddressBook().getSellerList().get(INDEX_SECOND_SELLER.getZeroBased());
        EditSellerCommand editSellerCommand = new EditSellerCommand(INDEX_FIRST_SELLER,
                new EditSellerDescriptorBuilder(sellerInList).build());

        assertSellerCommandFailure(editSellerCommand, model, EditSellerCommand.MESSAGE_DUPLICATE_SELLER);
    }

    @Test
    public void execute_invalidSellerIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredSellerList().size() + 1);
        EditSellerDescriptor descriptor = new EditSellerDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditSellerCommand editSellerCommand = new EditSellerCommand(outOfBoundIndex, descriptor);

        assertSellerCommandFailure(editSellerCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidSellerIndexFilteredList_failure() {
        showSellerAtIndex(model, INDEX_FIRST_SELLER);
        Index outOfBoundIndex = INDEX_SECOND_SELLER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSellerAddressBook().getSellerList().size());

        EditSellerCommand editSellerCommand = new EditSellerCommand(outOfBoundIndex,
                new EditSellerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editSellerCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditSellerCommand standardCommand = new EditSellerCommand(INDEX_FIRST_SELLER, DESC_AMY);

        // same values -> returns true
        EditSellerDescriptor copyDescriptor = new EditSellerDescriptor(DESC_AMY);
        EditSellerCommand commandWithSameValues = new EditSellerCommand(INDEX_FIRST_SELLER, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditSellerCommand(INDEX_SECOND_SELLER, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditSellerCommand(INDEX_FIRST_SELLER, DESC_BOB)));
    }

}
