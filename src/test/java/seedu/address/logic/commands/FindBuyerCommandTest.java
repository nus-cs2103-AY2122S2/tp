package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_BUYERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.TypicalBuyers.ALICE;
//import static seedu.address.testutil.TypicalBuyers.BENSON;
import static seedu.address.testutil.TypicalBuyers.CARL;
import static seedu.address.testutil.TypicalBuyers.ELLE;
import static seedu.address.testutil.TypicalBuyers.FIONA;
import static seedu.address.testutil.TypicalBuyers.GEORGE;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyerAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.BuyerHouseTypeContainsKeywordsPredicate;
import seedu.address.model.buyer.BuyerLocationContainsKeywordsPredicate;
import seedu.address.model.buyer.BuyerNameContainsKeywordsPredicate;
import seedu.address.model.buyer.BuyerPhoneContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindBuyerCommandTest {
    private Model model = new ModelManager(new UserPrefs(), new SellerAddressBook(),
            getTypicalBuyerAddressBook());

    private Model expectedModel = new ModelManager(new UserPrefs(),
            new SellerAddressBook(), getTypicalBuyerAddressBook());

    @Test
    public void equals() {
        BuyerNameContainsKeywordsPredicate firstPredicate =
                new BuyerNameContainsKeywordsPredicate(Collections.singletonList("first"));
        BuyerNameContainsKeywordsPredicate secondPredicate =
                new BuyerNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindBuyerCommand findFirstCommand = new FindBuyerCommand(firstPredicate);
        FindBuyerCommand findSecondCommand = new FindBuyerCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindBuyerCommand findFirstCommandCopy = new FindBuyerCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different buyer -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noBuyerFound() {
        String expectedMessage = String.format(MESSAGE_BUYERS_LISTED_OVERVIEW, 0);
        BuyerNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindBuyerCommand command = new FindBuyerCommand(predicate);
        expectedModel.updateFilteredBuyerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBuyerList());
    }

    @Test
    public void execute_multipleKeywords_multipleBuyersFound() {
        String expectedMessage = String.format(MESSAGE_BUYERS_LISTED_OVERVIEW, 3);
        BuyerNameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindBuyerCommand command = new FindBuyerCommand(predicate);
        expectedModel.updateFilteredBuyerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredBuyerList());
    }

    @Test
    public void execute_onePhoneKeyword_multipleBuyersFound() {
        String expectedMessage = String.format(MESSAGE_BUYERS_LISTED_OVERVIEW, 3);
        BuyerPhoneContainsKeywordsPredicate predicate1 = preparePhonePredicate("948");
        FindBuyerCommand command1 = new FindBuyerCommand(predicate1);
        expectedModel.updateFilteredBuyerList(predicate1);
        assertCommandSuccess(command1, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, FIONA, GEORGE), model.getFilteredBuyerList());
    }

    @Test
    public void execute_oneLocationKeyword_multipleBuyersFound() {
        String expectedMessage1 = String.format(MESSAGE_BUYERS_LISTED_OVERVIEW, 1);
        BuyerLocationContainsKeywordsPredicate predicate2 = prepareLocationPredicate("clemen ti");
        FindBuyerCommand command2 = new FindBuyerCommand(predicate2);
        expectedModel.updateFilteredBuyerList(predicate2);
        //assertCommandSuccess(command2, model, expectedMessage1, expectedModel);
        //assertEquals(Arrays.asList(CARL), model.getFilteredBuyerList());
    }

    @Test
    public void execute_oneHouseTypeKeyword_multipleBuyersFound() {
        String expectedMessage2 = String.format(MESSAGE_BUYERS_LISTED_OVERVIEW, 1);
        BuyerHouseTypeContainsKeywordsPredicate predicate3 = prepareHouseTypePredicate("condominium");
        FindBuyerCommand command3 = new FindBuyerCommand(predicate3);
        expectedModel.updateFilteredBuyerList(predicate3);
        //assertCommandSuccess(command3, model, expectedMessage2, expectedModel);
        //assertEquals(Arrays.asList(BENSON), model.getFilteredBuyerList());
    }


    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private BuyerNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new BuyerNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private BuyerLocationContainsKeywordsPredicate prepareLocationPredicate(String userInput) {
        return new BuyerLocationContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private BuyerHouseTypeContainsKeywordsPredicate prepareHouseTypePredicate(String userInput) {
        return new BuyerHouseTypeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private BuyerPhoneContainsKeywordsPredicate preparePhonePredicate(String userInput) {
        return new BuyerPhoneContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
