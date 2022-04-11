package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeliveryDateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeliveryDateTime(null));
    }

    @Test
    public void constructor_invalidDeliveryDateTime_throwsIllegalArgumentException() {
        String invalidDeliveryDateTime = "15/12/2022 1432"; //Invalid date time format
        assertThrows(IllegalArgumentException.class, () -> new DeliveryDateTime(invalidDeliveryDateTime));
    }

    @Test
    public void isValidDeliveryDateTime() {
        // null datetime
        assertThrows(NullPointerException.class, () -> DeliveryDateTime.isValidDeliveryDateTime(null));

        // invalid datetime
        assertFalse(DeliveryDateTime.isValidDeliveryDateTime("")); // empty string
        assertFalse(DeliveryDateTime.isValidDeliveryDateTime(" ")); // spaces only
        assertFalse(DeliveryDateTime.isValidDeliveryDateTime("15/12/13 13:43")); // wrong date format
        assertFalse(DeliveryDateTime.isValidDeliveryDateTime("15-12-2022 1340")); // wrong time format

        // valid datetime
        assertTrue(DeliveryDateTime.isValidDeliveryDateTime("25-06-2022 18:30"));
        assertTrue(DeliveryDateTime.isValidDeliveryDateTime("15-01-2022 13:40")); // Before current datetime

        // valid datetime leapyear
        assertTrue(DeliveryDateTime.isValidDeliveryDateTime("29-02-2024 18:30"));

        //invalid datetime leapyear
        assertTrue(DeliveryDateTime.isValidDeliveryDateTime("29-02-2023 18:30"));
        assertTrue(DeliveryDateTime.isValidDeliveryDateTime("29-02-3000 18:30"));


    }
}
