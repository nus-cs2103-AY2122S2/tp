package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LogNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LogName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new LogName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> LogName.isValidLogName(null));

        // invalid name
        assertFalse(LogName.isValidLogName("")); // empty string
        assertFalse(LogName.isValidLogName(" ")); // spaces only
        assertFalse(LogName.isValidLogName("ajdhfgos;hdgoi;asdhgio;asdhgiosdhgioshdigoshdioghsi")); // 51 characters

        // valid name
        assertTrue(LogName.isValidLogName("12345")); // numbers only
        assertTrue(LogName.isValidLogName("ajdhfgos;hdgoi;asdhgio;asdhgiosdhgioshdigosdioghsi")); // 50 characters
        assertTrue(LogName.isValidLogName("peter's bar mitzvah")); // contains non-alphanumeric characters
        assertTrue(LogName.isValidLogName("First time meeting jack")); // alphabets only
        assertTrue(LogName.isValidLogName("3rd time meeting peter")); // alphanumeric characters
        assertTrue(LogName.isValidLogName("The \"Capital\" incident")); // with capital letters
        assertTrue(LogName.isValidLogName("#BestDayEver")); // long names
        assertTrue(LogName.isValidLogName("^")); // only pun
    }
}
