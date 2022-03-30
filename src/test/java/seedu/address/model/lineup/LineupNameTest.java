package seedu.address.model.lineup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Name;

public class LineupNameTest {
    @Test
    public void isValidLineupName() {
        // null name
        assertThrows(NullPointerException.class, () -> seedu.address.model.lineup.LineupName.isValidLineupName(null));

        // invalid name
        assertFalse(Name.isValidLineupName("")); // empty string
        assertFalse(Name.isValidLineupName(" ")); // spaces only
        assertFalse(Name.isValidLineupName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidLineupName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidLineupName("start")); // alphabets only
        assertTrue(Name.isValidLineupName("12345")); // numbers only
        assertTrue(Name.isValidLineupName("the 5")); // alphanumeric characters
        assertTrue(Name.isValidLineupName("GG")); // with capital letters
        assertTrue(Name.isValidLineupName("the greatest lineup ever in history")); // long names
    }
}
