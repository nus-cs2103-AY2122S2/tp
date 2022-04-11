package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalStudents.ALEX;
import static seedu.address.testutil.TypicalStudents.BERNARD;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void equals() {
        Student alex = new StudentBuilder(ALEX).build();
        Student bernard = new StudentBuilder(BERNARD).build();

        // same object -> returns true
        assertTrue(alex.equals(alex));

        // same value -> returns true
        Student alexCopy = new StudentBuilder(ALEX).build();
        assertTrue(alex.equals(alexCopy));

        // different type -> returns false
        assertFalse(alex.equals(1));

        // different value -> returns false
        assertFalse(alex.equals(bernard));

        // null -> returns false
        assertFalse(alex.equals(null));
    }
}
