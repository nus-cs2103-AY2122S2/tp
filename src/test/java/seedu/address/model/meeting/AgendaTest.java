package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AgendaTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Agenda(null));
    }

    @Test
    public void constructor_invalidAgenda_throwsIllegalArgumentException() {
        String invalidAgenda = "";
        assertThrows(IllegalArgumentException.class, () -> new Agenda(invalidAgenda));
    }

    @Test
    public void isValidAgenda() {
        // null agenda
        assertThrows(NullPointerException.class, () -> Agenda.isValidAgenda(null));

        // invalid agenda
        assertFalse(Agenda.isValidAgenda("")); // empty string
        assertFalse(Agenda.isValidAgenda("  ")); // space

        // valid agenda
        assertTrue(Agenda.isValidAgenda("important meeting")); // alphabets only
        assertTrue(Agenda.isValidAgenda("very important meeting!!!")); // alphabets with symbols
        assertTrue(Agenda.isValidAgenda("meeting with 3 clients")); // alphanumeric characters
        assertTrue(Agenda.isValidAgenda("ImporTaNt meeting")); // with capital letters
    }
}
