package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CollectionTypeTest {

    @Test
    public void isValidDetail() {

        // invalid details
        assertFalse(CollectionType.isValidCollectionTypeValue("")); // empty string
        assertFalse(CollectionType.isValidCollectionTypeValue(" ")); // only whitespace
        assertFalse(CollectionType.isValidCollectionTypeValue("Deliveryy")); // whitespace followed by alphanumeric
        assertFalse(CollectionType.isValidCollectionTypeValue("Pickupy")); // whitespace followed by alphanumeric
        assertFalse(CollectionType.isValidCollectionTypeValue("Pick-up")); // whitespace followed by alphanumeric

        // valid details
        assertTrue(CollectionType.isValidCollectionTypeValue("Delivery")); // numbers only
        assertTrue(CollectionType.isValidCollectionTypeValue("Pickup")); // with capital letters
    }
}
