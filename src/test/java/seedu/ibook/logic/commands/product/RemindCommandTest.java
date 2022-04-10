package seedu.ibook.logic.commands.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.ibook.commons.core.Messages.MESSAGE_PRODUCTS_FOUND_OVERVIEW;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ibook.testutil.TypicalItems.Q5_TODAY_KAYA;
import static seedu.ibook.testutil.TypicalItems.getAllItemsRemind;
import static seedu.ibook.testutil.TypicalItems.getOnlyExpiringItems;
import static seedu.ibook.testutil.TypicalItems.getOnlyNonExpiringItems;
import static seedu.ibook.testutil.TypicalProducts.CHOCOLATE_BREAD;
import static seedu.ibook.testutil.TypicalProducts.KAYA_BREAD;
import static seedu.ibook.testutil.TypicalProducts.PEANUT_BUTTER_BREAD;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBookWithCustomList;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.item.RemindCommand;
import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;
import seedu.ibook.model.item.ExpiryDate;
import seedu.ibook.model.product.Product;
import seedu.ibook.model.product.filters.ExpiringFilter;
import seedu.ibook.testutil.ProductBuilder;

public class RemindCommandTest {

    private final Product kayaBreadWithAllItems = new ProductBuilder(KAYA_BREAD).buildWithItems(getAllItemsRemind());
    private final Product peanutButterBreadWithAllExpiringToday =
            new ProductBuilder(PEANUT_BUTTER_BREAD).buildWithItems(getOnlyExpiringItems());
    private final Product chocolateBreadWithAllNonExpiringToday =
            new ProductBuilder(CHOCOLATE_BREAD).buildWithItems(getOnlyNonExpiringItems());
    private final List<Product> products = Arrays.asList(kayaBreadWithAllItems,
            peanutButterBreadWithAllExpiringToday, chocolateBreadWithAllNonExpiringToday);

    private final Model model = new ModelManager(getTypicalIBookWithCustomList(products), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalIBookWithCustomList(products), new UserPrefs());

    @Test
    public void execute_oneExpiringToday_oneFound() {
        String expectedMessage = RemindCommand.MESSAGE_SUCCESS + String.format(MESSAGE_PRODUCTS_FOUND_OVERVIEW, 2);
        String dateToday = LocalDate.now().toString();
        ExpiryDate date = new ExpiryDate(dateToday);
        ExpiringFilter expiringFilter = new ExpiringFilter(date);
        RemindCommand remindCommand = new RemindCommand(date);

        expectedModel.addProductFilter(expiringFilter);
        expectedModel.updateFilteredItemListForProducts(item -> item.expiresBefore(date));
        assertCommandSuccess(remindCommand, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredProductList(), Arrays.asList(KAYA_BREAD, PEANUT_BUTTER_BREAD));
        for (Product p: model.getFilteredProductList()) {
            if (p.equals(KAYA_BREAD)) {
                assertEquals(p.getFilteredItems().getInternalList(),
                        Arrays.asList(Q5_TODAY_KAYA.toItem(p)));
            }

            if (p.equals(PEANUT_BUTTER_BREAD)) {
                assertEquals(p.getFilteredItems().getInternalList(),
                        Arrays.asList(Q5_TODAY_KAYA.toItem(p)));
            }

            assertNotEquals(p, CHOCOLATE_BREAD);
        }
    }
}
