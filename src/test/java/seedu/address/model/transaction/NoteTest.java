package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class NoteTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void constructor_validArguments_returnsNote() {
        String argument = "This is a note";
        assertEquals(argument, new Note(argument).getValue());
    }

}
