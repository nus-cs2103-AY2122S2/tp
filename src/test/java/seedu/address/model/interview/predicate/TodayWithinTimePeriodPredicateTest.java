package seedu.address.model.interview.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCandidates.ALICE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.interview.Interview;

public class TodayWithinTimePeriodPredicateTest {

    @Test
    public void equals() {
        String firstPredicateString = "26/03/2022 14:40";
        String secondPredicateString = "27/03/2022 14:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime firstPredicateDateTime = LocalDateTime.parse(firstPredicateString, formatter);
        LocalDateTime secondPredicateDateTime = LocalDateTime.parse(secondPredicateString, formatter);

        TodayWithinTimePeriodPredicate firstPredicate =
                new TodayWithinTimePeriodPredicate(firstPredicateDateTime);
        TodayWithinTimePeriodPredicate secondPredicate =
                new TodayWithinTimePeriodPredicate(secondPredicateDateTime);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TodayWithinTimePeriodPredicate firstPredicateCopy =
                new TodayWithinTimePeriodPredicate(firstPredicateDateTime);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different candidate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_interviewTodayWithinTimePeriod_returnsTrue() {
        String firstPredicateString = "26/03/2022 14:40";
        String secondPredicateString = "26/03/2022 23:59";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime firstPredicateDateTime = LocalDateTime.parse(firstPredicateString, formatter);
        LocalDateTime secondPredicateDateTime = LocalDateTime.parse(secondPredicateString, formatter);

        TodayWithinTimePeriodPredicate predicate = new TodayWithinTimePeriodPredicate(firstPredicateDateTime);
        assertTrue(predicate.test(new Interview(ALICE, secondPredicateDateTime)));
    }

    @Test
    public void test_interviewTodayWithinTimePeriod_returnsFalse() {
        String firstPredicateString = "26/03/2022 14:40";
        String secondPredicateString = "25/03/2022 23:59";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime firstPredicateDateTime = LocalDateTime.parse(firstPredicateString, formatter);
        LocalDateTime secondPredicateDateTime = LocalDateTime.parse(secondPredicateString, formatter);

        TodayWithinTimePeriodPredicate predicate = new TodayWithinTimePeriodPredicate(firstPredicateDateTime);
        assertFalse(predicate.test(new Interview(ALICE, secondPredicateDateTime)));
    }
}
