package seedu.trackermon.model.show;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackermon.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RatingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    public void constructor_invalidRating_throwsIllegalArgumentException() {
        String invalidRating = "+1";
        assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating));
    }

    @Test
    public void isValidRating() {
        //invalid rating
        assertFalse(Rating.isValidRating("-1"));
        assertFalse(Rating.isValidRating("6"));

        assertFalse(Rating.isValidRating(-1));
        assertFalse(Rating.isValidRating(6));

        //valid rating
        assertTrue(Rating.isValidRating("5"));
        assertTrue(Rating.isValidRating("0"));

        assertTrue(Rating.isValidRating(5));
        assertTrue(Rating.isValidRating(0));

    }

    @Test
    void testToString() {
        Rating testRating = new Rating("1");
        assertTrue(testRating.toString().equals("1"));
    }

    @Test
    void testEquals() {
        Rating testRating = new Rating("1");
        assertTrue(testRating.equals(new Rating("1")));
    }

    @Test
    void testHashCode() {
        Rating testRating = new Rating("1");
        assertEquals(Integer.valueOf(1).hashCode(), testRating.hashCode());
    }

    @Test
    void compareTo() {
        Rating testRating = new Rating("1");
        assertEquals(0, testRating.compareTo(new Rating("1")));
    }
}
