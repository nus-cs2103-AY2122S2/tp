package seedu.address.model.person.lab;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

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

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_studentHasSpecifiedLab_returnsTrue() {
        StudentHasLabPredicate predicate =
                new StudentHasLabPredicate((new Lab("1").thatIs(LabStatus.UNSUBMITTED)));
        assertTrue(predicate.test(new PersonBuilder()
                .withLabs(new Pair<String, String>("1", "UNSUBMITTED"),
                        new Pair<String, String>("2", "SUBMITTED"),
                        new Pair<String, String>("3", "SUBMITTED")).build()));
    }

    @Test
    public void test_studentDoesNotHaveSpecifiedLab_returnsFalse() {
        // Wrong LabStatus
        StudentHasLabPredicate predicate =
                new StudentHasLabPredicate((new Lab("3").thatIs(LabStatus.SUBMITTED)));
        assertFalse(predicate.test(new PersonBuilder()
                .withLabs(new Pair<String, String>("1", "UNSUBMITTED"),
                        new Pair<String, String>("2", "SUBMITTED"),
                        new Pair<String, String>("3", "GRADED")).build()));

        // Wrong LabNumber
        predicate = new StudentHasLabPredicate((new Lab("4").thatIs(LabStatus.UNSUBMITTED)));
        assertFalse(predicate.test(new PersonBuilder()
                .withLabs(new Pair<String, String>("1", "UNSUBMITTED"),
                        new Pair<String, String>("2", "SUBMITTED"),
                        new Pair<String, String>("3", "GRADED")).build()));
    }

}
