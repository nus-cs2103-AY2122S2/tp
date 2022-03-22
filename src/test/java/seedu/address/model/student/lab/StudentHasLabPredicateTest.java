package seedu.address.model.student.lab;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import seedu.address.testutil.StudentBuilder;

public class StudentHasLabPredicateTest {

    @Test
    public void equals() {
        Lab firstLab = (new Lab("1")).thatIs(LabStatus.SUBMITTED);
        Lab secondLab = (new Lab("2")).thatIs(LabStatus.GRADED);

        StudentHasLabPredicate firstPredicate = new StudentHasLabPredicate(firstLab);
        StudentHasLabPredicate secondPredicate = new StudentHasLabPredicate(secondLab);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentHasLabPredicate firstPredicateCopy = new StudentHasLabPredicate(firstLab);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_studentHasSpecifiedLab_returnsTrue() {
        StudentHasLabPredicate predicate =
                new StudentHasLabPredicate((new Lab("1").thatIs(LabStatus.UNSUBMITTED)));
        assertTrue(predicate.test(new StudentBuilder()
                .withLabs(new Pair<String, String>("1", "UNSUBMITTED"),
                        new Pair<String, String>("2", "SUBMITTED"),
                        new Pair<String, String>("3", "SUBMITTED")).build()));
    }

    @Test
    public void test_studentDoesNotHaveSpecifiedLab_returnsFalse() {
        // Wrong LabStatus
        StudentHasLabPredicate predicate =
                new StudentHasLabPredicate((new Lab("3").thatIs(LabStatus.SUBMITTED)));
        assertFalse(predicate.test(new StudentBuilder()
                .withLabs(new Pair<String, String>("1", "UNSUBMITTED"),
                        new Pair<String, String>("2", "SUBMITTED"),
                        new Pair<String, String>("3", "GRADED")).build()));

        // Wrong LabNumber
        predicate = new StudentHasLabPredicate((new Lab("4").thatIs(LabStatus.UNSUBMITTED)));
        assertFalse(predicate.test(new StudentBuilder()
                .withLabs(new Pair<String, String>("1", "UNSUBMITTED"),
                        new Pair<String, String>("2", "SUBMITTED"),
                        new Pair<String, String>("3", "GRADED")).build()));
    }

}
