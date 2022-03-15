package seedu.address.model.house;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class HouseTest {

    private House h1 = new House(HouseType.BUNGALOW, "Address1");
    private House h2 = new House(HouseType.BUNGALOW, "Address2");
    private House h3 = new House(HouseType.APARTMENT, "Address1");
    private House h4 = new House(HouseType.BUNGALOW, "Address1");
    @Test
    public void testHouseEquals() {
        assertFalse(h1.equals(h2));
        assertFalse(h1.equals(h3));
        assertTrue(h1.equals(h4));
    }
}
