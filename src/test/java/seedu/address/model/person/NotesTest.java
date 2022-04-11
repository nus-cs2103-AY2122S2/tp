package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class NotesTest {

    @Test
    void isValidNote() {
        assertFalse(Notes.isValidNote(""));
        assertFalse(Notes.isValidNote("   "));

        assertTrue(Notes.isValidNote("likes bread"));
        assertTrue(Notes.isValidNote(" likes bread "));
    }
}
