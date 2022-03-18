package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.model.candidate.Candidate;

public class SortCommandTest {
    private final Comparator<Candidate> sortComparator = (p1, p2) -> 0;

    @Test
    public void constructor_nullComparator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(null, "studentid"));
    }

    @Test
    public void constructor_emptySortKey_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(sortComparator, null));
    }

    @Test
    public void equals() {
        SortCommand sc1 = new SortCommand(sortComparator, "studentid");
        SortCommand sc2 = new SortCommand(sortComparator, "studentid");

        // same object -> returns true
        // have also tested overridden equals(SortCommand) method
        assertTrue(sc1.equals(sc2));

        // null -> returns false
        assertFalse(sc1.equals(null));

        // different sort key -> returns false
        SortCommand sc1Copy = new SortCommand(sortComparator, "name");
        assertFalse(sc1.equals(sc1Copy));
    }
}
