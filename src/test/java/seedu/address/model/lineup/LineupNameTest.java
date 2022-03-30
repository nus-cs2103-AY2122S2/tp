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
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("start")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("the 5")); // alphanumeric characters
        assertTrue(Name.isValidName("GG")); // with capital letters
        assertTrue(Name.isValidName("the greatest lineup ever in history")); // long names
    }
}
