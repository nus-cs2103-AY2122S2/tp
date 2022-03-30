package seedu.address.model.lineup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.LineupName;

public class LineupNameTest {
    @Test
    public void isValidLineupName() {
        // null name
        assertThrows(NullPointerException.class, () -> seedu.address.model.lineup.LineupName.isValidLineupName(null));

        // invalid name
        assertFalse(LineupName.isValidLineupName("")); // empty string
        assertFalse(LineupName.isValidLineupName(" ")); // spaces only
        assertFalse(LineupName.isValidLineupName("^")); // only non-alphanumeric characters
        assertFalse(LineupName.isValidLineupName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(LineupName.isValidLineupName("start")); // alphabets only
        assertTrue(LineupName.isValidLineupName("12345")); // numbers only
        assertTrue(LineupName.isValidLineupName("the 5")); // alphanumeric characters
        assertTrue(LineupName.isValidLineupName("GG")); // with capital letters
        assertTrue(LineupName.isValidLineupName("the greatest lineup ever in history")); // long names
    }
}
