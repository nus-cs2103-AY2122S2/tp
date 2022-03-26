package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LocationTest {

    @Test
    public void equalsTest() {
        Location location = new Location("Clementi");
        Location location2 = new Location("Clementi");
        Location location3 = new Location("Kent Ridge");

        assertTrue(location.equals(location2));

        assertFalse(location.equals(location3));
    }

    @Test
    public void isValidLocationTest() {
        assertTrue(Location.isValidLocation("Serangoon"));
        assertTrue(Location.isValidLocation("random gibberish"));
        assertTrue(Location.isValidLocation("       "));

        assertFalse(Location.isValidLocation(""));


    }
}
