package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InformationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Information(null));
    }

    @Test
    public void constructor_invalidInformation_throwsIllegalArgumentException() {
        String invalidInformation = "";
        assertThrows(IllegalArgumentException.class, () -> new Information(invalidInformation));
    }

    @Test
    public void isValidInformation() {
        assertThrows(NullPointerException.class, () -> Information.isValidInformation(null));

        assertFalse(Information.isValidInformation(""));
        assertFalse(Information.isValidInformation("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "ssssssssssssssssssssssssssssssssssssssssssss"
                + "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "ssssssssssssssssssssssssssssssssssssssssssss"
                + "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "ssssssssssssssssssssssssssssssssssssssssssss"));

        assertTrue(Information.isValidInformation("at HDL"));
        assertTrue(Information.isValidInformation("learning the basics of climbing"));
        assertTrue(Information.isValidInformation("climbing-drills"));
    }
}
