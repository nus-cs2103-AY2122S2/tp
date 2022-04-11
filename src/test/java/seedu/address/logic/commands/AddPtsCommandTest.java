package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.SellerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.SellerCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SellerCommandTestUtil.showSellerAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SELLER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SELLER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SEVENTH_SELLER;
import static seedu.address.testutil.TypicalPropertyToSell.PROPERTY_TO_SELL_FIVE;
import static seedu.address.testutil.TypicalPropertyToSell.PROPERTY_TO_SELL_ONE;
import static seedu.address.testutil.TypicalPropertyToSell.PROPERTY_TO_SELL_TWO;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.BuyerAddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.property.NullPropertyToSell;
import seedu.address.model.property.PropertyToSell;
import seedu.address.model.seller.Seller;
import seedu.address.testutil.PropertyToSellBuilder;
import seedu.address.testutil.SellerBuilder;
//import seedu.address.testutil.TypicalClients;
import seedu.address.testutil.TypicalSellers;

public class AddPtsCommandTest {

    private Model model = new ModelManager(new UserPrefs(),
            TypicalSellers.getTypicalSellerAddressBook(), new BuyerAddressBook());

    private PropertyToSell nullProperty = NullPropertyToSell.getNullPropertyToSell();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        PropertyToSell testProperty = new PropertyToSellBuilder(PROPERTY_TO_SELL_ONE).build();

        Seller testSeller = model.getFilteredSellerList().get(INDEX_SEVENTH_SELLER.getZeroBased());
        model.setSeller(testSeller, new SellerBuilder(testSeller).withProperty(nullProperty).build());
        AddPropertyToSellCommand ptsCommand = new AddPropertyToSellCommand(INDEX_SEVENTH_SELLER, testProperty);
        String expectedMessage = String.format(AddPropertyToSellCommand.MESSAGE_SUCCESS,
                new SellerBuilder(testSeller).withProperty(testProperty).build());
        ModelManager expectedModel = new ModelManager(new UserPrefs(),
                TypicalSellers.getTypicalSellerAddressBook(), new BuyerAddressBook());
        expectedModel.setSeller(testSeller, new SellerBuilder(testSeller).withProperty(testProperty).build());
        assertCommandSuccess(ptsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sellerAlreadyHasProperty_failure() {
        PropertyToSell testProperty = new PropertyToSellBuilder(PROPERTY_TO_SELL_ONE).build();
        AddPropertyToSellCommand ptbCommand = new AddPropertyToSellCommand(INDEX_FIRST_SELLER, testProperty);
        Seller testSeller = model.getFilteredSellerList().get(INDEX_FIRST_SELLER.getZeroBased());
        model.setSeller(testSeller, new SellerBuilder(testSeller).withProperty(PROPERTY_TO_SELL_ONE).build());

        assertCommandFailure(ptbCommand, model, AddPropertyToSellCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredSellerList().size() + 1);
        PropertyToSell testProperty = new PropertyToSellBuilder(PROPERTY_TO_SELL_ONE).build();
        AddPropertyToSellCommand ptbCommand = new AddPropertyToSellCommand(outOfBoundIndex, testProperty);

        assertCommandFailure(ptbCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showSellerAtIndex(model, INDEX_SEVENTH_SELLER);

        PropertyToSell testProperty = new PropertyToSellBuilder(PROPERTY_TO_SELL_TWO).build();
        Seller testSeller = model.getFilteredSellerList().get(INDEX_FIRST_SELLER.getZeroBased());
        model.setSeller(testSeller, new SellerBuilder(testSeller).withProperty(nullProperty).build());
        AddPropertyToSellCommand ptsCommand = new AddPropertyToSellCommand(INDEX_FIRST_SELLER, testProperty);

        String expectedMessage = String.format(AddPropertyToSellCommand.MESSAGE_SUCCESS,
                new SellerBuilder(testSeller).withProperty(testProperty).build());

        Model expectedModel = new ModelManager(new UserPrefs(),
                TypicalSellers.getTypicalSellerAddressBook(), new BuyerAddressBook());
        expectedModel.setSeller(testSeller, new SellerBuilder(testSeller).withProperty(testProperty).build());
        assertCommandSuccess(ptsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showSellerAtIndex(model, INDEX_FIRST_SELLER);

        PropertyToSell testProperty = new PropertyToSellBuilder(PROPERTY_TO_SELL_FIVE).build();
        Index outOfBoundIndex = INDEX_SECOND_SELLER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSellerAddressBook().getSellerList().size());

        AddPropertyToSellCommand ptbCommand = new AddPropertyToSellCommand(outOfBoundIndex, testProperty);

        assertCommandFailure(ptbCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        PropertyToSell propertyOne = new PropertyToSellBuilder(PROPERTY_TO_SELL_ONE).build();
        PropertyToSell propertyTwo = new PropertyToSellBuilder(PROPERTY_TO_SELL_TWO).build();

        AddPropertyToSellCommand addPropertyCommandOne = new AddPropertyToSellCommand(INDEX_FIRST_SELLER, propertyOne);
        AddPropertyToSellCommand addPropertyCommandTwo = new AddPropertyToSellCommand(INDEX_FIRST_SELLER, propertyTwo);
        AddPropertyToSellCommand addPropertyCommandThree = new AddPropertyToSellCommand(INDEX_SECOND_SELLER,
                propertyTwo);

        // same object -> returns true
        assertEquals(addPropertyCommandOne, addPropertyCommandOne);

        // same values -> returns true
        AddPropertyToSellCommand commandCopy = new AddPropertyToSellCommand(INDEX_FIRST_SELLER, propertyOne);
        assertEquals(commandCopy, addPropertyCommandOne);

        // different types -> returns false
        assertNotEquals(1, addPropertyCommandOne);

        // null -> returns false
        assertNotEquals(null, addPropertyCommandOne);

        // different properties && same index -> returns false
        assertNotEquals(addPropertyCommandOne, addPropertyCommandTwo);

        // different property and diff index -> returns false
        assertNotEquals(addPropertyCommandOne, addPropertyCommandThree);

        // different property and same index -> returns false
        assertNotEquals(addPropertyCommandTwo, addPropertyCommandThree);
    }
}
