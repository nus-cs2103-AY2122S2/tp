package seedu.contax.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.contax.model.onboarding.OnboardingStep;

public class OnboardingStepTest {

    @Test
    public void equals() {
        OnboardingStep step1 = new OnboardingStep("message 1", 0, 0, 0,
                0, 0, 0, "", null, 1);
        OnboardingStep step2 = new OnboardingStep("message 1", 0, 0, 0,
                0, 0, 0, "", null, 1);
        OnboardingStep step3 = new OnboardingStep("message 1", 2, 0, 0,
                0, 1, 0, "", null, 1);

        // same object -> returns true
        assertTrue(step1.equals(step1));

        // all values same -> returns true
        assertTrue(step1.equals(step2));

        // same message, some of other attributes different
        assertFalse(step1.equals(step3));

        // different type -> returns false
        assertFalse(step1.equals(5));

        // null -> returns false
        assertFalse(step1.equals(null));
    }

    @Test
    public void isValidStep() {
        OnboardingStep step1 = new OnboardingStep("message", 0, 0, 0,
                0, 0, 0, "", null, 1);
        OnboardingStep step2 = new OnboardingStep("message", -1, 0, 0,
                0, 0, 0, "", null, 1);
        OnboardingStep step3 = new OnboardingStep("message", 0, 0, 0,
                0, 4, 0, "", null, 1);
        OnboardingStep step4 = new OnboardingStep("message", 0, 0, 0,
                5, 0, 0, "", null, 1);

        // Position, highlight and overlay options are valid -> returns true
        assertTrue(step1.isValid());

        // Position and highlight options are valid, overlay option is not -> return false
        assertFalse(step2.isValid());

        // Position and overlay options are valid, highlight option is not -> returns false
        assertFalse(step3.isValid());

        // Overlay and highlight options are valid, position option is not -> returns false
        assertFalse(step4.isValid());
    }

    @Test
    public void setEventType() {
        OnboardingStep step1 = new OnboardingStep("message", 0, 0, 0,
                0, 0, 0, "", null, 1);
        step1.setEventType(1);

        // Value is set after setting -> returns true
        assertEquals(step1.getPositionOption(), 1);
    }

    @Test void hashCodeTest() {
        OnboardingStep step1 = new OnboardingStep("message 1", 0, 0, 0,
                0, 0, 0, "", null, 1);
        OnboardingStep step2 = new OnboardingStep("message 1", 0, 0, 0,
                0, 0, 0, "", null, 1);
        OnboardingStep step3 = new OnboardingStep("message 1", 2, 0, 0,
                0, 1, 0, "", null, 1);

        // same object -> returns true
        assertEquals(step1.hashCode(), step1.hashCode());

        // different object, same parameters -> returns true
        assertEquals(step1.hashCode(), step2.hashCode());

        // different object, different parameters -> returns false
        assertNotEquals(step3.hashCode(), step2.hashCode());

    }
}
