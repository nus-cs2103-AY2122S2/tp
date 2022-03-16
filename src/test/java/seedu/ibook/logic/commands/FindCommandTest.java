package seedu.ibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.commons.core.Messages.MESSAGE_PRODUCTS_LISTED_OVERVIEW;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_CATEGORY_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_NAME_A;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ibook.testutil.TypicalProducts.KAYA_BREAD;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_B;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.ExpiryDate;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.Product;
import seedu.ibook.model.product.ProductFulfillsFiltersPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {

    private Model model = new ModelManager(getTypicalIBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalIBook(), new UserPrefs());

    private ProductFulfillsFiltersPredicate firstPredicate =
            new ProductFulfillsFiltersPredicate(PRODUCT_A);
    private ProductFulfillsFiltersPredicate secondPredicate =
            new ProductFulfillsFiltersPredicate(PRODUCT_B);

    private ProductFulfillsFiltersPredicate thirdPredicate =
            new ProductFulfillsFiltersPredicate(new Product(
                    new Name(VALID_NAME_A),
                    new Category(VALID_CATEGORY_A),
                    new ExpiryDate(VALID_EXPIRY_DATE_A),
                    new Description(VALID_DESCRIPTION_A),
                    Price.WILD_PRICE));

    private ProductFulfillsFiltersPredicate kayaPredicate =
            new ProductFulfillsFiltersPredicate(KAYA_BREAD);

    @Test
    public void equals() {


        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_noKeywords_noProductFound() {
        String expectedMessage = String.format(MESSAGE_PRODUCTS_LISTED_OVERVIEW, 0);
        ProductFulfillsFiltersPredicate predicate = firstPredicate;
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredProductList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredProductList());
    }

    @Test
    public void execute_oneKeyword_oneProductFound() {
        String expectedMessage = String.format(MESSAGE_PRODUCTS_LISTED_OVERVIEW, 1);
        ProductFulfillsFiltersPredicate predicate = kayaPredicate;
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredProductList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(KAYA_BREAD), model.getFilteredProductList());
    }

    /*

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.

    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

     */
}
