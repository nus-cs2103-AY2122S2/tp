package seedu.ibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.ibook.commons.core.Messages.MESSAGE_PRODUCTS_LISTED_OVERVIEW;
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
import seedu.ibook.model.product.filters.ProductFulfillsFiltersPredicate;


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

    private ProductFulfillsFiltersPredicate kayaPredicate =
            new ProductFulfillsFiltersPredicate(KAYA_BREAD);

    @Test
    public void equals() {


        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different person -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_noKeywords_noProductFound() {
        String expectedMessage = String.format(MESSAGE_PRODUCTS_LISTED_OVERVIEW, 0);
        ProductFulfillsFiltersPredicate predicate = firstPredicate;
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateProductFilters(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredProductList());
    }

    @Test
    public void execute_oneKeyword_oneProductFound() {
        String expectedMessage = String.format(MESSAGE_PRODUCTS_LISTED_OVERVIEW, 1);
        ProductFulfillsFiltersPredicate predicate = kayaPredicate;
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateProductFilters(predicate);
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
