package seedu.ibook.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.testutil.TypicalItems.Q10_2022_03_01;
import static seedu.ibook.testutil.TypicalItems.Q5_2022_03_01;
import static seedu.ibook.testutil.TypicalItems.Q5_2022_03_02;
import static seedu.ibook.testutil.TypicalItems.QUANTITY_10;

import org.junit.jupiter.api.Test;

import seedu.ibook.testutil.ItemBuilder;

class ItemTest {

    @Test
    void add() {
        // 5 + 5 = 10
        Item combinedItem = Q5_2022_03_01.add(Q5_2022_03_01);
        assertEquals(Q10_2022_03_01, combinedItem);

        // adding Items with different expiry dates -> Exception
        assertThrows(IllegalArgumentException.class, () -> Q5_2022_03_01.add(Q5_2022_03_02));
    }

    @Test
    void subtract() {
        // 10 - 5 = 5
        Item newItem = Q10_2022_03_01.subtract(Q5_2022_03_01);
        assertEquals(Q5_2022_03_01, newItem);

        // subtracting Items with different expiry dates -> Exception
        assertThrows(IllegalArgumentException.class, () -> Q5_2022_03_01.subtract(Q5_2022_03_02));
    }

    @Test
    void isExpired() {
        Item expiredItem = new ItemBuilder().withExpiryDate("2000-01-01").build();
        assertTrue(expiredItem.isExpired());
    }

    @Test
    void isSameItem() {
        // same object -> returns true
        assertTrue(Q5_2022_03_01.isSame(Q5_2022_03_01));

        // null -> returns false
        assertFalse(Q5_2022_03_01.isSame(null));

        // same expiry date, different quantity -> returns true
        Item editedItem = new ItemBuilder(Q5_2022_03_01).withQuantity(QUANTITY_10).build();
        assertTrue(Q5_2022_03_01.isSame(editedItem));

        // different expiry date, same quantity -> returns false
        editedItem = new ItemBuilder(Q5_2022_03_01).withExpiryDate("2020-01-01").build();
        assertFalse(Q5_2022_03_01.isSame(editedItem));
    }

    @Test
    void testEquals() {
        // same values -> returns true
        Item itemCopy = new ItemBuilder(Q5_2022_03_01).build();
        assertEquals(Q5_2022_03_01, itemCopy);

        // different expiry date -> return false
        assertNotEquals(Q5_2022_03_01, Q5_2022_03_02);

        // different quantity -> return false
        assertNotEquals(Q5_2022_03_01, Q10_2022_03_01);

        // null -> returns false
        assertNotEquals(null, Q5_2022_03_01);

        // different type -> returns false
        assertNotEquals(5, Q5_2022_03_01);
    }

    @Test
    void compareTo() {
        Item itemWithEarlierExpiryDate = new ItemBuilder(Q5_2022_03_01).withExpiryDate("2000-01-01").build();
        assertTrue(itemWithEarlierExpiryDate.compareTo(Q5_2022_03_01) < 0);
        assertTrue(Q5_2022_03_01.compareTo(itemWithEarlierExpiryDate) > 0);
        assertEquals(0, Q5_2022_03_01.compareTo(Q10_2022_03_01));
    }
}
