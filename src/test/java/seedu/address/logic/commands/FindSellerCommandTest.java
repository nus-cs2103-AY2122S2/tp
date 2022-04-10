package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_SELLERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.SellerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.CARL;
import static seedu.address.testutil.TypicalClients.ELLE;
import static seedu.address.testutil.TypicalClients.FIONA;
import static seedu.address.testutil.TypicalSellers.getTypicalSellerAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.BuyerAddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.seller.SellerNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindSellerCommandTest {
    private Model model = new ModelManager(new UserPrefs(), getTypicalSellerAddressBook(),
            new BuyerAddressBook());
    private Model expectedModel = new ModelManager(new UserPrefs(),
            new SellerAddressBook(), new BuyerAddressBook());

    @Test
    public void equals() {
        SellerNameContainsKeywordsPredicate firstPredicate =
                new SellerNameContainsKeywordsPredicate(Collections.singletonList("first"));
        SellerNameContainsKeywordsPredicate secondPredicate =
                new SellerNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindSellerCommand findFirstCommand = new FindSellerCommand(firstPredicate);
        FindSellerCommand findSecondCommand = new FindSellerCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindSellerCommand findFirstCommandCopy = new FindSellerCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different seller -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noSellerFound() {
        String expectedMessage = String.format(MESSAGE_SELLERS_LISTED_OVERVIEW, 0);
        SellerNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindSellerCommand command = new FindSellerCommand(predicate);
        expectedModel.updateFilteredSellerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredSellerList());
    }

    @Test
    public void execute_multipleKeywords_multipleSellersFound() {
        String expectedMessage = String.format(MESSAGE_SELLERS_LISTED_OVERVIEW, 3);
        SellerNameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindSellerCommand command = new FindSellerCommand(predicate);
        expectedModel.updateFilteredSellerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredSellerList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private SellerNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new SellerNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
