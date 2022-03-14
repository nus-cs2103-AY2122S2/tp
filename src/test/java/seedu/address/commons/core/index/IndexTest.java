package seedu.address.commons.core.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IndexTest {
    private static final int INCREMENT_VALUE = 2;

    @Test
    public void createOneBasedIndex() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromOneBased(0));

        // check equality using the same base
        assertEquals(1, Index.fromOneBased(1).getOneBased());
        assertEquals(5, Index.fromOneBased(5).getOneBased());

        // convert from one-based index to zero-based index
        assertEquals(0, Index.fromOneBased(1).getZeroBased());
        assertEquals(4, Index.fromOneBased(5).getZeroBased());
    }

    @Test
    public void createZeroBasedIndex() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromZeroBased(-1));

        // check equality using the same base
        assertEquals(0, Index.fromZeroBased(0).getZeroBased());
        assertEquals(5, Index.fromZeroBased(5).getZeroBased());

        // convert from zero-based index to one-based index
        assertEquals(1, Index.fromZeroBased(0).getOneBased());
        assertEquals(6, Index.fromZeroBased(5).getOneBased());
    }

    @Test
    public void testIncrement() {
        final Index zeroBased = Index.fromZeroBased(2);
        final Index oneBased = Index.fromOneBased(2);
        final Index zeroBasedHigh = Index.fromZeroBased(6);
        final Index oneBasedHigh = Index.fromOneBased(6);

        // check equality for zero based low value
        zeroBased.increment(INCREMENT_VALUE);
        assertEquals(4, zeroBased.getZeroBased());
        assertEquals(5, zeroBased.getOneBased());

        // check equality for zero based high value
        zeroBasedHigh.increment(INCREMENT_VALUE);
        assertEquals(8, zeroBasedHigh.getZeroBased());
        assertEquals(9, zeroBasedHigh.getOneBased());

        // check equality for one based low value
        oneBased.increment(INCREMENT_VALUE);
        assertEquals(3, oneBased.getZeroBased());
        assertEquals(4, oneBased.getOneBased());

        // check equality for one based high value
        oneBasedHigh.increment(INCREMENT_VALUE);
        assertEquals(7, oneBasedHigh.getZeroBased());
        assertEquals(8, oneBasedHigh.getOneBased());
    }

    @Test
    public void equals() {
        final Index fifthPersonIndex = Index.fromOneBased(5);

        // same values -> returns true
        assertTrue(fifthPersonIndex.equals(Index.fromOneBased(5)));
        assertTrue(fifthPersonIndex.equals(Index.fromZeroBased(4)));

        // same object -> returns true
        assertTrue(fifthPersonIndex.equals(fifthPersonIndex));

        // null -> returns false
        assertFalse(fifthPersonIndex.equals(null));

        // different types -> returns false
        assertFalse(fifthPersonIndex.equals(5.0f));

        // different index -> returns false
        assertFalse(fifthPersonIndex.equals(Index.fromOneBased(1)));
    }
}
