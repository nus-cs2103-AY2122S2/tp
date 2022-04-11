package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.JobBuilder;

class JobIdSamePredicateTest {

    @Test
    public void equals() {

        String one = "1";
        String two = "2";

        JobIdSamePredicate firstPredicate = new JobIdSamePredicate(one);
        JobIdSamePredicate secondPredicate = new JobIdSamePredicate(two);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        JobIdSamePredicate firstPredicateCopy = new JobIdSamePredicate(one);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_jobContainsSameId_returnsTrue() {
        // Same Id
        JobIdSamePredicate predicate = new JobIdSamePredicate("1");
        assertTrue(predicate.test(new JobBuilder().withJobId("1").build()));
    }

    @Test
    public void test_jobIdNotSame_returnsFalse() {
        // Different Id
        JobIdSamePredicate predicate = new JobIdSamePredicate("1");
        assertFalse(predicate.test(new JobBuilder().withJobId("2").build()));
    }
}
