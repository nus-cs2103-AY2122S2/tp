package seedu.ibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.ibook.commons.core.Messages.MESSAGE_PRODUCTS_FOUND_OVERVIEW;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ibook.testutil.TypicalProductFilters.CATEGORY_FILTER_BREAD;
import static seedu.ibook.testutil.TypicalProductFilters.NAME_FILTER_A;
import static seedu.ibook.testutil.TypicalProductFilters.NAME_FILTER_B;
import static seedu.ibook.testutil.TypicalProductFilters.NAME_FILTER_KAYA;
import static seedu.ibook.testutil.TypicalProducts.KAYA_BREAD;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBook;
import static seedu.ibook.testutil.TypicalProducts.getTypicalProducts;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.product.FindCommand;
import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.filters.CategoryFilter;
import seedu.ibook.model.product.filters.NameFilter;


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
        String expectedMessage = String.format(MESSAGE_PRODUCTS_FOUND_OVERVIEW, 5);
        FindCommand command = new FindCommand();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalProducts(), model.getFilteredProductList());
    }

    @Test
    public void execute_oneKeyword_oneProductFound() {
        String expectedMessage = String.format(MESSAGE_PRODUCTS_FOUND_OVERVIEW, 1);
        FindCommand command = new FindCommand(List.of(NAME_FILTER_KAYA));
        expectedModel.addProductFilter(NAME_FILTER_KAYA);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(KAYA_BREAD), model.getFilteredProductList());
    }

    @Test
    public void execute_differentCasesName_productFound() {
        String expectedMessage = String.format(MESSAGE_PRODUCTS_FOUND_OVERVIEW, 1);
        expectedModel.addProductFilter(NAME_FILTER_KAYA);

        NameFilter upperNameFilter = new NameFilter(new Name("KAYA BREAD"));
        FindCommand upperNameCommand = new FindCommand(List.of(upperNameFilter));
        assertCommandSuccess(upperNameCommand, model, expectedMessage, expectedModel);

        NameFilter lowerNameFilter = new NameFilter(new Name("kaya bread"));
        FindCommand lowerNameCommand = new FindCommand(List.of(lowerNameFilter));
        assertCommandSuccess(lowerNameCommand, model, expectedMessage, expectedModel);

        NameFilter randomNameFilter = new NameFilter(new Name("kAYa BrEAd"));
        FindCommand randomNameCommand = new FindCommand(List.of(randomNameFilter));
        assertCommandSuccess(randomNameCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_differentCasesCategory_productFound() {
        String expectedMessage = String.format(MESSAGE_PRODUCTS_FOUND_OVERVIEW, 3);
        expectedModel.addProductFilter(CATEGORY_FILTER_BREAD);

        CategoryFilter upperCategoryFilter = new CategoryFilter(new Category("BREAD"));
        FindCommand upperCategoryCommand = new FindCommand(List.of(upperCategoryFilter));
        assertCommandSuccess(upperCategoryCommand, model, expectedMessage, expectedModel);

        CategoryFilter lowerCategoryFilter = new CategoryFilter(new Category("bread"));
        FindCommand lowerCategoryCommand = new FindCommand(List.of(lowerCategoryFilter));
        assertCommandSuccess(lowerCategoryCommand, model, expectedMessage, expectedModel);

        CategoryFilter randomCategoryFilter = new CategoryFilter(new Category("BrEAd"));
        FindCommand randomCategoryCommand = new FindCommand(List.of(randomCategoryFilter));
        assertCommandSuccess(randomCategoryCommand, model, expectedMessage, expectedModel);
    }
}
