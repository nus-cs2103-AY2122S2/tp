package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code ViewDetails}.
 */
public class ViewDetailsTest {

    @Test
    public void equals() {
        ViewDetails firstViewDetails = new ViewDetails(ALICE);
        ViewDetails secondViewDetails = new ViewDetails(BENSON);

        // same object -> returns true
        assertTrue(firstViewDetails.equals(firstViewDetails));

        // same values -> returns true
        ViewDetails firstViewDetailsCopy = new ViewDetails(ALICE);
        assertTrue(firstViewDetails.equals(firstViewDetailsCopy));

        // different types -> returns false
        assertFalse(firstViewDetails.equals(123));

        // null -> returns false
        assertFalse(firstViewDetails.equals(null));

        // different student -> returns false
        assertFalse(firstViewDetails.equals(secondViewDetails));
    }
}
