package seedu.ibook.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.testutil.TypicalItems.Q10_2022_03_01_KAYA;
import static seedu.ibook.testutil.TypicalItems.Q5_2022_03_01_KAYA;
import static seedu.ibook.testutil.TypicalItems.Q5_2022_03_02_KAYA;
import static seedu.ibook.testutil.TypicalItems.QUANTITY_10;
import static seedu.ibook.testutil.TypicalProducts.KAYA_BREAD;
import static seedu.ibook.testutil.TypicalProducts.PEANUT_BUTTER_BREAD;
import static seedu.ibook.testutil.TypicalProducts.WAFFLES;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.Product;
import seedu.ibook.testutil.ItemBuilder;
import seedu.ibook.testutil.ProductBuilder;

class ItemTest {

    private static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("d MMM yyyy", new Locale("en"));

    @Test
    void add() {
        // 5 + 5 = 10
        Item combinedItem = Q5_2022_03_01_KAYA.add(Q5_2022_03_01_KAYA);
        assertEquals(Q10_2022_03_01_KAYA, combinedItem);

        // adding Items with different expiry dates -> Exception
        assertThrows(IllegalArgumentException.class, () -> Q5_2022_03_01_KAYA.add(Q5_2022_03_02_KAYA));
    }

    @Test
    void isExpired() {
        Item expiredItem = new ItemBuilder().withExpiryDate("2000-01-01").build(KAYA_BREAD);
        assertTrue(expiredItem.isExpired());
    }

    @Test
    void isSameItem() {
        // same object -> returns true
        assertTrue(Q5_2022_03_01_KAYA.isSame(Q5_2022_03_01_KAYA));

        // null -> returns false
        assertFalse(Q5_2022_03_01_KAYA.isSame(null));

        // same expiry date, different quantity -> returns true
        Item editedItem = new ItemBuilder(Q5_2022_03_01_KAYA).withQuantity(QUANTITY_10).build(KAYA_BREAD);
        assertTrue(Q5_2022_03_01_KAYA.isSame(editedItem));

        // different expiry date, same quantity -> returns false
        editedItem = new ItemBuilder(Q5_2022_03_01_KAYA).withExpiryDate("2020-01-01").build(KAYA_BREAD);
        assertFalse(Q5_2022_03_01_KAYA.isSame(editedItem));

        // same item, different product -> returns false
        editedItem = new ItemBuilder(Q5_2022_03_01_KAYA).build(PEANUT_BUTTER_BREAD);
        assertFalse(Q5_2022_03_01_KAYA.isSame(editedItem));
    }

    @Test
    void testEquals() {
        // same values -> returns true
        Item itemCopy = new ItemBuilder(Q5_2022_03_01_KAYA).build(KAYA_BREAD);
        assertEquals(Q5_2022_03_01_KAYA, itemCopy);

        // different expiry date -> return false
        assertNotEquals(Q5_2022_03_01_KAYA, Q5_2022_03_02_KAYA);

        // different quantity -> return false
        assertNotEquals(Q5_2022_03_01_KAYA, Q10_2022_03_01_KAYA);

        // different product -> return false
        assertNotEquals(Q5_2022_03_01_KAYA, Q5_2022_03_01_KAYA.toItem(PEANUT_BUTTER_BREAD));

        // null -> returns false
        assertNotEquals(null, Q5_2022_03_01_KAYA);

        // different type -> returns false
        assertNotEquals(5, Q5_2022_03_01_KAYA);
    }

    @Test
    void compareTo() {
        Item itemWithEarlierExpiryDate = new ItemBuilder(Q5_2022_03_01_KAYA)
                .withExpiryDate("2000-01-01").build(KAYA_BREAD);
        assertTrue(itemWithEarlierExpiryDate.compareTo(Q5_2022_03_01_KAYA) < 0);
        assertTrue(Q5_2022_03_01_KAYA.compareTo(itemWithEarlierExpiryDate) > 0);
        assertEquals(0, Q5_2022_03_01_KAYA.compareTo(Q10_2022_03_01_KAYA));
    }

    @Test
    void getDiscountedPrice() {
        for (Item i : getExpiringItems()) {
            Product product = i.getProduct();
            Price discountedPrice = new Price(
                    product.getPrice().price * (100 - product.getDiscountRate().discountRate) / 100.0
            );
            assertEquals(i.getDiscountedPrice(), discountedPrice);
        }
        for (Item i : getAlmostExpiringItems()) {
            Product product = i.getProduct();
            assertEquals(i.getDiscountedPrice(), product.getPrice());
        }
    }

    private UniqueItemList getExpiringItems() {
        int discountStart = WAFFLES.getDiscountStart().discountStart;
        String expiryDate1 = LocalDate.now().plusDays(discountStart - 1).format(dateFormatter);
        String expiryDate2 = LocalDate.now().plusDays(discountStart - 2).format(dateFormatter);
        String expiryDate3 = LocalDate.now().plusDays(discountStart - 10000).format(dateFormatter);

        List<ItemDescriptor> items = new ArrayList<>(Arrays.asList(
                new ItemBuilder(Q5_2022_03_01_KAYA).withExpiryDate(expiryDate1).buildItemDescriptor(),
                new ItemBuilder(Q10_2022_03_01_KAYA).withExpiryDate(expiryDate2).buildItemDescriptor(),
                new ItemBuilder(Q10_2022_03_01_KAYA).withExpiryDate(expiryDate3).buildItemDescriptor()
        ));
        Product product = new ProductBuilder(WAFFLES).buildWithItems(items);
        return product.getItems();
    }

    private UniqueItemList getAlmostExpiringItems() {
        int discountStart = WAFFLES.getDiscountStart().discountStart;
        String expiryDate1 = LocalDate.now().plusDays(discountStart).format(dateFormatter);
        String expiryDate2 = LocalDate.now().plusDays(discountStart + 1).format(dateFormatter);
        String expiryDate3 = LocalDate.now().plusDays(discountStart + 10000).format(dateFormatter);

        List<ItemDescriptor> items = new ArrayList<>(Arrays.asList(
                new ItemBuilder(Q5_2022_03_01_KAYA).withExpiryDate(expiryDate1).buildItemDescriptor(),
                new ItemBuilder(Q10_2022_03_01_KAYA).withExpiryDate(expiryDate2).buildItemDescriptor(),
                new ItemBuilder(Q10_2022_03_01_KAYA).withExpiryDate(expiryDate3).buildItemDescriptor()
        ));
        Product product = new ProductBuilder(WAFFLES).buildWithItems(items);
        return product.getItems();
    }
}
