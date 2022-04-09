package seedu.ibook.logic.commands.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ibook.testutil.TypicalItems.Q5_2020_01_01_KAYA;
import static seedu.ibook.testutil.TypicalItems.Q5_2022_03_01_KAYA;
import static seedu.ibook.testutil.TypicalItems.getOnlyExpiredItems;
import static seedu.ibook.testutil.TypicalItems.getOnlyNonExpiredItems;
import static seedu.ibook.testutil.TypicalItems.getTypicalItems;
import static seedu.ibook.testutil.TypicalProducts.CHOCOLATE_BREAD;
import static seedu.ibook.testutil.TypicalProducts.KAYA_BREAD;
import static seedu.ibook.testutil.TypicalProducts.PEANUT_BUTTER_BREAD;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBookWithCustomList;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.item.ExpiredCommand;
import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;
import seedu.ibook.testutil.ProductBuilder;

public class ExpiredCommandTest {
    private final Product kayaBreadWithAllItems = new ProductBuilder(KAYA_BREAD).buildWithItems(getTypicalItems());
    private final Product peanutButterBreadWithAllExpired =
            new ProductBuilder(PEANUT_BUTTER_BREAD).buildWithItems(getOnlyExpiredItems());
    private final Product chocolateBreadWithAllNotExpired =
            new ProductBuilder(CHOCOLATE_BREAD).buildWithItems(getOnlyNonExpiredItems());
    private final List<Product> products = Arrays.asList(kayaBreadWithAllItems,
            peanutButterBreadWithAllExpired, chocolateBreadWithAllNotExpired);

    private final Model model = new ModelManager(getTypicalIBookWithCustomList(products), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalIBookWithCustomList(products), new UserPrefs());

    private final ExpiredCommand expiredCommand = new ExpiredCommand();

    @Test
    public void execute_someExpired_someFound() {
        String expectedMessage = "";
        Predicate<Product> expiredPredicate = Product::hasExpiredItems;
        expectedModel.updateProductFilters(expiredPredicate);
        expectedModel.updateFilteredItemListForProducts(Item::isExpired);
        assertCommandSuccess(expiredCommand, model, ExpiredCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(model.getFilteredProductList(), Arrays.asList(KAYA_BREAD, PEANUT_BUTTER_BREAD));
        for (Product p: model.getFilteredProductList()) {
            if (p.equals(KAYA_BREAD)) {
                assertEquals(p.getFilteredItems().getInternalList(),
                        Arrays.asList(Q5_2020_01_01_KAYA.toItem(p), Q5_2022_03_01_KAYA.toItem(p)));
            }

            if (p.equals(PEANUT_BUTTER_BREAD)) {
                assertEquals(p.getFilteredItems().getInternalList(),
                        Arrays.asList(Q5_2020_01_01_KAYA.toItem(p), Q5_2022_03_01_KAYA.toItem(p)));
            }

            assertNotEquals(p, CHOCOLATE_BREAD);
        }

    }


}
