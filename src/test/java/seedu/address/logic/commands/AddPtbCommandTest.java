package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.BuyerCommandTestUtil.showBuyerAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BUYER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BUYER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SEVENTH_BUYER;
import static seedu.address.testutil.TypicalPropertyToBuy.PROPERTY_TO_BUY_FIVE;
import static seedu.address.testutil.TypicalPropertyToBuy.PROPERTY_TO_BUY_ONE;
import static seedu.address.testutil.TypicalPropertyToBuy.PROPERTY_TO_BUY_TWO;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.property.PropertyToBuy;
import seedu.address.testutil.BuyerBuilder;
import seedu.address.testutil.PropertyToBuyBuilder;
import seedu.address.testutil.TypicalBuyers;
//import seedu.address.testutil.TypicalClients;

public class AddPtbCommandTest {

    private Model model = new ModelManager(new UserPrefs(),
            new SellerAddressBook(), TypicalBuyers.getTypicalBuyerAddressBook());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        PropertyToBuy testProperty = new PropertyToBuyBuilder(PROPERTY_TO_BUY_ONE).build();
        AddPropertyToBuyCommand ptbCommand = new AddPropertyToBuyCommand(INDEX_SEVENTH_BUYER, testProperty);
        Buyer testBuyer = model.getFilteredBuyerList().get(INDEX_SEVENTH_BUYER.getZeroBased());
        String expectedMessage = String.format(AddPropertyToBuyCommand.MESSAGE_SUCCESS,
                new BuyerBuilder(testBuyer).withProperty(testProperty).build());
        ModelManager expectedModel = new ModelManager(new UserPrefs(), new SellerAddressBook(),
                TypicalBuyers.getTypicalBuyerAddressBook());
        expectedModel.setBuyer(testBuyer, new BuyerBuilder(testBuyer).withProperty(testProperty).build());
        assertCommandSuccess(ptbCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_buyerAlreadyHasProperty_failure() {
        PropertyToBuy testProperty = new PropertyToBuyBuilder(PROPERTY_TO_BUY_ONE).build();
        AddPropertyToBuyCommand ptbCommand = new AddPropertyToBuyCommand(INDEX_FIRST_BUYER, testProperty);
        Buyer testBuyer = model.getFilteredBuyerList().get(INDEX_FIRST_BUYER.getZeroBased());
        model.setBuyer(testBuyer, new BuyerBuilder(testBuyer).withProperty(PROPERTY_TO_BUY_ONE).build());

        assertCommandFailure(ptbCommand, model, AddPropertyToBuyCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBuyerList().size() + 1);
        PropertyToBuy testProperty = new PropertyToBuyBuilder(PROPERTY_TO_BUY_ONE).build();
        AddPropertyToBuyCommand ptbCommand = new AddPropertyToBuyCommand(outOfBoundIndex, testProperty);

        assertCommandFailure(ptbCommand, model, Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showBuyerAtIndex(model, INDEX_SEVENTH_BUYER);

        PropertyToBuy testProperty = new PropertyToBuyBuilder(PROPERTY_TO_BUY_TWO).build();
        Buyer testBuyer = model.getFilteredBuyerList().get(INDEX_FIRST_BUYER.getZeroBased());
        AddPropertyToBuyCommand ptbCommand = new AddPropertyToBuyCommand(INDEX_FIRST_BUYER, testProperty);

        String expectedMessage = String.format(AddPropertyToBuyCommand.MESSAGE_SUCCESS,
                new BuyerBuilder(testBuyer).withProperty(testProperty).build());

        Model expectedModel = new ModelManager(new UserPrefs(), new SellerAddressBook(),
                TypicalBuyers.getTypicalBuyerAddressBook());
        expectedModel.setBuyer(testBuyer, new BuyerBuilder(testBuyer).withProperty(testProperty).build());
        assertCommandSuccess(ptbCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBuyerAtIndex(model, INDEX_FIRST_BUYER);

        PropertyToBuy testProperty = new PropertyToBuyBuilder(PROPERTY_TO_BUY_FIVE).build();
        Index outOfBoundIndex = INDEX_SECOND_BUYER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBuyerAddressBook().getBuyerList().size());

        AddPropertyToBuyCommand ptbCommand = new AddPropertyToBuyCommand(outOfBoundIndex, testProperty);

        assertCommandFailure(ptbCommand, model, Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        PropertyToBuy propertyOne = new PropertyToBuyBuilder(PROPERTY_TO_BUY_ONE).build();
        PropertyToBuy propertyTwo = new PropertyToBuyBuilder(PROPERTY_TO_BUY_TWO).build();

        AddPropertyToBuyCommand addPropertyCommandOne = new AddPropertyToBuyCommand(INDEX_FIRST_BUYER, propertyOne);
        AddPropertyToBuyCommand addPropertyCommandTwo = new AddPropertyToBuyCommand(INDEX_FIRST_BUYER, propertyTwo);
        AddPropertyToBuyCommand addPropertyCommandThree = new AddPropertyToBuyCommand(INDEX_SECOND_BUYER, propertyTwo);

        // same object -> returns true
        assertEquals(addPropertyCommandOne, addPropertyCommandOne);

        // same values -> returns true
        AddPropertyToBuyCommand commandCopy = new AddPropertyToBuyCommand(INDEX_FIRST_BUYER, propertyOne);
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
