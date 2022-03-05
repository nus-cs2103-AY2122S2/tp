package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidStudentId_throwsIllegalArgumentException() {
        String invalidId = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidId));
    }

    @Test
    public void isValidStudentId() {
        // null IDs
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId(null));

        // invalid IDs
        assertFalse(StudentId.isValidStudentId("")); // empty string
        assertFalse(StudentId.isValidStudentId(" ")); // spaces only
        assertFalse(StudentId.isValidStudentId("A00000000B")); // more than 7 numbers
        assertFalse(StudentId.isValidStudentId("A012345B")); // less than 7 numbers
        assertFalse(StudentId.isValidStudentId("B0123456B")); // does not start with A
        assertFalse(StudentId.isValidStudentId("00123456B")); // does not start with A
        assertFalse(StudentId.isValidStudentId("A01234561")); // does not end with alphabet

        // valid IDs
        assertTrue(StudentId.isValidStudentId("A0123456B"));
        assertTrue(StudentId.isValidStudentId("A1111111C"));
    }
}
