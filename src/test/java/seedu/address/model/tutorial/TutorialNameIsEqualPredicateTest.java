package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TutorialNameIsEqualPredicateTest {
    @Test
    public void equals() {
        TutorialNameIsEqualPredicate predA = new TutorialNameIsEqualPredicate(new TutorialName("T01"));
        TutorialNameIsEqualPredicate predB = new TutorialNameIsEqualPredicate(new TutorialName("T02"));

        // same object -> returns true
        assertTrue(predA.equals(predA));

        // same value -> returns true
        TutorialNameIsEqualPredicate predACopy = new TutorialNameIsEqualPredicate(new TutorialName("T01"));
        assertTrue(predA.equals(predACopy));

        // different type -> returns false
        assertFalse(predA.equals(1));

        // different value -> returns false
        assertFalse(predA.equals(predB));

        // null -> returns false
        assertFalse(predA.equals(null));
    }
}
