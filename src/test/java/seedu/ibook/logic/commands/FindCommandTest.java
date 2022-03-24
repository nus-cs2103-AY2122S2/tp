package seedu.ibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.ibook.commons.core.Messages.MESSAGE_PRODUCTS_LISTED_OVERVIEW;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ibook.testutil.TypicalProductFilters.NAME_FILTER_A;
import static seedu.ibook.testutil.TypicalProductFilters.NAME_FILTER_B;
import static seedu.ibook.testutil.TypicalProductFilters.NAME_FILTER_KAYA;
import static seedu.ibook.testutil.TypicalProducts.KAYA_BREAD;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBook;
import static seedu.ibook.testutil.TypicalProducts.getTypicalProducts;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {

    private final Model model = new ModelManager(getTypicalIBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalIBook(), new UserPrefs());

    @Test
    public void equals() {
        FindCommand findFirstCommand = new FindCommand(List.of(NAME_FILTER_A));
        FindCommand findSecondCommand = new FindCommand(List.of(NAME_FILTER_B));

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(List.of(NAME_FILTER_A));
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different person -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_noKeywords_allProductsFound() {
        String expectedMessage = String.format(MESSAGE_PRODUCTS_LISTED_OVERVIEW, 5);
        FindCommand command = new FindCommand();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalProducts(), model.getFilteredProductList());
    }

    @Test
    public void execute_oneKeyword_oneProductFound() {
        String expectedMessage = String.format(MESSAGE_PRODUCTS_LISTED_OVERVIEW, 1);
        FindCommand command = new FindCommand(List.of(NAME_FILTER_KAYA));
        expectedModel.addProductFilter(NAME_FILTER_KAYA);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(KAYA_BREAD), model.getFilteredProductList());
    }
}
