package seedu.address.model.house;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest {

    House h1 = new House(HouseType.BUNGALOW, "Address1");
    House h2 = new House(HouseType.BUNGALOW, "Address2");
    House h3 = new House(HouseType.APARTMENT, "Address1");
    House h4 = new House(HouseType.BUNGALOW, "Address1");
    @Test
    public void testHouseEquals() {
        assertFalse(h1.equals(h2));
        assertFalse(h1.equals(h3));
        assertTrue(h1.equals(h4));
    }
}