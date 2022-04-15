package seedu.address.model.task;

import static seedu.address.model.task.TypicalDeadlines.NULL_DATE;
import static seedu.address.model.task.TypicalDeadlines.getInvalidDeadlines;
import static seedu.address.model.task.TypicalDeadlines.getValidDeadlines;
import static seedu.address.testutil.Assert.assertAllFalse;
import static seedu.address.testutil.Assert.assertAllThrows;
import static seedu.address.testutil.Assert.assertAllTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_nullDescription_throwsNullPointerException() {
        // null case
        assertThrows(NullPointerException.class, () -> new Deadline(NULL_DATE));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {

        String[] invalidDeadlines = getInvalidDeadlines();
        assertAllThrows(IllegalArgumentException.class, Deadline::new, invalidDeadlines);
    }

    @Test
    public void isValidDeadline() {

        // null case
        assertAllFalse(Deadline::isValidDeadline, NULL_DATE);

        // invalid deadlines
        assertAllFalse(Deadline::isValidDeadline, getInvalidDeadlines());

        // valid deadlines
        assertAllTrue(Deadline::isValidDeadline, getValidDeadlines());
    }

    @Test
    public void equals() {

        // null case
        assertAllFalse(Deadline::isValidDeadline, NULL_DATE);

        // invalid deadlines
        assertAllFalse(Deadline::isValidDeadline, getInvalidDeadlines());

        // valid deadlines
        assertAllTrue(Deadline::isValidDeadline, getValidDeadlines());
    }
}
