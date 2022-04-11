package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.BuyerCommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.BuyerCommandTestUtil.showBuyerAtIndex;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyerAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BUYER;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SELLER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BUYER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SELLER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditBuyerCommand.EditBuyerDescriptor;
//import seedu.address.model.AddressBook;
import seedu.address.model.BuyerAddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.Buyer;
import seedu.address.testutil.BuyerBuilder;
import seedu.address.testutil.EditBuyerDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditBuyerCommandTest {

    private Model model = new ModelManager(new UserPrefs(), new SellerAddressBook(),
            getTypicalBuyerAddressBook());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Buyer editedBuyer = new BuyerBuilder().build();
        EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder(editedBuyer).build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_FIRST_BUYER, descriptor);

        String expectedMessage = String.format(EditBuyerCommand.MESSAGE_EDIT_BUYER_SUCCESS, editedBuyer);
        System.out.println(expectedMessage);
        Model expectedModel = new ModelManager(new UserPrefs(),
                new SellerAddressBook(), new BuyerAddressBook(model.getBuyerAddressBook()));
        expectedModel.setBuyer(model.getFilteredBuyerList().get(0), editedBuyer);
        assertCommandSuccess(editBuyerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastBuyer = Index.fromOneBased(model.getFilteredBuyerList().size());
        Buyer lastBuyer = model.getFilteredBuyerList().get(indexLastBuyer.getZeroBased());

        BuyerBuilder buyerInList = new BuyerBuilder(lastBuyer);
        Buyer editedBuyer = buyerInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(indexLastBuyer, descriptor);

        String expectedMessage = String.format(EditBuyerCommand.MESSAGE_EDIT_BUYER_SUCCESS, editedBuyer);

        Model expectedModel = new ModelManager(new UserPrefs(),
                new SellerAddressBook(), new BuyerAddressBook(model.getBuyerAddressBook()));
        expectedModel.setBuyer(lastBuyer, editedBuyer);

        assertCommandSuccess(editBuyerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_FIRST_BUYER, new EditBuyerDescriptor());
        Buyer editedBuyer = model.getFilteredBuyerList().get(INDEX_FIRST_BUYER.getZeroBased());

        String expectedMessage = String.format(EditBuyerCommand.MESSAGE_EDIT_BUYER_SUCCESS, editedBuyer);

        Model expectedModel = new ModelManager(new UserPrefs(),
                new SellerAddressBook(), new BuyerAddressBook(model.getBuyerAddressBook()));

        assertCommandSuccess(editBuyerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showBuyerAtIndex(model, INDEX_FIRST_BUYER);

        Buyer buyerInFilteredList = model.getFilteredBuyerList().get(INDEX_FIRST_BUYER.getZeroBased());
        Buyer editedBuyer = new BuyerBuilder(buyerInFilteredList).withName(VALID_NAME_BOB).build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_FIRST_SELLER,
                new EditBuyerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditBuyerCommand.MESSAGE_EDIT_BUYER_SUCCESS, editedBuyer);

        Model expectedModel = new ModelManager(new UserPrefs(),
                new SellerAddressBook(), new BuyerAddressBook(model.getBuyerAddressBook()));
        expectedModel.setBuyer(model.getFilteredBuyerList().get(0), editedBuyer);

        assertCommandSuccess(editBuyerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateBuyerUnfilteredList_failure() {
        Buyer firstBuyer = model.getFilteredBuyerList().get(INDEX_FIRST_BUYER.getZeroBased());
        EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder(firstBuyer).build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_SECOND_BUYER, descriptor);

        assertCommandFailure(editBuyerCommand, model, EditBuyerCommand.MESSAGE_DUPLICATE_BUYER);
    }

    @Test
    public void execute_duplicateBuyerFilteredList_failure() {
        showBuyerAtIndex(model, INDEX_FIRST_BUYER);

        // edit seller in filtered list into a duplicate in address book
        Buyer buyerInList = model.getBuyerAddressBook().getBuyerList().get(INDEX_SECOND_SELLER.getZeroBased());
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_FIRST_BUYER,
                new EditBuyerDescriptorBuilder(buyerInList).build());

        assertCommandFailure(editBuyerCommand, model, EditBuyerCommand.MESSAGE_DUPLICATE_BUYER);
    }

    @Test
    public void execute_invalidBuyerIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBuyerList().size() + 1);
        EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editBuyerCommand, model, Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidBuyerIndexFilteredList_failure() {
        showBuyerAtIndex(model, INDEX_FIRST_BUYER);
        Index outOfBoundIndex = INDEX_SECOND_BUYER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBuyerAddressBook().getBuyerList().size());

        EditBuyerCommand editSellerCommand = new EditBuyerCommand(outOfBoundIndex,
                new EditBuyerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editSellerCommand, model, Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditBuyerCommand standardCommand = new EditBuyerCommand(INDEX_FIRST_SELLER, DESC_AMY);

        // same values -> returns true
        EditBuyerDescriptor copyDescriptor = new EditBuyerDescriptor(DESC_AMY);
        EditBuyerCommand commandWithSameValues = new EditBuyerCommand(INDEX_FIRST_SELLER, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditBuyerCommand(INDEX_SECOND_SELLER, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditBuyerCommand(INDEX_FIRST_SELLER, DESC_BOB)));
    }

}
