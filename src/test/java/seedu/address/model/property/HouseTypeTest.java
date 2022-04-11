package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HouseTypeTest {

    @Test
    public void isValidHouseType_validHouseTypes_success() {
        assertTrue(HouseType.isValidHouseType("HDB"));
        assertTrue(HouseType.isValidHouseType("hdb_flat"));
        assertTrue(HouseType.isValidHouseType("unspecified"));
        assertTrue(HouseType.isValidHouseType("any"));
        assertTrue(HouseType.isValidHouseType("Any"));
        assertTrue(HouseType.isValidHouseType("apartment"));
        assertTrue(HouseType.isValidHouseType("bungalow"));
        assertTrue(HouseType.isValidHouseType("condo"));
        assertTrue(HouseType.isValidHouseType("condominium"));
        assertTrue(HouseType.isValidHouseType("semi-d"));
        assertTrue(HouseType.isValidHouseType("semi-detached"));
        assertTrue(HouseType.isValidHouseType("semid"));
        assertTrue(HouseType.isValidHouseType("semidetached"));
        assertTrue(HouseType.isValidHouseType("mansion"));

    }

    @Test
    public void isValidHouseType_invalidHouseTypes_failure() {
        assertFalse(HouseType.isValidHouseType("nullhouse"));
        assertFalse(HouseType.isValidHouseType("mansion1"));
        assertFalse(HouseType.isValidHouseType("hbd"));
        assertFalse(HouseType.isValidHouseType("condo123"));
        assertFalse(HouseType.isValidHouseType("lande231d"));
        assertFalse(HouseType.isValidHouseType("12351"));
    }

    @Test
    public void getHouseTypes() {
        assertEquals(HouseType.BUNGALOW, HouseType.getHouseType("bungalow"));
        assertEquals(HouseType.HDB_FLAT, HouseType.getHouseType("hdb"));
        assertEquals(HouseType.CONDOMINIUM, HouseType.getHouseType("condo"));
        assertEquals(HouseType.MANSION, HouseType.getHouseType("mansion"));
        assertNotEquals(HouseType.MANSION, HouseType.getHouseType("something"));
        assertEquals(HouseType.UNSPECIFIED, HouseType.getHouseType("nullhouse"));
    }

}
