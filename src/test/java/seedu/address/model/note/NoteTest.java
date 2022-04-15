package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void constructor_invalidNote_throwsIllegalArgumentException() {
        // a String of length 51
        String invalidNote = "0123456789"
                + "0123456789"
                + "0123456789"
                + "0123456789"
                + "0123456789"
                + "0";
        assertThrows(IllegalArgumentException.class, () -> new Note(invalidNote));
    }

    @Test
    public void isValidNote() {
        //null tag name
        assertThrows(NullPointerException.class, () -> Note.isValidNote(null));

        //valid note
        assertTrue(Note.isValidNote("note"));

        //over 50 characters
        assertFalse(Note.isValidNote("ValidValidValidValidValidValidValidValidValidValidValid"));
    }

    @Test
    public void equals() {
        Note note = new Note("something");
        Note differentNote = new Note("something else");

        Note noteCopy = new Note("something");
        Note noteCaseInsensitiveCopy = new Note("SomEthiNg");

        //same object reference
        assertTrue(note.equals(note));

        //same contents
        assertTrue(note.equals(noteCopy));

        //random capitals (equals is case sensitive
        assertTrue(note.equals(noteCaseInsensitiveCopy));

        //null
        assertFalse(note.equals(null));

        //different contents
        assertFalse(note.equals(differentNote));
    }
}
