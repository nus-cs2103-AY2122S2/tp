package seedu.trackermon.model.show;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackermon.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code Rating}.
 */
public class RatingTest {

    /**
     * Tests constructor of {@code Rating} in the event of null input.
     */
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    /**
     * Tests constructor of {@code Rating} in the event of invalid input.
     */
    @Test
    public void constructor_invalidRating_throwsIllegalArgumentException() {
        String invalidRating = "+1";
        assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating));
    }

    /**
     * Tests isValidRating method of {@code Rating}.
     */
    @Test
    public void testIsValidRating() {
        //invalid rating
        assertFalse(Rating.isValidScore("-1"));
        assertFalse(Rating.isValidScore("6"));

        assertFalse(Rating.isValidScore(-1));
        assertFalse(Rating.isValidScore(6));

        //valid rating
        assertTrue(Rating.isValidScore("5"));
        assertTrue(Rating.isValidScore("0"));

        assertTrue(Rating.isValidScore(5));
        assertTrue(Rating.isValidScore(0));

    }

    /**
     * Tests toString method of {@code Rating}.
     */
    @Test
    void testToString() {
        Rating testRating = new Rating("1");
        assertTrue(testRating.toString().equals("1"));
    }

    /**
     * Tests equals method of {@code Rating}.
     */
    @Test
    void testEquals() {
        Rating testRating = new Rating("1");
        assertTrue(testRating.equals(new Rating("1")));
    }

    /**
     * Tests hashcode method of {@code Rating}.
     */
    @Test
    void testHashCode() {
        Rating testRating = new Rating("1");
        assertEquals(Integer.valueOf(1).hashCode(), testRating.hashCode());
    }

    /**
     * Tests compareTo method of {@code Rating}.
     */
    @Test
    void testCompareTo() {
        Rating testRating = new Rating("1");
        assertEquals(0, testRating.compareTo(new Rating("1")));
    }
}
