package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_SELLERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.SellerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSellers.getTypicalSellerAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.BuyerAddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
//import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.seller.SellerHouseTypeContainsKeywordsPredicate;
import seedu.address.model.seller.SellerLocationContainsKeywordsPredicate;
import seedu.address.model.seller.SellerNameContainsKeywordsPredicate;
import seedu.address.model.seller.SellerPhoneContainsKeywordsPredicate;
import seedu.address.testutil.TypicalSellers;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindSellerCommandTest {
    private Model model = new ModelManager(new UserPrefs(), getTypicalSellerAddressBook(),
            new BuyerAddressBook());
    private Model expectedModel = new ModelManager(new UserPrefs(),
        getTypicalSellerAddressBook(), new BuyerAddressBook());

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
        SellerCommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(
                TypicalSellers.CARL, TypicalSellers.ELLE, TypicalSellers.FIONA), model.getFilteredSellerList());
    }

    @Test
    public void execute_onePhoneKeyword_multipleSellersFound() {
        String expectedMessage = String.format(MESSAGE_SELLERS_LISTED_OVERVIEW, 3);
        SellerPhoneContainsKeywordsPredicate predicate1 = preparePhonePredicate("948");
        FindSellerCommand command1 = new FindSellerCommand(predicate1);
        expectedModel.updateFilteredSellerList(predicate1);
        SellerCommandTestUtil.assertCommandSuccess(command1, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(
                TypicalSellers.ELLE, TypicalSellers.FIONA, TypicalSellers.GEORGE), model.getFilteredSellerList());
    }

    @Test
    public void execute_oneLocationKeyword_multipleSellersFound() {
        String expectedMessage1 = String.format(MESSAGE_SELLERS_LISTED_OVERVIEW, 1);
        SellerLocationContainsKeywordsPredicate predicate2 = prepareLocationPredicate("clemen");
        FindSellerCommand command2 = new FindSellerCommand(predicate2);
        expectedModel.updateFilteredSellerList(predicate2);
        SellerCommandTestUtil.assertCommandSuccess(command2, model, expectedMessage1, expectedModel);
        assertEquals(Arrays.asList(TypicalSellers.CARL), model.getFilteredSellerList());
    }

    @Test
    public void execute_oneHouseTypeKeyword_multipleSellersFound() {
        String expectedMessage2 = String.format(MESSAGE_SELLERS_LISTED_OVERVIEW, 1);
        SellerHouseTypeContainsKeywordsPredicate predicate3 = prepareHouseTypePredicate("HDB");
        FindSellerCommand command3 = new FindSellerCommand(predicate3);
        expectedModel.updateFilteredSellerList(predicate3);
        //SellerCommandTestUtil.assertCommandSuccess(command3, model, expectedMessage2, expectedModel);
        //assertEquals(Arrays.asList(TypicalSellers.BENSON), model.getFilteredSellerList());
    }


    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private SellerNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new SellerNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private SellerLocationContainsKeywordsPredicate prepareLocationPredicate(String userInput) {
        return new SellerLocationContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private SellerHouseTypeContainsKeywordsPredicate prepareHouseTypePredicate(String userInput) {
        return new SellerHouseTypeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private SellerPhoneContainsKeywordsPredicate preparePhonePredicate(String userInput) {
        return new SellerPhoneContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}
