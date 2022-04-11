package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PronounTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Pronoun(null));
    }

    @Test
    public void constructor_invalidPronoun_throwsIllegalArgumentException() {
        String invalidPronoun = "";
        assertThrows(IllegalArgumentException.class, () -> new Pronoun(invalidPronoun));
    }

    @Test
    public void isValidPronoun() {
        // null pronoun
        assertThrows(NullPointerException.class, () -> Pronoun.isValidPronoun(null));

        // invalid pronoun
        assertFalse(Pronoun.isValidPronoun("")); // empty string
        assertFalse(Pronoun.isValidPronoun(" ")); // spaces only

        // valid pronoun
        assertTrue(Pronoun.isValidPronoun("him"));
        assertTrue(Pronoun.isValidPronoun("-")); // one character
        assertTrue(Pronoun.isValidPronoun("Him-Her-She-He-They")); // long pronoun
    }
}
