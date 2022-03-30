package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MatricCardTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MatricCard(null));
    }

    @Test
    public void constructor_invalidMatricCard_throwsIllegalArgumentException() {
        String invalidMatricCard = "A123123@";
        assertThrows(IllegalArgumentException.class, () -> new MatricCard(invalidMatricCard));
    }

    @Test
    public void isValidMatricCard() {
        // null matric card
        assertThrows(NullPointerException.class, () -> MatricCard.isValidMatricCard(null));

        // invalid matric card
        assertFalse(MatricCard.isValidMatricCard("@")); // symbols
        assertFalse(MatricCard.isValidMatricCard("alice test")); // two words


        // valid matric card
        assertTrue(MatricCard.isValidMatricCard("")); // blank spaces (after trimmed)
        assertTrue(MatricCard.isValidMatricCard("A1231234E"));
    }
}
