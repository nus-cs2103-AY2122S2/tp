package seedu.contax.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SearchTypeTest {

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new SearchType(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> SearchType.isValidType(null));

        // invalid name
        assertFalse(SearchType.isValidType("")); // empty string
        assertFalse(SearchType.isValidType(" ")); // spaces only
        assertFalse(SearchType.isValidType("^")); // only non-alphanumeric characters
        assertFalse(SearchType.isValidType("peter*")); // contains non-alphanumeric characters

        // valid name
        assertEquals(new SearchType("name").searchType, "name");
        assertTrue(SearchType.isValidType("name")); // name
        assertEquals(new SearchType("phone").searchType, "phone");
        assertTrue(SearchType.isValidType("phone")); // phone
        assertEquals(new SearchType("email").searchType, "email");
        assertTrue(SearchType.isValidType("email")); // email
        assertEquals(new SearchType("address").searchType, "address");
        assertTrue(SearchType.isValidType("address")); // address
    }

    @Test
    public void stringConversion() {
        assertEquals("name", new SearchType("name").toString());
        assertEquals("phone", new SearchType("phone").toString());
        assertEquals("email", new SearchType("email").toString());
        assertEquals("address", new SearchType("address").toString());
    }

    @Test
    public void objectEquality() {
        SearchType reference1 = new SearchType("name");
        SearchType reference2 = new SearchType("phone");

        assertTrue(reference1.equals(reference1));
        assertTrue(reference1.equals(new SearchType("name")));

        assertFalse(reference1.equals(null));
        assertFalse(reference1.equals(-1));
        assertFalse(reference1.equals(""));
        assertFalse(reference1.equals(reference2));
    }

    @Test
    public void hashCodeEquality() {
        SearchType reference = new SearchType("name");

        assertEquals(reference.hashCode(), new SearchType("name").hashCode());
        assertNotEquals(reference.hashCode(), new SearchType("phone").hashCode());
    }
}
