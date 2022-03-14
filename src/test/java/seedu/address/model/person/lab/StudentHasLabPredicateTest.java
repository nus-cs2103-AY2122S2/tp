package seedu.address.model.person.lab;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        StudentHasLabPredicate predicate = new StudentHasLabPredicate((new Lab("1").thatIs(LabStatus.UNSUBMITTED)));
        assertTrue(predicate.test(new PersonBuilder().withLabs("1", "2", "3").build()));
    }

    @Test
    public void test_studentDoesNotHaveSpecifiedLab_returnsFalse() {
        // Wrong LabStatus
        StudentHasLabPredicate predicate = new StudentHasLabPredicate((new Lab("1").thatIs(LabStatus.SUBMITTED)));
        assertFalse(predicate.test(new PersonBuilder().withLabs("1", "2", "3").build()));

        // Wrong LabNumber
        predicate = new StudentHasLabPredicate((new Lab("4").thatIs(LabStatus.UNSUBMITTED)));
        assertFalse(predicate.test(new PersonBuilder().withLabs("1", "2", "3").build()));
    }

}
