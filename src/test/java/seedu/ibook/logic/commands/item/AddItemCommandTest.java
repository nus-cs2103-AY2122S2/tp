package seedu.ibook.logic.commands.item;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.ibook.testutil.Assert.assertThrows;
import static seedu.ibook.testutil.TypicalItems.ITEM_A;
import static seedu.ibook.testutil.TypicalItems.ITEM_B;

import org.junit.jupiter.api.Test;

import seedu.ibook.commons.core.index.Index;

class AddItemCommandTest {

    @Test
    void constructor_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddItemCommand(Index.fromOneBased(1), null));
    }

    @Test
    void equals() {
        AddItemCommand commandA = new AddItemCommand(Index.fromOneBased(1), ITEM_A);
        AddItemCommand commandB = new AddItemCommand(Index.fromOneBased(1), ITEM_B);

        // same object -> returns true
        assertEquals(commandA, commandA);

        // same values -> return true
        AddItemCommand commandACopy = new AddItemCommand(Index.fromOneBased(1), ITEM_A);
        assertEquals(commandA, commandACopy);
    }
}
