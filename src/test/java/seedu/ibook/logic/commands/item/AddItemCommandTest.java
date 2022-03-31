package seedu.ibook.logic.commands.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ibook.testutil.Assert.assertThrows;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.ibook.testutil.TypicalItems.ITEM_A;
import static seedu.ibook.testutil.TypicalItems.ITEM_B;

import org.junit.jupiter.api.Test;

class AddItemCommandTest {

    @Test
    void constructor_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddItemCommand(INDEX_FIRST_PRODUCT, null));
    }

    @Test
    void equals() {
        AddItemCommand commandA = new AddItemCommand(INDEX_FIRST_PRODUCT, ITEM_A);
        AddItemCommand commandB = new AddItemCommand(INDEX_FIRST_PRODUCT, ITEM_B);

        // same object -> returns true
        assertEquals(commandA, commandA);

        // same values -> return true
        AddItemCommand commandACopy = new AddItemCommand(INDEX_FIRST_PRODUCT, ITEM_A);
        assertEquals(commandA, commandACopy);
    }
}
