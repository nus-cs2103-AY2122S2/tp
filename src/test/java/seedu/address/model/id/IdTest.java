package seedu.address.model.id;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Id;

public class IdTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Id(null));
    }

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String invalidIdName = "12345";
        assertThrows(IllegalArgumentException.class, () -> new Id(invalidIdName));
    }

    @Test
    public void isValidId() {
        // null Id
        assertThrows(NullPointerException.class, () -> Id.isValidId(null));

        // pure numbers
        assertFalse(Id.isValidId("12345"));
        //alphabets
        assertFalse(Id.isValidId("abcdefg"));

        //valid Id
        assertTrue(Id.isValidId("123e4567-e89b-12d3-a456-556642440000"));
    }
}
