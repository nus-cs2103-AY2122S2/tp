package seedu.ibook.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.testutil.TypicalItems.Q10_2022_03_01;
import static seedu.ibook.testutil.TypicalItems.Q5_2022_03_01;
import static seedu.ibook.testutil.TypicalItems.QUANTITY_10;

import org.junit.jupiter.api.Test;

import seedu.ibook.commons.core.exceptions.ElementNotFoundException;
import seedu.ibook.testutil.ItemBuilder;

class UniqueItemListTest {

    private final UniqueItemList uniqueItemList = new UniqueItemList();

    @Test
    public void contains_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.contains(null));
    }

    @Test
    public void contains_itemNotInList_returnsFalse() {
        assertFalse(uniqueItemList.contains(Q5_2022_03_01));
    }

    @Test
    public void contains_itemInList_returnsTrue() {
        uniqueItemList.add(Q5_2022_03_01);
        assertTrue(uniqueItemList.contains(Q5_2022_03_01));
    }

    @Test
    public void contains_itemWithSameIdentityFieldsInList_returnsTrue() {
        uniqueItemList.add(Q5_2022_03_01);
        Item editedItem = new ItemBuilder(Q5_2022_03_01).withQuantity(QUANTITY_10)
            .build();
        assertTrue(uniqueItemList.contains(editedItem));
    }

    @Test
    public void add_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.add(null));
    }

    @Test
    public void add_duplicateItem_mergeItems() {
        uniqueItemList.add(Q5_2022_03_01);
        uniqueItemList.add(Q5_2022_03_01);
        Item expectedItem = Q10_2022_03_01;
        assertEquals(expectedItem, uniqueItemList.getExisting(expectedItem));
    }

    @Test
    public void remove_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.remove(null));
    }

    @Test
    public void remove_itemDoesNotExist_throwsItemNotFoundException() {
        assertThrows(ElementNotFoundException.class, () -> uniqueItemList.remove(Q5_2022_03_01));
    }

    @Test
    public void remove_existingItemWithSameQuantity_removesItem() {
        uniqueItemList.add(Q5_2022_03_01);
        uniqueItemList.remove(Q5_2022_03_01);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void remove_existingItemWithSmallerQuantity_throwsElementNotFoundException() {
        uniqueItemList.add(Q10_2022_03_01);
        assertThrows(ElementNotFoundException.class, () -> uniqueItemList.remove(Q5_2022_03_01));
    }

    @Test
    public void remove_existingItemWithLargerQuantity_throwsElementNotFoundException() {
        uniqueItemList.add(Q5_2022_03_01);
        assertThrows(ElementNotFoundException.class, () -> uniqueItemList.remove(Q10_2022_03_01));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueItemList.asUnmodifiableObservableList().remove(0));
    }
}
