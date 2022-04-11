package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalNotes.OVERSEAS;
import static seedu.address.testutil.TypicalNotes.SCHOOL;

import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void test_toString() {
        assertTrue(new Note("").toString().equals(""));
        assertFalse(new Note("").toString().equals("Test"));
        assertTrue(new Note("Note").toString().equals("Note"));
    }

    @Test
    public void equals() {
        // same value -> returns true
        Note noteToCopy = new Note(SCHOOL.value);
        assertTrue(SCHOOL.equals(noteToCopy));

        // same object -> returns true;
        assertTrue(SCHOOL.equals(SCHOOL));

        // null -> returns false
        assertFalse(SCHOOL.equals(null));

        // different type -> returns false;
        assertFalse(SCHOOL.equals(5));

        // different note -> returns false;
        assertFalse(SCHOOL.equals(OVERSEAS));
    }

    @Test
    public void test_hashCode() {
        Note first = new Note("");
        Note second = new Note("");
        assertTrue(first.hashCode() == second.hashCode());
    }
}
