package seedu.address.model.interview.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCandidates.ALICE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.interview.Interview;

public class ThisMonthWithinTimePeriodPredicateTest {

    @Test
    public void equals() {
        String firstPredicateString = "26/03/2022 14:40";
        String secondPredicateString = "27/03/2022 14:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime firstPredicateDateTime = LocalDateTime.parse(firstPredicateString, formatter);
        LocalDateTime secondPredicateDateTime = LocalDateTime.parse(secondPredicateString, formatter);

        ThisMonthWithinTimePeriodPredicate firstPredicate =
                new ThisMonthWithinTimePeriodPredicate(firstPredicateDateTime);
        ThisMonthWithinTimePeriodPredicate secondPredicate =
                new ThisMonthWithinTimePeriodPredicate(secondPredicateDateTime);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ThisMonthWithinTimePeriodPredicate firstPredicateCopy =
                new ThisMonthWithinTimePeriodPredicate(firstPredicateDateTime);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different candidate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_interviewMonthWithinTimePeriod_returnsTrue() {
        String firstPredicateString = "31/01/2022 14:40";
        String secondPredicateString = "28/02/2022 14:39";
        String thirdPredicateString = "31/12/2022 14:39";
        String fourthPredicateString = "01/01/2023 14:38";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime firstPredicateDateTime = LocalDateTime.parse(firstPredicateString, formatter);
        LocalDateTime secondPredicateDateTime = LocalDateTime.parse(secondPredicateString, formatter);
        LocalDateTime thirdPredicateDateTime = LocalDateTime.parse(thirdPredicateString, formatter);
        LocalDateTime fourthPredicateDateTime = LocalDateTime.parse(fourthPredicateString, formatter);

        ThisMonthWithinTimePeriodPredicate predicateOne =
                new ThisMonthWithinTimePeriodPredicate(firstPredicateDateTime);
        assertTrue(predicateOne.test(new Interview(ALICE, secondPredicateDateTime)));

        ThisMonthWithinTimePeriodPredicate predicateTwo =
                new ThisMonthWithinTimePeriodPredicate(thirdPredicateDateTime);
        assertTrue(predicateTwo.test(new Interview(ALICE, fourthPredicateDateTime)));
    }


    @Test
    public void test_interviewMonthWithinTimePeriod_returnsFalse() {
        String firstPredicateString = "31/01/2022 14:40";
        String secondPredicateString = "01/03/2022 14:40";
        String thirdPredicateString = "31/12/2022 14:39";
        String fourthPredicateString = "30/11/2022 14:41";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime firstPredicateDateTime = LocalDateTime.parse(firstPredicateString, formatter);
        LocalDateTime secondPredicateDateTime = LocalDateTime.parse(secondPredicateString, formatter);
        LocalDateTime thirdPredicateDateTime = LocalDateTime.parse(thirdPredicateString, formatter);
        LocalDateTime fourthPredicateDateTime = LocalDateTime.parse(fourthPredicateString, formatter);

        ThisMonthWithinTimePeriodPredicate predicateOne =
                new ThisMonthWithinTimePeriodPredicate(firstPredicateDateTime);
        assertFalse(predicateOne.test(new Interview(ALICE, secondPredicateDateTime)));

        ThisMonthWithinTimePeriodPredicate predicateTwo =
                new ThisMonthWithinTimePeriodPredicate(thirdPredicateDateTime);
        assertFalse(predicateTwo.test(new Interview(ALICE, fourthPredicateDateTime)));
    }

}
