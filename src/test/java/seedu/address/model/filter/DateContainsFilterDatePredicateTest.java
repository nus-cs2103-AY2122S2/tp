package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.PetBuilder;

public class DateContainsFilterDatePredicateTest {

    private static final String PARSE_EX_THROWN_MESSAGE = "Should not have thrown parse exception.";
    private static final String DATE_STUB = "22-03-2022";
    private static final String LOCAL_DATE_STUB = "2022-03-22";
    private static final String TODAY_LOCAL_DATE_STUB = "today";
    private static final String PICKUP_TIME_STUB = "09:00";
    private static final String DROP_OFF_TIME_STUB = "16:00";

    @Test
    public void test_equals() {
        try {
            DateContainsFilterDatePredicate firstPredicate =
                    new DateContainsFilterDatePredicate(TODAY_LOCAL_DATE_STUB);
            DateContainsFilterDatePredicate secondPredicate =
                    new DateContainsFilterDatePredicate(DATE_STUB);

            // same object -> returns true
            assertTrue(firstPredicate.equals(firstPredicate));

            // same values -> returns true
            DateContainsFilterDatePredicate firstPredicateCopy =
                    new DateContainsFilterDatePredicate(TODAY_LOCAL_DATE_STUB);
            assertTrue(firstPredicate.equals(firstPredicateCopy));

            // different types -> returns false
            assertFalse(firstPredicate.equals(1));

            // null -> returns false
            assertFalse(firstPredicate.equals(null));

            // different pet -> returns false
            assertFalse(firstPredicate.equals(secondPredicate));
        } catch (ParseException e) {
            fail(PARSE_EX_THROWN_MESSAGE);
        }
    }

    @Test
    public void test_dateContainsKeyword_returnsTrue() {
        try {
            DateContainsFilterDatePredicate predicate =
                    new DateContainsFilterDatePredicate(DATE_STUB);
            assertTrue(predicate.test(new PetBuilder()
                    .withPresentAttendanceEntry(LOCAL_DATE_STUB, PICKUP_TIME_STUB, DROP_OFF_TIME_STUB).build()));
        } catch (ParseException e) {
            fail(PARSE_EX_THROWN_MESSAGE);
        }
    }

    @Test
    public void test_dateDoesNotContainKeyword_returnsFalse() {
        try {
            DateContainsFilterDatePredicate predicate =
                    new DateContainsFilterDatePredicate(TODAY_LOCAL_DATE_STUB);
            assertFalse(predicate.test(new PetBuilder()
                    .withPresentAttendanceEntry(LOCAL_DATE_STUB, PICKUP_TIME_STUB, DROP_OFF_TIME_STUB).build()));
        } catch (ParseException e) {
            fail(PARSE_EX_THROWN_MESSAGE);
        }
    }
}
