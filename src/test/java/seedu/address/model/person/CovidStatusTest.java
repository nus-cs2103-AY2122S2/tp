package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class CovidStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CovidStatus(null));
    }

    @Test
    public void constructor_invalidCovidStatus_throwsIllegalArgumentException() {
        String invalidCovidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new CovidStatus(invalidCovidStatus));
    }

    @Test
    public void isValidCovidStatus() {
        //null faculty
        assertFalse(CovidStatus.isValidCovidStatus(null));

        //invalid faculty
        assertFalse(CovidStatus.isValidCovidStatus("")); // empty string
        assertFalse(CovidStatus.isValidCovidStatus(" ")); // spaces only
        assertFalse(CovidStatus.isValidCovidStatus("HRW")); // incorrect status

        //Valid faculty
        assertTrue(CovidStatus.isValidCovidStatus("hrn")); // all lowercase, correct status
        assertTrue(CovidStatus.isValidCovidStatus("POSITIVE")); // correct status
    }

    @Test
    public void getCovidStatusEnumAsString() {
        String listOfCovidStatus = "HRN NEGATIVE POSITIVE ";
        StringBuilder stringBuilder = new StringBuilder();
        Stream.of(CovidStatus.CovidStatusTier.values()).forEach(status -> stringBuilder.append(status + " "));
        assertEquals(listOfCovidStatus, stringBuilder.toString());
    }
}
