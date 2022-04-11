package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private String validDate = "1/1/2024";
    @Test
    public void hashcode_duplicateDeadline_equal() {
        assertEquals(new Deadline().hashCode(), new Deadline().hashCode());
    }

    @Test
    public void isValidDeadline_emptyDescription_false() {
        assertFalse(Deadline.isValidDeadline("", validDate));
    }

    @Test
    public void isValidDeadline_invalidDescription_false() {
        assertFalse(Deadline.isValidDeadline(" ", validDate));
    }
}
