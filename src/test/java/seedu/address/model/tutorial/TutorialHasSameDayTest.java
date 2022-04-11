package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TutorialBuilder;

public class TutorialHasSameDayTest {
    @Test
    public void equals() {
        Day monday = new Day("Monday");
        Day tuesday = new Day("Tuesday");

        TutorialHasSameDay mondayPredicate = new TutorialHasSameDay(monday);
        TutorialHasSameDay tuesdayPredicate = new TutorialHasSameDay(tuesday);

        // same object -> returns true
        assertTrue(mondayPredicate.equals((mondayPredicate)));

        // same values -> returns true
        TutorialHasSameDay mondayPredicateCopy = new TutorialHasSameDay(monday);
        assertTrue(mondayPredicate.equals(mondayPredicateCopy));

        // different types -> returns false
        assertFalse(mondayPredicate.equals(1));

        // null -> returns false
        assertFalse(mondayPredicate.equals(null));

        // different tutorial -> returns false
        assertFalse(mondayPredicate.equals(tuesdayPredicate));
    }

    @Test
    public void test_tutorialHasSameDay_returnsTrue() {
        TutorialHasSameDay predicate = new TutorialHasSameDay(new Day("Monday"));
        assertTrue(predicate.test(new TutorialBuilder().withDay("Monday").build()));
    }

    @Test
    public void test_tutorialHasNoSameDay_returnsFalse() {
        TutorialHasSameDay predicate = new TutorialHasSameDay(new Day("Monday"));
        assertFalse(predicate.test(new TutorialBuilder().withDay("Tuesday").build()));
    }
}
