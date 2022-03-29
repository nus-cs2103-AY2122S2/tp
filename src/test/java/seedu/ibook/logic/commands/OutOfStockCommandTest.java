package seedu.ibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ibook.testutil.TypicalItems.Q0_2022_03_01;
import static seedu.ibook.testutil.TypicalItems.Q0_2022_03_02;
import static seedu.ibook.testutil.TypicalItems.Q5_2020_01_01;
import static seedu.ibook.testutil.TypicalProducts.CHOCOLATE_BREAD;
import static seedu.ibook.testutil.TypicalProducts.KAYA_BREAD;
import static seedu.ibook.testutil.TypicalProducts.PEANUT_BUTTER_BREAD;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBookWithCustomList;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;
import seedu.ibook.model.product.filters.OutOfStockFilter;
import seedu.ibook.testutil.ProductBuilder;

public class OutOfStockCommandTest {
    private List<Item> allItems = Arrays.asList(Q0_2022_03_01, Q0_2022_03_02, Q5_2020_01_01);
    private List<Item> onlyZeroItems = Arrays.asList(Q0_2022_03_01, Q0_2022_03_02);
    private List<Item> onlyNonZeroItems = Arrays.asList(Q5_2020_01_01);
    private Product kayaBreadWithAllItems = new ProductBuilder(KAYA_BREAD).buildWithItems(allItems);
    private Product peanutButterBreadWithAllExpired =
            new ProductBuilder(PEANUT_BUTTER_BREAD).buildWithItems(onlyZeroItems);
    private Product chocolateBreadWithAllNotExpired =
            new ProductBuilder(CHOCOLATE_BREAD).buildWithItems(onlyNonZeroItems);
    private List<Product> products = Arrays.asList(kayaBreadWithAllItems,
            peanutButterBreadWithAllExpired, chocolateBreadWithAllNotExpired);

    private Model model = new ModelManager(getTypicalIBookWithCustomList(products), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalIBookWithCustomList(products), new UserPrefs());

    private final OutOfStockCommand outOfStockCommand = new OutOfStockCommand();

    @Test
    public void execute_someOutOfStock_someFound() {
        String expectedMessage = "Listed products that are out of stock.\n1 product listed!";
        expectedModel.addProductFilter(new OutOfStockFilter());
        expectedModel.updateFilteredItemListForProducts(Product.PREDICATE_SHOW_ALL_ITEMS);
        assertCommandSuccess(outOfStockCommand, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredProductList(), Arrays.asList(PEANUT_BUTTER_BREAD));
        for (Product p: model.getFilteredProductList()) {
            assertEquals(p, PEANUT_BUTTER_BREAD);
            assertEquals(p.getFilteredItems(), Arrays.asList(Q0_2022_03_01, Q0_2022_03_02));
        }

    }
}
