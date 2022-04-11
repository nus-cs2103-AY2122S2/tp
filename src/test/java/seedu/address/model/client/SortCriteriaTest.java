package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SortCriteriaTest {

    @Test
    public void constructor_validString_objectCreated() {
        SortCriteria sortCriteria = SortCriteria.premium;
        assertTrue(sortCriteria.toString().equals("Premium"));
    }

    @Test
    public void isValidEnum_invalidString_returnsFalse() {
        assertFalse(SortCriteria.isValidEnum("none"));
    }

    @Test
    public void isValidEnum_validString_returnsTrue() {
        assertTrue(SortCriteria.isValidEnum("lastContacted"));
    }
}
