package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_BUYERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalBuyers.CARL;
import static seedu.address.testutil.TypicalBuyers.ELLE;
import static seedu.address.testutil.TypicalBuyers.FIONA;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyerAddressBook;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.BuyerAddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.BuyerNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindBuyerCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new SellerAddressBook(),
            getTypicalBuyerAddressBook());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            new SellerAddressBook(), new BuyerAddressBook());

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

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private BuyerNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new BuyerNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
