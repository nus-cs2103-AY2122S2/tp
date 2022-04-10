package seedu.ibook.commons.core.index;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompoundIndexTest {

    @Test
    public void createOneBasedIndex() {

        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> CompoundIndex.fromOneBased(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> CompoundIndex.fromOneBased(1, 0));

        assertDoesNotThrow(() -> CompoundIndex.fromOneBased(1, 1));

        CompoundIndex compoundIndex = CompoundIndex.fromOneBased(1, 5);

        // check equality using the same base
        assertEquals(1, compoundIndex.getOneBasedFirst());
        assertEquals(5, compoundIndex.getOneBasedSecond());

        // convert from one-based index to zero-based index
        assertEquals(0, compoundIndex.getZeroBasedFirst());
        assertEquals(4, compoundIndex.getZeroBasedSecond());
    }

    @Test
    public void createZeroBasedIndex() {

        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> CompoundIndex.fromZeroBased(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> CompoundIndex.fromZeroBased(0, -1));

        assertDoesNotThrow(() -> CompoundIndex.fromZeroBased(0, 0));

        CompoundIndex compoundIndex = CompoundIndex.fromZeroBased(0, 5);

        // check equality using the same base
        assertEquals(0, compoundIndex.getZeroBasedFirst());
        assertEquals(5, compoundIndex.getZeroBasedSecond());

        // convert from zero-based index to one-based index
        assertEquals(1, compoundIndex.getOneBasedFirst());
        assertEquals(6, compoundIndex.getOneBasedSecond());
    }

    @Test
    public void equals() {
        final CompoundIndex oneTwoIndex = CompoundIndex.fromOneBased(1, 2);

        // same values -> returns true
        assertTrue(oneTwoIndex.equals(CompoundIndex.fromOneBased(1, 2)));
        assertTrue(oneTwoIndex.equals(CompoundIndex.fromZeroBased(0, 1)));

        // same object -> returns true
        assertTrue(oneTwoIndex.equals(oneTwoIndex));

        // null -> returns false
        assertFalse(oneTwoIndex.equals(null));

        // different types -> returns false
        assertFalse(oneTwoIndex.equals(5.0f));

        // different index -> returns false
        assertFalse(oneTwoIndex.equals(CompoundIndex.fromOneBased(1, 3)));
        assertFalse(oneTwoIndex.equals(CompoundIndex.fromOneBased(2, 2)));
    }
}
