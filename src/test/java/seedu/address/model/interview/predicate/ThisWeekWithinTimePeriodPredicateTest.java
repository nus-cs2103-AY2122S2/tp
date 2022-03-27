package seedu.address.model.interview.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCandidates.ALICE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.interview.Interview;

public class ThisWeekWithinTimePeriodPredicateTest {

    @Test
    public void equals() {
        String firstPredicateString = "26/03/2022 14:40";
        String secondPredicateString = "27/03/2022 14:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime firstPredicateDateTime = LocalDateTime.parse(firstPredicateString, formatter);
        LocalDateTime secondPredicateDateTime = LocalDateTime.parse(secondPredicateString, formatter);

        ThisWeekWithinTimePeriodPredicate firstPredicate =
                new ThisWeekWithinTimePeriodPredicate(firstPredicateDateTime);
        ThisWeekWithinTimePeriodPredicate secondPredicate =
                new ThisWeekWithinTimePeriodPredicate(secondPredicateDateTime);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ThisWeekWithinTimePeriodPredicate firstPredicateCopy =
                new ThisWeekWithinTimePeriodPredicate(firstPredicateDateTime);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different candidate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_interviewWeekWithinTimePeriod_returnsTrue() {
        String firstPredicateString = "26/03/2022 14:40";
        String secondPredicateString = "02/04/2022 14:39";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime firstPredicateDateTime = LocalDateTime.parse(firstPredicateString, formatter);
        LocalDateTime secondPredicateDateTime = LocalDateTime.parse(secondPredicateString, formatter);

        ThisWeekWithinTimePeriodPredicate predicate = new ThisWeekWithinTimePeriodPredicate(firstPredicateDateTime);
        assertTrue(predicate.test(new Interview(ALICE, secondPredicateDateTime)));
    }


    @Test
    public void test_interviewWeekWithinTimePeriod_returnsFalse() {
        String firstPredicateString = "26/03/2022 14:40";
        String secondPredicateString = "02/04/2022 14:41";
        String thirdPredicateString = "26/03/2022 13:41";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime firstPredicateDateTime = LocalDateTime.parse(firstPredicateString, formatter);
        LocalDateTime secondPredicateDateTime = LocalDateTime.parse(secondPredicateString, formatter);
        LocalDateTime thirdPredicateDateTime = LocalDateTime.parse(thirdPredicateString, formatter);

        ThisWeekWithinTimePeriodPredicate predicate = new ThisWeekWithinTimePeriodPredicate(firstPredicateDateTime);
        assertFalse(predicate.test(new Interview(ALICE, secondPredicateDateTime)));
        assertFalse(predicate.test(new Interview(ALICE, thirdPredicateDateTime)));
    }

}
